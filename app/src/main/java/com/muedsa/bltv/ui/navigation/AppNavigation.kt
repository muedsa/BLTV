package com.muedsa.bltv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.muedsa.bltv.model.live.LiveViewModel
import com.muedsa.bltv.model.login.LoginViewModel
import com.muedsa.bltv.model.video.VideoViewModel
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
            val videoViewModel = hiltViewModel<VideoViewModel>()
            val liveViewModel = hiltViewModel<LiveViewModel>()
            val loginViewModel = hiltViewModel<LoginViewModel>()
            HomeScreen(
                videoViewModel = videoViewModel,
                liveViewModel = liveViewModel,
                loginViewModel = loginViewModel,
                onNavigate = { navItem, pathParams ->
                    onNavigate(navController, navItem, pathParams)
                }
            )
        }

        composable(NavigationItems.VideoDetail.path) {
            VideoDetailScreen(onNavigate = { navItem, pathParams ->
                onNavigate(navController, navItem, pathParams)
            })
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

        composable(NavigationItems.UpVideos.path, arguments = listOf(navArgument("mid") {
            type = NavType.LongType
        })) {
            val videoViewModel = hiltViewModel<VideoViewModel>()
            val state = remember {
                mutableLongStateOf(checkNotNull(it.arguments?.getLong("mid")))
            }
            UpVideosScreen(
                state = state,
                videoViewModel = videoViewModel,
                onNavigate = { navItem, pathParams ->
                    onNavigate(navController, navItem, pathParams)
                }
            )
        }

        composable(NavigationItems.NotFound.path) {
            NotFoundScreen()
        }
    }
}

fun onNavigate(
    navController: NavHostController,
    navItem: NavigationItems,
    pathParams: List<String>?
) {
    var route = navItem.path
    if (!navItem.pathParams.isNullOrEmpty()) {
        checkNotNull(pathParams)
        check(pathParams.size == navItem.pathParams.size)
        for (i in 0 until navItem.pathParams.size) {
            route = route.replace(navItem.pathParams[i], pathParams[i])
        }
    }
    navController.navigate(route)
}