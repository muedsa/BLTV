package com.muedsa.bltv.repository

import com.muedsa.bltv.Foo
import com.muedsa.bltv.model.DemoVideo
import javax.inject.Inject

class VideosRepo @Inject constructor(
    private val foo: Foo
) {
    fun fetchDemoVideos(): List<DemoVideo> {
        return listOf(
            DemoVideo(
                "http://i0.hdslb.com/bfs/archive/ed2cba8037673df23714b625bb5316a9c6cfa2de.jpg",
                "【散人】国产悬疑惊悚《三伏》 旧时代三眼神童之谜（已更新至P4 明镜台）",
                "逍遥散人",
                "试玩视频BV1qF411x7ER 前作烟火BV15U4y1x7YS\r\n三伏终于上线了，期待很久的国产悬疑游戏，剧情优秀，情节震撼。\r\n喜欢的朋友欢迎收藏三连分享，去steam上购买支持下作者，非常感谢！"
            ),
            DemoVideo(
                "http://i2.hdslb.com/bfs/archive/3f6ce09fdb6dcca5d77b19a517c840d6626a5128.jpg",
                "汽车安全锤",
                "鉴货兄弟",
                "这东西关键时刻真能保命吗？#测评 #汽车安全锤 #有车必备"
            ),
            DemoVideo(
                "http://i1.hdslb.com/bfs/archive/3f80e62fd5e026eb9fba85cf435200697afe43cc.jpg",
                "【某幻】国产悬疑《三伏》全流程实况 1P三眼神童",
                "某幻君",
                "游戏：三伏\n结尾给我刀瞎了"
            ),
            DemoVideo(
                "http://i2.hdslb.com/bfs/archive/884e98a234d684d0dd1072d8863362feda6b6a84.jpg",
                "超燃打戏！功夫皇帝李连杰，22年前豪取15亿票房！",
                "摩斯神探",
                "超燃打戏！功夫皇帝李连杰，22年前豪取15亿票房！《龙之吻》"
            ),
            DemoVideo(
                "http://i2.hdslb.com/bfs/archive/5e3581d4e5c909fd5bb3b3266d88f7012bbc06f0.jpg",
                "《明日方舟》EP - Miss You",
                "明日方舟",
                "8月1日 Miss You 正式上架塞壬唱片官网，网易云音乐及QQ音乐等平台\n塞壬唱片官网链接：https://monster-siren.hypergryph.com/music/514540\n\n【专辑介绍】\nMagma is formed from molten rocks.\nThough separated by vast distances, \nthey all converge into a singular life force of molten heat.\nWith the long da"
            ),
            DemoVideo(
                "http://i1.hdslb.com/bfs/archive/50e182cc93ff8214cf9b50675bff5e6fb922a57c.jpg",
                "“他就想活命，他有什么罪!”",
                "听云up",
                "电影:我不是药神\nBGM:用什么把你留住"
            ),
            DemoVideo(
                "http://i0.hdslb.com/bfs/archive/bb84784e48d4fa283a772e4b755134181949e961.jpg",
                "网络热门生物鉴定49",
                "无穷小亮的科普日常",
                ""
            ),
        ).shuffled()
    }
}