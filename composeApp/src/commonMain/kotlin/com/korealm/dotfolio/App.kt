package com.korealm.dotfolio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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

@Composable
fun App() {
    // Important stuff
    val themeState = rememberAppThemeState()

    // Clock related
    var localDateTime by remember { mutableStateOf(Pair("", "")) }
    ClockThread { time, date -> localDateTime = localDateTime.copy(time, date) }


    // Windows related
    var openWindows by remember { mutableStateOf(listOf(AppId.NOTEPAD)) } // ! Debugging purposes

    var openWindowRef by remember { mutableStateOf<(AppId) -> Unit>({}) } // Due to circular dependency between this 2 functions and appRegistry
    var closeWindowRef by remember { mutableStateOf<(AppId) -> Unit>({}) } // I had to first declare them, and then associate the real function


    // This is like a sealed "registry" of apps, and now all apps can be called from here.
    val appRegistry = mapOf<AppId, (@Composable () -> WindowApp)> (
        AppId.NOTEPAD to { Win32Controller.Notepad { closeWindowRef(AppId.NOTEPAD) } },
//        AppId.WEB_BROWSER to { Win32Controller.WebBrowser { closeWindowRef(AppId.WEB_BROWSER) } },
        AppId.MEDIA_PLAYER to { Win32Controller.MediaPlayer (themeState) { closeWindowRef(AppId.MEDIA_PLAYER) } },
        AppId.PHOTOS to { Win32Controller.Photos { closeWindowRef(AppId.PHOTOS) } },
//        AppId.FILE_EXPLORER to { Win32Controller.FileExplorer { closeWindowRef(AppId.FILE_EXPLORER) } },
        AppId.SETTINGS to { Win32Controller.Settings (themeState) { closeWindowRef(AppId.SETTINGS) } },
    )

    openWindowRef = { appId ->
        if (appId !in openWindows) {
            openWindows = openWindows + appId
        }
    }


    closeWindowRef = { appId ->
        openWindows = openWindows.filterNot { it == appId }
    }


    // Run dotfolio!
    MicaTheme(
        darkTheme = themeState.isDarkTheme
    ){
        Box (
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize()
        ) {
            /* TODO:  has this comment become a bug tracker? Hmm, well...
            * Nothing in here by the moment...
            */

            DesktopEnvironment(
                clock = localDateTime,
                openAppsIds = openWindows,
                appRegistry = appRegistry,
                themeState = themeState,
                modifier = Modifier
            )

            DesktopShortcuts(
                onAppLaunch = { appId -> openWindowRef(appId) },
                modifier = Modifier.fillMaxSize()
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