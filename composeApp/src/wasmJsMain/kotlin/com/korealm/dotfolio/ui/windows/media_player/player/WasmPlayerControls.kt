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
            state?.isBuffering = true

            onended = {
                state?.isPlaying = false
                state?.currentTime = 0
            }

            ontimeupdate = { // TODO: Seems like this is not working... I'll check this later
                state?.currentTime = audioElement.currentTime.toInt()
            }

            onloadedmetadata = {
                state?.isBuffering = false
                state?.duration = audioElement.duration.toInt()
            }
        }
    }

    actual fun bindState(state: MediaPlayerState) {
        this.state = state
    }

    actual fun setSource(path: String, onReady: () -> Unit) {
        if (audioElement.src.endsWith(path)) {
            onReady()
            return
        }

        audioElement.src = path
        audioElement.load()

        audioElement.onloadedmetadata = {
            state?.isBuffering = false
            state?.duration = audioElement.duration.toInt()
            onReady() // ✅ Now it's really ready to play
        }
    }


    actual fun clearSource() {
        audioElement.src = ""
    }

    actual fun play() {
        scope.launch {
            audioElement.play()
            state?.isBuffering = false
            state?.isPlaying = true
        }
    }

    actual fun pause() {
        audioElement.pause()
        state?.isPlaying = false
    }

    actual fun isPlaying(): Boolean = !audioElement.paused
}