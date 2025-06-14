package com.korealm.dotfolio.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import dotfolio.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

// This composable recreates Windows 11 windows and requests a Composable function to display inside.
// As Windows 11 photo viewer, audio player, notepad and web browser (MS Edge) have different layouts, this Windows composable only holds
// Basic window behavior: Window drag, close, minimize and maximize capabilities.
@Composable
fun Window(
    onWindowClose: () -> Unit,
    content: @Composable () -> Unit // The actual contents of this window
) {
    Dialog(
        onDismissRequest = onWindowClose,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Surface (
            shape = RoundedCornerShape(4.dp),
            shadowElevation = 8.dp,
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                color = Color.Transparent,
                modifier = Modifier.fillMaxWidth()
            ) {

            }

            Surface(
                modifier = Modifier.fillMaxSize(),
                content = content
            )
        }
    }
}