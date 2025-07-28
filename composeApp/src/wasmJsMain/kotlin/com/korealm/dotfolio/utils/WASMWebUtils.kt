package com.korealm.dotfolio.utils

import kotlinx.browser.document
import org.w3c.dom.HTMLAnchorElement

@JsName("openInNewTab")
actual fun openInNewTab(url: String) {
    // Creates an <a> tag element but only in memory. It's not attached to the DOM.
    val a = document.createElement("a") as HTMLAnchorElement
    a.href = url
    a.target = "_blank"
    a.rel = "noopener noreferrer"
    a.click()
}

@JsName("encodeURIComponent")
external fun encodeURIComponent(str: String): String

actual fun encodeText(text: String) : String {
    return encodeURIComponent(text)
}

// WASM target only
@JsFun("(function() { return window.innerWidth; })")
external fun getWindowWidth(): Int

@JsFun("(function() { return window.innerHeight; })")
external fun getWindowHeight(): Int


// Why two functions that do the same, you may ask
// In a nutshell; I had to restrict desktops with resolution of 1366x768, as dotfolio does not play well at all in less than 1920x1080.
// I know. That was one of my huge mistakes creating this project. However, I learned from it.
// So one function is for tablets and mobile and the other to restrict low-res desktop monitors.
fun isBigScreen(): Boolean {
    return getWindowWidth() > 1000 && getWindowHeight() > 500
}

fun isDesktopBigScreen(): Boolean {
    return getWindowWidth() > 1400 && getWindowHeight() > 800
}

@JsFun("(function() {return navigator.userAgent })")
external fun getUserAgent(): String

fun isMobile(): Boolean {
    val agent = getUserAgent().lowercase()
    return agent.let { ua ->
        ua.contains("android") || ua.contains("iphone") || ua.contains("ipad") || ua.contains("mobile")
    }
}