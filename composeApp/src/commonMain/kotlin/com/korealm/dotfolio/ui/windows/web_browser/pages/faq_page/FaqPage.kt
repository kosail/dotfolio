package com.korealm.dotfolio.ui.windows.web_browser.pages.faq_page

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
fun FaqPage(
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
            modifier = modifier
                .fillMaxSize()
        ){
            // Ask box
            item {
                AskBox(
                    font = font,
                    modifier = Modifier
                )
            }

            // Question one
            item {
                Post(
                    postHeader = {
                        PostHeader(
                            specialTitle = stringResource(Res.string.web_browser_faq_question),
                            date = pluralStringResource(Res.plurals.web_browser_months_ago, 1, 1),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postBody = {
                        PostBody(
                            bodyHeader = stringResource(Res.string.web_browser_faq_question_one),
                            text = stringResource(Res.string.web_browser_faq_question_one_reply).trimIndent(),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postFooter = {
                        PostFooter(
                            numberOfReblogs = "7,003",
                            font = font,
                            tags = arrayOf(
                                Res.string.web_browser_faq_question_one_tag_one,
                                Res.string.web_browser_faq_question_one_tag_two,
                                Res.string.web_browser_faq_question_one_tag_three
                            )
                        )
                    },
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            // Question two
            item {
                Post(
                    postHeader = {
                        PostHeader(
                            specialTitle = stringResource(Res.string.web_browser_faq_question),
                            date = pluralStringResource(Res.plurals.web_browser_months_ago, 2, 2),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postBody = {
                        PostBody(
                            bodyHeader = stringResource(Res.string.web_browser_faq_question_two),
                            text = stringResource(Res.string.web_browser_faq_question_two_reply).trimIndent(),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postFooter = {
                        PostFooter(
                            numberOfReblogs = "119,111",
                            font = font,
                            tags = arrayOf(
                                Res.string.web_browser_faq_question_two_tag_one,
                                Res.string.web_browser_faq_question_two_tag_two,
                                Res.string.web_browser_faq_question_two_tag_three
                            )
                        )
                    },
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            // Question three
            item {
                Post(
                    postHeader = {
                        PostHeader(
                            specialTitle = stringResource(Res.string.web_browser_faq_question),
                            date = pluralStringResource(Res.plurals.web_browser_months_ago, 4, 4),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postBody = {
                        PostBody(
                            images = mapOf(
                                Res.drawable.baleada to Res.string.web_browser_faq_question_three_tag_one
                            ),
                            bodyHeader = stringResource(Res.string.web_browser_faq_question_three),
                            text = stringResource(Res.string.web_browser_faq_question_three_reply).trimIndent(),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postFooter = {
                        PostFooter(
                            numberOfReblogs = "50,107",
                            font = font,
                            tags = arrayOf(
                                Res.string.web_browser_faq_question_three_tag_one,
                                Res.string.web_browser_faq_question_three_tag_two,
                                Res.string.web_browser_faq_question_three_tag_three
                            )
                        )
                    },
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            // Last question, the fourth one
            item {
                Post(
                    postHeader = {
                        PostHeader(
                            specialTitle = stringResource(Res.string.web_browser_faq_question),
                            date = pluralStringResource(Res.plurals.web_browser_months_ago, 11, 11),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postBody = {
                        PostBody(
                            bodyHeader = stringResource(Res.string.web_browser_faq_question_four),
                            text = stringResource(Res.string.web_browser_faq_question_four_reply).trimIndent(),
                            font = font,
                            modifier = Modifier
                        )
                    },
                    postFooter = {
                        PostFooter(
                            numberOfReblogs = "50,107",
                            font = font,
                            tags = arrayOf(
                                Res.string.web_browser_faq_question_four_tag_one,
                                Res.string.web_browser_faq_question_four_tag_two,
                                Res.string.web_browser_faq_question_four_tag_three,
                                Res.string.web_browser_faq_question_four_tag_four
                            )
                        )
                    },
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }
        }

        CustomVerticalScrollbar(
            scrollState = lazyListState,
            modifier = Modifier
                .align(Alignment.CenterEnd)
        )
    }
}



