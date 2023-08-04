package com.muedsa.bltv.ui.navigation

sealed class NavigationItems(
    val path: String,
    val pathParams: List<String>? = null,
) {
    object Home : NavigationItems("home")
    object VideoDetail : NavigationItems("video_detail")

    object LiveDetail : NavigationItems("live_detail")

    object VideoPlayback : NavigationItems("video_playback")

    object LivePlayback : NavigationItems("live_playback")

    object UpVideos : NavigationItems("up_videos/{mid}", pathParams = listOf("{mid}"))

    object NotFound : NavigationItems("not_found")
}
