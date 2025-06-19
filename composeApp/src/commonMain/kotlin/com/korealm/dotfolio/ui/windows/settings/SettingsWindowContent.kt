package com.korealm.dotfolio.ui.windows.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            sidebarOption(
                icon = Res.drawable.desktop,
                text = Res.string.settings__system,
                isSelected = selectedIndex == 0,
                onClick = { onSelectIndex(0) }
            )

            sidebarOption(
                icon = Res.drawable.about,
                text = Res.string.settings__about,
                isSelected = selectedIndex == 1,
                onClick = { onSelectIndex(1) }
            )
        }

        // Main content
        Column(
            content = { if (selectedIndex == 0) systemScreen() else aboutScreen() },
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        )
    }
}

@Composable
fun sidebarOption(
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
fun systemScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Cyan))
}

@Composable
fun aboutScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Green))
}