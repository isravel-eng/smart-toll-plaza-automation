# 🚗 Vehicle Service

The **Vehicle Service** is responsible for managing vehicle registration and maintaining FASTag information in the Smart Toll Plaza Automation System.

It serves as the primary source of vehicle information for other microservices. Before processing any toll payment, the Toll Service validates the vehicle and retrieves its associated FASTag ID through this service.

---

# 🎯 Responsibilities

- Register new vehicles
- Maintain FASTag information
- Retrieve vehicle details
- Update vehicle information
- Delete vehicles
- Validate unique Vehicle Number
- Validate unique FASTag ID

---

# 🏗️ Architecture

```
                Client
                   │
                   ▼
          VehicleController
                   │
                   ▼
            VehicleService
                   │
                   ▼
         VehicleRepository
                   │
                   ▼
              H2 Database
```

---

# 📂 Project Structure

```
vehicle-service
│
├── controller
│     VehicleController
│
├── service
│     VehicleService
│
├── repository
│     VehicleRepository
│
├── entity
│     Vehicle
│
├── dto
│     VehicleDTO
│
├── enums
│     VehicleType
│
├── advice
│     ErrorResponse
│     GlobalExceptionHandler
│
├── exception
│     DuplicateVehicleException
│     FastTagNotFoundException
│     VehicleNotFoundException
│
└── VehicleApiApplication
```

---

# 📦 Entity

## Vehicle

| Field | Type | Description |
|---------|------|-------------|
| id | Integer | Primary Key |
| vehicleNumber | String | Unique Registration Number |
| ownerName | String | Vehicle Owner |
| vehicleType | Enum | CAR / BUS / TRUCK / BIKE |
| fastagId | String | Unique FASTag ID |

---

# 📑 DTO

## VehicleDTO

Used to receive client requests.

```java
vehicleNumber

ownerName

vehicleType

fastagId
```

Validation includes

- Vehicle Number Pattern
- Mandatory Fields
- Owner Name Length
- Vehicle Type Validation
- FASTag Required

---

# 🚙 Vehicle Type

Supported Vehicle Types

```
CAR

BUS

TRUCK

BIKE
```

---

# 🔄 Business Flow

```
Register Vehicle

       │

       ▼

Validate Request

       │

       ▼

Vehicle Number Exists?

       │

 ┌─────┴──────┐

 │            │

Yes          No

 │            │

 ▼            ▼

Throw      FASTag Exists?

Exception       │

         ┌─────┴─────┐

         │           │

       Yes          No

         │           │

         ▼           ▼

      Throw      Save Vehicle

      Exception      │

                     ▼

             Return Created
```

---

# 🚀 REST APIs

## Register Vehicle

```
POST /api/v1/vehicles
```

### Request

```json
{
  "vehicleNumber":"TN38AB1234",
  "ownerName":"Isravel",
  "vehicleType":"CAR",
  "fastagId":"FT1001"
}
```

### Success

```
201 CREATED
```

---

## Get Vehicle

```
GET /api/v1/vehicles/{vehicleNumber}
```

---

## Get All Vehicles

```
GET /api/v1/vehicles
```

---

## Update Vehicle

```
PUT /api/v1/vehicles/{id}
```

---

## Delete Vehicle

```
DELETE /api/v1/vehicles/{id}
```

---

# ⚠ Validation

| Field | Validation |
|---------|------------|
| Vehicle Number | Required + Regex |
| Owner Name | Minimum 3 Characters |
| Vehicle Type | Enum Validation |
| FASTag ID | Mandatory |

Example

```
TN42IK2007
```

---

# ⚠ Exception Handling

Global exception handling is implemented using

```
@RestControllerAdvice
```

Handled Exceptions

- DuplicateVehicleException
- VehicleNotFoundException
- FastTagNotFoundException
- MethodArgumentNotValidException

---

# 📄 Error Response

All exceptions return a common response format.

```json
{
  "statusCode":400,
  "errorType":"Validation Failed",
  "errorMessage":"Vehicle already exists",
  "timestamp":"2026-07-18T12:00:00"
}
```

---

# 🗄 Repository

Repository extends

```
JpaRepository<Vehicle,Integer>
```

Custom Methods

```java
existsByVehicleNumber()

existsByFastagId()

findByVehicleNumber()
```

---

# 🔍 Logging

SLF4J logging is used to trace application flow.

Examples

- Vehicle Registration
- Vehicle Fetch
- Vehicle Update
- Vehicle Delete
- Duplicate Vehicle
- Vehicle Not Found

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
8081
```

---

# 🧪 Testing

The APIs can be tested using

- Postman
- IntelliJ HTTP Client
- curl

---

# 🔗 Used By

This service is consumed by

- Toll Service

to

- Validate Vehicle
- Retrieve FASTag ID

---

# 📌 Future Improvements

- Pagination
- Vehicle Search
- Swagger Documentation
- MySQL Support
- Unit Testing
- Docker
- OpenFeign
- Eureka Discovery

---

# 👨‍💻 Author

**Isravel Y**

B.Tech Artificial Intelligence & Machine Learning

Saveetha Engineering College

GitHub:
https://github.com/isravel-eng
