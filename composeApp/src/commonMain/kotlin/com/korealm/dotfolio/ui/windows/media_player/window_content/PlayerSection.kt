package com.korealm.dotfolio.ui.windows.media_player.window_content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SymbolicIconButton
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MediaListRow(
    audioName: String,
    index: Int,
    year: Int,
    color: Color = MaterialTheme.colorScheme.surface,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isHover by remember { mutableStateOf(false) }
    var mp3Bytes by remember { mutableStateOf<ByteArray?>(null) }

    LaunchedEffect(Unit) {
        mp3Bytes = Res.readBytes(Audio.entries[index].path)
    }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .onPointerEvent(PointerEventType.Enter) { isHover = true }
            .onPointerEvent(PointerEventType.Exit) { isHover = false }
            .clickable { onClick() }
            .clip(RoundedCornerShape(5.dp))
            .background(color)
            .border(1.dp, color.copy(alpha = 0.5f))
    ) {
        AnimatedVisibility(isHover) {
            Surface (
                color = Color.Transparent,
                modifier = Modifier.padding(start = 20.dp)
            ){
                SymbolicIconButton(
                    icon = Res.drawable.media_playback_start_symbolic,
                    contentDescription = pluralStringResource(Res.plurals.media_player__play, 1),
                    modifier = Modifier
                )
            }
        }

        Row (
            modifier = Modifier
                .padding(vertical = 10.dp)
                .padding(start = 55.dp, end = 10.dp)
        ) {

            Text(
                text = "${index + 1}.    $audioName",
                fontSize = 15.sp,
                modifier = Modifier
                    .widthIn(min = 300.dp, max = 300.dp)
            )

            Text(
                text = stringResource(Res.string.media_player__artist),
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .widthIn(min = 120.dp, max = 120.dp)
            )

            Text(
                text = "$year",
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .widthIn(min = 50.dp, max = 50.dp)
            )

            Text(
                text = stringResource(Res.string.media_player__genre),
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .widthIn(min = 100.dp, max = 100.dp)
            )

            // TODO: Duration of the songs. I'm not sure if to hardcode them or do it dynamically
            Text(
                text = "01:30",
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 30.dp)
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PlayerSection(
    selectedAudio: Audio,
    isPlaying: Boolean,
    onPlayClick: () -> Unit,
    duration: Int = 124, // FIXME: Temp solution to be able to code the GUI. Later on I'll make an static function to call inside this code
    modifier: Modifier = Modifier
) {
    var progressInSeconds by remember { mutableIntStateOf(20) }

    Box(
        propagateMinConstraints = true,
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {

        Box(
            propagateMinConstraints = true,
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.align(Alignment.TopStart)
        )
        {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 15.dp)
            ) {
                Text( // Actual time
                    text = "${progressInSeconds / 3600}:${
                        (progressInSeconds / 60).toString().padStart(2, '0')
                    }:${(progressInSeconds % 60).toString().padStart(2, '0')}",
                    fontSize = 14.sp,
                    modifier = Modifier
                )

                Spacer(Modifier.width(15.dp))

                // TODO: Replace this shit with a custom progress bar indicator
                LinearProgressIndicator(
                    progress = { progressInSeconds / duration.toFloat() },
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier
                )

                Spacer(Modifier.width(15.dp))

                Text( // Actual time
                    text = "${duration / 3600}:${
                        (duration / 60).toString().padStart(2, '0')
                    }:${(duration % 60).toString().padStart(2, '0')}",
                    fontSize = 14.sp,
                    modifier = Modifier
                )
            }
        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.padding(5.dp)
                ) {
                    Image(
                        painter = painterResource(  if (selectedAudio.ordinal == 2) Res.drawable.me_and_my_neko else Res.drawable.me_and_my_cat ),
                        contentDescription = null,
                        modifier = Modifier
                    )
                }


                Column(
                    modifier = Modifier.padding(start = 13.dp, top = 15.dp)
                ) {
                    val name = stringArrayResource(Res.array.recordings)[selectedAudio.ordinal]
                    val displayName = when (selectedAudio.ordinal) {
                        2 -> if (name.length > 8) name.substring(0, 8) + "..." else name  // Japanese
                        else -> if (name.length > 15) name.substring(0, 15) + "..." else name  // English/Spanish
                    }

                    Text(
                        text = displayName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                    )

                    Spacer(Modifier.height(5.dp))

                    // Tricky line. I know it can be simplified, done in a clearer way, but it's ok there are only 3 audios. It's not a real media player.
                    val artistAndAlbum = "${stringResource(Res.string.media_player__artist)} • ${stringResource(Res.string.media_player__album)}"

                    Text(
                        text = artistAndAlbum.substring(0, 28) + "...",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    )
                }
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 30.dp, start = 10.dp)
            ) {
                Row( // First two buttons. I needed a nested Row due top padding alignment issues
                    modifier = Modifier
                        .padding(top = 20.dp, start = 25.dp, end = 20.dp)
                ) {
                    SymbolicIconButton(
                        icon = Res.drawable.media_playlist_shuffle_symbolic,
                        modifier = Modifier.size(22.dp)
                    ) { /*TODO*/ }

                    Spacer(Modifier.width(20.dp))
                    SymbolicIconButton(
                        icon = Res.drawable.media_skip_backward_symbolic,
                        modifier = Modifier.size(25.dp)
                    ) { /*TODO*/ }
                }

                // Using images instead of animated circular progress bar to improve performance, or well, to not worsen performance on unnecessary things
                Surface(
                    color = Color.Transparent,
                    modifier = Modifier
                ) {
                    Image(
                        painter = painterResource(if (isPlaying) Res.drawable.media_player_pause else Res.drawable.media_player_play),
                        contentDescription = null,
                        modifier = Modifier
                            .onPointerEvent(PointerEventType.Press) { onPlayClick() }
                    )
                }

                Row( // First two buttons. I needed a nested Row due top padding alignment issues
                    modifier = Modifier
                        .padding(top = 20.dp, start = 20.dp)
                ) {
                    SymbolicIconButton(
                        icon = Res.drawable.media_skip_forward_symbolic,
                        modifier = Modifier.size(25.dp)
                    )

                    Spacer(Modifier.width(20.dp))
                    SymbolicIconButton(
                        icon = Res.drawable.media_repeat_symbolic,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
    }
}