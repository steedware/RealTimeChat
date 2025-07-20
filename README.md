# Real-Time Chat Application

Aplikacja komunikatora w czasie rzeczywistym zbudowana z użyciem Spring Boot, WebSocket i JWT.

## Funkcje

- Rejestracja i logowanie użytkowników z hashowaniem haseł (BCrypt)  
- Uwierzytelnianie i autoryzacja za pomocą JWT  
- Komunikacja w czasie rzeczywistym przez WebSocket (STOMP)  
- Prywatne wiadomości między użytkownikami  
- Historia konwersacji i oznaczanie wiadomości jako przeczytane  
- Prosty frontend HTML/JavaScript  
- Wbudowana baza danych H2 z konsolą webową

## Technologie

- Backend: Spring Boot 3.2, Spring Security, WebSocket, Spring Data JPA  
- Frontend: HTML5, JavaScript, SockJS, STOMP.js  
- Baza danych: H2 (w pamięci)

## Wymagania

- Java 21 lub nowsza  
- Maven 3.6 lub nowszy

## Uruchomienie

```bash
git clone https://github.com/twoj-username/RealTimeChat.git
cd RealTimeChat
mvn spring-boot:run
```

Aplikacja dostępna pod adresem: `http://localhost:8080`

## API

### Autoryzacja

- `POST /api/auth/register` – rejestracja  
- `POST /api/auth/login` – logowanie

### Wiadomości

- `POST /api/messages/send` – wysyłanie wiadomości  
- `GET /api/messages/conversation/{username}` – historia konwersacji  
- `GET /api/messages/unread` – nieprzeczytane wiadomości  
- `PUT /api/messages/read/{messageId}` – oznaczenie jako przeczytane

### WebSocket

- Endpoint: `/ws`  
- Wysyłanie: `/app/sendMessage`  
- Odbiór: `/user/queue/messages`

## Baza danych

- Konsola H2: `http://localhost:8080/h2-console`  
- JDBC URL: `jdbc:h2:mem:testdb`  
- Username: `sa`  
- Password: `password`

## Konfiguracja

Główne ustawienia w `src/main/resources/application.properties`:

```properties
server.port=8080
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
```

## Struktura projektu

```
src/main/java/org/example/
├── config/         - konfiguracje JWT, WebSocket, Security
├── controller/     - REST API i WebSocket
├── dto/            - obiekty transferowe
├── model/          - encje JPA (User, Message)
├── repository/     - interfejsy Spring Data
├── service/        - logika biznesowa
└── Main.java       - klasa uruchamiająca aplikację
```

## Licencja

Projekt przeznaczony do celów edukacyjnych.  

Autor: `steedware`
