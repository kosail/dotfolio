package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily

@Composable
fun Foo(
    defaultFont: FontFamily,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .scrollable(rememberScrollState(0), Orientation.Vertical)
    ) {}
}