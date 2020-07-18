package com.github.viktorpenelski.hostban

class RedirectHosts(
    private val fileLocation: HostsFileLocation,
    private val bannedHosts: BannedHostProvider
) {
    private val start = "# Added by hostban - BEGIN"
    private val end = "# Added by hostban - END"

    fun redirectToLocalhost() {
        val file = fileLocation.fileLocation() ?: return

        val fileAsTxt = file.readText()

        val allBetweenStartAndEndInclusive = "$start*.+$end".toRegex(RegexOption.DOT_MATCHES_ALL)

        val newText = if (fileAsTxt.contains(allBetweenStartAndEndInclusive)) {
            replace(fileAsTxt, allBetweenStartAndEndInclusive)
        } else {
            appendToEnd(fileAsTxt)
        }

        file.writeText(newText)

    }

    private fun getBannedString(): String {
        return """
$start
${bannedHosts.get().map { "127.0.0.1 $it" }.joinToString("\n")}
$end
        """.trimIndent()
    }

    private fun appendToEnd(s: String): String {
        return s + "\n" + getBannedString()
    }

    private fun replace(s: String, r: Regex): String {
        return s.replace(r, getBannedString())
    }

}