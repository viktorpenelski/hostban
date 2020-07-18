package com.github.viktorpenelski.hostban

import java.io.File

open class HostsFileLocation {
    open fun fileLocation(): File? {
        val lowercaseOS = System.getProperty("os.name").toLowerCase()

        return when {
            // TODO impl others
            lowercaseOS.contains("windows") -> {
                File(System.getenv("WinDir") + "\\system32\\drivers\\etc\\hosts")
            }
            else -> null
        }

    }
}
