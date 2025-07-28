package com.korealm.dotfolio

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.korealm.dotfolio.model.AppId
import com.korealm.dotfolio.model.WindowApp
import com.korealm.dotfolio.state.rememberAppThemeState
import com.korealm.dotfolio.ui.DesktopEnvironment
import com.korealm.dotfolio.ui.DesktopShortcuts
import com.korealm.dotfolio.ui.theme.MicaTheme
import com.korealm.dotfolio.ui.windows.Win32Controller
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource

@Composable
fun App() {
    // Important stuff
    val themeState = rememberAppThemeState()

    // Clock related
    var localDateTime by remember { mutableStateOf(Pair("", "")) }
    ClockThread { time, date -> localDateTime = localDateTime.copy(time, date) }


    // Windows related
    var openWindows by remember { mutableStateOf(listOf(AppId.NOTEPAD)) }

    var openWindowRef by remember { mutableStateOf<(AppId) -> Unit>({}) } // Due to circular dependency between this 2 functions and appRegistry
    var closeWindowRef by remember { mutableStateOf<(AppId) -> Unit>({}) } // I had to first declare them, and then associate the real function

    val bringToFrontRef = remember { mutableStateOf<(AppId) -> Unit>({}) }
    var launchedApps by remember { mutableStateOf(listOf(AppId.NOTEPAD)) } // This one is needed for the taskbar icons. To make then independent of changes in focus being done in openWindows



    // This is like a sealed "registry" of apps, and now all apps can be called from here.
    val appRegistry = mapOf<AppId, (@Composable () -> WindowApp)> (
        AppId.NOTEPAD to { Win32Controller.Notepad { closeWindowRef(AppId.NOTEPAD) } },
        AppId.WEB_BROWSER to { Win32Controller.WebBrowser(themeState) { closeWindowRef(AppId.WEB_BROWSER) } },
        AppId.MEDIA_PLAYER to { Win32Controller.MediaPlayer (themeState) { closeWindowRef(AppId.MEDIA_PLAYER) } },
        AppId.PHOTOS to { Win32Controller.Photos { closeWindowRef(AppId.PHOTOS) } },
        AppId.FILE_EXPLORER to { Win32Controller.FileExplorer (themeState) { closeWindowRef(AppId.FILE_EXPLORER) } },
        AppId.SETTINGS to { Win32Controller.Settings (themeState) { closeWindowRef(AppId.SETTINGS) } },
        AppId.TRASH to { Win32Controller.RecycleBin (themeState) { closeWindowRef(AppId.TRASH) } },
    )

    openWindowRef = { appId ->
        if (appId !in openWindows) {
            openWindows = openWindows + appId
        }

        if (appId !in launchedApps) {
            launchedApps = launchedApps + appId
        }
    }



    closeWindowRef = { appId ->
        openWindows = openWindows.filterNot { it == appId }
        launchedApps = launchedApps.filterNot { it == appId }
    }

    // The following function simply deletes the app from the openApps list and readd it (changing the composition order and thus rendering the window over any other)
    // It will be triggered by the DraggableWindow assigned to every opened app.
    // This is performed inside DesktopEnvironment.kt (because there is where the apps are actually called)
    bringToFrontRef.value = { appId ->
        openWindows = openWindows.filterNot { it == appId } + appId
    }



    // Run dotfolio!
    MicaTheme(
        darkTheme = themeState.isDarkTheme
    ){
        Box(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize()
                .background(Color.DarkGray) // Default background color. Useful to not flash the user when background changing.
        ) {
            AnimatedContent(
                targetState = themeState.currentWallpaper,
                transitionSpec = {
                    fadeIn(animationSpec = tween(1000) ) togetherWith fadeOut(animationSpec = tween(1000))
                }
            ) { targetWallpaper ->
                Image(
                    painter = painterResource(targetWallpaper.resource),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }
        }

        Box (
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize()
        ) {
            DesktopShortcuts(
                onAppLaunch = { appId -> openWindowRef(appId) },
                modifier = Modifier.fillMaxSize()
            )

            DesktopEnvironment(
                clock = localDateTime,
                openAppsIds = openWindows,
                launchedAppsIds = launchedApps,
                appRegistry = appRegistry,
                onWindowFocus = bringToFrontRef.value,
                themeState = themeState,
                modifier = Modifier
            )
        }
    }
}


// Keep updating the time in the background every 6 seconds.
@Composable
fun ClockThread(
    onClockUpdate: (String, String) -> Unit
) {
    LaunchedEffect(Unit) {
        while(true) {
            val localDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            val hour = (if (localDateTime.hour > 12) localDateTime.hour - 12 else localDateTime.hour).toString().padStart(2, '0')
            val minute = localDateTime.minute.toString().padStart(2, '0')
            val month = localDateTime.monthNumber.toString().padStart(2, '0')

            val time = "$hour:$minute ${if (localDateTime.hour > 12) "PM" else "AM"}"

            val date = "$month/${localDateTime.dayOfMonth}/${localDateTime.year}"

            onClockUpdate(time, date)
            delay(6000)
        }
    }
}