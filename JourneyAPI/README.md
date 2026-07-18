# 🛣️ Journey Service

The **Journey Service** records every successful toll transaction by storing vehicle journey details.

Whenever a vehicle successfully passes through a toll plaza, the Toll Service sends the journey information to this service, which persists the record for future tracking and reporting.

This service acts as the journey history module of the Smart Toll Plaza Automation System.

---

# 🎯 Responsibilities

- Store completed journeys
- Maintain toll transaction history
- Retrieve journey records
- Search journeys by vehicle number
- Record entry and exit timestamps
- Store payment status

---

# 🏗️ Architecture

```
                Client
                   │
                   ▼
          JourneyController
                   │
                   ▼
           JourneyService
                   │
                   ▼
         JourneyRepository
                   │
                   ▼
              H2 Database
```

---

# 📂 Project Structure

```
journey-service
│
├── controller
│     JourneyController
│
├── service
│     JourneyService
│
├── repository
│     JourneyRepository
│
├── entity
│     Journey
│
├── dto
│     JourneyDTO
│
├── advice
│     ErrorResponse
│     GlobalExceptionHandler
│
├── exception
│     JourneyNotFoundException
│
└── JourneyApiApplication
```

---

# 📦 Entity

## Journey

| Field | Type | Description |
|--------|------|-------------|
| id | Integer | Primary Key |
| vehicleNumber | String | Registered Vehicle Number |
| startTime | LocalDateTime | Journey Start Time |
| endTime | LocalDateTime | Journey End Time |
| plaza | String | Toll Plaza Name |
| amount | Double | Toll Amount |
| paymentStatus | String | Payment Result |

---

# 📑 Journey DTO

Used to receive journey information from the Toll Service.

```text
vehicleNumber

startTime

endTime

plaza

amount

paymentStatus
```

---

# 🔄 Business Flow

```
Receive Journey Request

          │

          ▼

Validate Request

          │

          ▼

Create Journey Entity

          │

          ▼

Save into Database

          │

          ▼

Return Success Response
```

---

# 🚀 REST APIs

---

## Save Journey

```
POST /api/v1/journeys
```

### Request

```json
{
    "vehicleNumber":"TN38AB1234",
    "startTime":"2026-07-18T10:30:00",
    "endTime":"2026-07-18T10:35:00",
    "plaza":"Chennai Toll Plaza",
    "amount":150,
    "paymentStatus":"SUCCESS"
}
```

### Response

```
201 CREATED
```

<img width="1917" height="1023" alt="image" src="https://github.com/user-attachments/assets/03d610db-d9da-49f6-8356-e7f6d9bf9392" />


---

## Get All Journeys

```
GET /api/v1/journeys
```

<img width="1917" height="1078" alt="image" src="https://github.com/user-attachments/assets/131b3bd0-288c-440d-87b9-4b540bae2a87" />

---

## Get Journey By Vehicle Number

```
GET /api/v1/journeys/{vehicleNumber}
```

<img width="1917" height="1078" alt="image" src="https://github.com/user-attachments/assets/6fdb3546-cacf-404d-a75f-dcb71e09fa70" />


---


# 📌 Journey Lifecycle

```
Vehicle crosses Toll Plaza

        │

        ▼

Payment Successful

        │

        ▼

Toll Service

        │

        ▼

Journey Service

        │

        ▼

Journey Saved

        │

        ▼

History Available
```

---

# 📋 Validation

The Journey Service validates incoming requests before saving records.

Validation includes

- Vehicle Number Required
- Toll Plaza Required
- Amount Required
- Payment Status Required
- Entry Time Required
- Exit Time Required

---

# 💳 Payment Status

Typical values

```
SUCCESS

FAILED

PENDING
```

---

# ⚠ Exception Handling

Global exception handling is implemented using

```
@RestControllerAdvice
```

Handled Exceptions

- JourneyNotFoundException
- ValidationException

---

# 📄 Error Response

```json
{
    "statusCode":404,
    "errorType":"Journey Not Found",
    "errorMessage":"Journey not found for vehicle TN38AB1234",
    "timestamp":"2026-07-18T18:20:00"
}
```

---

# 🗄 Repository

Extends

```
JpaRepository<Journey,Integer>
```

Custom Methods

```java
findByVehicleNumber()

findAll()
```

---

# 🔗 Integration

This service is primarily consumed by

## Toll Service

Purpose

- Store successful toll transactions
- Maintain journey history
- Generate travel records

---

# 🔍 Logging

Application logging tracks

- Journey Creation
- Journey Retrieval
- Validation Errors
- Journey Search
- Database Operations

---

# 🛠 Technologies

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- Lombok
- H2 Database
- Maven

---

# ▶ Running

```bash
mvn spring-boot:run
```

Default Port

```
8083
```

---

# 🧪 Testing

Recommended Tools

- Postman
- IntelliJ HTTP Client
- curl

---

# 📊 Future Enhancements

- Journey Pagination
- Date Range Search
- Vehicle-wise Reports
- Monthly Reports
- Export to Excel
- Export to PDF
- Dashboard Analytics
- Swagger/OpenAPI
- Docker Support
- MySQL/PostgreSQL

---

# 👨‍💻 Author

**Isravel Y**

B.Tech Artificial Intelligence & Machine Learning

Saveetha Engineering College

GitHub

https://github.com/isravel-eng
