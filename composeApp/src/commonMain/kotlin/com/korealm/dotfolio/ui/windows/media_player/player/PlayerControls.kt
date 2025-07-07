package com.korealm.dotfolio.ui.windows.media_player.player

import com.korealm.dotfolio.state.MediaPlayerState

// I consider rewind and shuffle no necessary features for this portfolio.
// The goal is to introduce and tell more about me, not to build a full UX and a fully functional media player.

enum class PlayerControls {
    PLAY,
    PAUSE,
    PREVIOUS,
    NEXT
}


// Play/pause and prev/next buttons in the player are VERY similar, to not say that they have the same exact logic

fun playToggler(
    playerState: MediaPlayerState,
    action: PlayerControls
) {
    MediaPlayer.bindState(playerState) // Bind JS / JVM events changes with Compose, on every call. This is safe because MediaPlayer is a singleton

    when (action) {
        PlayerControls.PLAY -> {
            MediaPlayer.let {
                if (it.isPlaying()) {
                    it.pause()
                    it.clearSource()
                }

                it.setSource(playerState.currentPlayingItem.path)
                it.play()
            }
        }
        PlayerControls.PAUSE -> { MediaPlayer.pause() }
        else -> Unit // Do nothing
    }
}

fun changeAudio(
    playerState: MediaPlayerState,
    action: PlayerControls
) {
    // Let's keep it easy
    var index = playerState.itemIndex

    if (action == PlayerControls.NEXT && index == 2 ||
        action == PlayerControls.PREVIOUS && index == 0) return // Don't rewind

    if (action == PlayerControls.NEXT) index++ else index--

    playerState.changePlayingItem(Audio.entries[index])
    playToggler(playerState, PlayerControls.PAUSE)
    playToggler(playerState, PlayerControls.PLAY)
}