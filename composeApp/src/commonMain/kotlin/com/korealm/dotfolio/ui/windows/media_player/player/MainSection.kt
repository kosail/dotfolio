package com.korealm.dotfolio.ui.windows.media_player.player

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.dotfolio.ui.SimpleSymbolicIconButton
import dotfolio.composeapp.generated.resources.*
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource
import kotlin.random.Random

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
            SimpleSymbolicIconButton(
                icon = Res.drawable.hamburger_menu_symbolic,
                modifier = Modifier.size(22.dp)
            )

            Spacer(Modifier.height(20.dp))
            SimpleSymbolicIconButton(
                icon = Res.drawable.find_ltr_symbolic,
                modifier = Modifier.size(27.dp)
            )

            Spacer(Modifier.height(20.dp))
            SimpleSymbolicIconButton(
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

                SimpleSymbolicIconButton(
                    icon = Res.drawable.music_note_symbolic,
                    modifier = Modifier
                        .size(21.dp)
                        .align(Alignment.Center)
                )
            }

            Spacer(Modifier.height(19.dp))
            SimpleSymbolicIconButton(
                icon = Res.drawable.video_film_symbolic,
                modifier = Modifier.size(22.dp)
            )

            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                modifier = Modifier.padding(vertical = 20.dp)
            )

            SimpleSymbolicIconButton(
                icon = Res.drawable.media_repeat_playlist,
                modifier = Modifier.size(23.dp)
            )

            Spacer(Modifier.height(20.dp))
            SimpleSymbolicIconButton(
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
            SimpleSymbolicIconButton(
                icon = Res.drawable.settings_symbolic,
                modifier = Modifier
                    .size(22.dp)
            )
        }
    }
}


@Composable
fun MainSection(
    onPlayClick: () -> Unit,
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
                        onClick = { onPlayClick() },
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    HeaderButton(
                        icon = Res.drawable.media_playlist_shuffle_symbolic,
                        text = stringResource(Res.string.media_player__shuffle_and_play),
                        onClick = {
                            val index = Random.nextInt(0, 3)
                            onSelectedAudioChange(Audio.entries[index])
                        },
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
                val names = listOf(
                    Res.string.media_player__recordings_en,
                    Res.string.media_player__recordings_es,
                    Res.string.media_player__recordings_jp
                )

                Audio.entries.forEachIndexed { index, audio ->
                    MediaListRow(
                        audioName = stringResource(names[index]),
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
            SimpleSymbolicIconButton(
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