package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.korealm.dotfolio.ui.windows.web_browser.pages.general.CustomVerticalScrollbar
import com.korealm.dotfolio.ui.windows.web_browser.pages.general.FullScreenImageVisualizer
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.Post
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostBody
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostFooter
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostHeader
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GalleryPage(
    font: FontFamily,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val boxHeight = with(LocalDensity.current) { constraints.maxHeight.toDp() }

        LazyColumn(
            state = lazyListState,
            contentPadding = PaddingValues(50.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Post(
                    postHeader = {
                        PostHeader(
                            isPinned = true,
                            date = pluralStringResource(Res.plurals.web_browser_months_ago, 9, 9),
                            font = font
                        )
                    },
                    postBody = {
                        PostBody(
                            text = stringResource(Res.string.web_browser_gallery_msg).trimIndent(),
                            font = font
                        )

                        Spacer(Modifier.height(20.dp))

                        GalleryGrid(Modifier.heightIn(max = boxHeight))
                    },
                    postFooter = {
                        PostFooter(
                            numberOfReblogs = "999,311",
                            font = font,
                            tags = arrayOf(
                                Res.string.web_browser_gallery_tag_one,
                                Res.string.web_browser_gallery_tag_two,
                                Res.string.web_browser_gallery_tag_three,
                                Res.string.web_browser_gallery_tag_four
                            )
                        )
                    },
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }
        }

        CustomVerticalScrollbar(
            scrollState = lazyListState,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun GalleryGrid(
    modifier: Modifier = Modifier
) {
    // When click on an image, it selects it to open up the full screen dialog to see the image enlarged
    var selectedImage by remember { mutableStateOf<DrawableResource?>(null) }

    val imageList = remember {
        listOf(
            Res.drawable.gallery_amberol,
            Res.drawable.gallery_windows11,
            Res.drawable.gallery_cozy,
            Res.drawable.gallery_gato,
            Res.drawable.gallery_screenshot,
            Res.drawable.gallery_gnome,
            Res.drawable.gallery_flowers_erika
        )
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minSize = 250.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(items = imageList) { image ->
//            Image(
//                painter = painterResource(image),
//                contentDescription = null,
//                contentScale = ContentScale.Fit,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .wrapContentHeight()
//                    .pointerHoverIcon(PointerIcon.Hand)
//            )

            HoverFadeImage(
                drawable = image,
                modifier = Modifier
                    .clickable { selectedImage = image }
            )
        }
    }

    // To open up images visualizer.
    FullScreenImageVisualizer(
        selectedImage = selectedImage,
        onClick = { selectedImage = null }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HoverFadeImage(
    drawable: DrawableResource,
    modifier: Modifier = Modifier
) {
    var isHovered by remember { mutableStateOf(false) }

    // ColorMatrix to convert image to grayscale
    val grayscaleMatrix = remember {
        ColorMatrix().apply {
            setToSaturation(0f)
        }
    }

    val colorMatrix = if (isHovered) null else grayscaleMatrix

    AnimatedContent(
        targetState = isHovered,
        transitionSpec = {
            fadeIn(animationSpec = tween(100)) togetherWith fadeOut(animationSpec = tween(550))
        }
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = if (colorMatrix != null) ColorFilter.colorMatrix(colorMatrix) else null,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .onPointerEvent(PointerEventType.Enter) { isHovered = true }
                .onPointerEvent(PointerEventType.Exit) { isHovered = false }
                .pointerHoverIcon(PointerIcon.Hand)
        )
    }
}

