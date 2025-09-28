package com.korealm.dotfolio.ui.windows.file_explorer

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.model.AppId
import com.korealm.dotfolio.model.WindowApp
import com.korealm.dotfolio.state.AppThemeState
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FileManagerApp(
    themeState: AppThemeState,
    onClose: () -> Unit
): WindowApp {
    val elements = listOf(
        // JVM
        Pair(Res.drawable.java_logo, Res.string.file_explorer_java),
        Pair(Res.drawable.kotlin_logo, Res.string.file_explorer_kotlin),
        Pair(Res.drawable.compose_logo, Res.string.file_explorer_compose),
        Pair(Res.drawable.spring_boot_logo, Res.string.file_explorer_spring_boot),
        // Web
        Pair(Res.drawable.html5_logo, Res.string.file_explorer_html),
        Pair(Res.drawable.css_logo, Res.string.file_explorer_css),
        Pair(Res.drawable.js_logo, Res.string.file_explorer_js),
        Pair(Res.drawable.Typescript_logo_2020, Res.string.file_explorer_ts),
        Pair(Res.drawable.react_logo, Res.string.file_explorer_react),
        // DB
        Pair(Res.drawable.oracle_logo, Res.string.file_explorer_oracle),
        Pair(Res.drawable.ms_sql_server_2025, Res.string.file_explorer_sql_server),

        // Other
        Pair(Res.drawable.docker_logo, Res.string.file_explorer_docker),
        Pair(Res.drawable.linux_logo, Res.string.file_explorer_linux),
        Pair(Res.drawable.wordpress_logo, Res.string.file_explorer_wordpress),
//        Pair(Res.drawable.csharp_logo, Res.string.file_explorer_csharp),
    )

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
                title = Res.string.tech_stack,
                elements = elements
            )
        }
    )
}