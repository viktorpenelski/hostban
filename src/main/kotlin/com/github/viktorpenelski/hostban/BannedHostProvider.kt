package com.github.viktorpenelski.hostban

abstract class BannedHostProvider {

    fun get(): List<String> {
        return getList().flatMap { listOf(it, withWww(it)) }
    }

    protected abstract fun getList(): List<String>

    private fun withWww(s: String): String {
        return "www.$s"
    }
}