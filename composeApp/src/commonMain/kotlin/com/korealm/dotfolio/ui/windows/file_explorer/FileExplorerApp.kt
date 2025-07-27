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
        Pair(Res.drawable.java_logo, Res.string.file_explorer_java), // Java
        Pair(Res.drawable.kotlin_logo, Res.string.file_explorer_kotlin), // Kotlin
        Pair(Res.drawable.compose_logo, Res.string.file_explorer_compose), // Compose
        Pair(Res.drawable.csharp_logo, Res.string.file_explorer_csharp), // C#
        Pair(Res.drawable.html5_logo, Res.string.file_explorer_html), // HTML
        Pair(Res.drawable.css_logo, Res.string.file_explorer_css), // CSS
        Pair(Res.drawable.js_logo, Res.string.file_explorer_js), // JS
        Pair(Res.drawable.oracle_logo, Res.string.file_explorer_oracle), // Oracle
        Pair(Res.drawable.docker_logo, Res.string.file_explorer_docker), // Docker
        Pair(Res.drawable.linux_logo, Res.string.file_explorer_linux), // Linux
        Pair(Res.drawable.wordpress_logo, Res.string.file_explorer_wordpress) // WordPress
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