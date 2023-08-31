package com.muedsa.bltv.ui.navigation

sealed class NavigationItems(
    val path: String,
    val pathParams: List<String>? = null,
) {
    data object Home : NavigationItems("home")
    data object VideoDetail : NavigationItems("video_detail")

    data object LiveDetail : NavigationItems("live_detail")

    data object UpVideos : NavigationItems("up_videos/{mid}", pathParams = listOf("{mid}"))

    data object NotFound : NavigationItems("not_found")
}
