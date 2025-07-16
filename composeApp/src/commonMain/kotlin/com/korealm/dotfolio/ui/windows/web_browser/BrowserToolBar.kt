package com.korealm.dotfolio.ui.windows.web_browser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.arrow1_left_symbolic
import dotfolio.composeapp.generated.resources.arrow1_right_symbolic
import dotfolio.composeapp.generated.resources.dot_menu_symbolic
import dotfolio.composeapp.generated.resources.extensions_symbolic
import dotfolio.composeapp.generated.resources.favorite_symbolic
import dotfolio.composeapp.generated.resources.github_symbolic
import dotfolio.composeapp.generated.resources.history
import dotfolio.composeapp.generated.resources.reload_all_tabs_symbolic
import dotfolio.composeapp.generated.resources.searching_symbolic
import dotfolio.composeapp.generated.resources.share_symbolic
import dotfolio.composeapp.generated.resources.user_account
import dotfolio.composeapp.generated.resources.web_browser_github
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.skia.Surface

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BrowserToolBar(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints (
        modifier = modifier
            .fillMaxSize()
    ) {
        // Main bar
        BoxWithConstraints (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            val boxWidth = constraints.maxWidth
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(boxWidth.dp)
                    .padding(top = 15.dp, start = 25.dp, end = 25.dp)
            ) {
                SimpleSymbolicIconButton(
                    icon = Res.drawable.arrow1_left_symbolic,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    modifier = Modifier.size(27.dp)
                )

                Spacer(Modifier.width(20.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.arrow1_right_symbolic,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    modifier = Modifier.size(27.dp)
                )

                Spacer(Modifier.width(20.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.reload_all_tabs_symbolic,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(Modifier.width(20.dp))

                var isHover by remember { mutableStateOf(false) }
                Surface (
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .width((boxWidth * 0.5).dp)
                        .height(40.dp)
                        .onPointerEvent(PointerEventType.Enter) { isHover = true }
                        .onPointerEvent(PointerEventType.Exit) { isHover = false }
                        .then(
                            if (isHover) {
                                modifier.border(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.7f), RoundedCornerShape(6.dp))
                            } else {
                                Modifier
                            }
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Spacer(Modifier.width(16.dp))
                        SimpleSymbolicIconButton(
                            icon = Res.drawable.searching_symbolic,
                            modifier = Modifier.size(26.dp)
                        )
                        Spacer(Modifier.width(16.dp))

                        Text(
                            text = "http://localhost:8080/",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 17.sp,
                            modifier = Modifier
                        )
                    }
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

                Image(
                    painter = painterResource(Res.drawable.user_account),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(35.dp)
                )

                Spacer(Modifier.width(22.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.dot_menu_symbolic,
                    modifier = Modifier.size(26.dp)
                )

                Spacer(Modifier.width(22.dp))
            }
        }

        // Bookmarks bar
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, bottom = 11.dp)
            ) {
                SimpleSymbolicIconButton(
                    icon = Res.drawable.github_symbolic,
                    modifier = Modifier.size(20.dp),
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = stringResource(Res.string.web_browser_github),
                    fontSize = 15.sp
                )
            }
        }
    }
}