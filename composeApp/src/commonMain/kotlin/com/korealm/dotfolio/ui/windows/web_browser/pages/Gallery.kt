package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.ui.windows.web_browser.pages.general.CustomVerticalScrollbar
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.Post
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostBody
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostFooter
import com.korealm.dotfolio.ui.windows.web_browser.pages.post.PostHeader
import dotfolio.composeapp.generated.resources.*
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

@Composable
private fun GalleryGrid(
    modifier: Modifier = Modifier
) {
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
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}
