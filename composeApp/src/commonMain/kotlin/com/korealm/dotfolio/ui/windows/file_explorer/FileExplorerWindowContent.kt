package com.korealm.dotfolio.ui.windows.file_explorer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.computer
import dotfolio.composeapp.generated.resources.file_explorer_items
import dotfolio.composeapp.generated.resources.hamburger_four_symbolic
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .weight(.05f)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "${ stringResource(Res.string.file_explorer_items, elements.size) }",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .7f),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                SimpleSymbolicIconButton(
                    icon = Res.drawable.hamburger_four_symbolic,
                    modifier = Modifier
                )

                Spacer(Modifier.width(12.dp))

                SimpleSymbolicIconButton(
                    icon = Res.drawable.computer,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun FileExplorerMain(
    elements: List<Pair<DrawableResource, StringResource>>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        horizontalArrangement = Arrangement.Start,
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
                    modifier = Modifier.height(50.dp)
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