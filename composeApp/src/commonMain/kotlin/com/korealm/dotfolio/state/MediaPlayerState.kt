package com.korealm.dotfolio.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.korealm.dotfolio.ui.windows.media_player.window_content.Audio

class MediaPlayerState(
    initialPlayingState: Boolean,
    initialBufferingState: Boolean, // This may be useful for web target
    initialPlayingItem: Audio
) {
    var isPlaying by mutableStateOf(initialPlayingState)
    var isBuffering by mutableStateOf(initialBufferingState)
    var currentTime by mutableIntStateOf(0)

    var duration by mutableIntStateOf(0)
        private set

    // To avoid initial progress division by zero
    var progress = if (duration == 0) 0 else currentTime / duration

    var currentPlayingItem by mutableStateOf(initialPlayingItem)
    private set

    var itemIndex by mutableIntStateOf(0)
    private set

    fun changePlayingItem(item: Audio) {
        currentPlayingItem = item
        itemIndex = Audio.entries.indexOf(item)
    }
}

@Composable
fun rememberMediaPlayerState(
    initialPlayingState: Boolean = false,
    initialBufferingState: Boolean = false,
    initialPlayingItem: Audio = Audio.RECORDING_EN
): MediaPlayerState = remember { MediaPlayerState(initialPlayingState, initialBufferingState, initialPlayingItem) }