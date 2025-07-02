package com.korealm.dotfolio.ui.windows.media_player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
        var selectedAudio by remember { mutableStateOf(Audio.RECORDING_EN) } // Initialize the player with the english audio by default
        var isPlaying by remember { mutableStateOf(false) }

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
                        selectedAudio = Audio.entries[0] // Set the first media track

                        // * NOTE: A very cutre way to reset the playing audio to the beginning.
                        // ? This exists because I'm not sure if Compose re-compose the elements when there is a change, but
                        // ? the new value is the same as before (eg. changing isPlaying from true to true)
                        // TODO: I'll check when audio playing is actually implemented and change this accordingly.
//                        if (isPlaying) isPlaying = false // I think will not be necessary tbh

                        isPlaying = true
                    },
                    onSelectedAudioChange = {
                        selectedAudio = it
//                        isPlaying = true // I think will not be necessary because selectedAudio is already triggering a recomposing
                    },
                    modifier = Modifier
                )
            }

            PlayerSection(
                selectedAudio = selectedAudio,
                isPlaying = isPlaying,
                onPlayClick = { isPlaying = !isPlaying }, // Needed by play/stop button
                onAudioChange = { selectedAudio = it }, // Needed by prev/next buttons
                modifier = Modifier.weight(0.35f)
            )
        }
    }
}


