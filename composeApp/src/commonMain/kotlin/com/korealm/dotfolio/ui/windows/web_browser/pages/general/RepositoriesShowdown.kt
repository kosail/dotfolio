package com.korealm.dotfolio.ui.windows.web_browser.pages.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

private enum class RepoType(val stringRes: StringResource, val iconRes: DrawableResource, val platformBaseUrl: StringResource) {
    GITHUB(
        stringRes = Res.string.web_browser_projects_check_on_github,
        iconRes = Res.drawable.github_symbolic,
        platformBaseUrl = Res.string.web_browser_projects_github_base_url
    ),
    CODEBERG(
        stringRes = Res.string.web_browser_projects_check_on_codeberg,
        iconRes = Res.drawable.codeberg_symbolic,
        platformBaseUrl = Res.string.web_browser_projects_codeberg_base_url
    )
}

@Composable
fun RepositoriesShowdown(
    appNameRes: StringResource,
    font: FontFamily,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        for (type in RepoType.entries) {
            val appName = stringResource(appNameRes)

            SimpleUrlButton(
                title = stringResource(type.stringRes, appName),
                url = stringResource(type.platformBaseUrl, appName),
                font = font,
                icon = type.iconRes,
            )
        }
    }
}