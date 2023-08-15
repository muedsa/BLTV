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
            "https://upos-hz-mirrorakam.akamaized.net/upgcxcode/10/46/1215154610/1215154610-1-100110.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEqxTEto8BTrNvN0GvT90W5JZMkX_YN0MvXg8gNEV4NC8xNEV4N03eN0B5tZlqNxTEto8BTrNvNeZVuJ10Kj_g2UB02J0mN0B5tZlqNCNEto8BTrNvNC7MTX502C8f2jmMQJ6mqF2fka1mqx6gqj0eN0B599M=&uipk=5&nbs=1&deadline=1692092647&gen=playurlv2&os=akam&oi=2890290450&trid=798ec7297d5a496fa813f3689f8987b8u&mid=0&platform=pc&upsig=47dde88e0938c0dc4408ac80e984c70d&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,mid,platform&hdnts=exp=1692092647~hmac=3a09b564522fe133f87460e6d99a65c2925e33d3888ff9e03db7255f908d7990&bvc=vod&nettype=0&orderid=0,1&buvid=&build=0&agrr=1&bw=32270&logo=80000000"

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