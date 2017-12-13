# Vert.x Starter on Cloud Foundry

A simple Java webapp using [Eclipse Vert.x](http://vertx.io) on [Cloud Foundry](https://www.cloudfoundry.org/)

## Prerequisites

* [Java 1.8](https://www.java.com/download/)
* [CloudFoundry CLI](https://docs.cloudfoundry.org/cf-cli/install-go-cli.html)
* A Cloud service provider, such as [IBM Cloud](https://www.ibm.com/cloud/)

## Download and Build

1. Download code `git clone https://github.com/amdelamar/vertx-cf`
1. `cd vertx-cf`
1. Run build `./gradlew clean build`
1. Test app `java -jar build/libs/vertx-cf-0.1.0-all.jar`
1. Visit [http://localhost:8080/](http://localhost:8080/) to see the app running.

## Deploy to Cloud Foundry

1. Deploy `cf push -f manifest.yml myapp`
1. Run `cf apps` to see the app running. Visit the url provided.

## Notes

This demo runs [Vert.x 3.5.0](http://vertx.io) and is packed using the official [CloudFoundry java-buildpack](https://github.com/cloudfoundry/java-buildpack). The `manifest.yml` specifies memory to be 768MB because any lower and the java-buildpack throws an error that it can't allocate enough heap space.

The environment variable `PORT` is normally provided by your CloudFoundry service, and therefore can change when being deployed. Otherwise the default port is `8080`.

## License

[MIT](/LICENSE)
