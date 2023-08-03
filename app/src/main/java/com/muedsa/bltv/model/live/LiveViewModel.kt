package com.muedsa.bltv.model.live

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.muedsa.bltv.model.DemoVideo
import com.muedsa.bltv.repository.VideosRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LiveViewModel @Inject constructor(
    private val repo: VideosRepo
) : ViewModel() {

    val popularLives = mutableStateListOf<DemoVideo>()
    val followLives = mutableStateListOf<DemoVideo>()
    val searchLives = mutableStateListOf<DemoVideo>()

    fun fetchPopularLives() {
        popularLives.clear()
        popularLives.addAll(repo.fetchDemoVideos())
    }

    fun fetchFollowLives() {
        followLives.clear()
        followLives.addAll(repo.fetchDemoVideos())
    }

    fun fetchSearchLives(query: String) {
        searchLives.clear()
        searchLives.addAll(repo.fetchDemoVideos())
    }

    init {
        fetchPopularLives()
        fetchFollowLives()
    }
}