# 🌐 API Gateway

The **API Gateway** is the single entry point for the Smart Toll Plaza Automation System.

Instead of communicating directly with individual microservices, clients send every request to the API Gateway. The gateway forwards the request to the appropriate service, providing centralized routing and simplifying client interaction.

This architecture enables loose coupling, easier scalability, and centralized request management.

---

# 🎯 Responsibilities

- Single Entry Point
- Route Client Requests
- Forward Requests to Microservices
- Hide Internal Service URLs
- Simplify Client Communication
- Improve Maintainability

---

# 🏗 Architecture

```
                    Client
                       │
                       ▼
                API Gateway
                       │
      ┌────────────────┼────────────────┐
      │                │                │
      ▼                ▼                ▼
 Vehicle API      Wallet API      Journey API
                       │
                       ▼
                  Toll API
```

---

# 📂 Project Structure

```
api-gateway
│
├── src
│
├── application.yml
│
├── ApiGatewayApplication
│
└── pom.xml
```

The API Gateway is intentionally lightweight. It does not contain controllers, services, repositories, or database configurations.

---

# 🌍 Why API Gateway?

Without an API Gateway

```
Client

 ├── Vehicle Service

 ├── Wallet Service

 ├── Journey Service

 └── Toll Service
```

The client must know the URL and port of every service.

---

With API Gateway

```
             Client

                │

                ▼

          API Gateway

                │

     ┌──────────┼──────────┐

     ▼          ▼          ▼

 Vehicle     Wallet     Journey

 Service     Service     Service

                │

                ▼

          Toll Service
```

The client communicates with only one endpoint.

---

# 🚀 Default Port

```
8080
```

---

# 🔀 Route Configuration

| Incoming Request | Forwarded To |
|------------------|--------------|
| `/vehicles/**` | Vehicle Service |
| `/wallet/**` | Wallet Service |
| `/journeys/**` | Journey Service |
| `/toll/**` | Toll Service |

---

# 🔄 Request Flow

## Vehicle Registration

```
Client

   │

POST /vehicles

   │

   ▼

API Gateway

   │

   ▼

Vehicle Service

   │

   ▼

Response

   │

   ▼

Client
```


---

## Wallet Recharge

```
Client

   │

PUT /wallet/recharge

   │

   ▼

API Gateway

   │

   ▼

Wallet Service

   │

   ▼

Response
```

---

## Toll Payment

```
Client

      │

POST /toll/pay

      │

      ▼

API Gateway

      │

      ▼

Toll Service

      │

      ▼

Vehicle Service

      │

      ▼

Wallet Service

      │

      ▼

Journey Service

      │

      ▼

Return Success
```

---

# 🌐 Example URLs

Instead of calling

```
http://localhost:8081/api/v1/vehicles
```

Use

```
http://localhost:8080/api/v1/vehicles
```

<img width="1917" height="736" alt="image" src="https://github.com/user-attachments/assets/f8332db2-3d24-454b-bfac-414e3b5fc026" />


---

Instead of

```
http://localhost:8082/api/v1/wallets
```

Use

```
http://localhost:8080/api/v1/wallets
```

<img width="1917" height="597" alt="image" src="https://github.com/user-attachments/assets/447e5c21-28fe-4aff-9080-9cdd3e1bc0b4" />

---

Instead of

```
http://localhost:8083/api/v1/journeys
```

Use

```
http://localhost:8080/api/v1/journeys
```

<img width="1917" height="1041" alt="image" src="https://github.com/user-attachments/assets/22bac05e-1c3e-4032-8bf3-df0892bf642e" />


---

Instead of

```
http://localhost:8084/api/v1/toll/pay
```

Use

```
http://localhost:8080/api/v1/toll/pay
```

<img width="1911" height="815" alt="image" src="https://github.com/user-attachments/assets/46851162-e9e6-493b-a434-48f26c009ae1" />

---

# 📦 Advantages

- Single API endpoint
- Simplified client configuration
- Easier scalability
- Centralized routing
- Reduced client complexity
- Loose coupling between services
- Better maintainability

---

# 🔗 Connected Services

| Service | Port |
|----------|------|
| Vehicle Service | 8081 |
| Wallet Service | 8082 |
| Journey Service | 8083 |
| Toll Service | 8084 |

---

# ▶ Running

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

Run the Gateway

```bash
mvn spring-boot:run
```

---

# 🛠 Technologies

- Java 21
- Spring Boot
- Spring Cloud Gateway
- Spring Web MVC
- Maven

---

# 📈 Future Enhancements

- Eureka Service Discovery
- Config Server
- Load Balancing
- JWT Authentication
- Spring Security
- Rate Limiting
- API Versioning
- Circuit Breaker
- Distributed Tracing
- Request Logging
- Monitoring with Prometheus & Grafana

---

# 📚 Related Services

- 🚗 Vehicle Service
- 💳 Wallet Service
- 🛣️ Journey Service
- 🚧 Toll Service

Together, these services form the complete **Smart Toll Plaza Automation System**.

---

# 👨‍💻 Author

**Isravel Y**

**B.Tech Artificial Intelligence & Machine Learning**

Saveetha Engineering College

GitHub

https://github.com/isravel-eng

---

## ⭐ Star this repository if you found it useful.
