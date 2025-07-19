package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.Post
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostFooter
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostHeader
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.web_browser_weeks_ago
import org.jetbrains.compose.resources.pluralStringResource

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
            item {
                Post(
                    postHeader = {
                        PostHeader(
                            date = pluralStringResource(Res.plurals.web_browser_weeks_ago, 1, 1),
                            font = defaultFont,
                            modifier = Modifier
                        )
                    },
                    postBody = {},
                    postFooter = {
                        PostFooter(
                            numberOfReblogs = "115,955",
                            font = defaultFont,
                            tags = arrayOf("joined", "started", "compose")
                        )
                    },
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            item {
                Post(
                    postHeader = {
                        PostHeader(
                            rebloggedFrom = "tanaka",
                            date = pluralStringResource(Res.plurals.web_browser_weeks_ago, 2, 3),
                            font = defaultFont,
                            modifier = Modifier
                        )
                    },
                    postBody = {},
                    postFooter = {
                        PostFooter(
                            numberOfReblogs = "5,935",
                            font = defaultFont,
                            tags = arrayOf("first reblog", "kinda cool huh", "ocarina of time")
                        )
                    },
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