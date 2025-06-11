package com.korealm.dotfolio.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.audio_x_generic
import dotfolio.composeapp.generated.resources.microsoft_edge
import dotfolio.composeapp.generated.resources.ms_edge
import dotfolio.composeapp.generated.resources.ms_outlook
import dotfolio.composeapp.generated.resources.recycler_bin
import dotfolio.composeapp.generated.resources.user_trash_full

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
            Spacer(modifier = Modifier.height(25.dp))

            Image(
                painterResource(Res.drawable.user_trash_full),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            ShortcutTitle(text = stringResource(Res.string.recycler_bin), modifier = Modifier)

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painterResource(Res.drawable.microsoft_edge),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            ShortcutTitle(text = stringResource(Res.string.ms_edge), modifier = Modifier)

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painterResource(Res.drawable.audio_x_generic),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            ShortcutTitle(text = "about.mp3", modifier = Modifier)

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(Res.drawable.ms_outlook),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            ShortcutTitle(text = stringResource(Res.string.ms_outlook), modifier = Modifier)


            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

@Composable
fun ShortcutTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 17.sp,
        fontWeight = FontWeight.Light,
//        fontFamily = FontTheme(),  // TODO: Fix this shit
        style = TextStyle(
            shadow = Shadow(
                color = Color.Black,
                offset = Offset(1f, 1f),
                blurRadius = 2f
            )
        ),
        modifier = modifier
    )
}