package com.korealm.dotfolio.ui.windows.web_browser.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Index(
    modifier: Modifier = Modifier
) {
    val mPlusFontFamily = FontFamily(
        Font(Res.font.MPLUS1p, FontWeight.Normal),
    )

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
            color = MaterialTheme.colorScheme.background.copy(alpha = 0.65f),
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)),
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                IndexSidebar(
                    defaultFont = mPlusFontFamily,
                    modifier = Modifier
                        .weight(0.35f)
                        .fillMaxHeight()
                )

                VerticalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                )

                Foo(
                    defaultFont = mPlusFontFamily,
                    modifier = Modifier
                        .weight(0.65f)
                        .fillMaxHeight()
                )
            }
        }

    }
}

@Composable
fun IndexSidebar(
    defaultFont: FontFamily,
    modifier: Modifier = Modifier,
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()
        ) {
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.1f))

            ) {
                Image(
                    painter = painterResource(Res.drawable.milumu),
                    contentDescription = null,
                    modifier = Modifier
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                SimpleSymbolicIconButton(
                    icon = Res.drawable.home_symbolic,
                    modifier = Modifier.size(25.dp)
                )
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Text(
                text = stringResource(Res.string.web_browser_web_name),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = defaultFont
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(Res.string.web_browser_web_description).trimIndent(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = defaultFont
            )
        }
    }
}

@Composable
fun Foo(
    defaultFont: FontFamily,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .scrollable(rememberScrollState(0), Orientation.Vertical)
//            .background(Color.Red)
    ) {}
}