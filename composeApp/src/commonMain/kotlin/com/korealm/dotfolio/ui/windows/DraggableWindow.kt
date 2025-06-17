package com.korealm.dotfolio.ui.windows

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import kotlin.random.Random

// This composable recreates Windows 11 windows and requests a Composable function to display inside.
// As Windows 11 photo viewer, audio player, notepad and web browser (edge) have different layouts, this Windows composable only holds
// basic window behavior: Window drag and taskbar capabilities.
@Composable
fun DraggableWindow(
    windowWidth: Dp = 600.dp,
    windowHeight: Dp = 400.dp,
    modifier: Modifier = Modifier,
    titleBarColor: Color = MaterialTheme.colorScheme.surface,
    titleBar: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val parentWidthPx = constraints.maxWidth.toFloat()
        val parentHeightPx = constraints.maxHeight.toFloat()

        // Convert the window size from dp to px
        val density = LocalDensity.current
        val windowWidthPx = with(density) { windowWidth.toPx() }
        val windowHeightPx = with(density) { windowHeight.toPx() }

        var offset by remember {
            mutableStateOf(
                Offset(
                    x = (parentWidthPx - windowWidthPx) / 2f + Random.nextInt(-25, 25),
                    y = (parentHeightPx - windowHeightPx) / 2f + Random.nextInt(-25, 25)
                )
            )
        }

        Surface(
            shape = RoundedCornerShape(6.dp),
            shadowElevation = 8.dp,
            modifier = Modifier
                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                .size(windowWidth, windowHeight)
                .then(modifier)
        ) {
            Column {
                Row(
                    content = { titleBar() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .background(titleBarColor)
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()
                                offset += dragAmount
                            }
                        }
                )

                // Window content
                Box(
                    content = { content() },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .safeContentPadding()
                        .scrollable(rememberScrollState(), Orientation.Vertical)
                )
            }
        }
    }
}

// I cracked my head trying to find a way to remove the default shape of an IconButton (which by default is round), since Windows 11 has flat buttons. Tried with a Card, but failed... thus, a Box!
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TitleBarButton(
    onClick: () -> Unit,
    iconPainter: Painter,
    contentDescription: String,
    hoverColor: Color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
    iconSize: Dp = 16.dp,
    modifier: Modifier = Modifier
) {
    var isHovered by remember { mutableStateOf(false) }

    Box (
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxHeight()
            .width(40.dp)
            .background(
                color = if (isHovered) hoverColor else Color.Transparent,
                shape = RectangleShape
            )
            .onPointerEvent(PointerEventType.Enter) { isHovered = true }
            .onPointerEvent(PointerEventType.Exit) { isHovered = false }
            .clickable { onClick() }
    ) {
        Icon(
            painter = iconPainter,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(iconSize)
        )
    }
}