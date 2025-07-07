package com.korealm.dotfolio.ui.windows.media_player.player

import com.korealm.dotfolio.state.MediaPlayerState
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLAudioElement

actual object MediaPlayer {
    private val audioElement = document.createElement("audio") as HTMLAudioElement
    private var state: MediaPlayerState? = null
    private val scope = MainScope()

    init {
        with (audioElement) {
            volume = 0.9

            onended = {
                state?.isPlaying = false
                state?.currentTime = 0
            }

            ontimeupdate = {
                state?.currentTime = audioElement.currentTime.toInt()
            }

            onloadedmetadata = {
                state?.duration = audioElement.duration.toInt()
            }
        }
    }

    actual fun bindState(state: MediaPlayerState) {
        this.state = state
        setSource(state.currentPlayingItem.path)
    }

    actual fun setSource(path: String) {
        if (audioElement.src.endsWith(path)) return // audio path already set
        audioElement.src = path
        audioElement.load()
    }

    actual fun clearSource() {
        audioElement.src = ""
    }

    actual fun play() {
        scope.launch {
            audioElement.play()
            state?.isPlaying = true
        }
    }

    actual fun pause() {
        audioElement.pause()
        state?.isPlaying = false
    }

    actual fun isPlaying(): Boolean = !audioElement.paused
}