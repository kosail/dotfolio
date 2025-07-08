package com.korealm.dotfolio.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.korealm.dotfolio.ui.windows.media_player.player.Audio

class MediaPlayerState(
    initialPlayingState: Boolean,
    initialBufferingState: Boolean, // This may be useful for web target, or I may end deleting it
    initialPlayingItem: Audio
) {
    var isPlaying by mutableStateOf(initialPlayingState)
    var isBuffering by mutableStateOf(initialBufferingState)
    var currentTime by mutableFloatStateOf(0f)
    var duration by mutableFloatStateOf(0f)

    // To avoid initial progress division by zero
    val progress: Float
    get() = if (duration == 0f) 0f else currentTime / duration

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