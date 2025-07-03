package com.korealm.dotfolio.ui.windows.media_player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.korealm.dotfolio.state.rememberMediaPlayerState
import com.korealm.dotfolio.ui.windows.media_player.player.Audio
import com.korealm.dotfolio.ui.windows.media_player.player.MainSection
import com.korealm.dotfolio.ui.windows.media_player.player.MainSectionSideBar
import com.korealm.dotfolio.ui.windows.media_player.player.PlayerControls
import com.korealm.dotfolio.ui.windows.media_player.player.PlayerSection
import com.korealm.dotfolio.ui.windows.media_player.player.changeAudio
import com.korealm.dotfolio.ui.windows.media_player.player.playToggler

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

                // There's something wrong with me. This kind of programming goes more into the declarative one instead of reactive.
                // I need to learn more about reactive GUIs and refactor this code.
                MainSection(
                    onPlayClick = {
                        playerState.let {
                            it.isPlaying = true
                            it.changePlayingItem(Audio.entries[0])
                        }

                        playToggler(playerState, PlayerControls.PLAY)
                    },
                    onSelectedAudioChange = {
                        playerState.changePlayingItem(it)
                        playToggler(playerState, PlayerControls.PLAY)
                    },
                    modifier = Modifier
                )
            }

            PlayerSection(
                playerState = playerState,
                onPlayClick = {
                    playToggler(
                        playerState = playerState,
                        action = if (playerState.isPlaying) PlayerControls.PAUSE else PlayerControls.PLAY
                    )
                }, // Needed by play/stop button
                modifier = Modifier.weight(0.35f)
            )
        }
    }
}


