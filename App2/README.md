# App2 - Spring Boot Application

## Overview
App2 is a standalone Spring Boot application. This README outlines the typical architecture, build/run instructions, and guidance to extend it following best practices.

## Architecture
- Framework: Spring Boot
- Language: Java 17
- Build: Maven
- Common Starters: spring-boot-starter-web, spring-boot-starter-test, lombok (as applicable)

## Typical Project Structure
```
src/main/java/
  └─ your/package/
     ├─ App2Application.java      # Main class (@SpringBootApplication)
     ├─ controller/               # REST controllers
     ├─ service/                  # Business services
     ├─ repository/               # Spring Data repositories
     └─ model/                    # Entities/DTOs
src/main/resources/
  ├─ application.properties       # Config
  └─ static/templates             # Optional web assets
```

## Key Concepts Implemented/Recommended
- Layered architecture (Controller → Service → Repository)
- DTO mapping (ModelMapper or manual)
- Validation (Jakarta Bean Validation)
- Error handling (ControllerAdvice)

## Build & Run
```bash
mvn clean install
mvn spring-boot:run
```

## Configuration
Set properties in `application.properties`:
```properties
server.port=8080
spring.application.name=App2
```

## Extending App2
- Add REST endpoints in `controller`
- Implement business logic in `service`
- Persist data via Spring Data JPA in `repository`

## Testing
- Unit tests with JUnit 5 and Mockito
- Web layer tests with MockMvc
