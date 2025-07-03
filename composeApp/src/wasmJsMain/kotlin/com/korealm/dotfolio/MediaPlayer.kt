package com.korealm.dotfolio

import com.korealm.dotfolio.state.MediaPlayerState
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLAudioElement

object MediaPlayer {
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

    fun bind(state: MediaPlayerState) {
        this.state = state
        setSource(state.currentPlayingItem.path)
    }

    fun setSource(path: String) {
        if (audioElement.src.endsWith(path)) return // audio path already set
        audioElement.src = path
        audioElement.load()
    }

    fun clearSource() {
        audioElement.src = ""
    }

    fun play() {
        scope.launch {
            audioElement.play()
            state?.isPlaying = true
        }
    }

    fun pause() {
        audioElement.pause()
        state?.isPlaying = false
    }

    fun isPlaying() = !audioElement.paused
}