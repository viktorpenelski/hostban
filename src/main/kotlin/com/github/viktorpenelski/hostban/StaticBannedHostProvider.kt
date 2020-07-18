package com.github.viktorpenelski.hostban

class StaticBannedHostProvider(private val hosts: List<String>) : BannedHostProvider() {

    override fun getList(): List<String> {
        return hosts
    }

}