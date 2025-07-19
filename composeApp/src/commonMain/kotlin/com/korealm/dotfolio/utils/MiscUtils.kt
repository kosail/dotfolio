package com.korealm.dotfolio.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun RoundedPicture(
    painter: DrawableResource,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(painter),
        contentDescription = null,
        modifier = modifier
            .clip(CircleShape)
            .size(35.dp)
    )
}