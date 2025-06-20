package com.korealm.dotfolio.state


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import com.korealm.dotfolio.ui.theme.Wallpaper

class AppThemeState(initialDarkTheme: Boolean, initialWallpaper: Wallpaper) {
    var isDarkTheme by mutableStateOf(initialDarkTheme)
        private set
    var currentWallpaper by mutableStateOf(initialWallpaper)
        private set

    fun toggleTheme() {
        isDarkTheme = !isDarkTheme

        // Automatic wallpaper switching if the user has not manually changed the wallpaper from settings
        if (currentWallpaper == Wallpaper.DEFAULT_LIGHT && isDarkTheme) {
            currentWallpaper = Wallpaper.DEFAULT_DARK
        } else if (currentWallpaper == Wallpaper.DEFAULT_DARK && !isDarkTheme) {
            currentWallpaper = Wallpaper.DEFAULT_LIGHT
        }
    }

    fun changeWallpaper(wallpaper: Wallpaper) {
        currentWallpaper = wallpaper
    }
}

@Composable
fun rememberAppThemeState(
    initialDarkTheme: Boolean = isSystemInDarkTheme(),
    initialWallpaper: Wallpaper = if (isSystemInDarkTheme()) Wallpaper.DEFAULT_DARK else Wallpaper.DEFAULT_LIGHT,
): AppThemeState = remember { AppThemeState(initialDarkTheme, initialWallpaper) }