# RedisIntegration Module

## Overview
The RedisIntegration module is a simple integration test example designed for Spring projects that cache messages using Redis. This module showcases a scenario where a user is created based on a POST request and stored in a Redis database.

## Features
1. Demonstrates message caching for Spring projects using Redis.
2. Utilizes the `spring-boot-starter-data-redis` dependency.
3. Operates on port `8504`, handling POST requests to create a user and store it in a Redis database.
4. Focuses on Redis messaging, excluding other infrastructure components for simplicity.
5. To run the scenario, send a POST request with a payload similar to `json/CreateUserRequestBody.json` to `localhost:8504/api/v1/users`.
6. Contains a Docker Compose file in the `/docker` directory for setting up the required environment.
7. Employs two separate Redis containers:
    - Service Redis container on port `6379`.
    - Integration test Redis container on port `4379`.
8. Includes `redis-insight.yml` in the `/docker` directory to set up Redis Insight for database visualization.
9. Allows creation and management of separate app and test databases via Redis Insight. Update the IPs of these databases in their respective `application.yml` files for app and test configurations.
10. Integration test codes are located in the `RedisCachePortAdapterTest.java` class. Run this class to execute integration tests.
11. Default ports:
    - `6379` for the service Redis.
    - `4379` for the integration test Redis.
    - Update Docker and `application.yml` files if you prefer different ports.
12. As this is a Redis integration test module, no REST integration tests are written for the `UserController`. For REST call integration tests, refer to the `RestIntegration` module.

## Setup Instructions
1. Navigate to the `/docker` directory.
2. Use `redis.yml` and `redis-insight.yml` to start the Redis containers:
   ```bash
   docker-compose -f redis.yml -p app up -d
   docker-compose -f test-redis.yml -p test up -d
   ```
3. Optionally, use `redis-insight.yml` to set up Redis Insight for database management:
   ```bash
   docker-compose -f redis-insight.yml -p app up -d
   ```
4. Configure the app and test database IPs in the respective `application.yml` files.

## Running Integration Tests
- To execute integration tests, run the `RedisCachePortAdapterTest.java` class.

## Notes
- This project focuses solely on Redis integration testing.
- For REST call integration tests, refer to the `RestIntegration` module.

