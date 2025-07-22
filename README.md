# Real-Time Chat Application

A real-time chat application built with Spring Boot, WebSocket, and JWT.

## Features

- User registration and login with password hashing (BCrypt)  
- Authentication and authorization using JWT  
- Real-time communication via WebSocket (STOMP)  
- Private messaging between users  
- Conversation history and marking messages as read  
- Simple frontend with HTML/JavaScript  
- Built-in H2 database with web console

## Technologies

- **Backend**: Spring Boot 3.2, Spring Security, WebSocket, Spring Data JPA  
- **Frontend**: HTML5, JavaScript, SockJS, STOMP.js  
- **Database**: H2 (in-memory)

## Requirements

- Java 21 or newer  
- Maven 3.6 or newer

## Getting Started

```bash
git clone https://github.com/steedware/RealTimeChat.git
cd RealTimeChat
mvn spring-boot:run
```

App will be available at: `http://localhost:8080`

## API Endpoints

### Authentication

- `POST /api/auth/register` – Register a new user  
- `POST /api/auth/login` – Log in

### Messages

- `POST /api/messages/send` – Send a message  
- `GET /api/messages/conversation/{username}` – Get conversation history  
- `GET /api/messages/unread` – Get unread messages  
- `PUT /api/messages/read/{messageId}` – Mark message as read

### WebSocket

- WebSocket Endpoint: `/ws`  
- Send message to: `/app/sendMessage`  
- Receive messages from: `/user/queue/messages`

## Database Access

- H2 Console: `http://localhost:8080/h2-console`  
- JDBC URL: `jdbc:h2:mem:testdb`  
- Username: `sa`  
- Password: `password`

## Configuration

Main settings in `src/main/resources/application.properties`:

```properties
server.port=8080
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
```

## Project Structure

```
src/main/java/org/example/
├── config/         - JWT, WebSocket, and Security configuration
├── controller/     - REST API and WebSocket endpoints
├── dto/            - Data Transfer Objects
├── model/          - JPA entities (User, Message)
├── repository/     - Spring Data interfaces
├── service/        - Business logic
└── Main.java       - Application entry point
```

## License

This project is intended for educational purposes.

Author: `steedware`
