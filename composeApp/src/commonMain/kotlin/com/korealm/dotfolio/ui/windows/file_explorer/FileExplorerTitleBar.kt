package com.korealm.dotfolio.ui.windows.file_explorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.state.AppThemeState
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import com.korealm.dotfolio.ui.windows.StandardTitleBarButtonSet
import com.korealm.dotfolio.ui.windows.TitleBarMainIcon
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.add_plus_symbolic
import dotfolio.composeapp.generated.resources.window_close
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FileExplorerTitleBar(
    onClose: () -> Unit,
    themeState: AppThemeState,
    title: StringResource,
    icon: DrawableResource,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
            .background(
                if (themeState.isDarkTheme) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f) else MaterialTheme.colorScheme.primary.copy(alpha = .2f)
            )
    ) {
        // Tabs
        Surface (
            shape = MaterialTheme.shapes.small.copy(
                topStart = CornerSize(7.dp),
                topEnd = CornerSize(7.dp),
                bottomStart = CornerSize(0.dp),
                bottomEnd = CornerSize(0.dp)
            ),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .align(Alignment.Bottom)
                .height(37.dp)
                .padding(start = 16.dp)
                .shadow(
                    elevation = 7.dp,
                    shape = MaterialTheme.shapes.medium.copy(
                        topStart = CornerSize(7.dp),
                        topEnd = CornerSize(7.dp),
                        bottomStart = CornerSize(0.dp),
                        bottomEnd = CornerSize(0.dp)
                    )
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            ) {
                Spacer(Modifier.width(16.dp))

                TitleBarMainIcon(
                    icon = icon,
                    modifier = Modifier
                )

                Text(
                    text = stringResource(title),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 7.dp)
                        .padding(end = 50.dp)
                )

                SimpleSymbolicIconButton(Res.drawable.window_close)

                Spacer(modifier = Modifier.width(8.dp))
            }
        }


        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(start = 14.dp, bottom = 7.dp)
        ) {
            SimpleSymbolicIconButton(
                icon = Res.drawable.add_plus_symbolic,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = .9f),
                modifier = Modifier.size(20.dp)
            )
        }


        Surface(
            content = { Spacer(Modifier.fillMaxSize()) },
            color = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )


        StandardTitleBarButtonSet(
            onClose = onClose,
            modifier = Modifier
        )
    }
}