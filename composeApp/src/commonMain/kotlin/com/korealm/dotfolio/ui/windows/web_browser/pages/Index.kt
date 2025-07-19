package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    var currentPage by remember { mutableStateOf(Page.INDEX) }

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

                VerticalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                )

                BoxWithConstraints (
                    modifier = Modifier
                        .weight(0.6f)
                        .fillMaxHeight()
                ) {
                    AnimatedContent(
                        targetState = currentPage,
                        transitionSpec = {
                            fadeIn(animationSpec = tween(1000) ) togetherWith fadeOut(animationSpec = tween(1000))
                        }
                    ) { selected ->
                        when (selected) {
                            Page.INDEX -> {
                                Foo(
                                    defaultFont = mPlusFontFamily,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color(0xFF800080))
                                )
                            }
                            Page.ABOUT_ME -> {
                                Foo(
                                    defaultFont = mPlusFontFamily,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Green)
                                )
                            }
                            Page.PROJECTS -> {
                                Foo(
                                    defaultFont = mPlusFontFamily,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Yellow)
                                )
                            }
                            Page.THOUGHTS -> {}
                            Page.GALLERY -> {}
                            Page.CONTACT -> {}
                        }
                    }
                }
            }
        }
    }
}