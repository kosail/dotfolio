package com.korealm.dotfolio.ui.windows.media_player

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.model.AppId
import com.korealm.dotfolio.model.WindowApp
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.media_player
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MediaPlayerApp(onClose: () -> Unit): WindowApp {
    return WindowApp(
        appId = AppId.MEDIA_PLAYER,
        title = stringResource(Res.string.media_player),
        icon = painterResource(Res.drawable.media_player),
        isMinimized = false,
        defaultSize = DpSize(950.dp, 700.dp),
        titleBar = { MediaPlayerTitleBar(onClose = onClose) },
        content = { MediaPlayerWindowContent() }
    )
}