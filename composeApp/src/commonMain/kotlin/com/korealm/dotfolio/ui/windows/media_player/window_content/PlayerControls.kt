package com.korealm.dotfolio.ui.windows.media_player.window_content

import com.korealm.dotfolio.state.MediaPlayerState

// I consider rewind and shuffle non-necessary features for this portfolio.
// The goal is to introduce and tell more about me, not to build a full UX and a fully functional media player.

enum class PlayerControls {
    PLAY,
    PAUSE,
    PREVIOUS,
    NEXT
}

expect fun playAudio(
    playerState: MediaPlayerState
)


//expect fun PauseAudio()

// Next and prev buttons in the player are VERY similar, to not say that they have the same exact logic, with the only difference that one moves forward and the other backwards.
// This function will only be called from prev and nex buttons in the player. So it only has one job.

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
}