package com.korealm.dotfolio.ui.windows.media_player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.korealm.dotfolio.state.AppThemeState
import com.korealm.dotfolio.state.MediaPlayerState
import com.korealm.dotfolio.ui.windows.media_player.player.*
import dotfolio.composeapp.generated.resources.MPLUS1p
import dotfolio.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun MediaPlayerWindowContent(
    themeState: AppThemeState,
    playerState: MediaPlayerState,
    modifier: Modifier = Modifier
) {
    Box(
        propagateMinConstraints = true,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        val mplus1pFontFamily = FontFamily(
            Font(Res.font.MPLUS1p, weight = FontWeight.Normal)
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                MainSectionSideBar(
                    modifier = Modifier
                )

                // There's something wrong with me. This kind of programming goes more into the declarative one instead of reactive.
                // I need to learn more about reactive GUIs and refactor this code.
                MainSection(
                    onPlayClick = {
                        playerState.let {
                            it.isPlaying = true
                            it.changePlayingItem(Audio.entries[0])
                        }

                        controlMedia(playerState, PlayerControls.PLAY)
                    },
                    onSelectedAudioChange = {
                        playerState.changePlayingItem(it)
                        controlMedia(playerState, PlayerControls.PLAY)
                    },
                    japaneseFont = mplus1pFontFamily,
                    modifier = Modifier
                )
            }

            PlayerSection(
                themeState = themeState,
                playerState = playerState,
                onPlayClick = {
                    controlMedia(
                        playerState = playerState,
                        action = if (playerState.isPlaying) PlayerControls.PAUSE else PlayerControls.PLAY
                    )
                }, // Needed by play/stop button
                japaneseFont = mplus1pFontFamily,
                modifier = Modifier.weight(0.35f)
            )
        }
    }
}


