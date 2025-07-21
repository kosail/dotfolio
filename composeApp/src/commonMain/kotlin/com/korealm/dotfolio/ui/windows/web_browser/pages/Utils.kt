package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.utils.encodeText
import com.korealm.dotfolio.utils.openInNewTab

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
            hoverDurationMillis = 0,
            unhoverColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
            hoverColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        ),
        modifier = modifier
            .fillMaxHeight()
    )
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