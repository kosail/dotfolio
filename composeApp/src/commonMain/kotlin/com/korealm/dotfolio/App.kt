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
import com.korealm.dotfolio.model.dotApp
import com.korealm.dotfolio.state.rememberAppThemeState
import com.korealm.dotfolio.ui.DesktopEnvironment
import com.korealm.dotfolio.ui.DesktopShortcuts
import com.korealm.dotfolio.ui.theme.MicaTheme
import com.korealm.dotfolio.ui.windows.DraggableWindow
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.notepad
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
    var openApps by remember {
        mutableStateOf(mutableSetOf<dotApp>())
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
            DesktopEnvironment(
                clock = localDateTime,
                themeState = themeState,
                modifier = Modifier)

            DesktopShortcuts(modifier = Modifier.fillMaxSize())

//            DraggableWindow(
//                icon = painterResource(Res.drawable.notepad),
//                themeState = themeState,
//                onWindowClose = {},
//                onWindowMaximize = {},
//                onWindowMinimize = {},
//                modifier = Modifier
//            ) {
//                Text(
//                    text = "It works",
//                    textAlign = TextAlign.Center,
//                    fontSize = 40.sp,
//                    modifier = Modifier.fillMaxSize().padding(50.dp)
//                )
//            }
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