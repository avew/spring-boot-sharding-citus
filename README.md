# Spring Boot Citus Sharding Example

This project demonstrates how to implement database sharding with Citus and Spring Boot. Citus extends PostgreSQL to distribute data and queries across multiple nodes, enabling horizontal scaling for PostgreSQL databases.

## Overview

This application showcases a simple order management system with user profiles and orders, demonstrating how to:

- Configure a Spring Boot application to work with Citus
- Set up database migrations using Flyway
- Implement data access with Spring Data JPA
- Create RESTful APIs for distributed data
- Handle cross-shard queries

## Prerequisites

- Java 17 or higher
- Docker and Docker Compose
- Maven

## Getting Started

### Setup the Citus Cluster

1. Start the Citus cluster using Docker Compose:

```bash
docker-compose up -d
```

2. Initialize the Citus cluster:

```bash
./init-citus-cluster.sh
```

### Build and Run the Application

```bash
./mvnw clean install
./mvnw spring-boot:run
```

The application will be available at `http://localhost:8080`.

## Project Structure

- `src/main/java`: Java source code
  - `domain`: Entity classes (UserOrder, UserProfile)
  - `repository`: Spring Data JPA repositories
  - `web/rest`: REST controllers
- `src/main/resources`: Application resources
  - `db/migration`: Flyway migration scripts
  - `http`: Sample HTTP requests for testing
  - `application.properties`: Application configuration

## API Endpoints

The application provides the following endpoints:

- `GET /api/orders`: Get all orders
- `GET /api/orders/{id}`: Get order by ID
- `POST /api/orders`: Create a new order
- `GET /api/orders/user/{userId}`: Get orders by user ID

## Testing

The project includes both unit and integration tests. Run them with:

```bash
./mvnw test
```

## Sample HTTP Requests

The project includes sample HTTP request files in the `src/main/resources/http` directory that can be used with tools like IntelliJ IDEA's HTTP Client or VS Code's REST Client extension.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- [Citus Data](https://www.citusdata.com/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
