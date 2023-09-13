package com.muedsa.bltv.ui.features.home.live

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.muedsa.bltv.model.DemoVideo
import com.muedsa.bltv.model.live.LiveViewModel
import com.muedsa.compose.tv.model.ContentModel
import com.muedsa.compose.tv.widget.StandardImageCardsRow

@Composable
fun FollowLivesRow(
    liveViewModel: LiveViewModel = hiltViewModel(),
    onItemFocus: (child: Int, model: DemoVideo) -> Unit,
    onItemClick: (child: Int, model: DemoVideo) -> Unit,
) {
    val liveList = remember { liveViewModel.followLives }
    StandardImageCardsRow(
        title = "关注列表",
        modelList = liveList,
        imageFn = DemoVideo::image,
        contentFn = { ContentModel(it.title, subtitle = it.author) },
        onItemFocus = onItemFocus,
        onItemClick = onItemClick
    )
}