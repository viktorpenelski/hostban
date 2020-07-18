package com.github.viktorpenelski.hostban

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import java.io.File


internal class RedirectHostsTest {

    class FakeHostFileLocator(val f: File) : HostsFileLocation() {
        override fun fileLocation(): File? {
            return f
        }
    }

    class FakeBannedHostsProvider() : BannedHostProvider() {
        override fun getList(): List<String> {
            return listOf("foo.com", "bar.org")
        }
    }

    @Test
    fun `redirect existing`() {

        val originalTxt = """
                    # localhost name resolution is handled within DNS itself.
                    #	127.0.0.1       localhost
                    #	::1             localhost

                    # Added by hostban - BEGIN


                    		127.0.0.1 facebook.com
                    		127.0.0.1 www.facebook.com
                    		127.0.0.1 reddit.com
                    		127.0.0.1 www.reddit.com
                    #		127.0.0.1 youtube.com
                    #		127.0.0.1 www.youtube.com
                    		127.0.0.1 twitter.com
                    		127.0.0.1 www.twitter.com
                    # Added by hostban - END

                    # Added by Docker Desktop
                    192.168.1.100 host.docker.internal
                """.trimIndent()

        val expected = """
                    # localhost name resolution is handled within DNS itself.
                    #	127.0.0.1       localhost
                    #	::1             localhost

                    # Added by hostban - BEGIN
                    127.0.0.1 foo.com
                    127.0.0.1 www.foo.com
                    127.0.0.1 bar.org
                    127.0.0.1 www.bar.org
                    # Added by hostban - END

                    # Added by Docker Desktop
                    192.168.1.100 host.docker.internal
                """.trimIndent()
        val tmpFile = File.createTempFile("test-hosts", "")
        tmpFile.writeText(originalTxt)

        RedirectHosts(FakeHostFileLocator(tmpFile), FakeBannedHostsProvider()).redirectToLocalhost()


        Assertions.assertEquals(expected, tmpFile.readText())
    }

    @Test
    fun `redirect non-existing`() {

        val originalTxt = """
                    # localhost name resolution is handled within DNS itself.
                """.trimIndent()

        val expected = """
                    # localhost name resolution is handled within DNS itself.
                    # Added by hostban - BEGIN
                    127.0.0.1 foo.com
                    127.0.0.1 www.foo.com
                    127.0.0.1 bar.org
                    127.0.0.1 www.bar.org
                    # Added by hostban - END
                """.trimIndent()

        val tmpFile = File.createTempFile("test-hosts", "")
        tmpFile.writeText(originalTxt)

        RedirectHosts(FakeHostFileLocator(tmpFile), FakeBannedHostsProvider()).redirectToLocalhost()

        Assertions.assertEquals(expected, tmpFile.readText())
    }
}