package com.korealm.dotfolio.ui.windows.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.state.AppThemeState
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
                .width(10.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
        )

        // Main content
        Box(
            content = {
                if (selectedIndex == 0) SystemScreen(
                    themeState = themeState,
                    isDevModeOn = isDevModeOn,
                    onDevModeChange = onDevModeChange
                ) else AboutScreen()
          },
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        )
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
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
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
                content = { SettingsToggle(checked = themeState.isDarkTheme) }
            )

            Spacer(Modifier.height(4.dp))

            // TODO: Currently working on this
            var rotation by remember { mutableStateOf(0f) }
            SettingOption(
                icon = if (themeState.isDarkTheme) Res.drawable.picture_symbolic_light else Res.drawable.picture_symbolic_dark,
                title = Res.string.settings__background,
                subtitle = Res.string.settings__background_description,
                onClick = { rotation = if (rotation == 0f) 90f else 0f }
            ) {
                Icon(
                    painter = painterResource(if (themeState.isDarkTheme) Res.drawable.go_next_light else Res.drawable.go_next_dark),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp).rotate(rotation)
                )

            }

            Spacer(Modifier.height(4.dp))

            SettingOption(
                icon = if (themeState.isDarkTheme) Res.drawable.atom_symbolic_light else Res.drawable.atom_symbolic_dark,
                title = Res.string.settings__dev_mode,
                onClick = { onDevModeChange() },
                content = { SettingsToggle(checked = isDevModeOn) }
            )
        }
    }
}

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize().background(Color.Green))
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingOption(
    icon: DrawableResource,
    title: StringResource,
    subtitle: StringResource? = null,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .onPointerEvent(PointerEventType.Press) { onClick?.invoke() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(start = 22.dp, top = 15.dp, bottom = 15.dp, end = 25.dp)
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
            content()
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