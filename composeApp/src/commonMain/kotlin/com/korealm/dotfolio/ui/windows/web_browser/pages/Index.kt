package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.tsuki
import org.jetbrains.compose.resources.painterResource

@Composable
fun Index(
    modifier: Modifier = Modifier
) {
    Image( // Background image
        painter = painterResource(Res.drawable.tsuki),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )

    
}