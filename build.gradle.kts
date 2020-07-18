import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.3.72"
    application
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

group = "com.github.viktorpenelski"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
}

application {
    mainClassName = "com.github.viktorpenelski.hostban.MainKt"
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    shadowJar {
        mergeServiceFiles()
    }
    build {
        dependsOn(shadowJar)
    }
    jar {
        enabled = false
    }
}
