package com.muedsa.bltv.ui.features.home.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedIconButton
import com.muedsa.bltv.model.ContentModel
import com.muedsa.bltv.model.DemoVideo
import com.muedsa.bltv.ui.features.home.browser.fetchDemoVideos
import com.muedsa.bltv.ui.widget.ImageCardsRow
import com.muedsa.bltv.ui.widget.ScreenBackgroundType
import com.muedsa.bltv.ui.widget.StandardImageCardsRow
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTvMaterial3Api::class)
@Composable
fun SearchScreen(
    background: MutableState<String?>,
    backgroundType: MutableState<ScreenBackgroundType>
) {
    val queryText = remember { mutableStateOf("") }

    val videoResult = remember { mutableStateOf(listOf<DemoVideo>()) }

    TvLazyColumn {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.55f)
                        .background(
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = TextFieldDefaults.outlinedShape
                    ),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.surfaceTint,
                        cursorColor = MaterialTheme.colorScheme.background,
                        textColor = MaterialTheme.colorScheme.surface,
                    ),
                    value = queryText.value,
                    onValueChange = {
                        queryText.value = it
                    },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(20.dp))
                OutlinedIconButton(onClick = {
                    videoResult.value = fetchDemoVideos()
                }) {
                    Icon(Icons.Outlined.Search, contentDescription = "Localized description")
                }
            }

            if (videoResult.value.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(x = 50.dp)
                ) {
                    StandardImageCardsRow(
                        title = "视频",
                        modelList = videoResult.value,
                        imageFn = DemoVideo::image,
                        contentFn = { video -> ContentModel(video.title, subtitle = video.author) },
                        onItemFocus = { _, video ->
                            background.value = video.image
                            backgroundType.value = ScreenBackgroundType.FULL_SCREEN
                        },
                        onItemClick = { _, video ->
                            Timber.d("Click $video")
                        }
                    )
                }
            }
        }
    }

}