package com.muedsa.bltv.ui.features.playback

import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MergingMediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
fun VideoPlaybackScreen() {

    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build()
            .apply {
                playWhenReady = true

                val videoUrl =
                    "https://upos-sz-estgoss.bilivideo.com/upgcxcode/10/46/1215154610/1215154610-1-100023.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEqxTEto8BTrNvN0GvT90W5JZMkX_YN0MvXg8gNEV4NC8xNEV4N03eN0B5tZlqNxTEto8BTrNvNeZVuJ10Kj_g2UB02J0mN0B5tZlqNCNEto8BTrNvNC7MTX502C8f2jmMQJ6mqF2fka1mqx6gqj0eN0B599M=&uipk=5&nbs=1&deadline=1691638943&gen=playurlv2&os=upos&oi=1961621384&trid=56809f40a3d44e43b43ff1e20b00195bu&mid=7443740&platform=pc&upsig=9542b15b485b181983fb01355237220c&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,mid,platform&bvc=vod&nettype=0&orderid=0,3&buvid=4A9436A0-5D9D-62CC-3584-70D4CCC6D47977864infoc&build=0&agrr=1&bw=26916&logo=80000000";

                val audioUrl =
                    "https://upos-sz-mirroraliov.bilivideo.com/upgcxcode/10/46/1215154610/1215154610-1-30280.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEqxTEto8BTrNvN0GvT90W5JZMkX_YN0MvXg8gNEV4NC8xNEV4N03eN0B5tZlqNxTEto8BTrNvNeZVuJ10Kj_g2UB02J0mN0B5tZlqNCNEto8BTrNvNC7MTX502C8f2jmMQJ6mqF2fka1mqx6gqj0eN0B599M=&uipk=5&nbs=1&deadline=1691639440&gen=playurlv2&os=aliovbv&oi=2890374951&trid=3be47d10b7574a648eeca8494410385bu&mid=0&platform=pc&upsig=a3635a6c328ea7a35ed2e69c55b31448&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,mid,platform&bvc=vod&nettype=0&orderid=0,1&buvid=&build=0&agrr=1&bw=14657&logo=80000000"

                val dataSourceFactory = DefaultHttpDataSource.Factory()
                    .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
                    .setDefaultRequestProperties(
                        (mapOf(
                            "Referer" to "https://www.bilibili.com"
                        ))
                    )
                val mediaSourceFactory = ProgressiveMediaSource.Factory(dataSourceFactory)
                val videoSource = mediaSourceFactory.createMediaSource(MediaItem.fromUri(videoUrl))
                val audioSource = mediaSourceFactory.createMediaSource(MediaItem.fromUri(audioUrl))
                setMediaSource(MergingMediaSource(videoSource, audioSource))
                MergingMediaSource().mediaItem
                prepare()
            }
    }

    DisposableEffect(
        AndroidView(factory = {
            PlayerView(context).apply {
                hideController()
                useController = false
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

                player = exoPlayer
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
            }
        })
    ) {
        onDispose {
            exoPlayer.release()
        }
    }

}