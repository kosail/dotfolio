package com.korealm.dotfolio.ui.windows.web_browser.pages.general

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import com.korealm.dotfolio.utils.openInNewTab
import org.jetbrains.compose.resources.DrawableResource


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SimpleUrlButton(
    title: String,
    url: String,
    font: FontFamily,
    icon: DrawableResource,
    modifier: Modifier = Modifier
) {
    var isHover by remember { mutableStateOf(false) }

    val animatedBackgroundColor by animateColorAsState(
        targetValue = if (isHover) MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f) else Color.Transparent,
        animationSpec = tween(durationMillis = 200)
    )

    val animatedBorder by animateDpAsState(
        targetValue = if (isHover) 2.dp else 1.dp,
        animationSpec = tween(durationMillis = 200)
    )

    Surface (
        color = animatedBackgroundColor,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(animatedBorder, MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)),
        modifier = modifier
            .onClick { openInNewTab(url) }
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while(true) {
                        val type = awaitPointerEvent().type

                        when (type) {
                            PointerEventType.Enter -> isHover = true
                            PointerEventType.Exit -> isHover = false
                        }
                    }
                }
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            SimpleSymbolicIconButton(
                icon = icon,
                modifier = Modifier.size(28.dp)
            )

            Spacer(Modifier.width(12.dp))

            PostText(
                text = title,
                font = font,
                fontSize = 18.sp
            )
        }
    }
}