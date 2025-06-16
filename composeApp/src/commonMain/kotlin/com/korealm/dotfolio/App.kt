package com.korealm.dotfolio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.state.rememberAppThemeState
import com.korealm.dotfolio.ui.DesktopEnvironment
import com.korealm.dotfolio.ui.DesktopShortcuts
import com.korealm.dotfolio.ui.DraggableWindow
import com.korealm.dotfolio.ui.theme.MicaTheme
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
    var isWindowOpen by remember { mutableStateOf(true) }

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
                themeState = themeState,
                modifier = Modifier)

            DesktopShortcuts(modifier = Modifier.fillMaxSize())

            // Testings ahead
            // I'm building the basic window placeholder, so...
            if (isWindowOpen) {
                DraggableWindow(
                    themeState = themeState,
                    onWindowClose = { isWindowOpen = false },
                    onWindowMaximize = {},
                    onWindowMinimize = {},
//                    windowWidth = 100.dp,
//                    windowHeight = 100.dp,
                    modifier = Modifier
                ) {
                    Text(
                        text = "It works",
                        textAlign = TextAlign.Center,
                        fontSize = 40.sp,
                        modifier = Modifier.fillMaxSize().padding(50.dp)
                    )
                }
            }
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