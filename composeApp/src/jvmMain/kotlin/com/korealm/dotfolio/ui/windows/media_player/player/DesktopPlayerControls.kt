package com.korealm.dotfolio.ui.windows.media_player.player

import com.korealm.dotfolio.state.MediaPlayerState
import dotfolio.composeapp.generated.resources.Res
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.ByteArrayInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.LineEvent

actual object MediaPlayer {
    private var activeAudioPath = ""
    private val clip = AudioSystem.getClip()

    private var state: MediaPlayerState? = null
    private val scope = MainScope()

    actual fun bindState(state: MediaPlayerState) {
        this.state = state
    }

    actual fun setSource(path: String, onReady: () -> Unit) {
        if (activeAudioPath == path) return
        activeAudioPath = path

        scope.launch { // Async needed due byteArray loading. It's mandatory for compose on raw files...
            // Stop and reset existing clip
            if (clip.isOpen) clearSource()

            val byteArray = Res.readBytes(path)
            val stream = AudioSystem.getAudioInputStream(ByteArrayInputStream(byteArray))

            clip.open(stream)

            state?.duration = (clip.microsecondLength / 1_000_000).toFloat()

            clip.addLineListener { event ->
                if (event.type == LineEvent.Type.STOP) { // Rewind
                    state?.isPlaying = false
                    if (clip.framePosition >= clip.frameLength) {
                        clip.framePosition = 0
                        state?.currentTime = 0f
                    }
                }
            }

            onReady()
        }
    }


    actual fun clearSource() {
        clip.stop()
        clip.flush()
        clip.close()
    }

    actual fun play() {
        if (! clip.isOpen) {
            println("Clip is not open")
            return
        }

        clip.start()
        state?.isPlaying = true

        scope.launch {
            while (clip.isRunning) {
                state?.currentTime = (clip.microsecondPosition / 1_000_000).toFloat()
                delay(100)
            }
        }
    }

    actual fun pause() {
        clip.stop()
        state?.isPlaying = false
    }

    actual fun isPlaying(): Boolean = clip.isRunning

    actual fun seekTo(seconds: Float) {
        clip?.let {
            it.microsecondPosition = (seconds * 1_000_000L).toLong() // We need to convert seconds to microseconds
        }
    }

}