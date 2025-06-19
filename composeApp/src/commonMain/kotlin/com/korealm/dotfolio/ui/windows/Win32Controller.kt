package com.korealm.dotfolio.ui.windows

import androidx.compose.runtime.Composable
import com.korealm.dotfolio.model.WindowApp
import com.korealm.dotfolio.state.AppThemeState
import com.korealm.dotfolio.ui.windows.audio_player.AudioPlayerApp
import com.korealm.dotfolio.ui.windows.file_manager.FileManagerApp
import com.korealm.dotfolio.ui.windows.notepad.NotepadApp
import com.korealm.dotfolio.ui.windows.photos.PhotosApp
import com.korealm.dotfolio.ui.windows.settings.SettingsApp
import com.korealm.dotfolio.ui.windows.web_browser.WebBrowserApp

// Why "Win32"?, you may ask.
// Because of nothing, my friend. Don't ask, but enjoy.

// This is just a glue layer between the logic declared in each app package, and the main app
object Win32Controller {
    @Composable
    fun notepad(themeState: AppThemeState, onClose: () -> Unit): WindowApp = NotepadApp(themeState, onClose)

    // TODO: Make that all apps receive the themeState as a parameter, so they can handle well light/dark mode switch.
    @Composable
    fun webBrowser(themeState: AppThemeState, onClose: () -> Unit)/*: WindowApp*/ = WebBrowserApp(onClose)

    @Composable
    fun audioPlayer(themeState: AppThemeState, onClose: () -> Unit)/*: WindowApp*/ = AudioPlayerApp(onClose)

    @Composable
    fun photoViewer(themeState: AppThemeState, onClose: () -> Unit)/*: WindowApp*/ = PhotosApp(onClose)

    @Composable
    fun fileExplorer(themeState: AppThemeState, onClose: () -> Unit)/*: WindowApp*/ = FileManagerApp(onClose)

    @Composable
    fun settings(themeState: AppThemeState, onClose: () -> Unit)/*: WindowApp*/ = SettingsApp(onClose)
}