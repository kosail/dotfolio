package com.korealm.dotfolio.ui.windows.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.state.AppThemeState
import com.korealm.dotfolio.ui.windows.StandardTitleBarButtonSet
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsTitleBar (
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(MaterialTheme.colorScheme.surface)
    )

    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
    ) {
        // Icon
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 15.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.settings),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(Modifier.width(5.dp))

        // Window title
        Text(
            text = stringResource(Res.string.settings),
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        // Space Filler
        Surface(
            content = { Spacer(Modifier.fillMaxSize()) },
            color = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        StandardTitleBarButtonSet(onClose = onClose)
    }
}