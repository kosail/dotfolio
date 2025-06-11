package com.korealm.dotfolio

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.painterResource
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.favicon

fun main() = application {
    val icon = painterResource(Res.drawable.favicon)

    Window(
        onCloseRequest = ::exitApplication,
        title = "dotfolio",
        icon = icon,
    ) {
        App()
    }
}