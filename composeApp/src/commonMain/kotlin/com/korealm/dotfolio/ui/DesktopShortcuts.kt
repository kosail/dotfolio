package com.korealm.dotfolio.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DesktopShortcuts(
    appRegistry: Map<String, () -> Unit>,
    modifier: Modifier = Modifier,
) {
    // IDE complaints that I should remove "redundant" declaration, but I refuse to do so as I admit that this list is NOT intuitive at first glance
    val shortcutButtonsList = listOf< Triple<DrawableResource, String, () -> Unit> >(
        Triple(Res.drawable.trash, stringResource(Res.string.trash), appRegistry["trash"] ?: {}),
        Triple(Res.drawable.pdf, stringResource(Res.string.cv), {}),
        Triple(Res.drawable.web_browser, stringResource(Res.string.about_me) + ".html", appRegistry["webBrowser"] ?: {}),
        Triple(Res.drawable.mp3, stringResource(Res.string.voice_recording) + ".wav", appRegistry["audioPlayer"] ?: {}),
        Triple(Res.drawable.image_viewer, stringResource(Res.string.profile_pic) + ".jpg", appRegistry["photoViewer"] ?: {}),
        Triple(Res.drawable.folder, stringResource(Res.string.projects), appRegistry["fileExplorer"] ?: {}),
        Triple(Res.drawable.settings, stringResource(Res.string.settings), appRegistry["settings"] ?: {})
    )

    Box( modifier = modifier ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 20.dp)

        ) {
            for (shortcut in shortcutButtonsList) {
                ShortcutButton(shortcut.first, shortcut.second, { shortcut.third.invoke() })
            }
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

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ShortcutButton(
    icon: DrawableResource,
    title: String, // A String and not a StringResource to be able to send localize string with .ext
    onLaunch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isSelected by remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .onClick(
                onClick = {}, // I need you to do ✨ nothing ✨
                onDoubleClick = { onLaunch() },
            )
            .onPointerEvent(PointerEventType.Enter) { isSelected = true }
            .onPointerEvent(PointerEventType.Exit ) { isSelected = false }
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.09f) else Color.Transparent,
                shape = RoundedCornerShape(3.dp)
            )
            .padding(15.dp)

    ) {
        Image(
            painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(56.dp)
        )

        ShortcutTitle(
            text = title,
            modifier = Modifier
        )
    }
}