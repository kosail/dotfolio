package com.korealm.dotfolio.ui.windows.web_browser.pages.general

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun IndexSidebar(
    defaultFont: FontFamily,
    onNavigationClick: (Page) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()
        ) {
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .padding(top = 45.dp)
                    .padding(start = 50.dp, end = 28.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.web_main_img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(230.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 40.dp, bottom = 45.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    BoxedIcon(
                        painter = Res.drawable.lucide_house,
                        contentDescription = stringResource(Res.string.web_browser_home),
                        action = { onNavigationClick(Page.HOME) },
                        modifier = Modifier
                    )

                    BoxedIcon(
                        painter = Res.drawable.lucide_folder_code,
                        contentDescription = stringResource(Res.string.web_browser_projects),
                        action = { onNavigationClick(Page.PROJECTS) },
                        modifier = Modifier
                    )

                    BoxedIcon(
                        painter = Res.drawable.lucide_send,
                        contentDescription = stringResource(Res.string.web_browser_contact),
                        action = { onNavigationClick(Page.CONTACT) },
                        modifier = Modifier
                    )
                }

                Spacer(Modifier.width(20.dp))

                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    BoxedIcon(
                        painter = Res.drawable.lucide_user,
                        contentDescription = stringResource(Res.string.web_browser_about),
                        action = { onNavigationClick(Page.ABOUT_ME) },
                        modifier = Modifier
                    )

                    BoxedIcon(
                        painter = Res.drawable.lucide_flower,
                        contentDescription = stringResource(Res.string.web_browser_thoughts),
                        action = { onNavigationClick(Page.FAQ) },
                        modifier = Modifier
                    )

                    BoxedIcon(
                        painter = Res.drawable.lucide_images,
                        contentDescription = stringResource(Res.string.web_browser_gallery),
                        action = { onNavigationClick(Page.GALLERY) },
                        modifier = Modifier
                    )
                }
            }

        }

        SlightDivider(Orientation.Horizontal)

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Text(
                text = stringResource(Res.string.web_browser_web_name),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = defaultFont,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.85f),
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(Res.string.web_browser_web_description).trimIndent(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = defaultFont,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BoxedIcon(
    painter: DrawableResource,
    contentDescription: String? = null,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isHover by remember { mutableStateOf(false) }

    Surface (
        color = Color.Transparent,
        shape = RectangleShape,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f)),
        modifier = modifier
            .background(
                color = if (isHover) MaterialTheme.colorScheme.onBackground.copy(alpha = 0.07f) else Color.Transparent,
            )
            .onPointerEvent(PointerEventType.Enter) { isHover = true }
            .onPointerEvent(PointerEventType.Exit) { isHover = false }
            .onPointerEvent(PointerEventType.Press) { action() }
    ) {
        Box(
            propagateMinConstraints = true,
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            SimpleSymbolicIconButton(
                icon = painter,
                contentDescription = contentDescription,
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                modifier = modifier.size(22.dp)
            )
        }
    }
}