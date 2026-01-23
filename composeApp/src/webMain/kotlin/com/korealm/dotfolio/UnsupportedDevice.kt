package com.korealm.dotfolio

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.state.rememberAppThemeState
import com.korealm.dotfolio.ui.theme.MicaTheme
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.Post
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostBody
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostFooter
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostHeader
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun UnsupportedDevice() {
    val themeState = rememberAppThemeState()

    MicaTheme(
        darkTheme = themeState.isDarkTheme
    ) {
        val font = FontFamily(
            Font(Res.font.MPLUS1p_Light, FontWeight.Light),
            Font(Res.font.MPLUS1p_Regular, FontWeight.Normal),
            Font(Res.font.MPLUS1p_Medium, FontWeight.SemiBold),
            Font(Res.font.MPLUS1p_Bold, FontWeight.Bold),
        )

        BoxWithConstraints(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            val density = LocalDensity.current
            val maxWidth = with (density) { constraints.maxWidth.toDp() }
            val maxHeight = with (density) { constraints.maxHeight.toDp() }

            // Background wallpaper
            Image(
                painter = painterResource(Res.drawable.tsuki),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )


            Surface(
                shape = RoundedCornerShape(6.dp),
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.85f),
                modifier = Modifier.padding(horizontal = maxWidth * .04f, vertical = maxHeight * .04f)
            ) {
                LazyColumn(
                    contentPadding = PaddingValues( horizontal = maxWidth * .05f, vertical = maxHeight * .045f ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                ) {
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
                                    text = stringResource(Res.string.unsupported_msg),
                                    font = font,
                                    modifier = Modifier
                                )
                            },
                            postFooter = {
                                PostFooter(
                                    numberOfReblogs = "19,317",
                                    font = font,
                                    tags = arrayOf(
                                        Res.string.unsupported_tag_one,
                                        Res.string.unsupported_tag_two
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}