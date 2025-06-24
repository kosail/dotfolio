package com.korealm.dotfolio.ui.windows.media_player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.*

@Composable
fun MediaPlayerWindowContent(
    modifier: Modifier = Modifier
) {
    Box(
        propagateMinConstraints = true,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        var selectedAudio by remember { mutableStateOf(Audio.RECORDING_EN) } // Initialize the player without any audio

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                MainSectionSideBar(
                    modifier = Modifier
                )

                MainSection(
                    selectedAudio = selectedAudio,
                    onSelectedAudioChange = { selectedAudio = it },
                    modifier = Modifier
                )
            }

            PlayerSection(
                selectedAudio = selectedAudio,
                modifier = Modifier.weight(0.35f)
            )
        }
    }
}


@Composable
fun MainSectionSideBar(
    modifier: Modifier = Modifier
) {

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(0.09f)
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
    ) {
        Column (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
        ) {
            SymbolicIconButton(
                icon = Res.drawable.hamburger_menu_symbolic,
                modifier = Modifier.size(22.dp)
            )

            Spacer(Modifier.height(20.dp))
            SymbolicIconButton(
                icon = Res.drawable.find_ltr_symbolic,
                modifier = Modifier.size(27.dp)
            )

            Spacer(Modifier.height(20.dp))
            SymbolicIconButton(
                icon = Res.drawable.home_symbolic,
                modifier = Modifier.size(21.dp)
            )

            Spacer(Modifier.height(20.dp))
            Box( // This box just mimics as if the music icon was a button and it is selected
                contentAlignment = Alignment.CenterStart,
                modifier = modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f))
            ) {
                VerticalDivider(
                    thickness = 3.dp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(19.dp)
                        .padding(start = 3.dp)
                        .clip(RoundedCornerShape(2.dp))
                )

                SymbolicIconButton(
                    icon = Res.drawable.music_note_symbolic,
                    modifier = Modifier
                        .size(21.dp)
                        .align(Alignment.Center)
                )
            }

            Spacer(Modifier.height(19.dp))
            SymbolicIconButton(
                icon = Res.drawable.video_film_symbolic,
                modifier = Modifier.size(22.dp)
            )

            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                modifier = Modifier.padding(vertical = 20.dp)
            )

            SymbolicIconButton(
                icon = Res.drawable.media_repeat_playlist,
                modifier = Modifier.size(23.dp)
            )

            Spacer(Modifier.height(20.dp))
            SymbolicIconButton(
                icon = Res.drawable.media_album_cover_symbolic,
                modifier = Modifier.size(27.dp)
            )
        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        ) {
            SymbolicIconButton(
                icon = Res.drawable.settings_symbolic,
                modifier = Modifier
                    .size(22.dp)
            )
        }
    }
}


@Composable
fun MainSection(
    selectedAudio: Audio,
    onSelectedAudioChange: (Audio) -> Unit,
    modifier: Modifier = Modifier
) {
    // Needed for the year on top of the album and for each song
    val year = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(50.dp)
    ) {
        Row(
            modifier = Modifier
        ) {
            Surface(
                color = Color.Transparent,
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier.size(180.dp)
             ) {
                Image(
                    painter = painterResource(Res.drawable.me_and_my_cat),
                    contentDescription = stringResource(Res.string.media_player__album_content_desc),
                    modifier = Modifier
                )
            }

            Column (
                modifier = Modifier
                    .padding(start = 18.dp, top = 3.dp)
            ) {
                Text(
                    text = stringResource(Res.string.media_player__album),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(Res.string.media_player__artist),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(15.dp))

                val fullDescription = "$year • ${stringResource(Res.string.media_player__genre)} • ${stringResource(Res.string.media_player__audios_count)}"

                Text(
                    text = fullDescription,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                ) {
                    HeaderButton(
                        icon = Res.drawable.media_playback_start_symbolic,
                        text = pluralStringResource(Res.plurals.media_player__play, 2),
                        containerColor = Color(0xFFCF3E0B),
                        contentColor = Color.White,
                        onClick = {},
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    HeaderButton(
                        icon = Res.drawable.media_playlist_shuffle_symbolic,
                        text = stringResource(Res.string.media_player__shuffle_and_play),
                        onClick = {},
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    HeaderButton(
                        icon = Res.drawable.add_plus_symbolic,
                        text = stringResource(Res.string.media_player__add_to),
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    HeaderButton(
                        icon = Res.drawable.edit_symbolic,
                        text = stringResource(Res.string.media_player__edit_info)
                    )
                }
            }
        }

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .align(Alignment.Center)

        ) {
            Column(
                modifier = Modifier
                    .padding(top = 250.dp)
            ) {
                val names = stringArrayResource(Res.array.recordings)

                Audio.entries.forEachIndexed { index, audio ->
                    MediaListRow(
                        audioName = names[index],
                        index = index,
                        year = year,
                        color = if (index % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f) else MaterialTheme.colorScheme.surface,
                        onClick = { onSelectedAudioChange(audio) },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderButton(
    icon: DrawableResource,
    text: String,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        color = containerColor,
        tonalElevation = 4.dp,
        shadowElevation = 3.dp,
        border = BorderStroke(1.dp, containerColor.copy(alpha = 0.9f)),
        modifier = modifier
            .then(
                if (onClick != null) {
                    modifier.clickable(onClick = onClick)
                } else {
                    modifier
                }
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp)
        ) {
            SymbolicIconButton(
                icon = icon,
                tint = contentColor
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = text,
                fontSize = 14.sp,
                color = contentColor
            )
        }
    }
}

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
        mp3Bytes = Res.readBytes(Audio.values()[index].path)
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

@Composable
fun PlayerSection(
    selectedAudio: Audio,
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
                    val artist_n_album = "${stringResource(Res.string.media_player__artist)} • ${stringResource(Res.string.media_player__album)}"
                    
                    Text(
                        text = artist_n_album.substring(0, 28) + "...",
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
                    .padding(top = 35.dp, start = 10.dp)
            ) {
                SymbolicIconButton(
                    icon = Res.drawable.media_playlist_shuffle_symbolic,
                    modifier = Modifier.size(22.dp)
                )

                Spacer(Modifier.width(20.dp))
                SymbolicIconButton(
                    icon = Res.drawable.media_skip_backward_symbolic,
                    modifier = Modifier.size(25.dp)
                )

                // TODO: Play goes here

                Spacer(Modifier.width(20.dp))
                SymbolicIconButton(
                    icon = Res.drawable.media_skip_forward_symbolic,
                    modifier = Modifier.size(25.dp)
                )

                Spacer(Modifier.width(20.dp))
                SymbolicIconButton(
                    icon = Res.drawable.media_repeat_symbolic,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}