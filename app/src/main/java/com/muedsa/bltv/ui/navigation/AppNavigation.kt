package com.muedsa.bltv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.muedsa.bltv.ui.features.detail.LiveDetailScreen
import com.muedsa.bltv.ui.features.detail.VideoDetailScreen
import com.muedsa.bltv.ui.features.home.HomeScreen
import com.muedsa.bltv.ui.features.others.NotFoundScreen
import com.muedsa.bltv.ui.features.playback.LivePlaybackScreen
import com.muedsa.bltv.ui.features.playback.VideoPlaybackScreen
import com.muedsa.bltv.ui.features.up.UpVideosScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItems.Home.path) {

        composable(NavigationItems.Home.path) {
            HomeScreen(onNavigate = {
                navController.navigate(it.path)
            })
        }

        composable(NavigationItems.VideoDetail.path) {
            VideoDetailScreen()
        }

        composable(NavigationItems.LiveDetail.path) {
            LiveDetailScreen()
        }

        composable(NavigationItems.VideoPlayback.path) {
            VideoPlaybackScreen()
        }

        composable(NavigationItems.LivePlayback.path) {
            LivePlaybackScreen()
        }

        composable(NavigationItems.UpVideos.path) {
            UpVideosScreen()
        }

        composable(NavigationItems.NotFound.path) {
            NotFoundScreen()
        }
    }
}