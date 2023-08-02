package com.muedsa.bltv.ui.features.home

sealed class HomeNavTabs (val title: String) {
    object Video: HomeNavTabs("视频")
    object Live: HomeNavTabs("直播")
    object Search: HomeNavTabs("搜索")
    object User: HomeNavTabs("我的")
    object Setting: HomeNavTabs("设置")
}