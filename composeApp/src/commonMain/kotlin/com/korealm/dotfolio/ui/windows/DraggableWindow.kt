package com.korealm.dotfolio.ui.windows

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import kotlin.random.Random

// This composable recreates Windows 11 windows and requests a Composable function to display inside.
// As Windows 11 photo viewer, audio player, notepad and web browser (edge) have different layouts, this Windows composable only holds basic window behavior: Window drag and taskbar capabilities.
@Composable
fun DraggableWindow(
    windowWidth: Dp = 600.dp,
    windowHeight: Dp = 400.dp,
    modifier: Modifier = Modifier,
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
                Box(
                    content = { titleBar() },
                    modifier = Modifier
                        .fillMaxWidth()
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

