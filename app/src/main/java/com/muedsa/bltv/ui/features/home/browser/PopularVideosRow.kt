package com.muedsa.bltv.ui.features.home.browser

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.muedsa.bltv.model.DemoVideo
import com.muedsa.bltv.model.video.VideoViewModel
import com.muedsa.bltv.ui.widget.ImageCardsRow

@Composable
fun PopularVideosRow(
    videoViewModel: VideoViewModel = hiltViewModel(),
    onItemFocus: (child: Int, model: DemoVideo) -> Unit,
    onItemClick: (child: Int, model: DemoVideo) -> Unit,
) {
    val videoList = remember { videoViewModel.popularVideos }
    ImageCardsRow(
        title = "推荐视频",
        modelList = videoList,
        imageFn = DemoVideo::image,
        onItemFocus = onItemFocus,
        onItemClick = onItemClick
    )
}