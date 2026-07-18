<div align="center">

# 🚗 Smart Toll Plaza Automation System

### A Spring Boot Microservices-Based Toll Collection Platform

Automating toll collection using **FASTag**, **REST APIs**, and **Spring Boot Microservices**.

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot)
![Spring Cloud Gateway](https://img.shields.io/badge/API-Gateway-blue?style=for-the-badge)
![H2 Database](https://img.shields.io/badge/H2-Database-blue?style=for-the-badge)
![REST API](https://img.shields.io/badge/REST-API-success?style=for-the-badge)
![Microservices](https://img.shields.io/badge/Architecture-Microservices-red?style=for-the-badge)

</div>

---

# 📖 Overview

The **Smart Toll Plaza Automation System** is a microservices-based Spring Boot application developed as a capstone project to automate highway toll collection using FASTag.

The system eliminates manual toll collection by validating registered vehicles, checking wallet balance, deducting toll charges, recording journey details, and routing every client request through a centralized API Gateway.

Each module is developed as an independent Spring Boot microservice following a layered architecture and communicates synchronously using REST APIs.

---

# 🎯 Objectives

- Automate toll payment
- Maintain vehicle registry
- Manage FASTag wallets
- Store journey history
- Enable microservice communication
- Route all client requests through API Gateway

---

# 🏗️ System Architecture

```text
                    Client
                       │
                       ▼
               API Gateway (8080)
                       │
      ┌────────────────┼────────────────┐
      │                │                │
      ▼                ▼                ▼
 Vehicle Service   Wallet Service   Journey Service
      │
      │
      ▼
   Toll Service
```

---

# 🔄 Toll Payment Flow

```text
Vehicle Number

      │
      ▼

Vehicle Service

      │
      ▼

Retrieve FASTag ID

      │
      ▼

Wallet Service

      │
      ▼

Deduct Balance

      │
      ▼

Journey Service

      │
      ▼

Store Journey

      │
      ▼

Return Success Response
```

---

# 📦 Repository Structure

```text
smart-toll-plaza-automation
│
├── api-gateway
│
├── vehicle-service
│
├── wallet-service
│
├── journey-service
│
├── toll-service
│
└── README.md
```

---

# 🧩 Microservices

| Service | Description | README.md |
|----------|-------------|----------|
| 🚗 Vehicle Service | Maintains vehicle and FASTag information |[Readme-link](https://github.com/isravel-eng/smart-toll-plaza-automation/blob/main/VehicleAPI/README.md)|
| 💳 Wallet Service | Manages FASTag wallet balance and recharge |[Readme-link](https://github.com/isravel-eng/smart-toll-plaza-automation/blob/main/WalletAPI/README.md)|
| 🛣️ Journey Service | Stores vehicle journey history |[Readme-link](https://github.com/isravel-eng/smart-toll-plaza-automation/blob/main/JourneyAPI/README.md)|
| 🚧 Toll Service | Coordinates toll payment workflow |[Readme-link](https://github.com/isravel-eng/smart-toll-plaza-automation/blob/main/TollAPI/README.md)|
| 🌐 API Gateway | Central entry point for all APIs |[Readme-link](https://github.com/isravel-eng/smart-toll-plaza-automation/blob/main/APIGateway/README.md)|

---

# ✨ Features

## Vehicle Service

- Register Vehicle
- Update Vehicle
- Delete Vehicle
- Get Vehicle Details
- Unique Vehicle Number
- Unique FASTag ID

---

## Wallet Service

- Create Wallet
- Recharge Wallet
- Deduct Balance
- Check Balance
- Prevent Negative Balance

---

## Journey Service

- Save Journey
- Retrieve Journey History
- Search by Vehicle Number

---

## Toll Service

- Validate Vehicle
- Retrieve FASTag
- Deduct Wallet Balance
- Create Journey
- Return Payment Status

---

## API Gateway

- Route Requests
- Centralized Entry Point
- Simplified Client Communication

---

# 🛠️ Tech Stack

## Backend

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring Cloud Gateway

---

## Database

- H2 Database

---

## Communication

- REST APIs
- RestTemplate

---

## Tools

- IntelliJ IDEA
- Postman
- Git
- GitHub
- Maven

---

# 📁 Project Structure

Every microservice follows a layered architecture.

```text
service-name
│
├── controller
├── service
├── repository
├── entity
├── dto
├── advice
├── exception
└── config
```

---

# 🚀 Running the Project

Start the services in the following order.

```
Vehicle Service
        ↓
Wallet Service
        ↓
Journey Service
        ↓
Toll Service
        ↓
API Gateway
```

---

# 🌐 Default Ports

| Service | Port |
|----------|------|
| API Gateway | 8080 |
| Vehicle Service | 8081 |
| Wallet Service | 8082 |
| Journey Service | 8083 |
| Toll Service | 8084 |

---

# 📡 API Gateway Routes

| Route | Destination |
|---------|-------------|
| /vehicles/** | Vehicle Service |
| /wallet/** | Wallet Service |
| /journeys/** | Journey Service |
| /toll/** | Toll Service |

---

# 🔄 End-to-End Workflow

1. Register Vehicle
2. Create Wallet
3. Recharge Wallet
4. Pay Toll
5. Deduct Wallet Balance
6. Save Journey
7. Return Payment Status

---

# 📚 Key Concepts Demonstrated

- Spring Boot
- REST APIs
- Microservices
- Layered Architecture
- DTO Pattern
- Bean Validation
- Global Exception Handling
- RestTemplate
- API Gateway
- H2 Database
- Logging
- CRUD Operations

---

# 📌 Future Enhancements

- Service Discovery (Eureka)
- OpenFeign Client
- MySQL / PostgreSQL
- Docker
- JWT Authentication
- Spring Security
- Kafka
- Redis Cache
- Swagger / OpenAPI
- Kubernetes Deployment

---

# 👨‍💻 Author

**Isravel Y**

B.Tech Artificial Intelligence & Machine Learning

Saveetha Engineering College

GitHub: https://github.com/isravel-eng

---

## ⭐ If you found this project useful, consider giving it a star.
