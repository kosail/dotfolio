package com.korealm.dotfolio.ui.windows.media_player.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.state.AppThemeState
import com.korealm.dotfolio.state.MediaPlayerState
import com.korealm.dotfolio.ui.HoverableSymbolicIconButton
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MediaListRow(
    audioName: String,
    index: Int,
    year: Int,
    duration: String, // Since these are fixed audios, no need of overhead to dynamically getting the length of the audio sources
    color: Color = MaterialTheme.colorScheme.surface,
    onClick: () -> Unit,
    japaneseFont: FontFamily,
    modifier: Modifier = Modifier
) {
    var isHover by remember { mutableStateOf(false) }

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
                SimpleSymbolicIconButton(
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
                text = "${index + 1}.",
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(end = 10.dp)
            )

            Text(
                text = "$audioName",
                fontSize = 15.sp,
                fontFamily = if (index == 2) japaneseFont else null,
                modifier = Modifier
                    .widthIn(min = 280.dp, max = 280.dp)
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

            Text(
                text = duration,
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
    themeState: AppThemeState,
    playerState: MediaPlayerState,
    onPlayClick: () -> Unit,
    japaneseFont: FontFamily,
    modifier: Modifier = Modifier
) {
    Box(
        propagateMinConstraints = true,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
    ) {

        BoxWithConstraints(
            propagateMinConstraints = true,
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(5.dp)
        )
        {
            val containerWidth = constraints.maxWidth

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text( // Actual time
                    text = "${(playerState.currentTime / 3600).toInt()}:${
                        (playerState.currentTime / 60).toInt().toString().padStart(2, '0')
                    }:${(playerState.currentTime % 60).toInt().toString().padStart(2, '0')}",
                    fontSize = 15.sp,
                    modifier = Modifier
                )

                ProgressBar(
                    playerState = playerState,
                    backgroundColor = if (themeState.isDarkTheme) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.8f) else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier
                        .widthIn(max = (containerWidth * 0.8).dp)
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                )

                Text( // Total duration
                    text = "${(playerState.duration / 3600).toInt()}:${
                        (playerState.duration / 60).toInt().toString().padStart(2, '0')
                    }:${(playerState.duration % 60).toInt().toString().padStart(2, '0')}",
                    fontSize = 15.sp,
                    modifier = Modifier
                )
            }
        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(5.dp)
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
                        painter = painterResource(  if (playerState.itemIndex == 2) Res.drawable.me_and_my_neko else Res.drawable.me_and_my_cat ),
                        contentDescription = null,
                        modifier = Modifier
                    )
                }


                Column(
                    modifier = Modifier.padding(start = 13.dp, top = 15.dp)
                ) {
                    val names = listOf(
                        Res.string.media_player__recordings_en,
                        Res.string.media_player__recordings_es,
                        Res.string.media_player__recordings_jp
                    )

                    val name = stringResource(names[playerState.itemIndex])
                    val displayName = when (playerState.itemIndex) {
                        2 -> if (name.length > 8) name.substring(0, 8) + "..." else name  // Japanese
                        else -> if (name.length > 15) name.substring(0, 15) + "..." else name  // English/Spanish
                    }

                    Text(
                        text = displayName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = if (playerState.itemIndex == 2) japaneseFont else null,
                        modifier = Modifier
                    )

                    Spacer(Modifier.height(5.dp))

                    // Tricky line. I know it can be simplified, done in a clearer way. But it's ok, there are only 3 audios. It's not a real media player.
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
                .align(Alignment.Center)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 30.dp, start = 60.dp)
            ) {
                Row( // First two buttons. I needed a nested Row due top padding alignment issues
                    modifier = Modifier
                        .padding(top = 20.dp, start = 25.dp, end = 15.dp)
                ) {
                    Surface(
                        color = Color.Transparent,
                        modifier = Modifier.padding(top = 14.dp)
                    ){
                        SimpleSymbolicIconButton(
                            icon = Res.drawable.media_playlist_shuffle_symbolic,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Spacer(Modifier.width(15.dp))

                    HoverableSymbolicIconButton(
                        icon = Res.drawable.media_skip_backward_symbolic,
                        boxModifier = Modifier
                    ) {
                        controlMedia(
                            playerState = playerState,
                            action = PlayerControls.PREVIOUS
                        )
                    }
                }

                // Using images instead of animated circular progress bar to improve performance, or well, to not worsen performance on unnecessary things
                Surface(
                    color = Color.Transparent,
                    modifier = Modifier.padding(top = 15.dp)
                ) {
                    val painter = if (playerState.isBuffering) {
                        Res.drawable.media_player_loading_3
                    } else {
                        if (playerState.isPlaying) Res.drawable.media_player_pause else Res.drawable.media_player_play
                    }

                    Image(
                        painter = painterResource(painter),
                        contentDescription = null,
                        modifier = Modifier
                            .onPointerEvent(PointerEventType.Press) { onPlayClick() }
                    )

                }

                Row( // First two buttons. I needed a nested Row due top padding alignment issues
                    modifier = Modifier
                        .padding(top = 20.dp, start = 15.dp)
                ) {
                    HoverableSymbolicIconButton(
                        icon = Res.drawable.media_skip_forward_symbolic,
                        boxModifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        controlMedia(
                            playerState = playerState,
                            action = PlayerControls.NEXT
                        )
                    }

                    Spacer(Modifier.width(15.dp))
                    Surface(
                        color = Color.Transparent,
                        modifier = Modifier.padding(top = 14.dp)
                    ) {
                        SimpleSymbolicIconButton(
                            icon = Res.drawable.media_repeat_symbolic,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }
        }
    }
}