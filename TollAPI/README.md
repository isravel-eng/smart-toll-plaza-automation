# 🛣️ Toll Service

The **Toll Service** is the core orchestration service of the Smart Toll Plaza Automation System.

Unlike the other microservices, the Toll Service does **not** maintain its own database. Instead, it coordinates communication between the Vehicle Service, Wallet Service, and Journey Service to complete a toll transaction.

When a vehicle arrives at a toll plaza, this service validates the vehicle, retrieves the associated FASTag ID, deducts the toll amount from the wallet, records the journey, and returns the transaction status.

---

# 🎯 Responsibilities

- Receive toll payment requests
- Validate registered vehicles
- Retrieve FASTag information
- Deduct wallet balance
- Record completed journeys
- Return payment status
- Coordinate multiple microservices

---

# 🏗 Architecture

```
                    Client
                       │
                       ▼
                TollController
                       │
                       ▼
                 TollService
                       │
      ┌────────────────┼────────────────┐
      │                │                │
      ▼                ▼                ▼
VehicleClient     WalletClient    JourneyClient
      │                │                │
      ▼                ▼                ▼
 Vehicle API      Wallet API      Journey API
```

---

# 📂 Project Structure

```
toll-service
│
├── controller
│     TollController
│
├── service
│     TollService
│
├── client
│     VehicleClient
│     WalletClient
│     JourneyClient
│
├── dto
│     TollRequest
│     TollResponse
│     JourneyDTO
│     WalletDTO
│     VehicleDTO
│
├── config
│     RestTemplateConfig
│
└── TollApiApplication
```

---

# 💡 Why No Database?

The Toll Service is responsible only for **business orchestration**.

It does not own any persistent data.

Instead it communicates with other services through REST APIs.

| Service | Responsibility |
|----------|---------------|
| Vehicle Service | Vehicle Validation |
| Wallet Service | Wallet Deduction |
| Journey Service | Journey Recording |

This keeps the architecture loosely coupled and follows the **Database per Service** principle.

---

# 🔄 Toll Payment Workflow

```
Receive Toll Request

        │

        ▼

Vehicle Service

Validate Vehicle

        │

        ▼

Retrieve FASTag ID

        │

        ▼

Wallet Service

Deduct Balance

        │

        ▼

Enough Balance?

        │

 ┌──────┴─────────┐

 │                │

No               Yes

 │                │

 ▼                ▼

Return Error   Journey Service

                    │

                    ▼

             Save Journey

                    │

                    ▼

          Return Success Response
```

---

# 📡 Service Communication

```
                Toll Service

                      │

      ┌───────────────┼───────────────┐

      ▼               ▼               ▼

Vehicle API      Wallet API      Journey API

      │               │               │

Validate        Deduct Money     Save Journey

Vehicle         Update Balance   Return Success
```

---

# 🚀 REST API

## Pay Toll

```
POST /api/v1/toll/pay
```

### Request

```json
{
    "vehicleNumber":"TN38AB1234",
    "plaza":"Chennai Toll Plaza",
    "amount":150
}
```

---

### Success Response

```json
{
    "message":"Toll Paid Successfully",
    "vehicleNumber":"TN38AB1234",
    "fastagId":"FT1001",
    "amount":150,
    "status":"SUCCESS"
}
```

<img width="1917" height="958" alt="image" src="https://github.com/user-attachments/assets/eab39961-9f29-4e53-a741-39dc36baa39a" />

---

# 🔍 Complete Processing Flow

### Step 1

Receive Toll Request

↓

### Step 2

Call Vehicle Service

```
GET /vehicles/{vehicleNumber}
```

↓

Vehicle Found?

↓

No → Return Error

↓

Yes

↓

Retrieve FASTag ID

↓

### Step 3

Call Wallet Service

```
PUT /wallet/deduct
```

↓

Enough Balance?

↓

No

↓

Return Insufficient Balance

↓

Yes

↓

Balance Updated

↓

### Step 4

Call Journey Service

```
POST /journeys
```

↓

Journey Saved

↓

### Step 5

Return Success Response

---

# 📦 Request DTO

## TollRequest

```text
vehicleNumber

plaza

amount
```

---

# 📦 Response DTO

## TollResponse

```text
message

vehicleNumber

fastagId

amount

status
```

---

# 🌐 External Clients

## VehicleClient

Responsibilities

- Validate Vehicle
- Retrieve FASTag

---

## WalletClient

Responsibilities

- Deduct Balance
- Check Wallet

---

## JourneyClient

Responsibilities

- Save Journey
- Return Journey Status

---

# 🔄 RestTemplate

The Toll Service communicates with all other microservices using

```java
RestTemplate
```

A centralized configuration class provides a reusable RestTemplate bean for outbound REST calls.

---

# ⚠ Error Handling

Possible failures

- Vehicle Not Found
- FASTag Not Found
- Insufficient Balance
- Wallet Service Down
- Journey Service Down
- Invalid Request
- Network Timeout

---

# 📊 Transaction Lifecycle

```
Vehicle Arrives

      │

      ▼

Vehicle Validation

      │

      ▼

Wallet Deduction

      │

      ▼

Journey Recording

      │

      ▼

Payment Completed
```

---

# 🔗 Dependencies

The Toll Service depends on

- Vehicle Service
- Wallet Service
- Journey Service

It should be started **after** these services are running.

---

# 🛠 Technologies

- Java 21
- Spring Boot
- Spring Web
- RestTemplate
- Bean Validation
- Lombok
- Maven

---

# ▶ Running

```bash
mvn spring-boot:run
```

Default Port

```
8084
```

---

# 🚀 Running Order

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

# 📈 Future Improvements

- OpenFeign Client
- Circuit Breaker (Resilience4j)
- Retry Mechanism
- Distributed Tracing
- Kafka Integration
- Service Discovery (Eureka)
- Docker
- Kubernetes
- JWT Authentication
- Swagger/OpenAPI

---

# 👨‍💻 Author

**Isravel Y**

B.Tech Artificial Intelligence & Machine Learning

Saveetha Engineering College

GitHub

https://github.com/isravel-eng
