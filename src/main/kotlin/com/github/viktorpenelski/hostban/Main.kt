package com.github.viktorpenelski.hostban

fun main(args: Array<String>) {

    RedirectHosts(
            HostsFileLocation(),
            StaticBannedHostProvider(
                    args.asList()
            )).redirectToLocalhost()
}