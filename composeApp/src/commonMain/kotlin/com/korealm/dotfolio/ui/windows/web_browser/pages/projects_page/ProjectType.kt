package com.korealm.dotfolio.ui.windows.web_browser.pages.projects_page

import dotfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Project (
    val faviconRes: DrawableResource,
    val bannerRes: DrawableResource,
    val titleRes: StringResource,
    val contentRes: StringResource,
) {
    KVANTAGE (
        faviconRes = Res.drawable.kvantage_favicon,
        titleRes = Res.string.web_browser_projects_kvantage,
        bannerRes = Res.drawable.kvantage_banner,
        contentRes = Res.string.web_browser_projects_kvantage_content
    ),
    DOTFOLIO (
        faviconRes = Res.drawable.favicon,
        titleRes = Res.string.web_browser_projects_dotfolio,
        bannerRes = Res.drawable.dotfolio_banner,
        contentRes = Res.string.web_browser_projects_dotfolio_content
    ),
    BLANKET (
        faviconRes = Res.drawable.rafael_mardojai_blanket_favicon,
        titleRes = Res.string.web_browser_projects_blanket,
        bannerRes = Res.drawable.blanket_banner,
        contentRes = Res.string.web_browser_projects_blanket_content
    )
}