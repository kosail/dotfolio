package com.korealm.dotfolio.ui.windows.notepad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import com.korealm.dotfolio.ui.windows.web_browser.pages.general.CustomVerticalScrollbar
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NotepadWindowContent(
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column {
            // Menu bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            ) {
                TopBarMenuItem(Res.string.file)
                TopBarMenuItem(Res.string.edit)
                TopBarMenuItem(Res.string.view)


                Spacer(modifier = Modifier.fillMaxWidth().weight(1f))

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(25.dp)
                        .padding(top = 5.dp, end = 10.dp)
                ) {
                    SimpleSymbolicIconButton(icon = Res.drawable.settings_symbolic)
                }
            }

            // Content
            LazyColumn(
                state = lazyListState,
                modifier = modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                item {
                    Text(
                        text = stringResource(Res.string.main_content_message).trimIndent(),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Normal,
                        fontSize = 17.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(15.dp)
                    )
                }
            }

            // Bottom utility bar
            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            ) {
                Spacer(modifier = Modifier.fillMaxWidth().weight(1f))

                BottomBarSpacer()
                BottomBarStyledText(Res.string._100_percent)

                BottomBarSpacer()
                BottomBarStyledText(Res.string.windows_crlf)

                BottomBarSpacer()
                BottomBarStyledText(Res.string.utf8)

                Spacer(modifier = Modifier.size(60.dp))
            }
        }

        CustomVerticalScrollbar(
            scrollState = lazyListState,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(top = 40.dp, bottom = 35.dp)
        )
    }
}

@Composable
fun TopBarMenuItem(
    stringRes: StringResource,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(stringRes),
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier.padding(horizontal = 15.dp, vertical = 5.dp)
    )
}

@Composable
fun BottomBarSpacer(modifier: Modifier = Modifier) {
    VerticalDivider(
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 8.dp)
    )
}

@Composable
fun BottomBarStyledText(
    stringRes: StringResource,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(stringRes),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        modifier = modifier.padding(vertical = 5.dp)
    )
}