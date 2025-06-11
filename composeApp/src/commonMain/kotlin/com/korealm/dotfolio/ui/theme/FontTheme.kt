package com.korealm.dotfolio.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import dotfolio.composeapp.generated.resources.Res
import dotfolio.composeapp.generated.resources.selawik


// Forgive me, please
// I know Composable functions should NEVER have a return value... but I found no solution other than this.
@Composable
fun FontTheme(): FontFamily {

    val selawikFont = FontFamily(
        Font(Res.font.selawik, FontWeight.Thin),
        Font(Res.font.selawik, FontWeight.Light),
        Font(Res.font.selawik, FontWeight.Normal),
        Font(Res.font.selawik, FontWeight.Medium),
        Font(Res.font.selawik, FontWeight.SemiBold),
        Font(Res.font.selawik, FontWeight.Bold),
    )

    // Windows 11 Fluent Design Typography
    val selawikTypography = Typography(
        // Display styles (for large headlines)
        displayLarge = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 42.sp,
            lineHeight = 50.sp,
            letterSpacing = 0.sp
        ),
        displayMedium = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp
        ),
        displaySmall = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            lineHeight = 38.sp,
            letterSpacing = 0.sp
        ),

        // Headline styles (for titles)
        headlineLarge = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.sp
        ),

        // Title styles (for section headers)
        titleLarge = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        titleSmall = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.1.sp
        ),

        // Body styles (for regular text)
        bodyLarge = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),

        // Label styles (for captions and labels)
        labelLarge = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontFamily = selawikFont,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.5.sp
        )
    )

    return selawikFont
}