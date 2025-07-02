package com.korealm.dotfolio

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.w3c.dom.HTMLAudioElement

@OptIn(
    ExperimentalComposeUiApi::class,
//    ExperimentalResourceApi::class // TODO: I may require it for font loading. I'll check on that later
)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }
}