package com.korealm.dotfolio.ui.windows.file_explorer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FileExplorerSidebar(
    modifier: Modifier = Modifier
) {
    val elementsAtCenter = listOf(
        Pair(Res.drawable.desktop, Res.string.file_explorer_desktop),
        Pair(Res.drawable.downloads_folder, Res.string.file_explorer_downloads),
        Pair(Res.drawable.documents_folder, Res.string.file_explorer_documents),
        Pair(Res.drawable.photos, Res.string.file_explorer_pictures),
        Pair(Res.drawable.music_folder, Res.string.file_explorer_music),
        Pair(Res.drawable.videos_folder, Res.string.file_explorer_videos)
    )

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier.fillMaxSize()
    ) {
        SideMenuElement(
            icon = Res.drawable.home,
            title = Res.string.file_explorer_home,
            modifier = Modifier.padding(top = 20.dp, bottom = 4.dp, start = 48.dp)
        )

        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .15f),
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp)
        )

        elementsAtCenter.forEach { (icon, title) ->
            SideMenuElement(
                icon = icon,
                title = title,
                isPinned = true,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .padding(start = 48.dp)
            )
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .2f),
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp)
        )

        val elementsAtEnd = listOf(
            Pair(Res.drawable.pc, Res.string.file_explorer_this_pc),
            Pair(Res.drawable.network_folder, Res.string.file_explorer_network),
        )

        elementsAtEnd.forEach { (icon, title) ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 12.dp, start = 12.dp)
            ) {
                SimpleSymbolicIconButton(
                    icon = Res.drawable.simple_arrow_right_symbolic,
                    tint = Color.DarkGray,
                    modifier = Modifier.size(16.dp)
                )

                SideMenuElement(
                    icon = icon,
                    title = title,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
    }
}


@Composable
fun SideMenuElement(
    icon: DrawableResource,
    title: StringResource,
    isPinned: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 12.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

            Spacer(Modifier.width(4.dp))

            Text(
                text = stringResource(title),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                modifier = Modifier
            )
        }

        if (isPinned) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp)
            ) {
                SimpleSymbolicIconButton(
                    icon = Res.drawable.pin_symbolic,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }

}