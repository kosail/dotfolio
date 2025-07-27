package com.korealm.dotfolio.ui.windows.file_explorer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FileExplorerWindowContent(
    title: StringResource,
    elements: List<Pair<DrawableResource, StringResource>>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
        ) { FileExplorerToolbar(title = title) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.95f),
        ) {
            FileExplorerSidebar(
                modifier = Modifier.weight(.2f)
            )


            FileExplorerMain(
                elements = elements,
                modifier = Modifier.weight(.8f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.05f)
                .background(Color.Red)
        ) {
            // TODO: MAKE HERE THE ELEMENT COUNT AND THE TWO ICONS AT THE END
        }
    }
}

@Composable
fun FileExplorerMain(
    elements: List<Pair<DrawableResource, StringResource>>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 12.dp)
    ) {
        elements.forEach { (logo, name) ->
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .size(height = 140.dp, width = 130.dp)
                    .padding(12.dp)
            ) {
                Image(
                    painter = painterResource(logo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(40.dp)
                )

                Spacer(Modifier.height(20.dp))

                Text(
                    text = stringResource(name),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    softWrap = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}