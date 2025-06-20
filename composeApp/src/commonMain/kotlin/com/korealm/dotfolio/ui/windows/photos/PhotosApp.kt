package com.korealm.dotfolio.ui.windows.photos

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.model.AppId
import com.korealm.dotfolio.model.WindowApp
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.photos
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PhotosApp(
    onClose: () -> Unit
): WindowApp {
    return WindowApp(
        appId = AppId.PHOTOS,
        title = stringResource(Res.string.photos),
        icon = painterResource(Res.drawable.photos),
        defaultSize = DpSize(800.dp, 800.dp),
        titleBar = { PhotosAppTitleBar(onClose) },
        content = { PhotosAppWindowContent() }
    )
}