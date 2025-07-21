package com.korealm.dotfolio.ui.windows.web_browser.pages.post

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import com.korealm.dotfolio.utils.RoundedPicture
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun PostHeader(
    specialTitle: String? = null, // To use this composable as "updated profile pic" or some announcement like that
    rebloggedFrom: String? = null,
    isPinned: Boolean = false,
    date: String,
    font: FontFamily,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        RoundedPicture(
            painter = Res.drawable.kosaildev_profile,
            modifier = Modifier.size(50.dp)
        )

        Spacer(Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                modifier = Modifier.padding(end = 20.dp)
            ) {
                Text(
                    text = stringResource(Res.string.username),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = font,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Start,
                    modifier = modifier
                        .padding(end = 5.dp)
                )

                if (specialTitle.isNullOrBlank()) {
                    PostHeaderBlogText(
                        rebloggedFrom = rebloggedFrom,
                        font = font
                    )
                } else {
                    Text(
                        text = specialTitle,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
                        fontFamily = font,
                        fontWeight = FontWeight.Normal,
                        fontSize = 17.sp,
                        textAlign = TextAlign.Start,
                        modifier = modifier
                    )
                }

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                Text(
                    text = date,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    fontFamily = font,
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Start,
                    modifier = modifier
                )

                if (isPinned) {
                    Text(
                        text = "•",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        fontFamily = font,
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        modifier = modifier.padding(horizontal = 5.dp)
                    )

                    SimpleSymbolicIconButton(
                        icon = Res.drawable.lucide_bookmark,
                        tint = MaterialTheme.colorScheme.secondary,
                    )

                    Text(
                        text = stringResource(Res.string.web_browser_pinned_post),
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = font,
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        modifier = modifier.padding(horizontal = 10.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun PostHeaderBlogText(
    rebloggedFrom: String? = null,
    font: FontFamily,
    modifier: Modifier = Modifier
) {
    if (rebloggedFrom == null) {
        Text(
            text = stringResource(Res.string.web_browser_blogged),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            fontFamily = font,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            textAlign = TextAlign.Start,
            modifier = modifier
        )
    } else {
        Text(
            text = stringResource(Res.string.web_browser_reblogged),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            fontFamily = font,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            textAlign = TextAlign.Start,
            modifier = modifier.padding(end = 5.dp)
        )

        Text(
            text = rebloggedFrom,
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = font,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            textAlign = TextAlign.Start,
            modifier = modifier
        )
    }
}