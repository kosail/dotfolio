package com.korealm.dotfolio.ui.windows.media_player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.ui.windows.StandardTitleBarButtonSet
import com.korealm.dotfolio.ui.windows.TitleBarMainIcon
import com.korealm.dotfolio.ui.windows.TitleBarTitle
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.media_player

@Composable
fun MediaPlayerTitleBar(
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        TitleBarMainIcon(
            icon = Res.drawable.media_player,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        Spacer(Modifier.width(5.dp))

        TitleBarTitle(
            string = Res.string.media_player,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

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