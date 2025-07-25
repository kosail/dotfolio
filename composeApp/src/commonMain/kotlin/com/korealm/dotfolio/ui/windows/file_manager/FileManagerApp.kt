package com.korealm.dotfolio.ui.windows.file_manager

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.model.AppId
import com.korealm.dotfolio.model.WindowApp
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.file_explorer
import dotfolio.composeapp.generated.resources.file_manager
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FileManagerApp(onClose: () -> Unit): WindowApp {
    return WindowApp(
        appId = AppId.WEB_BROWSER,
        title = stringResource(Res.string.file_explorer),
        icon = painterResource(Res.drawable.file_manager),
        defaultSize = DpSize(1100.dp, 700.dp),
        titleBar = {  },
        content = {  }
    )
}