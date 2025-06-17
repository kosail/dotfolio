package com.korealm.dotfolio.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize

data class WindowApp(
    val appId: String,
    val title: String,
    val icon: Painter,
    val isMinimized: Boolean = false,
    val defaultSize: DpSize,
    val titleBar: @Composable () -> Unit = {},
    val content: @Composable () -> Unit = {}
)