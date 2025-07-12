# OpenTelemetry-Ktor Demo

[OpenTelemetry](https://opentelemetry.io/) provides support for Ktor with the `KtorClientTelemetry`and `KtorServerTelemetry`
plugins for the Ktor client and server respectively. For the source code, see
the [repository on GitHub](https://github.com/open-telemetry/opentelemetry-java-instrumentation/tree/main/instrumentation/ktor).

This project contains examples of how to use the `KtorClientTelemetry` and `KtorServerTelemetry` plugins.

You can find examples for the client plugin `KtorClientTelemetry` in
the [extractions](./client/src/main/kotlin/opentelemetry/ktor/example/plugins/opentelemetry) folder. \
And you can find examples for the server plugin `KtorServerTelemetry` in
the [extractions](./server/src/main/kotlin/opentelemetry/ktor/example/plugins/opentelemetry) folder.

## Running

**Note:** You need to have [Docker](https://www.docker.com/) installed and running to run the sample.

To run this sample, execute the following command from the `opentelemetry` directory::

```bash
./gradlew :runWithDocker
```

It will start a `Grafana`, `Grafana Tempo` and `Prometheus` in the docker container and
then it will start a `server` on http://localhost:8080/. 

Then, to run the client, which will send requests to a server, you can execute the following command in
an `opentelemetry` directory:

```bash
./gradlew :client:run
```

**Note:** In this example, we use
an [OpenTelemetry java agent](https://opentelemetry.io/docs/zero-code/java/agent/) on server side,
we set environment variables
in [build.gradle.kts](./build.gradle.kts) file.
You can find more information about these environment variables
in the [OpenTelemetry documentation](https://opentelemetry.io/docs/languages/sdk-configuration/).

Let's check what we will see in the `Grafana` after running the server (with Docker) and the client:

1) Go to http://localhost:3000, login with admin/admin.
2) Check created metrics either in explore section by selecting prometheus datasource or in one preconfigured dashboard with http traffic stats
3) Check created traces in explore section by selecting Tempo datasource and switching to search tab 
4) Check profiles in Drilldown/profiles 
