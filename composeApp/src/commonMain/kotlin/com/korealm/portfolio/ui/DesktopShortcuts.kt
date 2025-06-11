package com.korealm.portfolio.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.microsoft_edge
import portfolio.composeapp.generated.resources.ms_edge
import portfolio.composeapp.generated.resources.ms_outlook
import portfolio.composeapp.generated.resources.playmymusic
import portfolio.composeapp.generated.resources.recycler_bin
import portfolio.composeapp.generated.resources.start
import portfolio.composeapp.generated.resources.user_trash_full
import portfolio.composeapp.generated.resources.user_trash_full_dark

@Composable
fun DesktopShortcuts(
    modifier: Modifier = Modifier,
) {
    Box( modifier = modifier ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 20.dp)

        ) {
            Image(
                painterResource(Res.drawable.user_trash_full),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            ShortcutTitle(text = Res.string.recycler_bin, modifier = Modifier)

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painterResource(Res.drawable.microsoft_edge),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            ShortcutTitle(text = Res.string.ms_edge, modifier = Modifier)

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painterResource(Res.drawable.playmymusic),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            ShortcutTitle(text = Res.string.ms_edge, modifier = Modifier)

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(Res.drawable.ms_outlook),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            ShortcutTitle(text = Res.string.ms_outlook, modifier = Modifier)


            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

@Composable
fun ShortcutTitle(
    text: StringResource,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(text),
        color = MaterialTheme.colorScheme.background,
        style = TextStyle(
            color = Color.White,
            fontSize = 18.sp,
            shadow = Shadow(
                color = Color.Black,
                offset = Offset(1f, 1f),
                blurRadius = 2f
            )
        ),
        modifier = Modifier
    )
}