package com.korealm.dotfolio.ui.windows.web_browser.pages.post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.korealm.dotfolio.utils.RoundedPicture
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.kosaildev_profile
import dotfolio.composeapp.generated.resources.username
import dotfolio.composeapp.generated.resources.web_browser_blogged
import dotfolio.composeapp.generated.resources.web_browser_reblogged
import org.jetbrains.compose.resources.stringResource

@Composable
fun PostHeader(
    rebloggedFrom: String? = null,
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

            Text(
                text = date,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                fontFamily = font,
                fontWeight = FontWeight.Light,
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
                modifier = modifier
            )
        }

    }
}