package com.korealm.dotfolio.ui.windows.media_player.player

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.state.MediaPlayerState

@Composable
fun ProgressBar(
    playerState: MediaPlayerState, // For seek
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.8f),
    progressColor: Color = Color(0xFFCF3E0B),
    thumbColor: Color = Color(0xFFCF3E0B),
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(6.dp)
    ) {
        var thumbOffset by remember { mutableStateOf(0.0f) }

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
                size = Size(thumbOffset, size.height),
            )
        }

        Canvas( // Thumb
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        thumbOffset += dragAmount.x
                    }
                }
        ) {
            drawCircle( // Outer circle acting as a border
                color = backgroundColor.copy(alpha = 0.85f),
                radius = size.height * 2f,
                center = Offset(thumbOffset, size.height / 2)
            )
            
            drawCircle( // Inner circle acting as the actual thumb
                color = thumbColor,
                radius = size.height * 1.15f,
                center = Offset(thumbOffset, size.height / 2)
            )
        }
    }
}