package com.korealm.dotfolio.ui.windows.web_browser.pages.general

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TooltipDefaults.rememberTooltipPositionProvider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun IndexSidebar(
    activePage: Page,
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
                        contentDescription = Res.string.web_browser_home,
                        isActive = activePage == Page.HOME,
                        action = { onNavigationClick(Page.HOME) },
                        modifier = Modifier
                    )

                    BoxedIcon(
                        painter = Res.drawable.lucide_folder_code,
                        contentDescription = Res.string.web_browser_projects,
                        isActive = activePage == Page.PROJECTS,
                        action = { onNavigationClick(Page.PROJECTS) },
                        modifier = Modifier
                    )

                    BoxedIcon(
                        painter = Res.drawable.lucide_send,
                        contentDescription = Res.string.web_browser_contact,
                        isActive = activePage == Page.CONTACT,
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
                        contentDescription = Res.string.web_browser_about,
                        isActive = activePage == Page.ABOUT_ME,
                        action = { onNavigationClick(Page.ABOUT_ME) },
                        modifier = Modifier
                    )

                    BoxedIcon(
                        painter = Res.drawable.lucide_message_circle_heart,
                        contentDescription = Res.string.web_browser_thoughts,
                        isActive = activePage == Page.FAQ,
                        action = { onNavigationClick(Page.FAQ) },
                        modifier = Modifier
                    )

                    BoxedIcon(
                        painter = Res.drawable.lucide_images,
                        contentDescription = Res.string.web_browser_gallery,
                        isActive = activePage == Page.GALLERY,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxedIcon(
    painter: DrawableResource,
    contentDescription: StringResource,
    isActive: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isHover by remember { mutableStateOf(false) }

    val bgColor = if (isActive) { MaterialTheme.colorScheme.onBackground.copy(alpha = 0.125f) } else {
        if (isHover) MaterialTheme.colorScheme.onBackground.copy(alpha = 0.07f)
        else Color.Transparent
    }

    val borderColor = if (isActive) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f)

    TooltipBox(
        tooltip = { StyledTooltip(contentDescription) },
        state = rememberTooltipState(),
        positionProvider = rememberTooltipPositionProvider(
            positioning = TooltipAnchorPosition.Above,
            spacingBetweenTooltipAndAnchor = 4.dp
        )
    ) {
        Surface (
            color = Color.Transparent,
            shape = RectangleShape,
            border = BorderStroke(1.dp, borderColor),
            modifier = modifier
                .background(color = bgColor)
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while(true) {
                            val type = awaitPointerEvent().type

                            when (type) {
                                PointerEventType.Enter -> isHover = true
                                PointerEventType.Exit -> isHover = false
                            }
                        }
                    }
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            action()
                        }
                    )
                }
        ) {
            Box(
                propagateMinConstraints = true,
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(10.dp)
            ) {
                SimpleSymbolicIconButton(
                    icon = painter,
                    contentDescription = stringResource(contentDescription),
                    tint = if (isActive) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    modifier = modifier.size(22.dp)
                )
            }
        }
    }
}