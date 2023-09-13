package com.muedsa.bltv.ui.features.home.browser

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.muedsa.bltv.model.DemoVideo
import com.muedsa.bltv.model.video.VideoViewModel
import com.muedsa.compose.tv.model.ContentModel
import com.muedsa.compose.tv.widget.StandardImageCardsRow


@Composable
fun FollowDynamicVideosRow(
    videoViewModel: VideoViewModel = hiltViewModel(),
    onItemFocus: (child: Int, model: DemoVideo) -> Unit,
    onItemClick: (child: Int, model: DemoVideo) -> Unit,
) {
    val videoList = remember { videoViewModel.followDynamicVideos }
    StandardImageCardsRow(
        title = "关注动态",
        modelList = videoList,
        imageFn = DemoVideo::image,
        contentFn = { ContentModel(it.title, subtitle = it.author) },
        onItemFocus = onItemFocus,
        onItemClick = onItemClick
    )
}