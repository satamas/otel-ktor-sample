val ktor_version: String by project
val kotlin_version: String by project
val opentelemetry_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.1.20"
    id("io.ktor.plugin") version "3.2.1"
    id("application")
    id("com.ryandens.javaagent-application") version "0.8.0"
}

application {
    mainClass.set("opentelemetry.ktor.example.ServerKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(project(":shared"))

    implementation("io.ktor:ktor-server-cio-jvm")
    implementation("io.ktor:ktor-server-websockets:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    javaagent("io.opentelemetry.javaagent:opentelemetry-javaagent:2.17.0")
    javaagent("io.pyroscope:agent:2.0.0")
}