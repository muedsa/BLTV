package com.muedsa.bltv.ui.features.up

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.items
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.muedsa.bltv.model.video.VideoViewModel
import com.muedsa.bltv.ui.navigation.NavigationItems
import com.muedsa.compose.tv.model.ContentModel
import com.muedsa.compose.tv.widget.CardType
import com.muedsa.compose.tv.widget.ImageContentCard
import com.muedsa.compose.tv.widget.ScreenBackground
import com.muedsa.compose.tv.widget.rememberScreenBackgroundState

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun UpVideosScreen(
    state: State<Long>,
    videoViewModel: VideoViewModel = hiltViewModel(),
    onNavigate: (NavigationItems, List<String>?) -> Unit = { _, _ -> }
) {

    val videoList = remember {
        videoViewModel.upVideos
    }
    val screenBackgroundState = rememberScreenBackgroundState()

    LaunchedEffect(state) {
        videoViewModel.fetchUpVideos(state.value)
    }
    ScreenBackground(state = screenBackgroundState)
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 58.dp, end = 20.dp)) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, end = 20.dp),
            text = "title ${state.value}",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displayLarge
        )
        TvLazyVerticalGrid(
            columns = TvGridCells.Adaptive(minSize = 200.dp)
        ) {
            items(videoList) {
                ImageContentCard(url = it.image,
                    type = CardType.STANDARD,
                    model = ContentModel(it.title, it.author, it.desc),
                    onItemFocus = {
                        screenBackgroundState.url = it.image
                    },
                    onItemClick = {
                        onNavigate(NavigationItems.VideoDetail, null)
                    }
                )
            }
        }
    }
}