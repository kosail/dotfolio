package com.korealm.dotfolio.ui.windows

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SymbolicIconButton
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.settings
import dotfolio.composeapp.generated.resources.window_close
import dotfolio.composeapp.generated.resources.window_maximize
import dotfolio.composeapp.generated.resources.window_minimize
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StandardTitleBarButtonSet(
    minimizeButton: Boolean = true,
    maximizeButton: Boolean = true,
    exitButton: Boolean = true,
    onMinimize: () -> Unit = {},
    onMaximize: () -> Unit = {},
    onClose: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Minimize, Maximize and Close buttons
    Surface(
        color = Color.Transparent,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.height(32.dp)
        ) {
            if (minimizeButton) {
                TitleBarButton(
                    onClick = onMinimize,
                    iconPainter = Res.drawable.window_minimize,
                    contentDescription = "Minimize",
                    hoverColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                    iconSize = 20.dp
                )
            }

            if (maximizeButton) {
                TitleBarButton(
                    onClick = onMaximize,
                    iconPainter = Res.drawable.window_maximize,
                    contentDescription = "Maximize",
                    hoverColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                    iconSize = 20.dp
                )
            }

            if (exitButton) {
                TitleBarButton(
                    onClick = onClose,
                    iconPainter = Res.drawable.window_close,
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
    iconPainter: DrawableResource,
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
        SymbolicIconButton(
            icon = iconPainter,
            contentDescription = contentDescription,
            modifier = Modifier.size(iconSize)
        )
    }
}

@Composable
fun TitleBarMainIcon(
    icon: DrawableResource,
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) {
    // Since this Surface is declared on its own, out of scope of any Row, Column or Box parent, we cannot declare here that this Surface should align center vertically its content.
    // Thus, you'll probably want to always pass a Modifier with .align(Alignment.CenterVertically) extension property.
    Surface(
        color = Color.Transparent,
        modifier = modifier
            .padding(horizontal = 15.dp)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = contentDescription,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
fun TitleBarTitle(
    string: StringResource,
    modifier: Modifier = Modifier
) {
    // Same issue as above. You'll probably want to always pass a Modifier with .align(Alignment.CenterVertically) extension property.
    Text(
        text = stringResource(string),
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        modifier = modifier
    )
}