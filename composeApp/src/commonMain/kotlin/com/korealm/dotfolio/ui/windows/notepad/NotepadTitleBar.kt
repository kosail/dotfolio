package com.korealm.dotfolio.ui.windows.notepad

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
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
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import com.korealm.dotfolio.ui.windows.StandardTitleBarButtonSet
import com.korealm.dotfolio.ui.windows.TitleBarMainIcon
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.lock_symbolic
import dotfolio.composeapp.generated.resources.notepad
import dotfolio.composeapp.generated.resources.readme
import org.jetbrains.compose.resources.stringResource

@Composable
fun NotepadTitleBar(
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box( // Slight border in the taskbar to have more visual deepness. Good for the eye, good for the heart.
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
            .border(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.03f))
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.20f))
    ) {
        TitleBarMainIcon(
            icon = Res.drawable.notepad,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(top = 5.dp)
        )

        // Tabs
        Surface (
            shape = MaterialTheme.shapes.small.copy(
                topStart = CornerSize(7.dp),
                topEnd = CornerSize(7.dp),
                bottomStart = CornerSize(0.dp),
                bottomEnd = CornerSize(0.dp)
            ),
            shadowElevation = 3.dp,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.align(Alignment.Bottom)

        ) {
            // I need this extra surface just to set this specific background tone, which matches with the one in the menu bar
            // that you'll find in NotepadWindowContent.kt
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    SimpleSymbolicIconButton(Res.drawable.lock_symbolic)

                    Text(
                        text = stringResource(Res.string.readme) + ".txt",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 7.dp)
                            .padding(end = 50.dp)
                    )
                }
            }
        }


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