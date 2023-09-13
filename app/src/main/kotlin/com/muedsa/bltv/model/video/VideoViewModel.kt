package com.muedsa.bltv.model.video

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.muedsa.bltv.model.DemoVideo
import com.muedsa.bltv.repository.VideosRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val repo: VideosRepo
) : ViewModel() {

    val popularVideos = mutableStateListOf<DemoVideo>()
    val followDynamicVideos = mutableStateListOf<DemoVideo>()
    val historyVideos = mutableStateListOf<DemoVideo>()
    val searchVideos = mutableStateListOf<DemoVideo>()
    val upVideos = mutableStateListOf<DemoVideo>()

    fun fetchPopularVideos() {
        popularVideos.clear()
        popularVideos.addAll(repo.fetchDemoVideos())
    }

    fun fetchFollowDynamicVideos() {
        followDynamicVideos.clear()
        followDynamicVideos.addAll(repo.fetchDemoVideos())
    }

    fun fetchHistoryVideos() {
        historyVideos.clear()
        historyVideos.addAll(repo.fetchDemoVideos())
    }

    fun fetchSearchVideos(query: String) {
        searchVideos.clear()
        searchVideos.addAll(repo.fetchDemoVideos())
    }

    fun fetchUpVideos(mid: Long) {
        upVideos.clear()
        upVideos.addAll(repo.fetchDemoVideos())
        upVideos.addAll(repo.fetchDemoVideos())
        upVideos.addAll(repo.fetchDemoVideos())
        upVideos.addAll(repo.fetchDemoVideos())
        upVideos.addAll(repo.fetchDemoVideos())
    }

    init {
        fetchPopularVideos()
        fetchFollowDynamicVideos()
        fetchHistoryVideos()
    }
}