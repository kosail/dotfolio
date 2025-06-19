package com.korealm.dotfolio.ui.windows

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.state.AppThemeState
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource

@Composable
fun StandardTitleBarButtonSet(
    themeState: AppThemeState,
    minimizeButton: Boolean = true,
    maximizeButton: Boolean = true,
    exitButton: Boolean = true,
    onMinimize: () -> Unit = {},
    onMaximize: () -> Unit = {},
    onClose: () -> Unit = {},
) {
    // Minimize, Maximize and Close buttons
    Surface(
        color = Color.Transparent,
        modifier = Modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.height(32.dp)
        ) {
            if (minimizeButton) {
                TitleBarButton(
                    onClick = onMinimize,
                    iconPainter = painterResource(if (themeState.isDarkTheme) Res.drawable.window_minimize_dark else Res.drawable.window_minimize_light),
                    contentDescription = "Minimize",
                    hoverColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                    iconSize = 20.dp
                )
            }

            if (maximizeButton) {
                TitleBarButton(
                    onClick = onMaximize,
                    iconPainter = painterResource(if (themeState.isDarkTheme) Res.drawable.window_maximize_dark else Res.drawable.window_maximize_light),
                    contentDescription = "Maximize",
                    hoverColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                    iconSize = 20.dp
                )
            }

            if (exitButton) {
                TitleBarButton(
                    onClick = onClose,
                    iconPainter = painterResource(if (themeState.isDarkTheme) Res.drawable.window_close_dark else Res.drawable.window_close_light),
                    contentDescription = "Close",
                    hoverColor = MaterialTheme.colorScheme.error.copy(alpha = 0.45f),
                    iconSize = 20.dp
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