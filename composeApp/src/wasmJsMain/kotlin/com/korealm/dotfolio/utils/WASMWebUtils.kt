package com.korealm.dotfolio.utils

import org.w3c.dom.HTMLAnchorElement

@JsName("openInNewTab")
actual fun openInNewTab(url: String) {
    // Creates an <a> tag element but only in memory. It's not attached to the DOM.
    val a = kotlinx.browser.document.createElement("a") as HTMLAnchorElement
    a.href = url
    a.target = "_blank"
    a.rel = "noopener noreferrer"
    a.click()
}