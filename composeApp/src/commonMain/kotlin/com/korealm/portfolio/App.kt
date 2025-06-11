package com.korealm.portfolio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.korealm.portfolio.state.rememberAppThemeState
import com.korealm.portfolio.ui.DesktopEnvironment
import com.korealm.portfolio.ui.DesktopShortcuts
import com.korealm.portfolio.ui.theme.MicaTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    val themeState = rememberAppThemeState()

    MicaTheme(
        darkTheme = themeState.isDarkTheme
    ){
        Box (
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
        ){
            DesktopEnvironment(themeState, modifier = Modifier)
            DesktopShortcuts(modifier = Modifier.fillMaxSize())
        }
    }
}