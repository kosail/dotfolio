package com.korealm.dotfolio.utils

actual fun openInNewTab(url: String) {
    println("URL received: $url")
}

actual fun encodeText(text: String): String {
    val encodedText = java.net.URLEncoder.encode(text, "utf-8")
    println("Text received: $text\nText encoded: $encodedText")
    return encodedText
}