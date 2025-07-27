package com.korealm.dotfolio.ui

import androidx.compose.animation.*
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.model.AppId
import com.korealm.dotfolio.model.WindowApp
import com.korealm.dotfolio.state.AppThemeState
import com.korealm.dotfolio.ui.windows.DraggableWindow
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DesktopEnvironment(
    clock: Pair<String, String>,
    openAppsIds: List<AppId>,
    appRegistry: Map<AppId, @Composable () -> WindowApp>,
    themeState: AppThemeState,
    modifier: Modifier = Modifier,
) {
    val visibleApps by remember(openAppsIds) { derivedStateOf { openAppsIds } }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Surface(
            tonalElevation = 2.dp,
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(56.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxSize().padding(start = 18.dp)
            ) {

                // Top left weather section. Weather is hardcoded, not dynamic.
                Image (
                    painterResource(if (themeState.isDarkTheme) Res.drawable.weather_many_clouds else Res.drawable.weather_clouds),
                    contentDescription = null,
                    modifier = Modifier.size(34.dp)
                )

                Column (
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 13.dp)
                ) {
                    Text(
                        text = if (themeState.isDarkTheme) "26°C" else "29°C",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        lineHeight = 0.sp
                    )

                    Text(
                        text = stringResource(if (themeState.isDarkTheme) Res.string.cloudy else Res.string.partly_cloudy),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                        fontSize = 15.sp,
                        lineHeight = 0.sp
                    )
                }
            }

            // Central taskbar row
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                TaskbarIcon( // Start menu icon
                    icon = painterResource(Res.drawable.start),
                    onClick = { /* TODO LATER */ },
                )

                visibleApps.forEach { appId ->
                    val windowApp = appRegistry[appId]?.invoke()
                    if (windowApp != null) {
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn() + expandHorizontally(expandFrom = Alignment.Start),
                            exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.Start),
                            modifier = Modifier.padding(horizontal = 4.dp)
                        ) {
                            TaskbarIcon(
                                icon = windowApp.icon,
                                onClick = { windowApp.isMinimized = ! windowApp.isMinimized },
                                modifier = Modifier
                            )
                        }
                    }
                }

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxSize()
            ) {
                SimpleSymbolicIconButton(
                    icon = Res.drawable.go_up_symbolic,
                    modifier = Modifier.size(25.dp)
                )

                SimpleSymbolicIconButton(
                    icon = Res.drawable.wifi,
                    modifier = Modifier.size(30.dp)
                )

                SimpleSymbolicIconButton(
                    icon = Res.drawable.audio_volume_medium,
                    modifier = Modifier.size(30.dp)
                )

                Spacer(Modifier.width(5.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.battery_100,
                    modifier = Modifier.size(22.dp)
                )

                Spacer(Modifier.width(4.dp))

                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp)
                ) {
                    Text(
                        text = clock.first,
                        fontSize = 15.sp,
                        lineHeight = 0.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = clock.second,
                        fontSize = 15.sp,
                        lineHeight = 0.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(Modifier.width(6.dp))
            }
        }
    }

    openAppsIds.forEach { appId ->
        // This key(){} thing fixed the bug where when a window was closed, it took the previous window offset in the screen.
        // This issue happened because, apparently, Jetpack Compose tracks state by composition order unless I tell it explicitly what it should key on. So using a key binds the DraggableWindow composable for each AppId individually, instead of just reusing the DraggableWindow of the previous opened windows in the composition tree.

        // This is a little bit confusing, but according to my IA friends, using key(){} is like telling Compose:
        // "Hey, track this block using the unique key appId, not just the order it appeared in."
        key(appId) {
            val window = appRegistry[appId]?.invoke()
            if (window != null) {
                DraggableWindow(
                    windowWidth = window.defaultSize.width,
                    windowHeight = window.defaultSize.height,
                    titleBar = window.titleBar,
                    content = window.content
                )
            }
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TaskbarIcon(
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isHover by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(56.dp)
            .onPointerEvent(PointerEventType.Enter) { isHover = true }
            .onPointerEvent(PointerEventType.Exit) { isHover = false }
            .clickable { onClick() }
            .background(if (isHover) MaterialTheme.colorScheme.primary.copy(alpha = 0.01f) else Color.Transparent)
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(36.dp)
        )
    }
}