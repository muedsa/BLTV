package com.muedsa.bltv.ui.features.home.browser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.ImmersiveList
import com.muedsa.bltv.model.ContentModel
import com.muedsa.bltv.model.video.VideoViewModel
import com.muedsa.bltv.ui.navigation.NavigationItems
import com.muedsa.bltv.ui.widget.ContentBlock
import com.muedsa.bltv.ui.widget.ScreenBackgroundState
import com.muedsa.bltv.ui.widget.ScreenBackgroundType
import timber.log.Timber

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun BrowserScreen(
    videoViewModel: VideoViewModel = hiltViewModel(),
    backgroundState: ScreenBackgroundState,
    onNavigate: (NavigationItems, List<String>?) -> Unit = { _, _ -> }
) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    var title by remember { mutableStateOf("") }
    var subTitle by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val contentModel = ContentModel(title = title, subtitle = subTitle, description = description)

    TvLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .offset(x = 50.dp)
    ) {
        item {
            ImmersiveList(
                background = { _, _ ->
                    ContentBlock(
                        modifier = Modifier
                            .offset(x = 8.dp)
                            .width(screenWidth / 2)
                            .height(screenHeight - 150.dp - 75.dp - 48.dp),
                        model = contentModel,
                        descriptionMaxLines = 3
                    )
                },
            ) {
                Column {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight - 150.dp - 75.dp)
                    )
                    PopularVideosRow(
                        videoViewModel = videoViewModel,
                        onItemFocus = { _, video ->
                            title = video.title
                            subTitle = video.author
                            description = video.desc

                            backgroundState.url = video.image
                            backgroundState.type = ScreenBackgroundType.SCRIM
                        },
                        onItemClick = { _, video ->
                            Timber.d("Click $video")
                            onNavigate(NavigationItems.VideoDetail, null)
                        })
                }
            }

            FollowDynamicVideosRow(
                videoViewModel = videoViewModel,
                onItemFocus = { _, video ->
                    title = video.title
                    subTitle = video.author
                    description = video.desc
                    backgroundState.url = video.image
                    backgroundState.type = ScreenBackgroundType.BLUR
                },
                onItemClick = { _, video ->
                    Timber.d("Click $video")
                    onNavigate(NavigationItems.VideoDetail, null)
                })

            HistoryVideosRow(
                videoViewModel = videoViewModel,
                onItemFocus = { _, video ->
                    title = video.title
                    subTitle = video.author
                    description = video.desc
                    backgroundState.url = video.image
                    backgroundState.type = ScreenBackgroundType.BLUR
                },
                onItemClick = { index, video ->
                    Timber.d("Click $video")
                    onNavigate(NavigationItems.UpVideos, listOf(index.toString()))
                })
        }
    }
}