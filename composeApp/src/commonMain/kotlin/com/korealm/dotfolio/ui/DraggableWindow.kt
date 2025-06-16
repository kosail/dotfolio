package com.korealm.dotfolio.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.state.AppThemeState
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt
import kotlin.random.Random

// This composable recreates Windows 11 windows and requests a Composable function to display inside.
// As Windows 11 photo viewer, audio player, notepad and web browser (MS Edge) have different layouts, this Windows composable only holds
// Basic window behavior: Window drag, close, minimize and maximize capabilities.
@Composable
fun DraggableWindow(
    themeState: AppThemeState,
    onWindowMaximize: () -> Unit,
    onWindowMinimize: () -> Unit,
    onWindowClose: () -> Unit,
    windowWidth: Dp = 600.dp,
    windowHeight: Dp = 400.dp,
    modifier: Modifier = Modifier,
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
                // Title bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()
                                offset += dragAmount
                            }
                        },
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row(
                        modifier = Modifier.height(32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TitleBarButton(
                            onClick = onWindowMinimize,
                            iconPainter = painterResource(if (themeState.isDarkTheme) Res.drawable.window_minimize_dark else Res.drawable.window_minimize_light),
                            contentDescription = "Minimize"
                        )

                        TitleBarButton(
                            onClick = onWindowMaximize,
                            iconPainter = painterResource(if (themeState.isDarkTheme) Res.drawable.window_maximize_dark else Res.drawable.window_maximize_light),
                            contentDescription = "Maximize"
                        )

                        TitleBarButton(
                            onClick = onWindowClose,
                            iconPainter = painterResource(if (themeState.isDarkTheme) Res.drawable.window_close_dark else Res.drawable.window_close_light),
                            contentDescription = "Close",
                            hoverColor = MaterialTheme.colorScheme.error.copy(alpha = 0.45f) // Red on hover like real Windows
                        )
                    }
                }

                // Window content
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .safeContentPadding()
                ) {
                    content()
                }
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