package com.korealm.dotfolio.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.Selawik
import org.jetbrains.compose.resources.Font

@Composable
fun MicaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Windows 11 Light Theme (Mica Light)
    val Win11LightPrimary = Color(0xFF0067C0)              // Default accent blue (Active)
    val Win11LightPrimaryContainer = Color(0xFFD0E7FF)     // Light blue for containers

    val Win11LightSecondary = Color(0xFF6264A7)            // Fluent purple
    val Win11LightSecondaryContainer = Color(0xFFE0DEFA)

    val Win11LightTertiary = Color(0xFF498205)             // Fluent green
    val Win11LightTertiaryContainer = Color(0xFFDFF6DD)

    val Win11LightBackground = Color(0xFFF2F2F2)           // Window background (Mica base)
    val Win11LightSurface = Color(0xFFFFFFFF)              // Surface like dialogs or cards
    val Win11LightSurfaceVariant = Color(0xFFE5E5E5)       // Borders / controls background

    val Win11LightText = Color(0xFF1B1B1B)                 // Default text (black-variant)
    val Win11LightError = Color(0xFFD13438)                // Fluent error red
    val Win11LightErrorContainer = Color(0xFFFFDAD6)

    fun win11LightColors() = lightColorScheme(
        primary = Win11LightPrimary,
        onPrimary = Color.White,
        primaryContainer = Win11LightPrimaryContainer,
        onPrimaryContainer = Win11LightText,

        secondary = Win11LightSecondary,
        onSecondary = Color.White,
        secondaryContainer = Win11LightSecondaryContainer,
        onSecondaryContainer = Win11LightText,

        tertiary = Win11LightTertiary,
        onTertiary = Color.White,
        tertiaryContainer = Win11LightTertiaryContainer,
        onTertiaryContainer = Win11LightText,

        background = Win11LightBackground,
        onBackground = Win11LightText,

        surface = Win11LightSurface,
        onSurface = Win11LightText,
        surfaceVariant = Win11LightSurfaceVariant,
        onSurfaceVariant = Color(0xFF444444),  // Fluent neutral border

        error = Win11LightError,
        onError = Color.White,
        errorContainer = Win11LightErrorContainer,
        onErrorContainer = Win11LightText
    )

    // Windows 11 Dark Theme (Mica Dark)
    val Win11DarkPrimary = Color(0xFF3794FF)               // Accent blue (brightened for dark)
    val Win11DarkPrimaryContainer = Color(0xFF003A6D)      // Blue tint container

    val Win11DarkSecondary = Color(0xFFB4A7D6)             // Fluent purple tint
    val Win11DarkSecondaryContainer = Color(0xFF433D5A)

    val Win11DarkTertiary = Color(0xFFA4C400)              // Fluent lime (green accent)
    val Win11DarkTertiaryContainer = Color(0xFF3C4A1B)

    val Win11DarkBackground = Color(0xFF1E1E1E)            // Dark Mica base
    val Win11DarkSurface = Color(0xFF2C2C2C)               // Slightly lighter for surfaces
    val Win11DarkSurfaceVariant = Color(0xFF3F3F46)        // Card/border background

    val Win11DarkText = Color(0xFFF2F2F2)                  // Fluent white
    val Win11DarkError = Color(0xFFFF5F5F)                 // Error red on dark
    val Win11DarkErrorContainer = Color(0xFF5A1B1B)

    fun win11DarkColors() = darkColorScheme(
        primary = Win11DarkPrimary,
        onPrimary = Color.Black,
        primaryContainer = Win11DarkPrimaryContainer,
        onPrimaryContainer = Win11DarkText,

        secondary = Win11DarkSecondary,
        onSecondary = Color.Black,
        secondaryContainer = Win11DarkSecondaryContainer,
        onSecondaryContainer = Win11DarkText,

        tertiary = Win11DarkTertiary,
        onTertiary = Color.Black,
        tertiaryContainer = Win11DarkTertiaryContainer,
        onTertiaryContainer = Win11DarkText,

        background = Win11DarkBackground,
        onBackground = Win11DarkText,

        surface = Win11DarkSurface,
        onSurface = Win11DarkText,
        surfaceVariant = Win11DarkSurfaceVariant,
        onSurfaceVariant = Color(0xFFC9C9C9),  // Fluent light gray

        error = Win11DarkError,
        onError = Color.Black,
        errorContainer = Win11DarkErrorContainer,
        onErrorContainer = Win11DarkText
    )

    val selawikFontFamily = FontFamily(
        Font(Res.font.Selawik, weight = FontWeight.Normal)
    )

    val DefaultTypography = Typography().run {
        Typography(
            displayLarge = displayLarge.copy(fontFamily = selawikFontFamily),
            displayMedium = displayMedium.copy(fontFamily = selawikFontFamily),
            displaySmall = displaySmall.copy(fontFamily = selawikFontFamily),
            headlineLarge = headlineLarge.copy(fontFamily = selawikFontFamily),
            headlineMedium = headlineMedium.copy(fontFamily = selawikFontFamily),
            headlineSmall = headlineSmall.copy(fontFamily = selawikFontFamily),
            titleLarge = titleLarge.copy(fontFamily = selawikFontFamily),
            titleMedium = titleMedium.copy(fontFamily = selawikFontFamily),
            titleSmall = titleSmall.copy(fontFamily = selawikFontFamily),
            bodyLarge = bodyLarge.copy(fontFamily = selawikFontFamily),
            bodyMedium = bodyMedium.copy(fontFamily = selawikFontFamily),
            bodySmall = bodySmall.copy(fontFamily = selawikFontFamily),
            labelLarge = labelLarge.copy(fontFamily = selawikFontFamily),
            labelMedium = labelMedium.copy(fontFamily = selawikFontFamily),
            labelSmall = labelSmall.copy(fontFamily = selawikFontFamily),
        )
    }

    MaterialTheme(
        colorScheme = if (darkTheme) win11DarkColors() else win11LightColors(),
        typography = DefaultTypography,
        content = content
    )
}