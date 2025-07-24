package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.state.AppThemeState
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.AboutMePost
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.SimpleBarDecoration
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutMePage(
    font: FontFamily,
    themeState: AppThemeState,
    modifier: Modifier = Modifier
) {
    SimpleBarDecoration()

    LazyColumn (
        state = rememberLazyListState(),
        contentPadding = PaddingValues(start = 25.dp, end = 25.dp, top = 14.dp, bottom = 4.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        item {
            AboutMePost(
                icon = Res.drawable.lucide_sprout,
                title = Res.string.web_browser_aboutme_first_title,
                font = font,
                themeState = themeState
            ) {
                Text(
                    text = stringResource(Res.string.web_browser_aboutme_first_content).trimIndent(),
                    fontFamily = font,
                    fontSize = 16.sp,
                    modifier = Modifier
                )
            }
        }

        item {
            AboutMePost(
                icon = Res.drawable.lucide_heart_plus,
                title = Res.string.web_browser_aboutme_second_title,
                font = font,
                themeState = themeState
            ) {
                Column {
                    Text(
                        text = stringResource(Res.string.web_browser_aboutme_second_content_one).trimIndent(),
                        fontFamily = font,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = stringResource(Res.string.web_browser_aboutme_second_content_two),
                        fontFamily = font,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                    )
                }
            }
        }

        item {
            AboutMePost(
                icon = Res.drawable.lucide_compass,
                title = Res.string.web_browser_aboutme_third_title,
                font = font,
                themeState = themeState
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row (
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(horizontal = 5.dp),
                    ) {
                        SimpleSymbolicIconButton(
                            icon = Res.drawable.lucide_signature,
                            modifier = Modifier.size(24.dp),
                        )

                        Spacer(Modifier.width(10.dp))

                        Text(
                            text = stringResource(Res.string.web_browser_aboutme_third_content_personal),
                            fontFamily = font,
                            fontSize = 16.sp,
                            modifier = Modifier
                        )
                    }

                    Row (
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(horizontal = 5.dp),
                    ) {
                        SimpleSymbolicIconButton(
                            icon = Res.drawable.lucide_briefcase_business,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(Modifier.width(10.dp))

                        Text(
                            text = stringResource(Res.string.web_browser_aboutme_third_content_professional),
                            fontFamily = font,
                            fontSize = 16.sp,
                            modifier = Modifier
                        )
                    }
                }

                SlightDivider(
                    orientation = Orientation.Horizontal,
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(Res.string.web_browser_aboutme_third_content_final),
                        fontFamily = font,
                        fontSize = 16.sp,
                        modifier = Modifier
                    )
                }
            }
        }

        item {
            AboutMePost(
                icon = Res.drawable.lucide_rabbit,
                title = Res.string.web_browser_aboutme_last_title,
                font = font,
                themeState = themeState
            ) {
                Text(
                    text = stringResource(Res.string.web_browser_aboutme_last_content).trimIndent(),
                    fontFamily = font,
                    fontSize = 16.sp,
                    modifier = Modifier
                )
            }
        }
    }

}

