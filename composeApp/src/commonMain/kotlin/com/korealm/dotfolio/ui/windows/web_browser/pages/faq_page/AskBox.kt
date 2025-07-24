package com.korealm.dotfolio.ui.windows.web_browser.pages.faq_page

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import com.korealm.dotfolio.ui.windows.web_browser.pages.general.mailTrigger
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

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