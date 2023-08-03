package com.muedsa.bltv.ui.features.home

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muedsa.bltv.model.live.LiveViewModel
import com.muedsa.bltv.model.login.LoginViewModel
import com.muedsa.bltv.model.video.VideoViewModel
import com.muedsa.bltv.ui.navigation.NavigationItems
import com.muedsa.bltv.ui.theme.BLTVTheme
import com.muedsa.bltv.ui.widget.ScreenBackground
import com.muedsa.bltv.ui.widget.rememberScreenBackgroundState

@Composable
fun HomeScreen(
    videoViewModel: VideoViewModel = viewModel(),
    liveViewModel: LiveViewModel = viewModel(),
    loginViewModel: LoginViewModel = viewModel(),
    onNavigate: (NavigationItems) -> Unit = { _ -> },
) {
    val backgroundState = rememberScreenBackgroundState();
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
    BLTVTheme {
        HomeScreen()
    }
}