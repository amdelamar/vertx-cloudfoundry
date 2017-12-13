# Vert.x Starter on Cloud Foundry

A simple Java webapp using Eclipse Vert.x 3.5.0 (http://vertx.io) on Cloud Foundry

## Prerequisites

* [JDK 1.8](https://www.java.com/en/download/faq/develop.xml)
* [CloudFoundry CLI](https://docs.cloudfoundry.org/cf-cli/install-go-cli.html)
* [Eclipse](https://eclipse.org/downloads/) or [Spring Tools Suite](https://spring.io/tools) (Optional)

## Run Manually

1. Download code `git clone https://github.com/amdelamar/vertx-cf`
1. `cd vertx-cf`
1. Run build `./gradlew clean build`
1. Start server `java -jar build/libs/vertx-cf-0.1.0.jar`
1. Visit [https://localhost:8443/](https://localhost:8443/) to see the app running.

## Run with Redeploy

App can be run in redeploy mode, so any changes to files are recompiled quickly. Which is useful for development.

1. Run redeploy `./gradlew run`
1. Visit [https://localhost:8443/](https://localhost:8443/) to see the app running.

This last command launches the application and redeploys as soon as you change something in `src/`.

## Run in Cloud Foundry

1. Run build `./gradlew clean build`
1. Deploy `cf push -f manifest.yml myapp`
1. Run `cf apps` to see the app running. Visit the url provided by your Cloud Foundry service.

## License

[MIT](/LICENSE)
