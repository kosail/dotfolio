package com.korealm.dotfolio.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SimpleSymbolicIconButton(
    icon: DrawableResource,
    contentDescription: String? = null,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    Icon(
        painter = painterResource(icon),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(18.dp)
            .then(
                if (onClick != null) {
                    // I'm using onPointerPress instead of .clickable to have no hover changes on the button
                    // In this way, I can have an icon with an action and nothing more, and manage myself manually any behavior like hovering and so on
                    Modifier.onPointerEvent(PointerEventType.Press) { onClick() }
                } else {
                    modifier
                }
            )
    )
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HoverableSymbolicIconButton(
    icon: DrawableResource,
    contentDescription: String? = null,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    boxModifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    hoverColor: Color = Color.Black.copy(alpha = 0.1f),
    onClick: (() -> Unit)? = null,
) {
    var isHover by remember { mutableStateOf(false) }

    Box(
        modifier = boxModifier
            .size(50.dp)
            .clip(CircleShape)
            .background(if (isHover) hoverColor else Color.Transparent)
            .onPointerEvent(PointerEventType.Enter) { isHover = true }
            .onPointerEvent(PointerEventType.Exit) { isHover = false }
            .then(if (onClick != null) Modifier.onPointerEvent(PointerEventType.Press) { onClick() } else Modifier)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = contentDescription,
            tint = tint,
            modifier = iconModifier
                .size(25.dp)
                .align(Alignment.Center) // optional: center icon inside the circle
        )
    }
}