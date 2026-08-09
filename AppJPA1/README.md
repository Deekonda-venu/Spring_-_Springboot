# AppJPA1 - Spring Boot + JPA Reference App

## Overview
AppJPA1 demonstrates typical Spring Data JPA patterns: entities, repositories, service layer, and REST exposure.

## Stack
- Spring Boot (Java 17)
- Spring Data JPA + Hibernate
- MySQL (or H2 for dev)
- ModelMapper (optional)
- Lombok

## Reference Structure
```
src/main/java/
  └─ your/package/
     ├─ AppJpa1Application.java
     ├─ entity/                # @Entity classes
     ├─ repository/            # JpaRepository interfaces
     ├─ service/               # Transactional services
     ├─ web/                   # REST controllers
     └─ dto/                   # Request/Response DTOs
```

## Database Configuration
`src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/appjpa1
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## Patterns Demonstrated
- Repository method naming (`findByXxx`, `existsByYyy`)
- Pagination & sorting (`Pageable`)
- Transactions (`@Transactional` in service layer)
- DTO mapping (avoid exposing entities directly)

## Build & Run
```bash
mvn clean install
mvn spring-boot:run
```

## Testing
- Repository tests with embedded DB (H2)
- Service tests with mocks
- Web tests with MockMvc
