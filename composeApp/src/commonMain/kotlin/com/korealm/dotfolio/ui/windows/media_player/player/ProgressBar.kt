package com.korealm.dotfolio.ui.windows.media_player.player

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.state.MediaPlayerState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProgressBar(
    playerState: MediaPlayerState, // For seek
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.8f),
    progressColor: Color = Color(0xFFCF3E0B),
    thumbColor: Color = Color(0xFFCF3E0B),
    modifier: Modifier = Modifier,
) {
    var thumbOffset by remember { mutableStateOf(playerState.progress) }

    LaunchedEffect(playerState.isPlaying) {
        while (playerState.isPlaying) {
            thumbOffset = playerState.progress
            delay(150)
        }
    }

    BoxWithConstraints (
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(6.dp)
    ) {
        val barWidthPx = constraints.maxWidth

        Canvas( // Progress bar
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
        ) {
            drawRect( // Background
                color = backgroundColor,
                size = size
            )

            drawRect( // Progress bar
                color = progressColor,
                size = Size(size.width * thumbOffset, size.height),
            )
        }

        Canvas( // Thumb
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        val thumbPositionPx = barWidthPx * thumbOffset + dragAmount.x
                        thumbOffset = (thumbPositionPx / barWidthPx).coerceIn(0f, 1f)
                        MediaPlayer.seekTo(playerState.duration * thumbOffset)
                    }
                }
        ) {
            val thumbX = size.width * thumbOffset
            val centerY = size.height / 2

            drawCircle( // Outer circle acting as a border
                color = backgroundColor.copy(alpha = 0.85f),
                radius = size.height * 2f,
                center = Offset(thumbX, centerY),
            )

            drawCircle( // Inner circle acting as the actual thumb
                color = thumbColor,
                radius = size.height * 1.15f,
                center = Offset(thumbX, centerY)
            )
        }
    }
}