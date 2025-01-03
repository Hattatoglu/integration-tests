# MySqlJpaIntegration Module

## Overview
MySqlJpaIntegration is a simple integration test example for Spring projects that use MySQL. It demonstrates how to use multiple MySQL containers and provides basic functionality to handle POST requests and perform database operations.

## Features
1. **Integration Testing:** A basic integration test setup for Spring projects using MySQL.
2. **Multiple MySQL Containers:** The project utilizes two MySQL containers running on ports 3306 (for app) and 4306 (for test), respectively.
3. **Basic Functionality:** The application listens on port 8501 and handles POST requests to create records in the database.
4. **Endpoint Usage:** To run the project, send a POST request to `http://localhost:8501/api/v1/accounts` with a JSON payload similar to the one found in `json/CreateAccountRequest.json`.
5. **Docker Compose:** The `docker-compose.yml` file used in this project is located in the `/docker` directory.
6. **Integration Tests:** All integration test cases are available in the `AccountServiceImplTest.java` class. Run this class to execute the tests.

> **Note:** Since this project focuses on MySQL JPA integration testing, no REST integration tests have been written for `AccountController`. For REST call integration tests, refer to the `RestIntegration` module.

## How to Run
1. Ensure Docker and Docker Compose are installed and running on your machine.
2. Navigate to the `/docker` directory and start the containers using:
   ```bash
   docker compose -f mysql_app_and_test_db.yml -p integration up -d
   ```
3. Run the Spring Boot application.
4. Send a POST request to `http://localhost:8501/api/v1/accounts` with the payload provided in `json/CreateAccountRequest.json`.
5. To execute the integration tests, run the `AccountServiceImplTest.java` class.

## Directory Structure
```
MySqlJpaIntegration/
├── docker/
│   └── docker-compose.yml
├── json/
│   └── CreateAccountRequest.json
├── src/
│   └── main/
│       └── java
│           └── ...
│       └── resources
│           └── application.yml (for app)
│   └── test/
│       └── java/
│           └── xmpl.eyaz.integration.mysql.service.AccountServiceImplTest.java
│       └── resources
│           └── application.yml (for test)
└── README.md
```

## Prerequisites
- Java 21 or higher
- Spring Boot
- Docker and Docker Compose

## Additional Notes
- Ensure the MySQL containers are up and running before testing the application.
- Modify the database configurations if required in the `application.yml` files.

---

Feel free to contribute and improve this project!
