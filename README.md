# Task Management API

A secure Spring Boot backend for managing users and tasks. The application supports user registration and login, JWT-based authentication, and task CRUD operations backed by PostgreSQL.

## Overview

This project is designed as a lightweight task management system where users can:

- create an account
- log in securely
- create tasks
- view all tasks
- view a task by ID
- update existing tasks

The backend uses Spring Security and JWT to protect task endpoints while allowing public access to user registration and login routes.

## Tech Stack

- Java 17
- Spring Boot 4.0.5
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Spring Security
- JWT (jjwt)
- Maven

## Features

- User registration with password hashing via BCrypt
- User authentication via JWT token generation
- Protected task endpoints requiring a valid bearer token
- Task persistence to PostgreSQL
- Global exception handling for invalid credentials and missing records
- Support for task lifecycle statuses such as `To Do`, `In Progress`, and other custom statuses

## Project Structure

```text
Backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── Config/
│   │   │   ├── Controller/
│   │   │   ├── Entity/
│   │   │   ├── Exception/
│   │   │   ├── Repository/
│   │   │   ├── Service/
│   │   │   └── dto/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/demo/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── .gitattributes
```

## Prerequisites

Before running the project, make sure you have:

- Java 17+
- Maven or the included Maven wrapper
- PostgreSQL installed and running
- A PostgreSQL database named `taskManager` (or update the configuration to match your setup)

## Configuration

Update the database and JWT settings in `Backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskManager
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

jwt.secret=YourVeryLongSecretKeyMustBeAtLeast32CharactersLong12345
jwt.expiration=86400000
```

Important:

- ensure the PostgreSQL database exists before starting the app
- use a production-grade secret key in real deployments

## Running the Application

From the project root:

```bash
cd Backend
./mvnw clean install
./mvnw spring-boot:run
```

On Windows, use:

```powershell
cd Backend
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

The app will start on the default Spring Boot port:

```text
http://localhost:8080
```

## API Endpoints

### Public Routes

#### Register a user

```http
POST /user/register
Content-Type: application/json
```

Request body:

```json
{
  "username": "alice",
  "password": "secret123"
}
```

#### Log in a user

```http
POST /user/login
Content-Type: application/json
```

Request body:

```json
{
  "username": "alice",
  "password": "secret123"
}
```

Response: a JWT string returned as a plain text response.

### Protected Routes

All `/task` routes require a valid bearer token in the `Authorization` header.

#### Create a task

```http
POST /task
Authorization: Bearer <token>
Content-Type: application/json
```

Request body:

```json
{
  "taskName": "Prepare sprint demo",
  "status": "To Do"
}
```

#### Get all tasks

```http
GET /task
Authorization: Bearer <token>
```

#### Get a task by ID

```http
GET /task/{id}
Authorization: Bearer <token>
```

#### Update a task

```http
PUT /task/{id}
Authorization: Bearer <token>
Content-Type: application/json
```

Request body:

```json
{
  "taskName": "Prepare sprint demo",
  "status": "In Progress"
}
```

## Example cURL Requests

Register:

```bash
curl -X POST http://localhost:8080/user/register \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"secret123"}'
```

Login:

```bash
curl -X POST http://localhost:8080/user/login \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"secret123"}'
```

Get tasks with token:

```bash
curl -X GET http://localhost:8080/task \
  -H "Authorization: Bearer <your_jwt_token>"
```

## Notes

- User passwords are stored as BCrypt-hashed values, not plain text.
- Task creation automatically sets timestamps and a default status as `To Do`.
- The project is a backend-only implementation and can be connected to a frontend client or API consumer.
- This is a good starting point for expanding into categories, due dates, task assignments, and user-specific task ownership.
