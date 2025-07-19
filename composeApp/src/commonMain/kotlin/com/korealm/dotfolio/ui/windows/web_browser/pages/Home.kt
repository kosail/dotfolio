package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun Home(
    defaultFont: FontFamily,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn (
            state = lazyListState,
            contentPadding = PaddingValues(50.dp),
            modifier = modifier
                .fillMaxSize()
        ) {
            items(15) { index ->
                Post(
                    postHeader = {
                        Text(
                            text = "Testing $index",
                            fontFamily = defaultFont,
                            modifier = Modifier.padding(16.dp)
                        )
                    },
                    postContent = {},
                    postFooter = {},
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }
        }

        CustomVerticalScrollbar(
            scrollState = lazyListState,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}