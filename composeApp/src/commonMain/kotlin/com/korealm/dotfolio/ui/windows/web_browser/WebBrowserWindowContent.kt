package com.korealm.dotfolio.ui.windows.web_browser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.state.AppThemeState
import com.korealm.dotfolio.ui.windows.web_browser.pages.Index

@Composable
fun WebBrowserWindowContent(
    themeState: AppThemeState,
    modifier: Modifier = Modifier,
) {
    Box(
        propagateMinConstraints = true,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                propagateMinConstraints = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            ) { BrowserToolbar() }

            Box(
                propagateMinConstraints = true,
                modifier = Modifier.fillMaxSize()
            ) { Index(themeState = themeState) }
        }
    }
}