package com.korealm.dotfolio

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import com.korealm.dotfolio.state.rememberAppThemeState
import com.korealm.dotfolio.ui.theme.MicaTheme
import com.korealm.dotfolio.ui.windows.web_browser.pages.Index
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.limited_ux_msg
import dotfolio.composeapp.generated.resources.limited_ux_title
import dotfolio.composeapp.generated.resources.tsuki_blurred
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/* ? About this file purpose:
    This composable is a stripped-down version of dotfolio made for tablets and low-resolution screens (like 1366x768 monitors).

    Up until finishing this project, I realize that Compose doesn't automatically adapt my content to different resolutions, and that all this time I've been building just taking 1920x1080 resolution in mind.
    I tried several things to make it work on smaller screens, but none of them ended in something that I liked or that I consider beautiful. Thus, I decided to use the blog that I created for the web browser as an actual web page. Gave it a try and, honestly? I LOVED IT.

    I loved it so much that I'm impressed with myself, and I think I deserve a good coffee for huge effort I placed in the blog running inside dotfolio.

    So this is it.
*/

@Composable
fun LimitedDotfolio() {
    val themeState = rememberAppThemeState()

    // Run limited dotfolio! (commafolio? Ok I'll stop)
    MicaTheme(
        darkTheme = themeState.isDarkTheme
    ){
        var showWarning by remember { mutableStateOf(true) }

        Index(themeState)

        AnimatedVisibility(
            visible = showWarning,
            enter = fadeIn(tween(500)),
            exit = fadeOut(tween(500))
        ) {
            LimitedUxWarning(onDismiss = { showWarning = false })
        }
    }
}
@Composable
fun LimitedUxWarning(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onDismiss() }
                )
            }
    ) {
        val density = LocalDensity.current
        val maxWidthDp = with(density) { constraints.maxWidth.toDp() }
        val maxHeightDp = with(density) { constraints.maxHeight.toDp() }

        // Background to simulate frosted glass effect
        Image(
            painter = painterResource(Res.drawable.tsuki_blurred),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.3f))
                .padding(horizontal = maxWidthDp * .2f, vertical = maxHeightDp * .2f)
        ) {
            Text(
                text = stringResource(Res.string.limited_ux_title),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )

            Spacer(Modifier.height(maxHeightDp * .1f))

            Text(
                text = stringResource(Res.string.limited_ux_msg).trimIndent(),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = maxWidthDp * .1f, vertical = maxHeightDp * .05f)
            )
        }
    }
}