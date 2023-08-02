package com.muedsa.bltv.ui.features.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Tab
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import androidx.tv.material3.Text
import com.muedsa.bltv.ui.features.home.browser.BrowserScreen
import com.muedsa.bltv.ui.features.home.live.LiveScreen
import com.muedsa.bltv.ui.features.home.login.LoginScreen
import com.muedsa.bltv.ui.features.home.search.SearchScreen
import com.muedsa.bltv.ui.features.others.NotFoundScreen
import com.muedsa.bltv.ui.navigation.NavigationItems
import com.muedsa.bltv.ui.theme.BLTVTheme
import com.muedsa.bltv.ui.widget.ScreenBackground
import com.muedsa.bltv.ui.widget.ScreenBackgroundType

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigate: (NavigationItems) -> Unit = { _ -> },
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        HomeNavTabs.Video,
        HomeNavTabs.Live,
        HomeNavTabs.Search,
        HomeNavTabs.User,
        HomeNavTabs.Setting
    )
    val background = remember { mutableStateOf<String?>(null) }
    val backgroundType = remember { mutableStateOf(ScreenBackgroundType.FULL_SCREEN) }
    ScreenBackground(url = background.value, type = backgroundType.value)
    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onFocus = {
                        if(selectedTabIndex != index) {
                            background.value = null
                            backgroundType.value = ScreenBackgroundType.FULL_SCREEN
                            selectedTabIndex = index
                        }
                    },
                    colors = TabDefaults.pillIndicatorTabColors(
                        activeContentColor = Color.White,
                        contentColor = Color.White,
                        selectedContentColor = Color.White,
                        focusedContentColor = Color.White,
                        focusedSelectedContentColor = Color.White
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
        HomeContent(selectedTabIndex, background, backgroundType, onNavigate)
    }
}

@Composable
fun HomeContent(
    tabIndex: Int,
    background: MutableState<String?>,
    backgroundType: MutableState<ScreenBackgroundType>,
    onNavigate: (NavigationItems) -> Unit = { _ -> },
) {
    when (tabIndex) {
        0 -> BrowserScreen(background, backgroundType, onNavigate)
        1 -> LiveScreen(background, backgroundType, onNavigate)
        2 -> SearchScreen(background, backgroundType, onNavigate)
        3 -> LoginScreen()
        else -> NotFoundScreen()
    }
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