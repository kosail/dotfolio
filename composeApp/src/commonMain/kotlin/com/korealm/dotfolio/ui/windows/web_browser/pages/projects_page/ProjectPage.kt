package com.korealm.dotfolio.ui.windows.web_browser.pages.projects_page

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.Post
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostBody
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostHeader
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostHeaderSpecialText
import com.korealm.dotfolio.utils.RoundedPicture
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun ProjectsPage(
    font: FontFamily,
    modifier: Modifier = Modifier
) {
    // I decide to extract the animation into a variable to be able to sync the title + content changes, in the same motion
    val slideAnimation = {
        slideInHorizontally(animationSpec = tween(250)) { fullWidth -> fullWidth } + fadeIn(animationSpec = tween(250)
        ) togetherWith

                slideOutHorizontally(animationSpec = tween(250)) { fullWidth -> -fullWidth } + fadeOut(animationSpec = tween(250)
        )
    }

    var selectedProject by remember { mutableStateOf(Project.DOTFOLIO) }

    // The title changes with the content, but the rest remains the same. Instead or recomposing everything, better to extract this and recompose just the title.
    val postTitle = if (selectedProject != Project.BLANKET) {
        "${ stringResource(Res.string.web_browser_projects_created)} ${ stringResource(selectedProject.titleRes) }"
    } else {
        "${ stringResource(Res.string.web_browser_projects_interested) } ${ stringResource(Project.BLANKET.titleRes) }"
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp, vertical = 30.dp)
    ) {
        // New UX
        Post(
            postHeader = {
                Surface(
                    color = Color.Transparent,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PostHeader(
                        animatedTitle = {
                            // Change the title from the cached title in postTitle
                            AnimatedContent(
                                targetState = postTitle,
                                transitionSpec = { slideAnimation() }
                            ) { title ->
                                PostHeaderSpecialText(
                                    text = title,
                                    font = font
                                )
                            }
                        },
                        date = stringResource(Res.string.web_browser_projects_hint),
                        font = font,
                        modifier = Modifier
                    )
                }
            },
            postBody = {
                LazyColumn (
                    modifier = Modifier
                ) {
                    item {
                        // Project selector
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp)
                        ) {
                            Project.entries.forEach { project ->
                                CircledWidget(
                                    project = project,
                                    selectedProject = selectedProject,
                                    modifier = Modifier
                                ) { selectedProject = project }
                            }
                        }
                    }

                    item {
                        // Project information
                        AnimatedContent(
                            targetState = selectedProject,
                            contentAlignment = Alignment.Center,
                            transitionSpec = { slideAnimation() },
                        ) { target ->
                            PostBody(
                                images = mapOf(
                                    target.bannerRes to target.titleRes
                                ),
                                text = stringResource(target.contentRes).trimIndent(),
                                font = font,
                                modifier = Modifier
                            )
                        }
                    }

                }
            },
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CircledWidget(
    project: Project,
    selectedProject: Project,
    isSelected: Boolean = selectedProject == project,
    iconSize: Dp = 60.dp,
    modifier: Modifier = Modifier,
    onProjectClick: () -> Unit
) {
    var isPointerHovering by remember { mutableStateOf(false) }
    val isHover = isPointerHovering || isSelected

    var targetSize = if (isSelected) iconSize * 1.2f else iconSize

    val animatedIconSize by animateDpAsState(
        targetValue = targetSize,
        animationSpec = tween(durationMillis = 150)
    )

    val animatedAlpha by animateFloatAsState(
        targetValue = if (isHover) 1f else 0.5f,
        animationSpec = tween(durationMillis = 150)
    )

    val maxIconSize = iconSize * 1.34f

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(maxIconSize)
            .clip(CircleShape)
    ) {
        AnimatedVisibility(
            visible = isSelected,
            enter = fadeIn(animationSpec = tween(durationMillis = 200)),
            exit = fadeOut(animationSpec = tween(durationMillis = 200))
        ) {
            Box(
                modifier = Modifier
                    .size(maxIconSize)
                    .background(MaterialTheme.colorScheme.secondary)
            ) {}
        }

        Surface(
        shape = CircleShape,
        color = Color.Transparent,
        shadowElevation = 2.dp,
            modifier = Modifier
                .pointerHoverIcon(PointerIcon.Hand)
                .onPointerEvent(PointerEventType.Enter) { isPointerHovering = true }
                .onPointerEvent(PointerEventType.Exit) { isPointerHovering = false }
                .onPointerEvent(PointerEventType.Press) { onProjectClick() }
        ) {
            RoundedPicture(
                painter = project.faviconRes,
                contentDescription = stringResource(project.titleRes),
                modifier = Modifier
                    .background(Color.White)
                    .size(animatedIconSize)
                    .alpha(animatedAlpha)
            )
        }
    }
}