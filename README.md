# ApexPay: Distributed Payment Orchestrator

ApexPay is a high-performance, microservices-based payment orchestration system built with **Java 21**, **Spring Boot 3.4**, and **Keycloak**.

## 🚀 Key Features (In Progress)
- **Identity Management:** Secure OIDC authentication via Keycloak.
- **Idempotency:** Distributed locking to prevent double-spending.
- **Resilience:** Circuit breakers and retry patterns using Resilience4j.
- **Event-Driven:** Asynchronous notifications via RabbitMQ.

## 🛠 Tech Stack
- **Backend:** Java 21, Spring Boot 3.4, Spring Data JPA
- **Security:** Keycloak (OAuth2/OIDC)
- **Database:** PostgreSQL
- **DevOps:** Docker, Docker Compose

## 🏗 System Architecture


## 🚦 Getting Started
1. Clone the repo.
2. Run `docker-compose up -d`.
3. Run `./mvnw spring-boot:run`.