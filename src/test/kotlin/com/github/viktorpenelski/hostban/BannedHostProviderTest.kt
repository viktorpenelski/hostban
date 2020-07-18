package com.github.viktorpenelski.hostban

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class BannedHostProviderTest {

    class TestImpl() : BannedHostProvider() {
        override fun getList(): List<String> {
            return listOf("foo.com", "bar.org")
        }
    }

    @Test
    fun `get adds www to all elements`() {
        Assertions.assertEquals(
                listOf("foo.com", "www.foo.com", "bar.org", "www.bar.org"),
                TestImpl().get()
        )
    }
}