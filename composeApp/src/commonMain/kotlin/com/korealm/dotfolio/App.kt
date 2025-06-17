package com.korealm.dotfolio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
    var openApps by remember { mutableStateOf(mutableSetOf<String>()) }

    // This is like a sealed "registry" of apps. All apps' instantiation in here.
    val appRegistry = mapOf<String, () -> Unit>(
        "notepad" to {
            Win32Controller.notepad()
            openApps.add("notepad")
         },
        "webBrowser" to {
            Win32Controller.webBrowser()
            openApps.add("webBrowser")
        },
        "audioPlayer" to {
            Win32Controller.audioPlayer()
            openApps.add("audioPlayer")
         },
        "photoViewer" to {
            Win32Controller.photoViewer()
            openApps.add("photoViewer")
         },
        "fileExplorer" to {
            Win32Controller.fileExplorer()
            openApps.add("fileExplorer")
        },
        "settings" to {
            Win32Controller.settings()
            openApps.add("settings")
        }
    )


    // Run dotfolio!
    MicaTheme(
        darkTheme = themeState.isDarkTheme
    ){
        Box (
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize()
        ) {
            DesktopEnvironment(
                clock = localDateTime,
                openApps = openApps,
                themeState = themeState,
                modifier = Modifier)

            DesktopShortcuts(
                appRegistry = appRegistry,
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