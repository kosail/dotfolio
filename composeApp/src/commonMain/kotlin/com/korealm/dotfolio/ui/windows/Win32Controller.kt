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
    fun notepad(onClose: () -> Unit): WindowApp = NotepadApp(onClose)

    @Composable
    fun webBrowser(onClose: () -> Unit)/*: WindowApp*/ = WebBrowserApp(onClose)

    @Composable
    fun audioPlayer(onClose: () -> Unit)/*: WindowApp*/ = AudioPlayerApp(onClose)

    @Composable
    fun photoViewer(onClose: () -> Unit): WindowApp = PhotosApp(onClose)

    @Composable
    fun fileExplorer(onClose: () -> Unit)/*: WindowApp*/ = FileManagerApp(onClose)

    @Composable
    fun settings(themeState: AppThemeState, onClose: () -> Unit): WindowApp = SettingsApp(themeState, onClose)
}