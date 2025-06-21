package com.korealm.dotfolio.ui.windows.photos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.ui.SymbolicIconButton
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PhotosAppWindowContent(
    modifier: Modifier = Modifier
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    Box(modifier = modifier.fillMaxSize()) {
        /*
            * This MUST be a surface due a weird bug that I cannot explain why is happening.
            * In a nutshell, when zooming, the image is covering not only the entire body (intended), but also the taskbar (unintended) which is a TOTALLY different composable
            * It doesn't make sense that it covers the taskbar too...
        * */
        Surface( // Image rendering section
            color = Color.Transparent,
        ) {
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .onPointerEvent(PointerEventType.Scroll) { event ->
                        val scrollDelta = event.changes.first().scrollDelta.y
                        val speed = 0.05f

                        scale = (scale + scrollDelta * speed).coerceIn(0.5f, 4f)
                    }
                    .pointerInput(Unit) {
                        detectDragGestures { _, dragAmount ->
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                        }
                    }
            ) {
                Image(
                    painter = painterResource(Res.drawable.selfie),
                    contentDescription = stringResource(Res.string.photos__photo_content_description),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = offsetX,
                            translationY = offsetY
                        )
                )
            }
        }

        Box( // Bottom bar
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .background(MaterialTheme.colorScheme.surface)
                .align(Alignment.BottomCenter)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
            ) {
                Text(
                    text = "${(scale * 100).toInt()}%",
                    modifier = Modifier
                )

                Spacer(Modifier.width(10.dp))

                PhotosBottomBarIcon(
                    icon = Res.drawable.zoom_in_symbolic,
                    contentDescription = stringResource(Res.string.photos__zoom_in),
                    onClick = { scale += 0.1f }
                )

                Spacer(Modifier.width(5.dp))

                PhotosBottomBarIcon(
                    icon = Res.drawable.zoom_out_symbolic,
                    contentDescription = stringResource(Res.string.photos__zoom_out),
                    onClick = { scale -= 0.1f }
                )

                Spacer(Modifier.width(30.dp))

            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PhotosBottomBarIcon(
    icon: DrawableResource,
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    var isHovered by remember { mutableStateOf(false) }

    Box (
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxHeight()
            .width(40.dp)
            .background(
                color = if (isHovered) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f) else Color.Transparent,
                shape = RectangleShape
            )
            .onPointerEvent(PointerEventType.Enter) { isHovered = true }
            .onPointerEvent(PointerEventType.Exit) { isHovered = false }
            .clickable { onClick() }
    ) {
        SymbolicIconButton(
            icon = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(18.dp)
        )
    }
}