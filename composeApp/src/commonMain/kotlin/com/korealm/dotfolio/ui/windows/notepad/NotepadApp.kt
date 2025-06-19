package com.korealm.dotfolio.ui.windows.notepad

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.model.WindowApp
import com.korealm.dotfolio.state.AppThemeState
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.notepad
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
// This file intends to compose both, the TitleBar and WindowContent into one single functional composable element
fun NotepadApp(
    themeState: AppThemeState,
    onClose: () -> Unit
): WindowApp {
    return WindowApp(
        appId = "notepad",
        title = stringResource(Res.string.notepad),
        icon = painterResource(Res.drawable.notepad),
        defaultSize = DpSize(700.dp, 500.dp),
        titleBar = { NotepadTitleBar(themeState, onClose) },
        content = { NotepadWindowContent() }
    )
}