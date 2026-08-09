# App3 - Spring Boot Application

## Overview
App3 is a Spring Boot application scaffold. This guide documents architecture, conventions, and how to run and evolve the service.

## Architecture
- Spring Boot (Java 17)
- Maven build
- Typical starters: Web, Data JPA (if DB used), Lombok

## Suggested Structure
```
src/main/java/
  └─ your/package/
     ├─ App3Application.java          # Main app
     ├─ config/                        # Beans/configuration
     ├─ controller/                    # REST controllers
     ├─ service/                       # Business logic
     ├─ repository/                    # Data access
     └─ domain/                        # Entities/DTOs
src/main/resources/
  └─ application.properties
```

## Good Practices Implemented/Recommended
- Clear package boundaries per layer
- DTOs for API payloads
- ModelMapper for mapping
- Exception handling via `@ControllerAdvice`

## Build & Run
```bash
mvn clean install
mvn spring-boot:run
```

## Configuration
```properties
server.port=8081
spring.application.name=App3
```

## Testing
- Unit tests: JUnit + Mockito
- Controller tests: MockMvc
