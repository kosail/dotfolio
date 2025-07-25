package com.korealm.dotfolio.ui.windows.web_browser

import androidx.compose.foundation.Image
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
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import com.korealm.dotfolio.ui.windows.StandardTitleBarButtonSet
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WebBrowserTitleBar(
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        SimpleSymbolicIconButton(
            icon = Res.drawable.tag_properties,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))

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
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Surface(
                    color = Color.Transparent,
                    modifier = Modifier.size(27.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.chinese_redbud),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                Text(
                    text = stringResource(Res.string.web_browser_tab_name),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(start = 7.dp)
                        .padding(end = 55.dp)
                )

                SimpleSymbolicIconButton(Res.drawable.window_close)

                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Spacer(modifier = Modifier.width(14.dp))

        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(bottom = 7.dp)
        ) {
            SimpleSymbolicIconButton(
                icon = Res.drawable.add_plus_symbolic,
                modifier = Modifier
                    .size(20.dp)
            )
        }

        // Space Filler
        Surface(
            content = { Spacer(Modifier.fillMaxSize()) },
            color = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )


        StandardTitleBarButtonSet(
            onClose = onClose,
            modifier = Modifier.fillMaxHeight()
        )
    }
}