package dev.yuyuyuyuyu.topappbar

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform