package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.byebye_banner
import dotfolio.composeapp.generated.resources.web_browser_contact_msg
import dotfolio.composeapp.generated.resources.web_browser_faq_askbox_recipient
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ContactPage(
    font: FontFamily,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(50.dp)
            .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = .1f))
    ) {
        Box(
            modifier = Modifier.height(290.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.byebye_banner),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
            )
        }

        val fullMsg = "${ stringResource(Res.string.web_browser_contact_msg, stringResource(Res.string.web_browser_faq_askbox_recipient)).trimIndent() }"
        Text(
            text = fullMsg,
            fontFamily = font,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(25.dp)
        )
    }
}