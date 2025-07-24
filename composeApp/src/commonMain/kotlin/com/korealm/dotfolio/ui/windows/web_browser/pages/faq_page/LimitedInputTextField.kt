package com.korealm.dotfolio.ui.windows.web_browser.pages.faq_page

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.web_browser_faq_askbox_max_chars
import org.jetbrains.compose.resources.stringResource

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