package com.korealm.dotfolio.ui.windows.settings

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.state.AppThemeState
import com.korealm.dotfolio.ui.theme.Wallpaper
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsWindowContent (
    themeState: AppThemeState,
    isDevModeOn: Boolean,
    onDevModeChange: () -> Unit,
    selectedIndex: Int,
    onSelectIndex: (Int) -> Unit,
) {

    Row (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        // Left sidebar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.5f)
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
        ) {
            // Account info
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp)
            ) {
                // Account profile picture
                Image(
                    painter = painterResource(Res.drawable.user_account),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(60.dp)
                )

                // Account name and email
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 13.dp, top = 5.dp)
                ) {
                    Text(
                        text = stringResource(Res.string.username),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier
                    )

                    Text(
                        text = stringResource(Res.string.email),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        modifier = Modifier
                    )
                }
            }

            // Mock search bar
            Surface (
                shape = RoundedCornerShape(5.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(33.dp)
                    .padding(horizontal = 15.dp)
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 10.dp)
                ) {
                    Text(
                        text = stringResource(Res.string.settings__search_bar_content),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                    )

                    Surface(
                        content = { Spacer(Modifier.fillMaxSize()) },
                        color = Color.Transparent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    Image(
                        painter = painterResource(if (themeState.isDarkTheme) Res.drawable.find_light else Res.drawable.find_dark),
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                    )
                }
            }

            Spacer(Modifier.height(25.dp))

            // Options
            SidebarOption(
                icon = Res.drawable.desktop,
                text = Res.string.settings__system,
                isSelected = selectedIndex == 0,
                onClick = { onSelectIndex(0) }
            )

            AnimatedVisibility(isDevModeOn) {
                SidebarOption(
                    icon = Res.drawable.about,
                    text = Res.string.settings__about,
                    isSelected = selectedIndex == 1,
                    onClick = { onSelectIndex(1) }
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(5.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
        )

        // Main content
        Box(modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
            .scrollable(rememberScrollState(), Orientation.Vertical)
        ) {
            AnimatedContent (
                targetState = selectedIndex,
                transitionSpec = {
                    fadeIn(animationSpec = tween(250)) togetherWith fadeOut(animationSpec = tween(250))
                }
            ) { index ->
                when (index) {
                    0 -> SystemScreen(
                            themeState = themeState,
                            isDevModeOn = isDevModeOn,
                            onDevModeChange = onDevModeChange
                        )
                    else -> AboutScreen()
                }
            }
        }
    }
}

@Composable
fun SidebarOption(
    icon: DrawableResource,
    text: StringResource,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface (
        shape = RoundedCornerShape(5.dp),
        color = if (isSelected) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.8f) else Color.Transparent,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 10.dp)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
        ) {
            AnimatedVisibility (isSelected) {
                VerticalDivider(
                    color = MaterialTheme.colorScheme.primary,
                    thickness = 3.dp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp)) // ROUND IT BITCH ROUND IT
                        .padding(vertical = 10.dp)
                )
            }
            Spacer(Modifier.width(15.dp))

            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Spacer(Modifier.width(10.dp))

            Text(
                text = stringResource(text),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
        }
    }
}

@Composable
fun SystemScreen(
    themeState: AppThemeState,
    isDevModeOn: Boolean,
    onDevModeChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        // Title
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        ) {
            Text(
                text = stringResource(Res.string.settings__system),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )

            Spacer(Modifier.height(40.dp))

            SettingOption(
                icon = if (themeState.isDarkTheme) Res.drawable.dark_mode_symbolic_light else Res.drawable.dark_mode_symbolic_dark,
                title = Res.string.settings__dark_mode,
                subtitle = Res.string.settings__dark_mode_description,
                onClick = { themeState.toggleTheme() },
                inRowContent = { SettingsToggle(checked = themeState.isDarkTheme) }
            )

            Spacer(Modifier.height(4.dp))

            // TODO: Currently working on this
            var rotation by remember { mutableStateOf(0f) }
            SettingOption(
                icon = if (themeState.isDarkTheme) Res.drawable.picture_symbolic_light else Res.drawable.picture_symbolic_dark,
                title = Res.string.settings__background,
                subtitle = Res.string.settings__background_description,
                onClick = { rotation = if (rotation == 0f) 90f else 0f },
                inRowContent = {
                    Icon(
                        painter = painterResource(if (themeState.isDarkTheme) Res.drawable.go_next_light else Res.drawable.go_next_dark),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp).rotate(rotation)
                    )
                },
            ) {
                AnimatedVisibility(rotation != 0f) {
                    Column (
                        modifier = Modifier.padding(top = 15.dp)
                    ) {
                        Text(
                            text = stringResource(Res.string.settings__background_available_images),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                        )

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )

                        // Image gallery ahead!
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                        ) {
                            Wallpaper.entries.forEachIndexed { index, entry ->
                                // A nice border around the image
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = Color.Transparent,
                                    modifier = Modifier
                                        .size(85.dp)
                                        .padding(horizontal = 2.dp)
                                        .pointerHoverIcon(PointerIcon.Hand)
                                ) {
                                    Image(
                                        painter = painterResource(entry.resource),
                                        contentDescription = "${stringResource(Res.string.settings__background_images_content_description)} $index",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .clickable { themeState.changeWallpaper(entry) }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(4.dp))

            SettingOption(
                icon = if (themeState.isDarkTheme) Res.drawable.atom_symbolic_light else Res.drawable.atom_symbolic_dark,
                title = Res.string.settings__dev_mode,
                onClick = { onDevModeChange() },
                inRowContent = { SettingsToggle(checked = isDevModeOn) }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingOption(
    icon: DrawableResource,
    title: StringResource,
    subtitle: StringResource? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    inRowContent: @Composable () -> Unit,
    content: (@Composable () -> Unit)? = null
) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        Column (
            modifier = Modifier
                .padding(start = 22.dp, top = 15.dp, bottom = 15.dp, end = 25.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .onPointerEvent(PointerEventType.Press) { onClick() }
            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(Modifier.width(22.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                ) {
                    Text(
                        text = stringResource(title),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 0.sp,
                        modifier = Modifier
                    )

                    if (subtitle != null) {
                        Text(
                            text = stringResource(subtitle),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            modifier = Modifier
                        )
                    }
                }

                Spacer(Modifier.fillMaxWidth().weight(1f))
                inRowContent()
            }

            content?.invoke()
        }
    }
}

@Composable
fun SettingsToggle(
    checked: Boolean,
    modifier: Modifier = Modifier
) {
    // This switch serves just as a visual representation of the current values. We don't need it to handle changes here.
    // Instead, the Composable parent of this Switch will handle that so it behaves like the real Settings app on W11:
    // Click wherever over the option, and the option will be triggered.
    Switch(
        checked = checked,
        onCheckedChange = {},
        colors = SwitchDefaults.colors(
            uncheckedTrackColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = modifier.scale(0.9f)
    )
}


@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp)
        ) {
            Surface(
                color = Color.Transparent,
                modifier = modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(Res.drawable.banner),
                    contentDescription = stringResource(Res.string.dev_mode_logo_content_description),
                    modifier = Modifier.size(150.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(Res.string.dev_mode_about_description),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    lineHeight = 0.sp,
                    modifier = Modifier
                )
            }
        }
    }
}