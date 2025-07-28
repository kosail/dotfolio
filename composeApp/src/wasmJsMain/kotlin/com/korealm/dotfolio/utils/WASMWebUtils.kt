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

fun isBigScreen(): Boolean {
    return getWindowWidth() > 1300 && getWindowHeight() > 600
}

@JsFun("(function() {return navigator.userAgent })")
external fun getUserAgent(): String

fun isMobile(): Boolean {
    val agent = getUserAgent().lowercase()
    return agent.let { ua ->
        ua.contains("android") || ua.contains("iphone") || ua.contains("ipad") || ua.contains("mobile")
    }
}