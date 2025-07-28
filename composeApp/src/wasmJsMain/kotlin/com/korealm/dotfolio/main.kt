package com.korealm.dotfolio

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.korealm.dotfolio.utils.isBigScreen
import com.korealm.dotfolio.utils.isMobile
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        if (! isMobile()) {
            App()
        } else {
            if (isBigScreen()) LimitedDotfolio() else UnsupportedDevice()
        }
    }
}