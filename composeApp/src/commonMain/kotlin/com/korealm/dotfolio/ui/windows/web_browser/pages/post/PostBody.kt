package com.korealm.dotfolio.ui.windows.web_browser.pages.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.web_browser_reblogs
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.skia.Surface

@Composable
fun PostBody(
    bodyHeader: String? = null,
    images: Map<DrawableResource, StringResource>? = null,
    text: String? = null,
    font: FontFamily,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        if (! bodyHeader.isNullOrBlank()) {
            Surface(
                color = Color(0xEEDEB3C6), // Cute pink. Hardcoded because I need it to be static. Does not change
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = bodyHeader,
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.8f),
                    fontFamily = font,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    modifier = modifier.padding(20.dp)
                )
            }
        }

        if (! images.isNullOrEmpty()) {
            for ((image, description) in images) {
                Image(
                    painter = painterResource(image),
                    contentDescription = stringResource(description),
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                )
            }
        }

        if (! text.isNullOrBlank()) {
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 17.dp)
            ) {
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
                    fontFamily = font,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Start,
                    modifier = modifier.padding(end = 5.dp)
                )
            }
        }
    }
}