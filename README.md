# Real-Time Chat Application

Aplikacja komunikatora w czasie rzeczywistym zbudowana z użyciem Spring Boot, WebSocket i JWT.

## 🚀 Funkcjonalności

- **Rejestracja i logowanie użytkowników** z hashowaniem haseł (BCrypt)
- **Uwierzytelnianie JWT** z tokenami dostępu
- **Komunikacja w czasie rzeczywistym** przez WebSocket
- **Prywatne wiadomości** między użytkownikami
- **Historia konwersacji** z możliwością oznaczania jako przeczytane
- **Responsywny interfejs** HTML/JavaScript
- **Baza danych H2** z interfejsem webowym

## 🛠️ Technologie

- **Backend:** Spring Boot 3.2.0
- **Bezpieczeństwo:** Spring Security + JWT
- **WebSocket:** Spring WebSocket + STOMP
- **Baza danych:** H2 Database (w pamięci)
- **ORM:** Spring Data JPA + Hibernate
- **Frontend:** HTML5, JavaScript, SockJS, STOMP.js

## 📋 Wymagania

- Java 21 lub nowsza
- Maven 3.6+

## 🚀 Uruchomienie

1. **Sklonuj repozytorium:**
```bash
git clone https://github.com/twoj-username/RealTimeChat.git
cd RealTimeChat
```

2. **Uruchom aplikację:**
```bash
mvn spring-boot:run
```

3. **Otwórz w przeglądarce:**
```
http://localhost:8080
```

## 📡 API Endpoints

### Autoryzacja
- `POST /api/auth/register` - Rejestracja użytkownika
- `POST /api/auth/login` - Logowanie użytkownika

### Wiadomości
- `POST /api/messages/send` - Wysłanie wiadomości
- `GET /api/messages/conversation/{username}` - Historia konwersacji
- `GET /api/messages/unread` - Nieprzeczytane wiadomości
- `PUT /api/messages/read/{messageId}` - Oznacz jako przeczytane

### WebSocket
- **Endpoint:** `/ws`
- **Wysyłanie:** `/app/sendMessage`
- **Odbieranie:** `/user/queue/messages`

## 🗄️ Baza danych

Aplikacja używa H2 Database w pamięci. Konsola H2 dostępna pod:
```
http://localhost:8080/h2-console
```

**Dane logowania:**
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

## 🔧 Konfiguracja

Główne ustawienia w `src/main/resources/application.properties`:

```properties
server.port=8080
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
```

## 📝 Jak używać

1. **Rejestracja:**
   - Wprowadź username, hasło i email
   - Kliknij "Register"

2. **Logowanie:**
   - Wprowadź username i hasło
   - Kliknij "Login"

3. **Czat:**
   - Wprowadź username odbiorcy
   - Wpisz wiadomość i wyślij
   - Wiadomości pojawiają się w czasie rzeczywistym

## 🏗️ Architektura

```
src/main/java/org/example/
├── config/           # Konfiguracja Spring Security, WebSocket, JWT
├── controller/       # REST API i WebSocket kontrolery
├── dto/             # Data Transfer Objects
├── model/           # Encje JPA (User, Message)
├── repository/      # Repozytoria Spring Data
├── service/         # Logika biznesowa
└── Main.java        # Główna klasa aplikacji
```

## 🔐 Bezpieczeństwo

- **Hasła** hashowane za pomocą BCrypt
- **JWT tokeny** z 10-godzinnym czasem ważności
- **CORS** skonfigurowany dla rozwoju
- **Spring Security** z filtrami autoryzacji

## 🧪 Testowanie

Dla celów testowych można utworzyć wielu użytkowników i testować komunikację:

1. Otwórz aplikację w dwóch kartach przeglądarki
2. Zarejestruj różnych użytkowników w każdej karcie
3. Zaloguj się i wyślij wiadomości między nimi

## 📄 Licencja

Projekt utworzony w celach edukacyjnych.

## 👨‍💻 Autor

Stworzony jako przykład aplikacji Real-Time Chat w Spring Boot.

---

⭐ **Jeśli projekt Ci się podoba, zostaw gwiazdkę na GitHubie!**
