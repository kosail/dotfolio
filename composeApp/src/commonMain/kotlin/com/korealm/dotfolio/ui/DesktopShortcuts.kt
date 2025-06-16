package com.korealm.dotfolio.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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
                painterResource(Res.drawable.trash),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            ShortcutTitle(text = stringResource(Res.string.recycler_bin), modifier = Modifier)

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painterResource(Res.drawable.web_browser),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            ShortcutTitle(text = stringResource(Res.string.ms_edge), modifier = Modifier)

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painterResource(Res.drawable.mp3),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            ShortcutTitle(text = "about.mp3", modifier = Modifier)

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(Res.drawable.outlook),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            ShortcutTitle(text = stringResource(Res.string.outlook), modifier = Modifier)


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