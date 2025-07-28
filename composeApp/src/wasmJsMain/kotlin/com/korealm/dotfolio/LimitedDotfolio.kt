package com.korealm.dotfolio

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import com.korealm.dotfolio.state.rememberAppThemeState
import com.korealm.dotfolio.ui.theme.MicaTheme
import com.korealm.dotfolio.ui.windows.web_browser.pages.Index

/* ? About this file purpose:
    This composable is a stripped-down version of dotfolio made for tablets and low-resolution devices.

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
        AnimatedContent(
            targetState = Unit,
            transitionSpec = {
                fadeIn(animationSpec = tween(1000) ) togetherWith fadeOut(animationSpec = tween(1000))
            }
        ) {
            Index(themeState)
        }
    }
}