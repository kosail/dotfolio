package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Post(
    postHeader: @Composable () -> Unit,
    postContent: @Composable () -> Unit,
    postFooter: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {

    Box (
        modifier = modifier
            .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)),
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            postHeader()

            SlightDivider(orientation = Orientation.Horizontal, modifier = Modifier.padding(vertical = 10.dp))

            postContent()

            SlightDivider(orientation = Orientation.Horizontal, modifier = Modifier.padding(vertical = 10.dp))

            postFooter()
        }
    }
}
