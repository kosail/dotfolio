package com.korealm.dotfolio.ui.windows.media_player.player

import com.korealm.dotfolio.MediaPlayer
import com.korealm.dotfolio.state.MediaPlayerState

actual fun playToggler(
    playerState: MediaPlayerState,
    action: PlayerControls
) {
    MediaPlayer.bind(playerState) // Bind JS and DOM events changes with Compose, on every call. This is safe because MediaPlayer is a singleton

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