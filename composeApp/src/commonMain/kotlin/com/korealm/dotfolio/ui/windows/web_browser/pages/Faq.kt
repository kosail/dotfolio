package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
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
        }

        CustomVerticalScrollbar(
            scrollState = lazyListState,
            modifier = Modifier
                .align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun AskBox(
    font: FontFamily,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        var text by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Text(
                text = stringResource(Res.string.web_browser_faq_askbox_title),
                fontFamily = font,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 6.dp)
            )

            SimpleSymbolicIconButton(
                icon = Res.drawable.lucide_message_circle_heart,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(30.dp)
            )
        }

        LimitedInputTextField(
            limit = 180,
            value = text,
            onValueChange = { text = it },
            font = font,
            placeholder = stringResource(Res.string.web_browser_faq_askbox_msg).trimIndent(),
            textFieldModifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(bottom = 10.dp)
        )

        val recipient = stringResource(Res.string.web_browser_faq_askbox_recipient)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.End)
        ) {
            LimitedInputTextField(
                limit = 30,
                showLimit = false,
                value = name,
                onValueChange = { name = it },
                font = font,
                placeholder = stringResource(Res.string.web_browser_faq_askbox_sender),
                modifier = Modifier.padding(end = 20.dp),
                textFieldModifier = Modifier.size(width= 250.dp, height = 50.dp)
            )

            Button(
                onClick = {
                    if (!text.isNullOrBlank() && !name.isNullOrBlank()) {
                        mailTrigger(
                            recipient = recipient,
                            subject = "Dotfolio contact: $name sent a message",
                            body = text,
                        )
                    }
                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier.height(42.dp)
            ) {
                Text(
                    text = stringResource(Res.string.web_browser_faq_askbox_button),
                    fontFamily = font,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun LimitedInputTextField(
    limit: Int,
    showLimit: Boolean = true,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    font: FontFamily,
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier
) {
    var charCount by remember { mutableStateOf(0) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        TextField(
            value = value,
            onValueChange = {
                if (it.length <= limit) {
                    onValueChange(it)
                    charCount = it.length
                }
            },
            placeholder = {
                Text(
                    text = placeholder,
                    fontFamily = font,
                    fontSize = 15.sp,
                )
            },
            shape = RoundedCornerShape(4.dp),
            maxLines = 5,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f),
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.05f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                selectionColors = TextSelectionColors(
                    handleColor = MaterialTheme.colorScheme.onBackground,
                    backgroundColor = MaterialTheme.colorScheme.secondary
                )
            ),
            textStyle = LocalTextStyle.current.copy(
                fontFamily = font,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
            ),
            modifier = textFieldModifier
                .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f), RoundedCornerShape(4.dp))
        )

        if (showLimit) {
            Text(
                text = "${stringResource(Res.string.web_browser_faq_askbox_max_chars)} $charCount/$limit",
                fontFamily = font,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 14.dp, end = 10.dp)
            )
        }
    }
}