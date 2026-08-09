# Addressdetailes Microservice

## Overview
The Addressdetailes microservice is a Spring Boot REST API that manages address information for employees. It provides endpoints to retrieve address data by employee ID through database joins and serves as a supporting service for the EmployeeDetails microservice.

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
src/main/java/com/example/Addressdetailes/
├── AddressdetailesApplication.java   # Main Spring Boot Application
├── Model/
│   └── Addressdetails.java          # Address Entity
├── Repo/
│   └── AddressRepo.java             # Address Repository with Custom Query
├── Service/
│   └── Employeeservice.java         # Business Logic Layer
├── Controller/
│   └── Addresscontroller.java       # REST Controller
├── Respose/
│   └── AddressRespose.java          # Response DTO
└── Addresscongif/
    └── Config.java                  # Configuration Class
```

## Components Deep Dive

### 1. Entity Layer - `Addressdetails.java`
```java
@Entity
@Table(name = "address_table")
@Data
public class Addressdetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
```

**What we implemented:**
- JPA entity mapping to `address_table`
- Auto-generated primary key using `IDENTITY` strategy
- Employee relationship through `employeeId` foreign key
- Address fields: street, city, state, zipCode
- Lombok `@Data` for automatic getters/setters

**Issues Fixed:**
- Added missing JPA imports (`jakarta.persistence.*`)
- Added Lombok `@Data` import
- Added `employeeId` field for foreign key relationship
- Corrected package declaration

**Database Mapping:**
- Java `employeeId` → MySQL `employee_id` (snake_case conversion by Hibernate)
- Java `zipCode` → MySQL `zip_code` (snake_case conversion by Hibernate)

### 2. Repository Layer - `AddressRepo.java`
```java
@Repository
public interface AddressRepo extends JpaRepository<Addressdetails, Long> {
    @Query(nativeQuery = true, 
           value = "select a.id, a.employee_id, a.street, a.city, a.state, a.zip_code " +
                   "from address_table a join employedetailes e " +
                   "on a.employee_id = e.id where e.id = :employeeid")
    Optional<Addressdetails> findaddressbyemplyeeid(@Param("employeeid") Long employeeid);
}
```

**What we implemented:**
- Interface extending `JpaRepository<Addressdetails, Long>`
- Custom native SQL query with table joins
- Parameterized query with `@Param` annotation
- Optional return type for safe null handling

**Issues Fixed:**
- Fixed typo: `Jparespositray` → `JpaRepository`
- Corrected table name: `employee_table` → `employedetailes`
- Resolved SQL alias conflicts by selecting specific columns
- Added table aliases (`a` for address_table, `e` for employedetailes)
- Fixed `@Param` annotation syntax: `@Param("employeeid")`
- Added all required Spring Data JPA imports

**Query Evolution:**
1. **Initial**: `select * from address_table join employee_table...` (wrong table name)
2. **Fixed Table**: `select * from address_table join employedetailes...` (alias conflict)
3. **Final**: Specific column selection with aliases to avoid conflicts

### 3. Service Layer - `Employeeservice.java`
```java
@Service
public class Employeeservice {
    @Autowired
    private AddressRepo addressRepo;
    
    @Autowired
    private ModelMapper modelMapper;

    public AddressRespose getaddressbyemplyeeid(Long employeeid) {
        Optional<Addressdetails> address = addressRepo.findaddressbyemplyeeid(employeeid);
        Addressdetails addressdetails = address.orElseThrow(
            () -> new RuntimeException("Address not found for employee id: " + employeeid)
        );
        return modelMapper.map(addressdetails, AddressRespose.class);
    }
}
```

**What we implemented:**
- Service class with business logic for address retrieval
- ModelMapper integration for Entity → DTO conversion
- Safe Optional handling with `orElseThrow()`
- Proper exception handling for missing addresses
- Repository integration with custom query method

**Issues Fixed:**
- Added missing imports for all dependencies
- Implemented safe Optional handling instead of direct `.get()`
- Added meaningful exception messages
- Corrected service method name and parameters

### 4. Controller Layer - `Addresscontroller.java`
```java
@RestController
@RequestMapping("/address/v1")
public class Addresscontroller {
    @Autowired
    private Employeeservice employeeservice;

    @GetMapping("/getaddressbyemplyeeid/{employeeid}")
    public ResponseEntity<AddressRespose> getaddressbyemplyeeid(@PathVariable Long employeeid) {
        return ResponseEntity.ok(employeeservice.getaddressbyemplyeeid(employeeid));
    }
}
```

**What we implemented:**
- RESTful endpoint: `GET /address/v1/getaddressbyemplyeeid/{employeeid}`
- Path variable binding for employee ID (Long type)
- ResponseEntity wrapper for proper HTTP responses
- Integration with service layer

**Issues Fixed:**
- Added all required Spring MVC imports
- Fixed return type from `AddressEntity<Addressdetails>` to `ResponseEntity<AddressRespose>`
- Proper REST controller annotations
- Corrected method signatures and parameter types

### 5. Response DTO - `AddressRespose.java`
```java
@Data
public class AddressRespose {
    Long id;
    Long employeeId;
    String street;
    String city;
    String state;
    String zipCode;
}
```

**What we implemented:**
- DTO for API responses
- Fields matching address entity
- `employeeId` for relationship tracking
- Lombok for automatic getters/setters

**Issues Fixed:**
- Added Lombok `@Data` import
- Added `employeeId` field to maintain relationship context
- Proper field naming conventions

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
- Proper bean method signature with return type

**Issues Fixed:**
- Corrected package name from Service to Addresscongif
- Fixed class name from `Employeeservice` to `Config`
- Fixed `@beam` to `@Bean` annotation
- Added `public` modifier to bean method
- Added required Spring imports

## Database Configuration

### application.properties
```properties
spring.application.name=Addressdetailes
server.port=9191

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
-- Address table (created by Hibernate with employee_id added manually)
CREATE TABLE address_table (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT,
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    zip_code VARCHAR(255),
    FOREIGN KEY (employee_id) REFERENCES employedetailes(id)
);

