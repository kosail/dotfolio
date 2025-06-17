package com.korealm.dotfolio.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.state.AppThemeState
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
    openApps: Set<String>,
    themeState: AppThemeState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

        AnimatedVisibility(themeState.isDarkTheme) {
            Image(
                painter = painterResource(Res.drawable.bg_dark),
                contentDescription = "Wallpaper",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
        }

        AnimatedVisibility(!themeState.isDarkTheme) {
            Image(
                painter = painterResource(Res.drawable.bg),
                contentDescription = "Wallpaper",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
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
                Image(
                    painterResource(Res.drawable.start),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )

                Spacer(Modifier.width(16.dp))

                Image(
                    painterResource(Res.drawable.file_manager),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource( if (themeState.isDarkTheme) Res.drawable.go_up_light else Res.drawable.go_up_dark),
                    contentDescription = null,
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
}