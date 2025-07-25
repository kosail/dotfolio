package com.korealm.dotfolio.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.korealm.dotfolio.model.AppId
import com.korealm.dotfolio.utils.openInNewTab
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DesktopShortcuts(
    onAppLaunch: (AppId) -> Unit,
    modifier: Modifier = Modifier,
) {
    val shortcutButtonsList = listOf(
        Triple(Res.drawable.trash, stringResource(Res.string.trash), AppId.TRASH),
        Triple(Res.drawable.pdf, stringResource(Res.string.cv) + ".pdf", AppId.CV),
        Triple(Res.drawable.web_browser, stringResource(Res.string.about_me) + ".html", AppId.WEB_BROWSER),
        Triple(Res.drawable.mp3, stringResource(Res.string.voice_recording) + ".wav", AppId.MEDIA_PLAYER),
        Triple(Res.drawable.photos, stringResource(Res.string.profile_pic) + ".jpg", AppId.PHOTOS),
        Triple(Res.drawable.folder, stringResource(Res.string.tech_stack), AppId.FILE_EXPLORER),
        Triple(Res.drawable.settings, stringResource(Res.string.settings), AppId.SETTINGS)
    )

    Box( modifier = modifier ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 20.dp)

        ) {
            for ((icon, title, appId) in shortcutButtonsList) { // Unpacking Triple for more readability
                ShortcutButton(
                    icon = icon,
                    title = title,
                    onLaunch = {
                        if (appId == AppId.CV) {
                            openInNewTab("composeResources/dotfolio.composeapp.generated.resources/files/cv.pdf")
                        } else {
                            onAppLaunch(appId)
                        }
                    }
                )
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