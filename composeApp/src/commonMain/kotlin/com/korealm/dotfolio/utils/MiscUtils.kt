package com.korealm.dotfolio.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun RoundedPicture(
    painter: DrawableResource,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(painter),
        contentDescription = contentDescription,
        modifier = modifier
            .clip(CircleShape)
            .size(35.dp)
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UrlBar(
    modifier: Modifier = Modifier,
    maxWidth: Dp,
    addGlow: Boolean = true,
    content: @Composable () -> Unit = {}
) {
    var isHover by remember { mutableStateOf(false) }
    Surface (
        shape = RoundedCornerShape(6.dp),
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .width(maxWidth)
            .height(40.dp)
            .then(
                if (! addGlow) {
                    Modifier
                } else {
                    Modifier
                        .onPointerEvent(PointerEventType.Enter) { isHover = true }
                        .onPointerEvent(PointerEventType.Exit) { isHover = false }
                        .then(
                            if (isHover) {
                                modifier.border(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                            } else {
                                Modifier
                            }
                        )
                }
            )

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            content()
        }
    }
}