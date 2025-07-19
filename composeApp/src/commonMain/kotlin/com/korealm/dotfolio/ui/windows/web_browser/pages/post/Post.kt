package com.korealm.dotfolio.ui.windows.web_browser.pages.post

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.ui.windows.web_browser.pages.SlightDivider

@Composable
fun Post(
    postHeader: @Composable () -> Unit,
    postBody: @Composable () -> Unit,
    postFooter: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {

    Box (
        modifier = modifier
            .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)),
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            postHeader()

            SlightDivider(orientation = Orientation.Horizontal)

            postBody()

            SlightDivider(orientation = Orientation.Horizontal)

            postFooter()
        }
    }
}