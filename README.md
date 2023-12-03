# Guice Quick Start

## About

https://github.com/drbubbletea/guice-quickstart

[MIT Licensed](LICENSE)

## Libraries

### [lib-foundation-lib](./lib-foundation)

### [lib-vaadin-lib](./lib-vaadin)

## Examples

A few examples of using these libraries are available in the [examples](./examples) folder.

### [Vaadin Web Application](./examples/internal-webapp)

### [Job backend with Quartz](./examples/jobs-backend)

### [REST API](./examples/rest-api)

# Development

Prepare for deploy.
```
# remove snapshot suffix
mvn versions:set -DremoveSnapshot

# ensure build is successful
mvn clean package

# deploy
mvn clean deploy
```