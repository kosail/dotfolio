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
import dotfolio.composeapp.generated.resources.*
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.DrawableResource


@Composable
fun App() {
    // Important stuff
    val themeState = rememberAppThemeState()

    // Clock related
    var localDateTime by remember { mutableStateOf(Pair("", "")) }
    ClockThread { time, date -> localDateTime = localDateTime.copy(time, date) }


    // Windows related
    var openApps by remember { mutableStateOf(emptySet<Pair<String, DrawableResource>>()) }

    // This is like a sealed "registry" of apps, and now all apps can be called from here.
    // It also syncs opened window apps with the taskbar icons.
    val appRegistry = mapOf<String, () -> Unit>(
        "notepad" to {
            val app = Pair("notepad", Res.drawable.notepad)
            openApps = openApps + app
            Win32Controller.notepad()
        },
        "webBrowser" to {
            val app = Pair("webBrowser", Res.drawable.web_browser)
            openApps = openApps + app
            Win32Controller.webBrowser()
        },
        "audioPlayer" to {
            val app = Pair("audioPlayer", Res.drawable.playmymusic)
            openApps = openApps + app
            Win32Controller.audioPlayer()
        },
        "photoViewer" to {
            val app = Pair("photoViewer", Res.drawable.image_viewer)
            openApps = openApps + app
            Win32Controller.photoViewer()
        },
        "fileExplorer" to {
            val app = Pair("fileExplorer", Res.drawable.file_manager)
            openApps = openApps + app
            Win32Controller.fileExplorer()
        },
        "settings" to {
            val app = Pair("settings", Res.drawable.settings)
            openApps = openApps + app
            Win32Controller.settings()
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