-- Add employee_id column (if not exists)
ALTER TABLE address_table ADD COLUMN employee_id BIGINT;

-- Sample data with employee relationships
INSERT INTO address_table (employee_id, street, city, state, zip_code) VALUES 
(1, '123 Main St', 'New York', 'NY', '10001'),
(2, '456 Oak Ave', 'Los Angeles', 'CA', '90001'),
(3, '789 Pine Rd', 'Chicago', 'IL', '60601');

-- Update existing data with employee_id
UPDATE address_table SET employee_id = 1 WHERE id = 1;
UPDATE address_table SET employee_id = 2 WHERE id = 2;
UPDATE address_table SET employee_id = 3 WHERE id = 3;
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

**Issues Fixed:**
- Added ModelMapper dependency to resolve compilation errors
- Ensured proper version compatibility with Spring Boot 4.1.0

## API Endpoints

### GET /address/v1/getaddressbyemplyeeid/{employeeid}
- **Purpose**: Retrieve address by employee ID
- **Method**: GET
- **URL**: `http://localhost:9191/address/v1/getaddressbyemplyeeid/{employeeid}`
- **Path Parameter**: `employeeid` (Long) - Employee ID to fetch address for
- **Response**:
```json
{
    "id": 1,
    "employeeId": 1,
    "street": "123 Main St",
    "city": "New York",
    "state": "NY",
    "zipCode": "10001"
}
```

## Issues Resolved

### 1. Compilation Errors
- **Missing ModelMapper**: Added dependency to pom.xml
- **Missing Imports**: Added all required JPA, Spring, and Lombok imports
- **Package Declarations**: Corrected all package names

### 2. SQL Query Issues
- **Wrong Table Name**: Changed `employee_table` to `employedetailes`
- **Alias Conflicts**: Used specific column selection with table aliases
- **Parameter Binding**: Fixed `@Param` annotation syntax

### 3. Runtime Errors
- **404 Not Found**: Fixed URL pattern and controller mapping
- **500 Internal Server Error**: Resolved SQL query and entity mapping issues
- **Duplicated SQL Alias**: Solved by selecting specific columns instead of `SELECT *`

### 4. Database Schema Issues
- **Missing employee_id Column**: Added foreign key column to address_table
- **Data Relationships**: Inserted proper employee_id values for joins

### 5. Architecture Issues
- **Repository Structure**: Changed from class to interface extending JpaRepository
- **Controller Return Types**: Fixed return types and response wrappers
- **Service Layer**: Implemented proper Optional handling and exception management

## Current Status

### ✅ Working Features:
- Address retrieval by employee ID
- Database connectivity with joins
- Entity-DTO mapping with ModelMapper
- RESTful API endpoints
- Custom native SQL queries
- Exception handling for missing addresses

### ✅ Fixed Issues:
- SQL alias conflicts resolved
- Table name corrections applied
- Foreign key relationships established
- Compilation errors resolved
- Runtime exceptions handled

## How to Run

1. **Prerequisites**:
   - MySQL running on localhost:3306
   - `employee_db` database exists
   - `employedetailes` table has sample data

2. **Database Setup**:
   ```sql
   -- Ensure address_table has employee_id column
   ALTER TABLE address_table ADD COLUMN employee_id BIGINT;
   
   -- Insert/update sample data with employee relationships
   UPDATE address_table SET employee_id = 1 WHERE id = 1;
   ```

3. **Build & Run**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access**:
   - Base URL: `http://localhost:9191`
   - Test Endpoint: `http://localhost:9191/address/v1/getaddressbyemplyeeid/1`

## Integration with EmployeeDetails

This microservice is designed to be called by the EmployeeDetails service:

### Communication Flow:
1. **EmployeeDetails** receives request for employee ID
2. **EmployeeDetails** calls this service: `GET /address/v1/getaddressbyemplyeeid/{employeeId}`
3. **Addressdetailes** returns address data
4. **EmployeeDetails** combines employee + address data in single response

### Expected Integration:
- EmployeeDetails should use RestTemplate/WebClient to call this service
- Response should be mapped to `AddressResponse` in EmployeeDetails
- Final response includes both employee and address information

## Performance Considerations

### Database Query Optimization:
- Native SQL query with explicit joins for better performance
- Indexed employee_id column for faster lookups
- Specific column selection reduces data transfer

### Microservice Architecture:
- Separate database concerns (addresses vs employees)
- Independent scaling and deployment
- Clear API boundaries between services

## Future Enhancements

1. **Caching**: Add Redis for frequently accessed addresses
2. **Validation**: Input validation for employee IDs
3. **Pagination**: Support for multiple addresses per employee
4. **Error Handling**: Global exception handler with proper HTTP status codes
5. **Testing**: Unit and integration tests
6. **Documentation**: OpenAPI/Swagger documentation
7. **Monitoring**: Health checks and metrics endpoints

This microservice provides a solid foundation for address management in a distributed system architecture.
