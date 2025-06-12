package com.korealm.dotfolio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.korealm.dotfolio.state.rememberAppThemeState
import com.korealm.dotfolio.ui.DesktopEnvironment
import com.korealm.dotfolio.ui.DesktopShortcuts
import com.korealm.dotfolio.ui.theme.MicaTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    val themeState = rememberAppThemeState()
    var localDateTime by remember { mutableStateOf(Pair<String, String>("", "")) }

    MicaTheme(
        darkTheme = themeState.isDarkTheme
    ){
        Box (
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
        ) {
            DesktopEnvironment(
                clock = localDateTime,
                onClockUpdate = { time, date -> localDateTime = localDateTime.copy(time, date) },
                themeState = themeState,
                modifier = Modifier)
            DesktopShortcuts(modifier = Modifier.fillMaxSize())
        }
    }
}