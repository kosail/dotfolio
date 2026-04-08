package com.korealm.dotfolio.ui.windows.web_browser.pages.general

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import com.korealm.dotfolio.utils.encodeText
import com.korealm.dotfolio.utils.openInNewTab
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SlightDivider(
    orientation: Orientation,
    modifier: Modifier = Modifier,
) {
    if (orientation == Orientation.Horizontal) {
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
            modifier = modifier
        )
    } else {
        VerticalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
            modifier = modifier
        )
    }
}

@Composable
fun CustomVerticalScrollbar(
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    VerticalScrollbar(
        adapter = rememberScrollbarAdapter(scrollState),
        style = ScrollbarStyle(
            minimalHeight = 16.dp,
            thickness = 7.dp,
            shape = RoundedCornerShape(4.dp),
            hoverDurationMillis = 200,
            unhoverColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
            hoverColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        ),
        modifier = modifier.fillMaxHeight()
    )
}

@Composable
fun FullScreenImageVisualizer(
    selectedImage: DrawableResource?,
    onClick: () -> Unit,
) {
    // We need another variable to hold the image because when AnimatedVisibility starts exiting, the Image composable inside it is still executed briefly, but selectedImage is already null (nulled when exit of the Dialog) — causing the function to crash.
    // I found no other simple way to solve this.
    val selectedImageCache = selectedImage

    if (selectedImageCache != null) {
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(durationMillis = 250)) + expandIn(animationSpec = tween(durationMillis = 250)),
            exit = fadeOut(animationSpec = tween(durationMillis = 250)) + shrinkOut(animationSpec = tween(durationMillis = 250))
        ) {
            Dialog(
                onDismissRequest = { onClick() },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    usePlatformDefaultWidth = false
                )
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
                        .pointerInput(Unit) {
                            awaitPointerEventScope {
                                while(true) {
                                    val type = awaitPointerEvent().type

                                    when (type) {
                                        PointerEventType.Press -> onClick() // Click anywhere to exit
                                    }
                                }
                            }
                        }
                ) {
                    Image(
                        painter = painterResource(selectedImageCache),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    )
                }
            }
        }
    }
}

// This composable is just to centralize the style of text.
// As you can tell, it literally just passes down the defaults to the Text Composable
@Composable
fun PostText(
    text: String,
    fontSize: TextUnit = 17.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
    font: FontFamily,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        fontFamily = font,
        fontWeight = fontWeight,
        fontSize = fontSize,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun PageBookmarkButton(
    url: String,
    iconRes: DrawableResource,
    titleRes: StringResource,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            openInNewTab(url)
        }
            .padding(horizontal = 20.dp, vertical = 11.dp)
    ) {
        SimpleSymbolicIconButton(
            icon = iconRes,
            modifier = Modifier.size(20.dp),
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = stringResource(titleRes),
            fontSize = 15.sp
        )
    }
}

fun mailTrigger(
    recipient: String,
    subject: String,
    body: String,
) {
    val encodedSubject = encodeText(subject)
    val encodedBody = encodeText(body)
    val fullString = "mailto:$recipient?subject=$encodedSubject&body=$encodedBody"

    openInNewTab(fullString)
}