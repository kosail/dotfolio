package com.korealm.dotfolio.ui.windows.media_player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.korealm.dotfolio.state.MediaPlayerState
import com.korealm.dotfolio.state.rememberMediaPlayerState
import com.korealm.dotfolio.ui.windows.media_player.window_content.Audio
import com.korealm.dotfolio.ui.windows.media_player.window_content.MainSection
import com.korealm.dotfolio.ui.windows.media_player.window_content.MainSectionSideBar
import com.korealm.dotfolio.ui.windows.media_player.window_content.PlayerSection

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
        var playerState = rememberMediaPlayerState()

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
                    onPlayClick = {
                        playerState.let {
                            it.isPlaying = true
                            it.changePlayingItem(Audio.entries[0])
                        }
                    },
                    onSelectedAudioChange = { playerState.changePlayingItem(it) },
                    modifier = Modifier
                )
            }

            PlayerSection(
                playerState = playerState,
                onPlayClick = { playerState.isPlaying = !playerState.isPlaying }, // Needed by play/stop button
                modifier = Modifier.weight(0.35f)
            )
        }
    }
}


