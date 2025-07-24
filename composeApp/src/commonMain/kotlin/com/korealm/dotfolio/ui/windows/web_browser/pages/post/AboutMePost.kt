package com.korealm.dotfolio.ui.windows.web_browser.pages.post

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.state.AppThemeState
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import com.korealm.dotfolio.ui.windows.web_browser.pages.SlightDivider
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutMePost(
    icon: DrawableResource,
    title: StringResource,
    font: FontFamily,
    themeState: AppThemeState,
    modifier: Modifier = Modifier,
    content: (@Composable () -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
    ) {
        SimpleDotDecoration(
            yOffset = 2f,
            themeState = themeState,
            modifier = Modifier.padding(top = 5.dp)
        )

        Spacer(modifier = Modifier.width(40.dp))

        SimpleBoxWithTitle(
            icon = icon,
            title = title,
            font = font,
            content = { content?.invoke() },
            modifier = Modifier.padding(bottom = 40.dp)
        )
    }
}

@Composable
fun SimpleBoxWithTitle(
    icon: DrawableResource,
    title: StringResource,
    font: FontFamily,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
            .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                SimpleSymbolicIconButton(
                    icon = icon,
                    modifier = Modifier.size(24.dp),
                )

                Spacer(Modifier.width(15.dp))

                Text(
                    text = stringResource(title),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = font,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            SlightDivider(
                orientation = Orientation.Horizontal,
                modifier = Modifier.padding(vertical = 20.dp)
            )

            content()
        }
    }
}

// Scrollbar-like section
@Composable
fun SimpleBarDecoration(
    modifier: Modifier = Modifier
) {
    val barColor = MaterialTheme.colorScheme.onBackground.copy(alpha = .1f)

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp, vertical = 16.dp),
    ) {
        Canvas(
            modifier = modifier
                .fillMaxHeight()
                .width(6.dp)
        ) {
            drawRect(
                color = barColor,
                size = size
            )
        }
    }
}

@Composable
fun SimpleDotDecoration(
    yOffset: Float,
    themeState: AppThemeState,
    modifier: Modifier = Modifier
) {
    val dotColor = if (themeState.isDarkTheme) Color(0xFF767676) else Color(0xFFBABABA)

    Box(
        modifier = modifier
    ) {
        Canvas(
            modifier = modifier
                .width(6.dp)
                .height(40.dp)
        ) {
            drawCircle(
                color = dotColor,
                radius = size.minDimension / 0.5f,
                center = Offset(
                    x = size.width / 2, // Horizontal positioning
                    y = size.height / yOffset // Vertical positioning
                ),
            )
        }
    }
}