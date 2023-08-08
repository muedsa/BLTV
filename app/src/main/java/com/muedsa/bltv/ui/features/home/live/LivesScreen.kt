package com.muedsa.bltv.ui.features.home.live

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.foundation.lazy.list.TvLazyColumn
import com.muedsa.bltv.model.live.LiveViewModel
import com.muedsa.bltv.ui.navigation.NavigationItems
import com.muedsa.bltv.ui.widget.ScreenBackgroundState
import com.muedsa.bltv.ui.widget.ScreenBackgroundType
import timber.log.Timber

@Composable
fun LiveScreen(
    liveViewModel: LiveViewModel = hiltViewModel(),
    backgroundState: ScreenBackgroundState,
    onNavigate: (NavigationItems, List<String>?) -> Unit = { _, _ -> }
) {
    TvLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .offset(x = 50.dp)
    ) {
        item {
            PopularLivesRow(
                liveViewModel = liveViewModel,
                onItemFocus = { _, video ->
                    backgroundState.url = video.image
                    backgroundState.type = ScreenBackgroundType.BLUR
                }, onItemClick = { _, video ->
                    Timber.d("Click $video")
                    onNavigate(NavigationItems.LiveDetail, null)
                })
            FollowLivesRow(
                liveViewModel = liveViewModel,
                onItemFocus = { _, video ->
                    backgroundState.url = video.image
                    backgroundState.type = ScreenBackgroundType.BLUR
                }, onItemClick = { _, video ->
                    Timber.d("Click $video")
                    onNavigate(NavigationItems.LiveDetail, null)
                })
        }
    }

}