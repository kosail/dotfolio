package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
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
import com.korealm.dotfolio.state.AppThemeState
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun Index(
    themeState: AppThemeState,
    modifier: Modifier = Modifier
) {
    val mPlusFontFamily = FontFamily(
        Font(Res.font.MPLUS1p_Light, FontWeight.Light),
        Font(Res.font.MPLUS1p_Regular, FontWeight.Normal),
        Font(Res.font.MPLUS1p_Medium, FontWeight.SemiBold),
        Font(Res.font.MPLUS1p_Bold, FontWeight.Bold),
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
                                    font = mPlusFontFamily,
                                    modifier = Modifier
                                )
                            }
                            Page.ABOUT_ME -> {
                                AboutMePage(
                                    font = mPlusFontFamily,
                                    themeState = themeState,
                                    modifier = Modifier
                                )
                            }
                            Page.PROJECTS -> {
                                ProjectsPage(
                                    font = mPlusFontFamily,
                                    modifier = Modifier
                                )
                            }
                            Page.FAQ -> {
                                FaqPage(
                                    font = mPlusFontFamily,
                                    modifier = Modifier
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