package com.korealm.dotfolio.ui.windows.web_browser.pages.projects_page

import androidx.compose.foundation.gestures.Orientation
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
import com.korealm.dotfolio.ui.windows.web_browser.pages.general.SimpleGitHubButton
import com.korealm.dotfolio.ui.windows.web_browser.pages.general.SlightDivider
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostBody
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun DotfolioContent(
    font: FontFamily,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = stringResource(Res.string.web_browser_projects_dotfolio_title),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            fontFamily = font,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 17.dp)
        )

        SlightDivider(
            orientation = Orientation.Horizontal,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        )

        PostBody(
            text = stringResource(Res.string.web_browser_projects_dotfolio_content).trimIndent(),
            font = font
        )

        Spacer(Modifier.height(16.dp))

        SimpleGitHubButton(
            appName = stringResource(Res.string.web_browser_projects_dotfolio),
            url = stringResource(Res.string.web_browser_projects_aria_link),
            font = font,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp)
        )
    }
}