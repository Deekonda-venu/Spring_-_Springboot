# EmployeeDetails Microservice

## Overview
The EmployeeDetails microservice is a Spring Boot REST API that manages employee information. It provides endpoints to retrieve employee data and is designed to integrate with the Addressdetailes microservice to provide complete employee profiles with address information.

## Architecture

### Technology Stack
- **Framework**: Spring Boot 4.1.0
- **Java Version**: 17
- **Database**: MySQL 8.0
- **ORM**: JPA/Hibernate
- **Build Tool**: Maven
- **Dependencies**: 
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Web MVC
  - MySQL Connector
  - Lombok
  - ModelMapper

### Project Structure
```
src/main/java/com/example/EmployeeDetails/
├── EmployeeDetailsApplication.java    # Main Spring Boot Application
├── Model/
│   └── Employe.java                  # Employee Entity
├── Reposoitary/
│   └── Employeerepo.java            # Employee Repository
├── Service/
│   └── Employeeservice.java         # Business Logic Layer
├── Contoller/
│   └── EmployeController.java       # REST Controller
├── Respose/
│   └── EmployeRespose.java          # Response DTO
└── Employeeconfig/
    └── Config.java                  # Configuration Class
```

## Components Deep Dive

### 1. Entity Layer - `Employe.java`
```java
@Entity
@Table(name = "employedetailes")
@Data
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
}
```

**What we implemented:**
- JPA entity mapping to `employedetailes` table
- Auto-generated primary key using `IDENTITY` strategy
- Lombok `@Data` for automatic getters/setters
- Basic employee fields: id, name, email, phone

**Issues Fixed:**
- Added missing JPA imports (`jakarta.persistence.*`)
- Added Lombok `@Data` import
- Corrected package declaration

### 2. Repository Layer - `Employeerepo.java`
```java
@Repository
public interface Employeerepo extends JpaRepository<Employe, Integer> {
}
```

**What we implemented:**
- Interface extending `JpaRepository` for CRUD operations
- Type parameters: `<Employe, Integer>` for entity and ID type
- Automatic query methods provided by Spring Data JPA

**Issues Fixed:**
- Changed from class to interface
- Corrected typo: `Jparespositary` → `JpaRepository`
- Added required imports for Spring Data JPA

### 3. Service Layer - `Employeeservice.java`
```java
@Service
public class Employeeservice {
    @Autowired
    private Employeerepo employeerepo;
    
    @Autowired
    private ModelMapper modelMapper;

    public EmployeRespose getEmploye(int id) {
        Employe employe = employeerepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return modelMapper.map(employe, EmployeRespose.class);
    }
}
```

**What we implemented:**
- Service class with business logic
- ModelMapper integration for Entity → DTO conversion
- Safe Optional handling with `orElseThrow()`
- Proper exception handling for missing employees

**Issues Fixed:**
- Added missing imports for all dependencies
- Removed duplicate class definitions
- Implemented safe Optional handling instead of `.get()`
- Added proper exception messages

### 4. Controller Layer - `EmployeController.java`
```java
@RestController
@RequestMapping("/employee/v1")
public class EmployeController {
    @Autowired
    private Employeeservice employeeservice;

    @GetMapping("/getemploye/{id}")
    public ResponseEntity<EmployeRespose> getEmploye(@PathVariable int id) {
        return ResponseEntity.ok(employeeservice.getEmploye(id));
    }
}
```

**What we implemented:**
- RESTful endpoint: `GET /employee/v1/getemploye/{id}`
- Path variable binding for employee ID
- ResponseEntity wrapper for proper HTTP responses
- Integration with service layer

**Issues Fixed:**
- Added all required Spring MVC imports
- Proper REST controller annotations
- ResponseEntity for better HTTP response handling

### 5. Response DTO - `EmployeRespose.java`
```java
@Data
public class EmployeRespose {
    int id;
    String name;
    String email;
    String phone;
    AddressResponse addressResponse;  // For future integration
}
```

**What we implemented:**
- DTO for API responses
- Fields matching employee entity
- `AddressResponse` field for microservice integration
- Lombok for automatic getters/setters

**Issues Fixed:**
- Added Lombok `@Data` import
- Renamed `EmployeeAddress` to `addressResponse`

### 6. Configuration - `Config.java`
```java
@Configuration
public class Config {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
```

**What we implemented:**
- Spring Configuration class
- ModelMapper bean for entity-DTO mapping
- Proper bean method signature

**Issues Fixed:**
- Corrected package name and class name
- Fixed `@beam` to `@Bean` annotation
- Added required Spring imports

## Database Configuration

### application.properties
```properties
spring.application.name=EmployeeDetails
server.port=8181

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### Database Schema
```sql
-- Table created by Hibernate
CREATE TABLE employedetailes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255)
);

-- Sample data
INSERT INTO employedetailes (name, email, phone) VALUES 
('John Doe', 'john.doe@example.com', '123-456-7890'),
('Jane Smith', 'jane.smith@example.com', '098-765-4321'),
('Bob Johnson', 'bob.johnson@example.com', '555-123-4567');
```

## Maven Configuration (pom.xml)

### Key Dependencies Added:
```xml
<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>3.2.0</version>
</dependency>
```

## API Endpoints

### GET /employee/v1/getemploye/{id}
- **Purpose**: Retrieve employee by ID
- **Method**: GET
- **URL**: `http://localhost:8181/employee/v1/getemploye/{id}`
- **Response**:
```json
{
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "123-456-7890",
    "addressResponse": null
}
```

## Issues Resolved

1. **Missing Imports**: Added all required JPA, Spring, and Lombok imports
2. **Repository Structure**: Changed from class to interface extending JpaRepository
3. **Duplicate Classes**: Removed duplicate class definitions in service
4. **Optional Handling**: Implemented safe `.orElseThrow()` instead of `.get()`
5. **Maven Dependencies**: Added ModelMapper dependency
6. **Package Declarations**: Corrected all package names
7. **Annotation Errors**: Fixed typos like `@beam` → `@Bean`

## Current Status

### ✅ Working Features:
- Employee CRUD operations
- Database connectivity
- Entity-DTO mapping
- RESTful API endpoints
- Exception handling

### 🔄 Pending Integration:
- **Address Service Integration**: The `addressResponse` field is currently null
- **RestTemplate/WebClient**: Need to call Addressdetailes microservice
- **JSON Field Formatting**: Can add Jackson annotations for better API response format

## How to Run

1. **Start MySQL**: Ensure MySQL is running on localhost:3306
2. **Database Setup**: Create `employee_db` database and insert sample data
3. **Build**: `mvn clean install`
4. **Run**: `mvn spring-boot:run`
5. **Access**: `http://localhost:8181/employee/v1/getemploye/1`

## Next Steps

1. **Add RestTemplate Configuration**: For calling external services
2. **Implement Address Integration**: Call Addressdetailes service to populate `addressResponse`
3. **Add JSON Annotations**: Control field names and order in API responses
4. **Error Handling**: Implement global exception handler
5. **Testing**: Add unit and integration tests

## Microservice Communication

To complete the microservice architecture, this service needs to:
1. Call `GET http://localhost:9191/address/v1/getaddressbyemplyeeid/{employeeId}`
2. Map the response to `AddressResponse`
3. Set it in `EmployeRespose.addressResponse`

This will provide a complete employee profile with address information in a single API call.
