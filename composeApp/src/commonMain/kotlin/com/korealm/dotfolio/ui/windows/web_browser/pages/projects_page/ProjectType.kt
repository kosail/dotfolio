package com.korealm.dotfolio.ui.windows.web_browser.pages.projects_page

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Project (
    val faviconRes: DrawableResource,
    val bannerRes: DrawableResource,
    val titleRes: StringResource,
    val isOwn: Boolean,
    val contentComposable: @Composable (font: FontFamily) -> Unit,
) {
    KVANTAGE (
        faviconRes = Res.drawable.kvantage_favicon,
        titleRes = Res.string.web_browser_projects_kvantage,
        bannerRes = Res.drawable.kvantage_banner,
        isOwn = true,
        contentComposable = { font -> KvantageContent(font) }
    ),
    DOTFOLIO (
        faviconRes = Res.drawable.favicon,
        titleRes = Res.string.web_browser_projects_dotfolio,
        bannerRes = Res.drawable.dotfolio_banner,
        isOwn = true,
        contentComposable = { font -> DotfolioContent(font) }
    ),
    BLANKET (
        faviconRes = Res.drawable.rafael_mardojai_blanket_favicon,
        titleRes = Res.string.web_browser_projects_blanket,
        bannerRes = Res.drawable.blanket_banner,
        isOwn = false,
        contentComposable = { font -> BlanketContent(font) }
    )
}