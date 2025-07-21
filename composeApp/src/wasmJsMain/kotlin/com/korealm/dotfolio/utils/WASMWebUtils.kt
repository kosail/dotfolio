package com.korealm.dotfolio.utils

import org.w3c.dom.HTMLAnchorElement
import kotlinx.browser.document
import org.w3c.dom.get

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