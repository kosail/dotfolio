package com.korealm.dotfolio.ui.windows.photos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import com.korealm.dotfolio.ui.windows.StandardTitleBarButtonSet
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun PhotosAppTitleBar(
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Icon background
        Box(
            modifier = Modifier
                .size(height = 50.dp, width = 56.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            // Icon
            Surface(
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .padding(horizontal = 19.dp, vertical = 18.dp)
            ) {
                SimpleSymbolicIconButton(
                    icon = Res.drawable.picture_symbolic,
                    modifier = Modifier.size(17.dp)
                )
            }
        }

        // Special toolbar with lots of icons, following the real Photos app design
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text( // File name
                text = stringResource(Res.string.profile_pic) + ".jpg",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                modifier = Modifier.padding(horizontal = 30.dp)
            )

            Spacer(Modifier.width(27.dp))
            SimpleSymbolicIconButton(
                icon = Res.drawable.edit_photo_symbolic,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = modifier.size(24.dp)
            )

            Spacer(Modifier.width(20.dp))
            SimpleSymbolicIconButton(
                icon = Res.drawable.rotate_right_symbolic,
                tint = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(Modifier.width(20.dp))
            SimpleSymbolicIconButton(
                icon = Res.drawable.trash_symbolic,
                tint = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(Modifier.width(20.dp))
            SimpleSymbolicIconButton(
                icon = Res.drawable.favorite_symbolic,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = modifier.size(22.dp)
            )

            Spacer(Modifier.width(20.dp))
            SimpleSymbolicIconButton(
                icon = Res.drawable.help_about_symbolic,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = modifier.size(26.dp)
            )

            Spacer(Modifier.width(20.dp))
            SimpleSymbolicIconButton(
                icon = Res.drawable.printer_symbolic,
                tint = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(Modifier.width(20.dp))
            SimpleSymbolicIconButton(
                icon = Res.drawable.cloud_symbolic,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = modifier.size(25.dp)
            )

            Spacer(Modifier.width(20.dp))
            SimpleSymbolicIconButton(
                icon = Res.drawable.dot_menu_symbolic,
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }


        // Space Filler
        Surface(
            content = { Spacer(Modifier.fillMaxSize()) },
            color = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )


        StandardTitleBarButtonSet(
            onClose = onClose,
            modifier = Modifier.fillMaxHeight()
        )
    }
}