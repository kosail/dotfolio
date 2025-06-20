package com.korealm.dotfolio.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SymbolicIconButton(
    icon: DrawableResource,
    contentDescription: String? = null,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
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