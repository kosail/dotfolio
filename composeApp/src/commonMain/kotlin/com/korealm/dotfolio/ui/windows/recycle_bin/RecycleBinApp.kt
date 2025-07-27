package com.korealm.dotfolio.ui.windows.recycle_bin

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.model.AppId
import com.korealm.dotfolio.model.WindowApp
import com.korealm.dotfolio.state.AppThemeState
import com.korealm.dotfolio.ui.windows.file_explorer.FileExplorerTitleBar
import com.korealm.dotfolio.ui.windows.file_explorer.FileExplorerWindowContent
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun RecycleBinApp(
    themeState: AppThemeState,
    onClose: () -> Unit
): WindowApp {
    val elements = listOf(
        Pair(Res.drawable.among_us, Res.string.file_explorer_amungos),
        Pair(Res.drawable.discord, Res.string.file_explorer_discord)
    )

    return WindowApp(
        appId = AppId.TRASH,
        title = stringResource(Res.string.trash),
        icon = painterResource(Res.drawable.trash),
        defaultSize = DpSize(1100.dp, 700.dp),
        titleBar = {
            FileExplorerTitleBar(
                onClose = onClose,
                title = Res.string.trash,
                icon = Res.drawable.trash,
                themeState = themeState
            )
        },
        content = {
            FileExplorerWindowContent(
                title = Res.string.trash,
                elements = elements
            )
        }
    )
}