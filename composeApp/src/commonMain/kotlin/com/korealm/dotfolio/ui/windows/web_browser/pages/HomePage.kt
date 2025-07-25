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
import com.korealm.dotfolio.ui.windows.web_browser.pages.general.CustomVerticalScrollbar
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.Post
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostBody
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostFooter
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostHeader
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomePage(
    font: FontFamily,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()

    Box (
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn (
            state = lazyListState,
            contentPadding = PaddingValues(50.dp),
            modifier = modifier.fillMaxSize()
        ) {
            // Welcome post
            item {
                Post(
                    postHeader = {
                        PostHeader(
                            isPinned = true,
                            date = pluralStringResource(Res.plurals.web_browser_months_ago, 2, 2),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postBody = {
                        PostBody(
                            text = stringResource(Res.string.web_browser_post_welcome).trimIndent(),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postFooter = {
                        PostFooter(
                            numberOfReblogs = "1,055",
                            font = font,
                            tags = arrayOf(
                                Res.string.web_browser_post_welcome_tag_one,
                                Res.string.web_browser_post_welcome_tag_two,
                                Res.string.web_browser_post_welcome_tag_three
                            )
                        )
                    },
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            // Post one
            item {
                Post(
                    postHeader = {
                        PostHeader(
                            date = pluralStringResource(Res.plurals.web_browser_weeks_ago, 1, 1),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postBody = {
                        PostBody(
                            images = mapOf(
                                Res.drawable.milumu to Res.string.web_browser_post_one_content_desc
                            ),
                            text = stringResource(Res.string.web_browser_post_one).trimIndent(),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postFooter = {
                        PostFooter(
                            numberOfReblogs = "113,317",
                            font = font,
                            tags = arrayOf(
                                Res.string.web_browser_post_one_tag_one,
                                Res.string.web_browser_post_one_tag_two,
                                Res.string.web_browser_post_one_tag_three,
                                Res.string.web_browser_post_one_tag_four,
                            )
                        )
                    },
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            // Post two
            item {
                Post(
                    postHeader = {
                        PostHeader(
                            rebloggedFrom = stringResource(Res.string.web_browser_post_two_reblogger_account),
                            date = pluralStringResource(Res.plurals.web_browser_weeks_ago, 2, 2),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postBody = {
                        PostBody(
                            images = mapOf(
                                Res.drawable.asano_inio_oyasumi_punpun to Res.string.web_browser_post_two_content_desc
                            ),
                            text = stringResource(Res.string.web_browser_post_two).trimIndent(),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postFooter = {
                        PostFooter(
                            numberOfReblogs = "500,107",
                            font = font,
                            tags = arrayOf(
                                Res.string.web_browser_post_two_tag_one,
                                Res.string.web_browser_post_two_tag_two,
                                Res.string.web_browser_post_two_tag_three
                            )
                        )
                    },
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            // Post three
            item {
                Post(
                    postHeader = {
                        PostHeader(
                            date = pluralStringResource(Res.plurals.web_browser_months_ago, 1, 1),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postBody = {
                        PostBody(
                            images = mapOf(
                                Res.drawable.takayan_ran_drawing_suki_na_hito_ubawanaide to Res.string.web_browser_post_three_content_desc
                            ),
                            text = stringResource(Res.string.web_browser_post_three).trimIndent(),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postFooter = {
                        PostFooter(
                            numberOfReblogs = "1,200,463",
                            font = font,
                            tags = arrayOf(
                                Res.string.web_browser_post_three_tag_one,
                                Res.string.web_browser_post_three_tag_two,
                                Res.string.web_browser_post_three_tag_three,
                            )
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