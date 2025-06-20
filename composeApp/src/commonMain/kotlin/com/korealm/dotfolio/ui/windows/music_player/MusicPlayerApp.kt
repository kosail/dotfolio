package com.korealm.dotfolio.ui.windows.music_player

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.model.AppId
import com.korealm.dotfolio.model.WindowApp
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.music_player
import dotfolio.composeapp.generated.resources.playmymusic
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MusicPlayerApp(onClose: () -> Unit): WindowApp {
    return WindowApp(
        appId = AppId.MUSIC,
        title = stringResource(Res.string.music_player),
        icon = painterResource(Res.drawable.playmymusic),
        isMinimized = false,
        defaultSize = DpSize(500.dp, 500.dp),
        titleBar = { MusicPlayerTitleBar(onClose = onClose) },
        content = { MusicPlayerWindowContent() }
    )
}