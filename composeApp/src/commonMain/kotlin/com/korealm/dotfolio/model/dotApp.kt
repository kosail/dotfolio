package com.korealm.dotfolio.model

import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

// All apps that are available in dotfolio
enum class dotApp (icon: DrawableResource, title: String) {
    NOTEPAD (Res.drawable.notepad, "Notepad"),
    WEB_BROWSER (Res.drawable.web_browser, "Edge"),
    MEDIA_PLAYER (Res.drawable.playmymusic, "Music"),
    PHOTO_VIEWER (Res.drawable.image_viewer, "Photos"),
    FILE_EXPLORER (Res.drawable.file_manager, "Files"),
    SETTINGS (Res.drawable.preferences_system, "Settings"),
}