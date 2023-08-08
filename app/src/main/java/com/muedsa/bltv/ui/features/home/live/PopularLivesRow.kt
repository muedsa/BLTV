package com.muedsa.bltv.ui.features.home.live

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.muedsa.bltv.model.ContentModel
import com.muedsa.bltv.model.DemoVideo
import com.muedsa.bltv.model.live.LiveViewModel
import com.muedsa.bltv.ui.widget.StandardImageCardsRow

@Composable
fun PopularLivesRow(
    liveViewModel: LiveViewModel = hiltViewModel(),
    onItemFocus: (child: Int, model: DemoVideo) -> Unit,
    onItemClick: (child: Int, model: DemoVideo) -> Unit,
) {
    val liveList = remember { liveViewModel.popularLives }
    StandardImageCardsRow(
        title = "推荐直播",
        modelList = liveList,
        imageFn = DemoVideo::image,
        contentFn = { video -> ContentModel(video.title, subtitle = video.author) },
        onItemFocus = onItemFocus,
        onItemClick = onItemClick
    )
}