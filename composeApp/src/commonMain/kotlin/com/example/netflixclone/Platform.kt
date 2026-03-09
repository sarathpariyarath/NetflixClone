package com.example.netflixclone

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform