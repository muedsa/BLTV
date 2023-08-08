package com.muedsa.bltv.ui.features.home

sealed class HomeNavTabs (val title: String) {
    data object Video : HomeNavTabs("视频")
    data object Live : HomeNavTabs("直播")
    data object Search : HomeNavTabs("搜索")
    data object User : HomeNavTabs("我的")
    data object Setting : HomeNavTabs("设置")
}