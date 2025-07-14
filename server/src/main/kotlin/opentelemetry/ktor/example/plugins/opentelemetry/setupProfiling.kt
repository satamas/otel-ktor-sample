package opentelemetry.ktor.example.plugins.opentelemetry

import io.pyroscope.http.Format
import io.pyroscope.javaagent.EventType
import io.pyroscope.javaagent.PyroscopeAgent
import io.pyroscope.javaagent.config.Config

fun setupPyroscope() {
    PyroscopeAgent.start(
        Config.Builder()
            .setApplicationName("ride-sharing-app-java")
            .setProfilingEvent(EventType.ITIMER)
            .setFormat(Format.JFR)
            .setProfilingAlloc("512k")
            .setServerAddress("http://localhost:4040")
            .build()
    )

}