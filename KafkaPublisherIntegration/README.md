# KafkaPublisherIntegration Module

## Overview
KafkaPublisherIntegration is a Spring project designed as a simple example of an integration test for publishing messages using Kafka. The module focuses on testing the functionality of a Kafka message publisher.

## Features
1. **Integration Testing for Kafka**: Demonstrates how to test message publishing in Spring projects using Kafka.
2. **Dependencies**: Utilizes `spring-kafka` for Kafka integration and `spring-kafka-test` for testing.
3. **Order Creation Scenario**: The project simulates an order creation scenario via POST requests to port `8503`. Orders are intended to be "APPROVED" by a hypothetical restaurant service.
4. **Publisher Focus**: The project focuses solely on the message publishing functionality, omitting the implementation of the consuming restaurant service.
5. **Scenario Execution**: To execute the scenario, send a POST request to `localhost:8503/api/v1/orders` with a payload similar to `json/CreateOrderRequestBody.json`.
6. **Docker Support**: A `docker-compose` file is provided under the `/docker` directory for setting up the necessary infrastructure.
7. **Integration Tests**:
    - Integration test codes are located in the `RestaurantEventPublisherHelperIT.java` class.
    - To run the tests, execute this class.
8. **Embedded Kafka**: Leverages Kafka's embedded broker feature for integration testing, eliminating the need to run an external Kafka broker during tests.

## Notes
- As this project focuses on Kafka publisher integration testing, REST integration tests for `OrderController` have not been implemented.
- For REST call integration testing, refer to the `RestIntegration` module.

## How to Use
1. **Running the Infrastructure**
    - Update the `docker-compose` files in the `/docker` directory to set up necessary infrastructure components if needed.
    - Navigate to the `/docker` directory.
        ```bash
        cd ..\<<your directory>>\docker
        ```
    - Start the Zookeeper container:
        ```bash
        docker compose -f zookeeper.yml -p integration up -d
        ```
    - Wait 30-60 seconds, then start the Kafka container:
        ```bash
        docker compose -f kafka.yml -p integration up -d
        ```
    - Optionally, start Kafka UI:
        ```bash
        docker compose -f kafkaui.yml -p integration up -d
        ```
2. **Run the Project**:
    - Start the project and ensure the application is running on port `8503`.
3. **Post Order Requests**:
    - Send a POST request to `http://localhost:8503/api/v1/orders`.
    - Use the JSON payload from `json/CreateOrderRequestBody.json` as an example.
4. **Run Integration Tests**:
    - Execute the `RestaurantEventPublisherHelperIT.java` class to verify the Kafka message publishing functionality.


## Directory Structure
- `/src/main`: Contains the main application code.
- `/src/test`: Contains the test cases, including integration tests.
- `/docker`: Includes the yml files for infrastructure setups.
- `/json`: Contains example payloads for API requests.

## Prerequisites
- Java Development Kit (JDK 21+)
- Docker (if using the provided `docker-compose` file)
- Maven for project build and dependency management

## Contact
For any questions or contributions, feel free to open an issue or submit a pull request on the GitHub repository.

---

Enjoy testing Kafka message publishing with KafkaPublisherIntegration!

