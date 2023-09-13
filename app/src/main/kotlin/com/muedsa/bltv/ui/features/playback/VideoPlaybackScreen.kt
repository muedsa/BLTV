package com.muedsa.bltv.ui.features.playback

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.kuaishou.akdanmaku.DanmakuConfig
import com.kuaishou.akdanmaku.data.DanmakuItemData
import com.muedsa.compose.tv.widget.player.DanmakuVideoPlayer

@OptIn(UnstableApi::class)
@Composable
fun VideoPlaybackScreen() {

    DanmakuVideoPlayer(
        videoPlayerInit = {
            playWhenReady = true

            val videoUrl = "https://media.w3.org/2010/05/sintel/trailer.mp4"
            val audioUrl = ""

            val dataSourceFactory = DefaultHttpDataSource.Factory()
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
                .setDefaultRequestProperties(
                    (mapOf(
                        "Referer" to "https://www.bilibili.com"
                    ))
                )
            val mediaSourceFactory = ProgressiveMediaSource.Factory(dataSourceFactory)
            val videoSource = mediaSourceFactory.createMediaSource(MediaItem.fromUri(videoUrl))
            //val audioSource = mediaSourceFactory.createMediaSource(MediaItem.fromUri(audioUrl))
            //MergingMediaSource().mediaItem
            setMediaSource(videoSource)
            prepare()

        },
        danmakuPlayerInit = {
            val danmakuDataList = mutableListOf<DanmakuItemData>()
            for (i in 1..100) {
                danmakuDataList.add(
                    DanmakuItemData(
                        danmakuId = i.toLong(),
                        position = 1000L + i.toLong() * 500,
                        content = "hahaha$i",
                        mode = DanmakuItemData.DANMAKU_MODE_ROLLING,
                        textSize = 25,
                        textColor = android.graphics.Color.WHITE,
                        score = 9,
                        danmakuStyle = DanmakuItemData.DANMAKU_STYLE_NONE,
                        rank = 9
                    )
                )
            }
            updateData(danmakuDataList)
            val config = getConfig() ?: DanmakuConfig()
            updateConfig(config.copy(textSizeScale = 1.4f))
        }
    )
}