package com.korealm.portfolio.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.portfolio.state.AppThemeState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.bg_dark
import portfolio.composeapp.generated.resources.bg
import portfolio.composeapp.generated.resources.cloudy
import portfolio.composeapp.generated.resources.file_manager
import portfolio.composeapp.generated.resources.start
import portfolio.composeapp.generated.resources.weather_many_clouds

//        ██████╗  ██████╗      █████╗ ███╗   ██╗██████╗     ████████╗ █████╗ ███████╗██╗  ██╗██████╗  █████╗ ██████╗
//        ██╔══██╗██╔════╝     ██╔══██╗████╗  ██║██╔══██╗    ╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝██╔══██╗██╔══██╗██╔══██╗
//        ██████╔╝██║  ███╗    ███████║██╔██╗ ██║██║  ██║       ██║   ███████║███████╗█████╔╝ ██████╔╝███████║██████╔╝
//        ██╔══██╗██║   ██║    ██╔══██║██║╚██╗██║██║  ██║       ██║   ██╔══██║╚════██║██╔═██╗ ██╔══██╗██╔══██║██╔══██╗
//        ██████╔╝╚██████╔╝    ██║  ██║██║ ╚████║██████╔╝       ██║   ██║  ██║███████║██║  ██╗██████╔╝██║  ██║██║  ██║
//        ╚═════╝  ╚═════╝     ╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝        ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝

@Composable
fun DesktopEnvironment(
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
                    painterResource(Res.drawable.weather_many_clouds),
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
                        text = "26°C",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 15.sp,
                    )

                    Text(
                        text = stringResource(Res.string.cloudy),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                        fontSize = 15.sp,
                    )
                }
            }

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
        }
    }
}