# java-example-mq-monorepo

This repository contains a minimal producer demo using:

- Maven multi-module build
- Avro contracts packaged as a JAR (`contracts-avro`)
- Spring Boot producer application (`producer-app`)
- Spring Boot consumer application (`consumer-app`)
- RabbitMQ as the message broker
- Optional: Nx to orchestrate Maven targets and run affected builds in CI

## Repository layout

- `contracts-avro/`  
  Avro `.avsc` schemas and generated Java classes (SpecificRecord). This module is the shared “contracts” artifact that can be published and consumed by other JVM services.

- `producer-app/`  
  Spring Boot app that exposes HTTP endpoints and publishes Avro-encoded messages to RabbitMQ.

- `consumer-app/`  
  Spring Boot app that exposes HTTP endpoints and listens for Avro-encoded messages from RabbitMQ.

## Prerequisites

- Java 21
- Maven 3.9+
- Docker (for RabbitMQ)
- Node.js 20+ (only if you use Nx commands / workflows)

## Start RabbitMQ

From the repo root:

```bash
docker compose up -d
```

## Running the application

```bash
mvn -pl producer-app spring-boot:run
```