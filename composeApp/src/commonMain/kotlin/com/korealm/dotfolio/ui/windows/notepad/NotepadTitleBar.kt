package com.korealm.dotfolio.ui.windows.notepad

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.state.AppThemeState
import com.korealm.dotfolio.ui.windows.TitleBarButton
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NotepadTitleBar(
    themeState: AppThemeState,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box( // Slight border in the taskbar to have more visual deepness. Good for the eye, good for the heart.
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
            .border(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.03f))
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.20f))
    ) {
        // Icon
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 15.dp)
                .padding(top = 5.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.notepad),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }


        // Tabs
        Surface (
            shape = MaterialTheme.shapes.small.copy(
                topStart = CornerSize(7.dp),
                topEnd = CornerSize(7.dp),
                bottomStart = CornerSize(0.dp),
                bottomEnd = CornerSize(0.dp)
            ),
            shadowElevation = 3.dp,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.align(Alignment.Bottom)

        ) {
            // I need this extra surface just to set this specific background tone, which matches with the one in the menu bar
            // that you'll find in NotepadWindowContent.kt
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
            ) {
                Text(
                    text = stringResource(Res.string.readme) + ".txt",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                        .padding(end = 50.dp)
                )
            }
        }


        // Space Filler
        Surface(
            content = { Spacer(Modifier.fillMaxSize()) },
            color = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )


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
                TitleBarButton(
                    onClick = {},
                    iconPainter = painterResource(if (themeState.isDarkTheme) Res.drawable.window_minimize_dark else Res.drawable.window_minimize_light),
                    contentDescription = "Minimize",
                    hoverColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                    iconSize = 20.dp
                )

                TitleBarButton(
                    onClick = { },
                    iconPainter = painterResource(if (themeState.isDarkTheme) Res.drawable.window_maximize_dark else Res.drawable.window_maximize_light),
                    contentDescription = "Maximize",
                    hoverColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                    iconSize = 20.dp
                )

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