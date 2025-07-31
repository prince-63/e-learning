### E-Learning (Udemy) - Microservices Architecture

A scalable, modular, and production-ready **E-Learning platform**, inspired by **Udemy**, built with **Java 17**, **Spring Boot 3**, **Spring Security 6**, and **Spring Cloud**. This platform follows a **microservices architecture**, enabling horizontal scaling, independent service deployment, and better fault tolerance.

#### Architecture Overview

| Service Name                                  | Port | Responsibility                                                                              |
| --------------------------------------------- | ---- | ------------------------------------------------------------------------------------------- |
| **API Gateway**                               | 8080 | Central entry point for all client requests, handles routing, auth, CORS, and rate-limiting |
| **Eureka Server**                             | 8000 | Service discovery and registration for all microservices                                    |
| **Auth Service**                              | 8081 | Handles user registration, login, and JWT token management                                  |
| **User Service**                              | 8082 | Manages user profiles, avatars, and additional user metadata                                |
| **Course Service**                            | 8083 | Manages course creation, sections, and lectures (MongoDB backed)                            |
| **Media Service** *(Presigned URL Generator)* | 8084 | Handles media uploads via cloud storage (videos, PDFs) using signed URLs                    |
| **Review Service**                            | 8085 | Allows users to submit reviews and ratings for courses                                      |
| **Notification Service**                      | 8087 | Sends emails, SMS, or push notifications using event-driven Kafka messaging                 |

#### Tech Stack & Tools

* **Java 17**, **Spring Boot 3**, **Spring Security 6**, **Spring Cloud Netflix**
* **MongoDB** for dynamic and unstructured data (used in `Course Service`)
* **MySQL** for relational storage (used in `Auth` and `User` services)
* **OpenFeign** for declarative REST clients across services
* **JWT Authentication** (stateless token-based security)
* **Spring Validation** with custom error handling
* **Swagger / OpenAPI 3** for interactive API documentation
* **Docker + Docker Compose** for service orchestration
* **Kafka** for asynchronous event handling and inter-service communication
* **Redis** for caching (e.g., token blacklisting, frequently accessed content)
* **Prometheus + Grafana** for monitoring and alerting
* **Lombok**, **MapStruct**, **AOP Logging**, **ModelMapper**

#### Scalability & Design Principles

* **Separation of Concerns**: Every service has a single, well-defined responsibility.
* **Horizontal Scaling**: Services can be scaled independently based on demand.
* **Stateless Auth**: JWT eliminates session management overhead.
* **Async Processing**: Kafka decouples services for non-blocking operations like notifications.
* **Centralized Configuration** *(Config Server - optional)*: Externalized and versioned configuration.
* **Monitoring & Observability**: Prometheus scrapes metrics; Grafana visualizes trends and alerts.

#### Features Implemented

* 🔐 Secure User Registration & Login (JWT + Refresh Token)
* 👤 Complete User Profile Management with Avatar Upload
* 📘 Course Creation with Sections & Lectures (video, PDF)
* 🎥 Media Uploads using Presigned URLs (Cloudinary)
* ✍️ Course Reviews and Ratings System
* 📣 Event-Driven Notification Service (via Email/SMS)
* 📊 Real-time Monitoring with Prometheus & Grafana
* 📄 Full Swagger API Documentation for each service