package com.korealm.dotfolio.ui.windows.web_browser.pages.post

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PostFooter(
    numberOfReblogs: String,
    tags: Array<StringResource>,
    font: FontFamily,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(horizontal = 24.dp, vertical = 17.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "$numberOfReblogs ${stringResource(Res.string.web_browser_reblogs)}",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
                fontFamily = font,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(end = 5.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                SimpleSymbolicIconButton(
                    icon = Res.drawable.lucide_heart
                )

                Spacer(Modifier.width(10.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.lucide_repeat_2,
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        FlowRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            for (tag in tags) {
                Surface (
                    color = Color.Transparent,
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)),
                    modifier = Modifier.padding(end = 10.dp, bottom = 10.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        SimpleSymbolicIconButton(
                            icon = Res.drawable.lucide_tag
                        )

                        Spacer(Modifier.width(4.dp))

                        Text(
                            text = stringResource(tag),
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                            fontFamily = font,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start,
                            textDecoration = TextDecoration.Underline,
                            modifier = modifier
                        )
                    }
                }
            }
        }
    }
}