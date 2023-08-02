package com.muedsa.bltv.ui.features.home.live

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import com.muedsa.bltv.ui.navigation.NavigationItems
import com.muedsa.bltv.ui.widget.ScreenBackgroundType
import timber.log.Timber

@Composable
fun LiveScreen(
    background: MutableState<String?>,
    backgroundType: MutableState<ScreenBackgroundType>,
    onNavigate: (NavigationItems) -> Unit = { _ -> }
) {
    TvLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .offset(x = 50.dp)
    ) {
        item {
            PopularLivesRow(onItemFocus = { _, video ->
                background.value = video.image
                backgroundType.value = ScreenBackgroundType.FULL_SCREEN
            }, onItemClick = { _, video ->
                Timber.d("Click $video")
                onNavigate(NavigationItems.LiveDetail)
            })
            FollowLivesRow(onItemFocus = { _, video ->
                background.value = video.image
                backgroundType.value = ScreenBackgroundType.FULL_SCREEN
            }, onItemClick = { _, video ->
                Timber.d("Click $video")
                onNavigate(NavigationItems.LiveDetail)
            })
        }
    }

}