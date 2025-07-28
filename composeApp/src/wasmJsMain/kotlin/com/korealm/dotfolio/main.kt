package com.korealm.dotfolio

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.korealm.dotfolio.utils.isBigScreen
import com.korealm.dotfolio.utils.isDesktopBigScreen
import com.korealm.dotfolio.utils.isMobile
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        if (! isMobile()) {
            if (isDesktopBigScreen()) App() else LimitedDotfolio()
        } else {
            if (isBigScreen()) LimitedDotfolio() else UnsupportedDevice()
        }
    }
}