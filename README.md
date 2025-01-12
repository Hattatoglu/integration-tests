# Spring Integration Test Examples

## General Overview

This repository contains various Java projects built using the Spring Framework.

## Purpose

1. The main goal of this project is to provide examples of integration tests that can be written for the infrastructures used in Spring-based projects.
2. This project serves as a reference for developers who are new to writing integration tests or those interested in learning more about this topic within Spring applications.

## RestIntegration Module Features

1. This module is a RESTful service designed using `spring-boot-starter-web`.
2. Integration tests have been written for both `POST` and `GET` requests handled by the Controllers in this module.

## MySqlJpaIntegration Module Features
1. This module was developed for MySQL JPA integration tests.
2. Integration tests related to CRUD operations were written in this module.

## KafkaPublisherIntegration Module Features
1. This module is an example for Kafka message publisher integration tests.
2. `spring-kafka` and `spring-kafka-test` dependencies is utilized for integration tests.

## RedisIntegration Module Features
1. Demonstrates message caching for Spring projects using Redis.
2. Utilizes the `spring-boot-starter-data-redis` dependency.

## Getting Started

To get started with this project:

1. Clone the repository:
    ```bash
    git clone https://github.com/Hattatoglu/integration-tests.git
    ```

2. Navigate to the desired module (for example, the `RestIntegration` module).

3. Run the Spring Boot application:
    ```bash
    ./mvnw spring-boot:run
    ```

4. To run the integration tests:
    ```bash
    ./mvnw test
    ```

## Modules

- **RestIntegration**: Contains examples of integration tests for a RESTful service, including testing of `POST` and `GET` requests.

## Contribution

Feel free to fork this repository and contribute by submitting pull requests or opening issues if you have suggestions or find bugs.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

