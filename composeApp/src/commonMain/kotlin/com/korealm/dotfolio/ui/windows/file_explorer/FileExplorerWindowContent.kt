package com.korealm.dotfolio.ui.windows.file_explorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.StringResource

@Composable
fun FileExplorerWindowContent(
    title: StringResource,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
        ) { FileExplorerToolbar(title = title) }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            FileExplorerSidebar(
                modifier = Modifier.weight(.2f)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(.8f)
                    .background(Color.Green)
            ) {
            }
        }
    }
}