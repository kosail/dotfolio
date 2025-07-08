package com.korealm.dotfolio.ui.windows.media_player.player

import com.korealm.dotfolio.state.MediaPlayerState


expect object MediaPlayer {
    fun bindState(state: MediaPlayerState)
    fun setSource(path: String, onReady: () -> Unit)
    fun clearSource()
    fun play()
    fun pause()
    fun isPlaying(): Boolean
    fun seekTo(seconds: Float)
}