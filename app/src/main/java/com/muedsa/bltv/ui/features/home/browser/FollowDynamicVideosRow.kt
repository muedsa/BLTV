package com.muedsa.bltv.ui.features.home.browser

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.muedsa.bltv.model.ContentModel
import com.muedsa.bltv.model.DemoVideo
import com.muedsa.bltv.ui.widget.StandardImageCardsRow


@Composable
fun FollowDynamicVideosRow(
    onItemFocus: (child: Int, model: DemoVideo) -> Unit,
    onItemClick: (child: Int, model: DemoVideo) -> Unit,
) {
    val videoList = remember { fetchDemoVideos() }
    StandardImageCardsRow(
        title = "关注动态",
        modelList = videoList,
        imageFn = DemoVideo::image,
        contentFn = { ContentModel(it.title, subtitle = it.author) },
        onItemFocus = onItemFocus,
        onItemClick = onItemClick
    )
}