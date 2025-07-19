package com.korealm.dotfolio.ui.windows.web_browser

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.model.AppId
import com.korealm.dotfolio.model.WindowApp
import com.korealm.dotfolio.state.AppThemeState
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.web_browser
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WebBrowserApp (
    themeState: AppThemeState,
    onClose: () -> Unit
): WindowApp {
    return WindowApp(
        appId = AppId.WEB_BROWSER,
        title = stringResource(Res.string.web_browser),
        icon = painterResource(Res.drawable.web_browser),
        defaultSize = DpSize(1300.dp, 900.dp),
        titleBar = {
            WebBrowserTitleBar(
                themeState = themeState,
                onClose = onClose
            )
        },
        content = { WebBrowserWindowContent() }
    )
}