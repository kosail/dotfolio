package com.korealm.dotfolio.ui.windows.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.model.WindowApp
import com.korealm.dotfolio.state.AppThemeState
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsApp(
    themeState: AppThemeState,
    onClose: () -> Unit
): WindowApp {
    // isSelected is to handle selected menu options
    var selectedIndex by remember { mutableIntStateOf(0) }
    var isDevModeOn by remember { mutableStateOf(false) }

    return WindowApp(
        appId = "settings",
        title = stringResource(Res.string.settings),
        icon = painterResource(Res.drawable.settings),
        defaultSize = DpSize(950.dp, 550.dp),
        titleBar = { SettingsTitleBar(themeState, onClose) },
        content = {
            SettingsWindowContent(
                themeState = themeState,
                isDevModeOn = isDevModeOn,
                selectedIndex = selectedIndex,
                onSelectIndex = { selectedIndex = it },
                onDevModeChange = { isDevModeOn = !isDevModeOn }
            )
        }
    )
}