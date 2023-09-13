package com.muedsa.bltv.ui.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Tab
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import androidx.tv.material3.Text
import com.muedsa.bltv.model.live.LiveViewModel
import com.muedsa.bltv.model.login.LoginViewModel
import com.muedsa.bltv.model.video.VideoViewModel
import com.muedsa.bltv.ui.features.home.browser.BrowserScreen
import com.muedsa.bltv.ui.features.home.live.LiveScreen
import com.muedsa.bltv.ui.features.home.login.LoginScreen
import com.muedsa.bltv.ui.features.home.search.SearchScreen
import com.muedsa.bltv.ui.features.others.NotFoundScreen
import com.muedsa.bltv.ui.navigation.NavigationItems
import com.muedsa.compose.tv.widget.ScreenBackgroundState
import com.muedsa.compose.tv.widget.ScreenBackgroundType
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds


val tabs = listOf(
    HomeNavTabs.Video,
    HomeNavTabs.Live,
    HomeNavTabs.Search,
    HomeNavTabs.User,
    HomeNavTabs.Setting
)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeNavTab(
    videoViewModel: VideoViewModel = hiltViewModel(),
    liveViewModel: LiveViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    backgroundState: ScreenBackgroundState = ScreenBackgroundState(),
    onNavigate: (NavigationItems, List<String>?) -> Unit = { _, _ -> },
) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    var tabPanelIndex by remember { mutableIntStateOf(selectedTabIndex) }

    LaunchedEffect(selectedTabIndex) {
        delay(150.milliseconds)
        tabPanelIndex = selectedTabIndex
    }

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 24.dp)
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onFocus = {
                        if (selectedTabIndex != index) {
                            backgroundState.url = null
                            backgroundState.type = ScreenBackgroundType.BLUR
                            selectedTabIndex = index
                        }
                    },
                    colors = TabDefaults.pillIndicatorTabColors(
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        selectedContentColor = MaterialTheme.colorScheme.onBackground,
                        focusedContentColor = MaterialTheme.colorScheme.onBackground,
                        focusedSelectedContentColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Text(
                        tab.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                }
            }
        }
        HomeContent(
            tabPanelIndex,
            videoViewModel,
            liveViewModel,
            loginViewModel,
            backgroundState,
            onNavigate
        )
    }
}

@Composable
fun HomeContent(
    tabIndex: Int,
    videoViewModel: VideoViewModel,
    liveViewModel: LiveViewModel,
    loginViewModel: LoginViewModel,
    backgroundState: ScreenBackgroundState,
    onNavigate: (NavigationItems, List<String>?) -> Unit = { _, _ -> },
) {
    when (tabIndex) {
        0 -> BrowserScreen(
            videoViewModel = videoViewModel,
            backgroundState = backgroundState,
            onNavigate = onNavigate
        )

        1 -> LiveScreen(
            liveViewModel = liveViewModel,
            backgroundState = backgroundState,
            onNavigate = onNavigate
        )

        2 -> SearchScreen(
            videoViewModel = videoViewModel,
            liveViewModel = liveViewModel,
            backgroundState = backgroundState,
            onNavigate = onNavigate
        )

        3 -> LoginScreen(loginViewModel = loginViewModel)
        else -> NotFoundScreen()
    }
}