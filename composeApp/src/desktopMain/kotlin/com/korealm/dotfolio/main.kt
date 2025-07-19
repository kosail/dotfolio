package com.korealm.dotfolio

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.favicon
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    val icon = painterResource(Res.drawable.favicon)

    Window(
        onCloseRequest = ::exitApplication,
        title = "dotfolio",
        state = WindowState(size = DpSize(1500.dp, 1000.dp)),
        icon = icon,
    ) {
        App()
    }
}