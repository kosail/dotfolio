package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dotfolio.composeapp.generated.resources.MPLUS1p
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.tsuki
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun Index(
    modifier: Modifier = Modifier
) {
    val mPlusFontFamily = FontFamily(
        Font(Res.font.MPLUS1p, FontWeight.Normal),
    )

    var currentPage by remember { mutableStateOf(Page.HOME) }

    Image( // Background image
        painter = painterResource(Res.drawable.tsuki),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )

    // Foreground UI layer
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 50.dp, horizontal = 100.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = MaterialTheme.colorScheme.background.copy(alpha = 0.85f),
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)),
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                IndexSidebar(
                    defaultFont = mPlusFontFamily,
                    onNavigationClick = { currentPage = it },
                    modifier = Modifier
                        .weight(0.4f)
                        .fillMaxHeight()
                )

                SlightDivider(Orientation.Vertical)

                Box (
                    modifier = Modifier
                        .weight(0.6f)
                        .fillMaxHeight()
                ) {
                    AnimatedContent(
                        targetState = currentPage,
                        transitionSpec = {
                            slideInHorizontally(animationSpec = tween(500)) { fullWidth -> fullWidth } +
                                    fadeIn(animationSpec = tween(500)
                                ) togetherWith

                                slideOutHorizontally(animationSpec = tween(500)) { fullWidth -> -fullWidth } +
                                    fadeOut(animationSpec = tween(500)
                                )
                        }
                    ) { selected ->
                        when (selected) {
                            Page.HOME -> {
                                HomePage(
                                    defaultFont = mPlusFontFamily,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Page.ABOUT_ME -> {}
                            Page.PROJECTS -> {}
                            Page.FAQ -> {
                                FaqPage(
                                    defaultFont = mPlusFontFamily,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Page.GALLERY -> {}
                            Page.CONTACT -> {}
                        }
                    }
                }
            }
        }
    }
}