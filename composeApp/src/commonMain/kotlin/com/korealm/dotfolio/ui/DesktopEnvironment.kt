package com.korealm.dotfolio.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.model.WindowApp
import com.korealm.dotfolio.state.AppThemeState
import com.korealm.dotfolio.ui.windows.DraggableWindow
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

//        ██████╗  ██████╗      █████╗ ███╗   ██╗██████╗     ████████╗ █████╗ ███████╗██╗  ██╗██████╗  █████╗ ██████╗
//        ██╔══██╗██╔════╝     ██╔══██╗████╗  ██║██╔══██╗    ╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝██╔══██╗██╔══██╗██╔══██╗
//        ██████╔╝██║  ███╗    ███████║██╔██╗ ██║██║  ██║       ██║   ███████║███████╗█████╔╝ ██████╔╝███████║██████╔╝
//        ██╔══██╗██║   ██║    ██╔══██║██║╚██╗██║██║  ██║       ██║   ██╔══██║╚════██║██╔═██╗ ██╔══██╗██╔══██║██╔══██╗
//        ██████╔╝╚██████╔╝    ██║  ██║██║ ╚████║██████╔╝       ██║   ██║  ██║███████║██║  ██╗██████╔╝██║  ██║██║  ██║
//        ╚═════╝  ╚═════╝     ╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝        ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝

@Composable
fun DesktopEnvironment(
    clock: Pair<String, String>,
    openAppsIds: List<String>,
    appRegistry: Map<String, @Composable () -> WindowApp>,
    themeState: AppThemeState,
    modifier: Modifier = Modifier,
) {
    val visibleApps by remember(openAppsIds) { derivedStateOf { openAppsIds } }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Default background color. Useful to not flash the user when background changing.
    ) {
        AnimatedContent(
            targetState = themeState.currentWallpaper,
            transitionSpec = {
                fadeIn(animationSpec = tween(1000) ) togetherWith fadeOut(animationSpec = tween(1000))
            }
        ) { targetWallpaper ->
            Image(
                painter = painterResource(targetWallpaper.resource),
                contentDescription = "Wallpaper",
                contentScale = ContentScale.Crop,
                modifier = modifier.matchParentSize()
            )
        }

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
                                onClick = { /* TODO LATER */ },
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
                Icon(
                    painter = painterResource(Res.drawable.go_up_symbolic),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(25.dp)
                )

                Image(
                    painter = painterResource(Res.drawable.wifi),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier.size(30.dp)
                )

                Image(
                    painter = painterResource(Res.drawable.audio_volume_medium),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier.size(30.dp)
                )

                Spacer(Modifier.width(5.dp))

                Image(
                    painter = painterResource(Res.drawable.battery_100),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
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