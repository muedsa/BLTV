package com.muedsa.bltv.ui.features.home

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.muedsa.bltv.model.live.LiveViewModel
import com.muedsa.bltv.model.login.LoginViewModel
import com.muedsa.bltv.model.video.VideoViewModel
import com.muedsa.bltv.ui.navigation.NavigationItems
import com.muedsa.compose.tv.theme.TvTheme
import com.muedsa.compose.tv.widget.ScreenBackground
import com.muedsa.compose.tv.widget.rememberScreenBackgroundState

@Composable
fun HomeScreen(
    videoViewModel: VideoViewModel = hiltViewModel(),
    liveViewModel: LiveViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigate: (NavigationItems, List<String>?) -> Unit = { _, _ -> },
) {
    val backgroundState = rememberScreenBackgroundState(
        initHeaders = mapOf("Referer" to "https://www.bilibili.com")
    )
    ScreenBackground(state = backgroundState)
    HomeNavTab(
        videoViewModel = videoViewModel,
        liveViewModel = liveViewModel,
        loginViewModel = loginViewModel,
        backgroundState = backgroundState,
        onNavigate = onNavigate
    )
}

@Preview(
    device = "id:tv_1080p",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_TELEVISION
)
@Composable
fun HomeScreenPreview() {
    TvTheme {
        HomeScreen()
    }
}