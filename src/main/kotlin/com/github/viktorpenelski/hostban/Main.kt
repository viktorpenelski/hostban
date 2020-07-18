package com.github.viktorpenelski.hostban

fun main() {
    RedirectHosts(
            HostsFileLocation(),
            StaticBannedHostProvider(
                    listOf(
                            "facebook.com",
                            "reddit.com",
                            "youtube.com",
                            "twitter.com"
                    )
            )).redirectToLocalhost()
}