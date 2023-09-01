package com.muedsa.bltv.ui.features.playback

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.source.MergingMediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.muedsa.bltv.ui.widget.player.DanmakuVideoPlayer

@OptIn(UnstableApi::class)
@Composable
fun VideoPlaybackScreen() {

    DanmakuVideoPlayer {
        playWhenReady = true

        val videoUrl =
            "https://upos-sz-mirroraliov.bilivideo.com/upgcxcode/10/46/1215154610/1215154610-1-100022.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEqxTEto8BTrNvN0GvT90W5JZMkX_YN0MvXg8gNEV4NC8xNEV4N03eN0B5tZlqNxTEto8BTrNvNeZVuJ10Kj_g2UB02J0mN0B5tZlqNCNEto8BTrNvNC7MTX502C8f2jmMQJ6mqF2fka1mqx6gqj0eN0B599M=&uipk=5&nbs=1&deadline=1693543310&gen=playurlv2&os=aliovbv&oi=2890291950&trid=75033206a09a41e4a541789db4ccb532u&mid=0&platform=pc&upsig=e504e31288d67b57cf1d44592c248b9e&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,mid,platform&bvc=vod&nettype=0&orderid=0,1&buvid=&build=0&f=u_0_0&agrr=0&bw=22290&logo=80000000"
        val audioUrl =
            "https://upos-sz-mirroraliov.bilivideo.com/upgcxcode/10/46/1215154610/1215154610-1-30280.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEqxTEto8BTrNvN0GvT90W5JZMkX_YN0MvXg8gNEV4NC8xNEV4N03eN0B5tZlqNxTEto8BTrNvNeZVuJ10Kj_g2UB02J0mN0B5tZlqNCNEto8BTrNvNC7MTX502C8f2jmMQJ6mqF2fka1mqx6gqj0eN0B599M=&uipk=5&nbs=1&deadline=1692071971&gen=playurlv2&os=aliovbv&oi=2890292037&trid=c7a22ca6428246a6a1ea3e7fae68f7e4u&mid=0&platform=pc&upsig=6e881bb971dc8df1aed44e414bc037c7&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,mid,platform&bvc=vod&nettype=0&orderid=0,1&buvid=&build=0&agrr=1&bw=14657&logo=80000000"

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
        setMediaSource(videoSource)
        MergingMediaSource().mediaItem
        prepare()
    }
}