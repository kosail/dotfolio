package com.korealm.dotfolio.ui.windows.file_explorer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import com.korealm.dotfolio.utils.UrlBar
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FileExplorerToolbar(
    title: StringResource,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier.fillMaxSize()
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
                    modifier = Modifier.size(18.dp)
                )

                Spacer(Modifier.width(20.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.arrow_right_symbolic,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    modifier = Modifier.size(18.dp)
                )

                Spacer(Modifier.width(20.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.arrow_up_symbolic,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(Modifier.width(20.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.reload_all_tabs_symbolic,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(Modifier.width(20.dp))

                UrlBar(
                    maxWidth = boxWidthDp * 0.50f,
                    modifier = Modifier
                ) {
                    SimpleSymbolicIconButton(
                        icon = Res.drawable.computer,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(16.dp))

                    SimpleSymbolicIconButton(
                        icon = Res.drawable.simple_arrow_right_symbolic,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(16.dp))

                    Text(
                        text = stringResource(title),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp,
                        modifier = Modifier
                    )
                }

                Spacer(Modifier.width(16.dp))

                UrlBar(
                    maxWidth = boxWidthDp * 0.30f,
                    addGlow = false,
                    modifier = Modifier
                ) {
                    SimpleSymbolicIconButton(
                        icon = Res.drawable.searching_symbolic,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(16.dp))

                    Text(
                        text = stringResource(Res.string.settings__search_bar_content),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        fontSize = 16.sp,
                        modifier = Modifier
                    )
                }
            }
        }

        Box(
            modifier = Modifier.align(Alignment.Center)
        ) {
            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                modifier = Modifier.padding(top = 10.dp)
                )
        }

        // Toolbar actions. The map below holds the icon resource and their size, since they have different proportions
        val actionIcons = mapOf(
            Res.drawable.cut_symbolic to 24,
            Res.drawable.copy_symbolic to 26,
            Res.drawable.paste_symbolic to 26,
            Res.drawable.rename_symbolic to 26,
            Res.drawable.share_symbolic to 22,
            Res.drawable.trash_symbolic to 20,
        )
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 16.dp, top = 8.dp)
            ) {
                ToolbarButton(
                    icon = Res.drawable.computer,
                    text = Res.string.file_explorer_toolbar_new,
                    isDisabled = true,
                    isDropdown = true,
                    modifier = Modifier
                )

                VerticalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .05f),
                    modifier = Modifier
                        .height(20.dp)
                        .padding(horizontal = 26.dp)
                )

                actionIcons.forEach { (icon, size) ->
                    SimpleSymbolicIconButton(
                        icon = icon,
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                        modifier = Modifier.size(size.dp),
                    )

                    Spacer(Modifier.width(24.dp))
                }

                Spacer(Modifier.width(12.dp))

                ToolbarButton(
                    icon = Res.drawable.sort_symbolic,
                    text = Res.string.file_explorer_toolbar_sort,
                    isDropdown = true,
                    modifier = Modifier
                )

                Spacer(Modifier.width(24.dp))

                ToolbarButton(
                    icon = Res.drawable.ui_menu_symbolic,
                    text = Res.string.file_explorer_toolbar_view,
                    isDropdown = true,
                    modifier = Modifier
                )

                Spacer(Modifier.width(24.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.dot_menu_symbolic,
                    modifier = Modifier.size(26.dp)
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                ToolbarButton(
                    icon = Res.drawable.sidebar_left_symbolic,
                    text = Res.string.file_explorer_toolbar_details,
                    modifier = Modifier.padding(bottom = 8.dp, end = 24.dp)
                )
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun ToolbarButton(
    icon: DrawableResource,
    text: StringResource,
    isDisabled: Boolean = false,
    isDropdown: Boolean = false,
    modifier: Modifier = Modifier
) {
    val color = if (isDisabled) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f) else MaterialTheme.colorScheme.onSurface

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        SimpleSymbolicIconButton(
            icon = icon,
            tint = color,
            modifier = Modifier.size(20.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = stringResource(text),
            color = color,
            fontSize = 16.sp,
            modifier = Modifier
        )

        if (isDropdown) {
            Spacer(Modifier.width(4.dp))

            SimpleSymbolicIconButton(
                icon = Res.drawable.simple_arrow_down_symbolic,
                tint = color,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}