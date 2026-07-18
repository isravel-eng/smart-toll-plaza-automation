# 💳 Wallet Service

The **Wallet Service** manages FASTag wallet operations in the Smart Toll Plaza Automation System.

It is responsible for creating FASTag wallets, maintaining wallet balances, recharging accounts, deducting toll amounts, and validating sufficient balance before every toll transaction.

The Toll Service communicates with this microservice whenever a toll payment is initiated.

---

# 🎯 Responsibilities

- Create FASTag Wallet
- Recharge Wallet
- Deduct Toll Amount
- Retrieve Wallet Details
- Delete Wallet
- Maintain Wallet Balance
- Prevent Duplicate FASTag IDs
- Validate Available Balance

---

# 🏗️ Architecture

```
                Client
                   │
                   ▼
           WalletController
                   │
                   ▼
            WalletService
                   │
                   ▼
          WalletRepository
                   │
                   ▼
              H2 Database
```

---

# 📂 Project Structure

```
wallet-service
│
├── controller
│     WalletController
│
├── service
│     WalletService
│
├── repository
│     WalletRepository
│
├── entity
│     Wallet
│
├── dto
│     WalletRequest
│     RechargeRequest
│     DeductRequest
│
├── advice
│     ErrorResponse
│     GlobalExceptionHandler
│
├── exception
│     DuplicateFastTagException
│     FastTagNotFoundException
│     InsufficientBalanceException
│     InvalidAmountException
│
└── WalletApiApplication
```

---

# 📦 Entity

## Wallet

| Field | Type | Description |
|--------|------|-------------|
| id | Integer | Primary Key |
| fastagId | String | Unique FASTag ID |
| balance | Double | Current Wallet Balance |

---

# 📑 DTOs

## WalletRequest

Used while creating a wallet.

```text
fastagId
```

---

## RechargeRequest

```text
fastagId

amount
```

---

## DeductRequest

```text
fastagId

amount
```

---

# 🔄 Business Flow

## Create Wallet

```
Client

   │

   ▼

FASTag Exists?

   │

Yes──────────────► Throw Exception

   │

No

   │

Create Wallet

   │

Balance = 0

   │

Return Created
```

---

## Recharge Wallet

```
Receive Request

      │

      ▼

FASTag Exists?

      │

No────────► Exception

      │

Yes

      ▼

Amount > 100 ?

      │

No────────► Invalid Amount

      │

Yes

      ▼

Increase Balance

      │

Save Wallet

      │

Return Success
```

---

## Deduct Wallet Balance

```
Receive Request

      │

      ▼

FASTag Exists?

      │

No────────► Exception

      │

Yes

      ▼

Enough Balance?

      │

No────────► Insufficient Balance

      │

Yes

      ▼

Deduct Amount

      │

Save Wallet

      ▼

Return Updated Wallet
```

---

# 🚀 REST APIs

---

## Create Wallet

```
POST /api/v1/wallets
```

### Request

```json
{
    "fastagId":"FT1001"
}
```

### Response

```
201 CREATED
```

---

## Get Wallet

```
GET /api/v1/wallets/{fastagId}
```

---

## Get All Wallets

```
GET /api/v1/wallets
```

---

## Recharge Wallet

```
PUT /api/v1/wallets/recharge
```

### Request

```json
{
    "fastagId":"FT1001",
    "amount":500
}
```

---

## Deduct Wallet

```
PUT /api/v1/wallets/deduct
```

### Request

```json
{
    "fastagId":"FT1001",
    "amount":150
}
```

---

## Delete Wallet

```
DELETE /api/v1/wallets/{id}
```

---

# 💰 Wallet Rules

## Wallet Creation

- FASTag ID must be unique.
- Every new wallet starts with a balance of **₹0**.

---

## Recharge Rules

- FASTag ID must exist.
- Recharge amount must be greater than **₹100**.

---

## Deduction Rules

- FASTag ID must exist.
- Wallet must contain sufficient balance.
- Balance is updated immediately after successful deduction.

---

# ⚠ Exception Handling

Global exception handling is implemented using:

```
@RestControllerAdvice
```

Handled Exceptions

- DuplicateFastTagException
- FastTagNotFoundException
- InvalidAmountException
- InsufficientBalanceException
- ValidationException

---

# 📄 Error Response

```json
{
    "statusCode":400,
    "errorType":"Validation Failed",
    "errorMessage":"Amount must be greater than 100",
    "timestamp":"2026-07-18T15:20:00"
}
```

---

# 🗄 Repository

Extends

```
JpaRepository<Wallet,Integer>
```

Custom Methods

```java
findByFastagId()

existsByFastagId()
```

---

# 🔍 Logging

SLF4J logging is implemented throughout the service.

Logs include

- Wallet Creation
- Recharge Request
- Deduction Request
- Duplicate FASTag
- Invalid Amount
- Insufficient Balance
- Wallet Deletion

---

# 🔗 Integration

This service is consumed by

## Toll Service

Purpose

- Deduct Toll Amount
- Verify Available Balance

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
8082
```

---

# 🧪 Testing

Recommended Tools

- Postman
- IntelliJ HTTP Client
- curl

---

# 📌 Future Improvements

- Transaction History
- Recharge History
- Wallet Statements
- Daily Limits
- Auto Recharge
- JWT Authentication
- Swagger/OpenAPI
- Docker Support
- MySQL/PostgreSQL
- OpenFeign Client

---

# 👨‍💻 Author

**Isravel Y**

B.Tech Artificial Intelligence & Machine Learning

Saveetha Engineering College

GitHub

https://github.com/isravel-eng
