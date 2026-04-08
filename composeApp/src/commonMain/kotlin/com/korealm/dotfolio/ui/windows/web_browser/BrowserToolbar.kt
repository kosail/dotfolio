package com.korealm.dotfolio.ui.windows.web_browser

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import com.korealm.dotfolio.ui.windows.web_browser.pages.general.PageBookmarkButton
import com.korealm.dotfolio.utils.RoundedPicture
import com.korealm.dotfolio.utils.UrlBar
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun BrowserToolbar(
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
            .fillMaxSize()
    ) {
        // Main bar
        BoxWithConstraints (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            val boxWidthDp = constraints.maxWidth.dp
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(boxWidthDp)
                    .padding(top = 15.dp, start = 25.dp, end = 25.dp)
            ) {
                SimpleSymbolicIconButton(
                    icon = Res.drawable.arrow_left_symbolic,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    modifier = Modifier.size(27.dp)
                )

                Spacer(Modifier.width(20.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.arrow_right_symbolic,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    modifier = Modifier.size(27.dp)
                )

                Spacer(Modifier.width(20.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.reload_all_tabs_symbolic,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(Modifier.width(20.dp))

                UrlBar(
                    maxWidth = boxWidthDp * 0.62f,
                    modifier = Modifier
                ) {
                    Spacer(Modifier.width(16.dp))
                    SimpleSymbolicIconButton(
                        icon = Res.drawable.searching_symbolic,
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(Modifier.width(16.dp))

                    Text(
                        text = stringResource(Res.string.web_browser_website),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 17.sp,
                        modifier = Modifier
                    )
                }

                Spacer(Modifier.width(20.dp))


                SimpleSymbolicIconButton(
                    icon = Res.drawable.extensions_symbolic,
                    modifier = Modifier.size(22.dp)
                )

                VerticalDivider(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                    thickness = 2.dp,
                    modifier = Modifier
                        .height(19.dp)
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(20.dp))
                )

                SimpleSymbolicIconButton(
                    icon = Res.drawable.history,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(Modifier.width(22.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.favorite_symbolic,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(Modifier.width(22.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.share_symbolic,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(Modifier.width(22.dp))

                RoundedPicture(Res.drawable.user_account)

                Spacer(Modifier.width(22.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.dot_menu_symbolic,
                    modifier = Modifier.size(26.dp)
                )

                Spacer(Modifier.width(22.dp))
            }
        }

        // Bookmarks bar
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            PageBookmarkButton(
                url = "https://github.com/kosail",
                iconRes = Res.drawable.github_symbolic,
                titleRes = Res.string.web_browser_github,
            )

            PageBookmarkButton(
                url = "https://codeberg.com/kosail",
                iconRes = Res.drawable.codeberg_symbolic,
                titleRes = Res.string.web_browser_codeberg,
            )
        }
    }
}