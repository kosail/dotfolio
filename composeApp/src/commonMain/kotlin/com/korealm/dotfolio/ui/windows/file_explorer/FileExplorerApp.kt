package com.korealm.dotfolio.ui.windows.file_explorer

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.model.AppId
import com.korealm.dotfolio.model.WindowApp
import com.korealm.dotfolio.state.AppThemeState
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.file_explorer
import dotfolio.composeapp.generated.resources.file_manager
import dotfolio.composeapp.generated.resources.folder
import dotfolio.composeapp.generated.resources.tech_stack
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FileManagerApp(
    themeState: AppThemeState,
    onClose: () -> Unit
): WindowApp {
    return WindowApp(
        appId = AppId.FILE_EXPLORER,
        title = stringResource(Res.string.file_explorer),
        icon = painterResource(Res.drawable.file_manager),
        defaultSize = DpSize(1100.dp, 700.dp),
        titleBar = {
            FileExplorerTitleBar(
                onClose = onClose,
                title = Res.string.tech_stack,
                icon = Res.drawable.folder,
                themeState = themeState
            )
        },
        content = {
            FileExplorerWindowContent(
                title = Res.string.tech_stack
            )
        }
    )
}