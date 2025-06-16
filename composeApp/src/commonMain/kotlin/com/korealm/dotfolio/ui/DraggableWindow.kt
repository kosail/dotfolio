import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.korealm.dotfolio.state.AppThemeState
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.window_close_dark
import dotfolio.composeapp.generated.resources.window_close_light
import dotfolio.composeapp.generated.resources.window_maximize_dark
import dotfolio.composeapp.generated.resources.window_maximize_light
import dotfolio.composeapp.generated.resources.window_minimize_dark
import dotfolio.composeapp.generated.resources.window_minimize_light
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@Composable
fun Window(
    themeState: AppThemeState,
    onWindowClose: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var offset by remember { mutableStateOf(Offset.Zero) }

    Surface(
        shape = RoundedCornerShape(6.dp),
        shadowElevation = 8.dp,
        modifier = Modifier
            .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
            .then(modifier)
            .width(600.dp)
    ) {
        Column {
            // Title bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            offset += dragAmount
                        }
                    }
                    .padding(horizontal = 4.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row {
                    IconButton(onClick = { /* Minimize logic */ }) {
                        Icon(
                            painter = painterResource(if (themeState.isDarkTheme) Res.drawable.window_minimize_dark else Res.drawable.window_minimize_light),
                            contentDescription = "Minimize"
                        )
                    }

                    IconButton(onClick = { /* Maximize logic */ }) {
                        Icon(
                            painter = painterResource(if (themeState.isDarkTheme) Res.drawable.window_maximize_dark else Res.drawable.window_maximize_light),
                            contentDescription = "Maximize"
                        )
                    }

                    IconButton(onClick = onWindowClose) {
                        Icon(
                            painter = painterResource(if (themeState.isDarkTheme) Res.drawable.window_close_dark else Res.drawable.window_close_light),
                            contentDescription = "Close"
                        )
                    }
                }
            }

            // Window content
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                content()
            }
        }
    }
}
