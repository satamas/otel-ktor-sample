description = "OpenTelemetry-Ktor example"

plugins {
    id("com.avast.gradle.docker-compose") version "0.17.1"
}

subprojects {
    group = "opentelemetry.ktor.example"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }
}

dockerCompose {
    useComposeFiles.add("docker/docker-compose.yml")
}

tasks.register("runWithDocker") {
    dependsOn("composeUp", ":server:run")
}

project(":server").setEnvironmentVariablesForOpenTelemetry()
project(":client").setEnvironmentVariablesForOpenTelemetry()

fun Project.setEnvironmentVariablesForOpenTelemetry() {
    tasks.withType<JavaExec> {
        environment("OTEL_METRICS_EXPORTER", "prometheus")
        environment("OTEL_LOGS_EXPORTER", "none")
        environment("OTEL_TRACES_EXPORTER", "otlp")
        environment("OTEL_EXPORTER_OTLP_PROTOCOL", "grpc")
        environment("PYROSCOPE_APPLICATION_NAME", "my-app")
        environment("PYROSCOPE_SERVER_ADDRESS", "http://localhost:4040")
    }
}