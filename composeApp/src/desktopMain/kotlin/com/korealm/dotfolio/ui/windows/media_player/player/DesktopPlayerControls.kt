package com.korealm.dotfolio.ui.windows.media_player.player

import com.korealm.dotfolio.state.MediaPlayerState
import dotfolio.composeapp.generated.resources.Res
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.ByteArrayInputStream
import java.io.File
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.LineEvent

actual object MediaPlayer {
    private var activeAudioPath = ""
    private lateinit var audioStream: AudioInputStream
    private val clip = AudioSystem.getClip()

    private var state: MediaPlayerState? = null
    private val scope = MainScope()

    init {
        // I suspect this will cause issues...
        setSource(Audio.RECORDING_ES.path)
    }

    actual fun bindState(state: MediaPlayerState) {
        this.state = state
    }

    actual fun setSource(path: String) {
        if (activeAudioPath == path) return
        activeAudioPath = path

        scope.launch { // Async needed due byteArray loading. It's mandatory for compose on raw files...
            // Stop and reset existing clip
            if (clip.isOpen) {
                clip.stop()
                clip.flush()
                clip.close()
            }

            val byteArray = Res.readBytes(path)
            val stream = AudioSystem.getAudioInputStream(ByteArrayInputStream(byteArray))

            clip.open(stream)

            state?.duration = (clip.microsecondLength / 1_000_000).toInt()

            clip.addLineListener { event ->
                if (event.type == LineEvent.Type.STOP) {
                    state?.isPlaying = false
                    if (clip.framePosition >= clip.frameLength) {
                        clip.setFramePosition(0)
                        state?.currentTime = 0
                    }
                }
            }
        }
    }


    actual fun clearSource() {
        clip.flush()
    }

    actual fun play() {
        clip.start()
        state?.isPlaying = true

        scope.launch {
            while (clip.isRunning) {
                state?.currentTime = (clip.microsecondLength / 1_000_000).toInt()
                delay(100)
            }
        }
    }

    actual fun pause() {
        clip.stop()
        state?.isPlaying = false
    }

    actual fun isPlaying(): Boolean = clip.isRunning
}