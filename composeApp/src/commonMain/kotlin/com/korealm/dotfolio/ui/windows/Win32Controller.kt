package com.korealm.dotfolio.ui.windows

import androidx.compose.runtime.Composable
import com.korealm.dotfolio.model.WindowApp
import com.korealm.dotfolio.state.AppThemeState
import com.korealm.dotfolio.ui.windows.file_manager.FileManagerApp
import com.korealm.dotfolio.ui.windows.music_player.MusicPlayerApp
import com.korealm.dotfolio.ui.windows.notepad.NotepadApp
import com.korealm.dotfolio.ui.windows.photos.PhotosApp
import com.korealm.dotfolio.ui.windows.settings.SettingsApp
import com.korealm.dotfolio.ui.windows.web_browser.WebBrowserApp

// Why "Win32"?, you may ask.
// Because of nothing, my friend. Don't ask, but enjoy.

// This is just a glue layer between the logic declared in each app package, and the main app
object Win32Controller {
    @Composable
    fun Notepad(onClose: () -> Unit): WindowApp = NotepadApp(onClose)

    @Composable
    fun WebBrowser(onClose: () -> Unit)/*: WindowApp*/ = WebBrowserApp(onClose)

    @Composable
    fun MusicPlayer(onClose: () -> Unit): WindowApp = MusicPlayerApp(onClose)

    @Composable
    fun Photos(onClose: () -> Unit): WindowApp = PhotosApp(onClose)

    @Composable
    fun FileExplorer(onClose: () -> Unit)/*: WindowApp*/ = FileManagerApp(onClose)

    @Composable
    fun Settings(themeState: AppThemeState, onClose: () -> Unit): WindowApp = SettingsApp(themeState, onClose)
}