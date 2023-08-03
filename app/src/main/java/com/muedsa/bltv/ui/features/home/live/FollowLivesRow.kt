package com.muedsa.bltv.ui.features.home.live

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muedsa.bltv.model.ContentModel
import com.muedsa.bltv.model.DemoVideo
import com.muedsa.bltv.model.live.LiveViewModel
import com.muedsa.bltv.ui.widget.StandardImageCardsRow

@Composable
fun FollowLivesRow(
    liveViewModel: LiveViewModel = viewModel(),
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