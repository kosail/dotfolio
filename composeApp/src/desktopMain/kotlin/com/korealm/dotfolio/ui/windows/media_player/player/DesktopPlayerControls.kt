package com.korealm.dotfolio.ui.windows.media_player.player

import com.korealm.dotfolio.state.MediaPlayerState
import com.korealm.dotfolio.ui.windows.media_player.player.Audio
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.LineEvent

actual object MediaPlayer {
    private var activeAudioPath = ""
    private var audioStream = AudioSystem.getAudioInputStream(File(activeAudioPath))
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
        if (activeAudioPath != path) return

        activeAudioPath = path
        audioStream = AudioSystem.getAudioInputStream(File(activeAudioPath))
        clip.let {
            it.open(audioStream)

            it.addLineListener { event ->
                when (event.type) {
                    LineEvent.Type.OPEN -> {
                        state?.duration = clip.frameLength
                    }

                    LineEvent.Type.STOP -> {
                        state?.isPlaying = false

                        if (clip.framePosition >= clip.frameLength) state?.currentTime = 0
                    }
                }
            }
        }
    }

    actual fun clearSource() {
        clip.flush()
    }

    actual fun play() {
        scope.launch {
            clip.start()
            state?.isPlaying = true
        }

        scope.launch {
            while (isPlaying()) {
                state?.currentTime = (clip.microsecondLength / 1000).toInt()
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