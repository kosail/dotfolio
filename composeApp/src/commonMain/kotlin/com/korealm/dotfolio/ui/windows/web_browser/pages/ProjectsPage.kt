package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.utils.RoundedPicture
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

enum class Project (val drawableRes: DrawableResource, val stringRes: StringResource) {
    KVANTAGE (Res.drawable.kvantage_favicon, Res.string.web_browser_projects_kvantage),
    DOTFOLIO (Res.drawable.favicon, Res.string.web_browser_projects_dotfolio),
    BLANKET (Res.drawable.rafael_mardojai_blanket_favicon, Res.string.web_browser_projects_blanket)
}


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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
            .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
    ) {

        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            AnimatedContent(
                targetState = selectedProject,
                transitionSpec = { slideAnimation() }
            ) { target ->
                Text(
                    text = stringResource(target.stringRes),
                    fontFamily = font,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                )
            }
        }

        // Project selector
        Row (
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

        // Project information
        AnimatedContent(
            targetState = selectedProject,
            contentAlignment = Alignment.Center,
            transitionSpec = { slideAnimation() },
        ) { target ->
            when (target) {
                Project.KVANTAGE -> {}
                Project.DOTFOLIO -> {}
                Project.BLANKET -> {}
            }
        }
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
                .onPointerEvent(PointerEventType.Enter) { isPointerHovering = true }
                .onPointerEvent(PointerEventType.Exit) { isPointerHovering = false }
                .onPointerEvent(PointerEventType.Press) { onProjectClick() }
        ) {
            RoundedPicture(
                painter = project.drawableRes,
                modifier = Modifier
                    .background(Color.White)
                    .size(animatedIconSize)
                    .alpha(animatedAlpha)
            )
        }
    }
}