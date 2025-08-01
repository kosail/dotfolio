package com.korealm.dotfolio.ui.windows.web_browser.pages.projects_page

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.windows.web_browser.pages.general.PostText
import com.korealm.dotfolio.ui.windows.web_browser.pages.general.SimpleGitHubButton
import com.korealm.dotfolio.ui.windows.web_browser.pages.general.SlightDivider
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostBody
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun BlanketContent(
    font: FontFamily,
    modifier: Modifier = Modifier
) {
    Column {
        PostText(
            text = stringResource(Res.string.web_browser_projects_blanket_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            font = font,
            modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 17.dp)
        )

        SlightDivider(
            orientation = Orientation.Horizontal,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        )

        PostBody(
            text = stringResource(Res.string.web_browser_projects_blanket_content).trimIndent(),
            font = font,
        )

        Spacer(Modifier.height(16.dp))

        SimpleGitHubButton(
            appName = stringResource(Res.string.web_browser_projects_blanket),
            url = stringResource(Res.string.web_browser_projects_blanket_link),
            font = font,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp)
        )
    }
}