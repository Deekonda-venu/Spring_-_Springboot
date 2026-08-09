# Spring Boot Microservices Architecture

## Project Overview
This project demonstrates a microservices architecture using Spring Boot with two interconnected services: **EmployeeDetails** and **Addressdetailes**. The architecture follows REST API principles with MySQL database integration and service-to-service communication.

## Architecture Diagram
```
┌─────────────────┐    HTTP Request    ┌──────────────────┐
│                 │ ────────────────── │                  │
│     Client      │                    │ EmployeeDetails  │
│  (Postman/Web)  │ ←────────────────── │   Service        │
│                 │   JSON Response    │  (Port: 8181)    │
└─────────────────┘                    └──────────────────┘
                                                │
                                                │ REST Call
                                                │ (HTTP GET)
                                                ▼
                                       ┌──────────────────┐
                                       │                  │
                                       │ Addressdetailes  │
                                       │   Service        │
                                       │  (Port: 9191)    │
                                       └──────────────────┘
                                                │
                        ┌───────────────────────┼───────────────────────┐
                        │                       │                       │
                        ▼                       ▼                       ▼
               ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
               │                 │    │                 │    │                 │
               │ MySQL Database  │    │ MySQL Database  │    │ MySQL Database  │
               │                 │    │                 │    │                 │
               │ employedetailes │    │ address_table   │    │ Shared Schema   │
               │     table       │    │     table       │    │ employee_db     │
               └─────────────────┘    └─────────────────┘    └─────────────────┘
```

## Microservices Overview

### 1. EmployeeDetails Service (Port: 8181)
- **Purpose**: Primary service managing employee information
- **Database Table**: `employedetailes`
- **Main Endpoint**: `GET /employee/v1/getemploye/{id}`
- **Features**:
  - Employee CRUD operations
  - Integration with Address service
  - Complete employee profile responses

### 2. Addressdetailes Service (Port: 9191)
- **Purpose**: Supporting service managing address information
- **Database Table**: `address_table`
- **Main Endpoint**: `GET /address/v1/getaddressbyemplyeeid/{employeeid}`
- **Features**:
  - Address retrieval by employee ID
  - Database joins with employee table
  - Standalone address management

## Technology Stack

### Backend Framework
- **Spring Boot**: 4.1.0
- **Java Version**: 17
- **Build Tool**: Maven

### Database & ORM
- **Database**: MySQL 8.0
- **ORM**: JPA/Hibernate
- **Connection**: MySQL Connector/J

### Key Dependencies
- **Spring Boot Starter Data JPA**: Database operations
- **Spring Boot Starter Web MVC**: REST API endpoints
- **Lombok**: Boilerplate code reduction
- **ModelMapper**: Entity-DTO mapping
- **MySQL Connector**: Database connectivity

## Database Schema

### Shared Database: `employee_db`

#### Table: `employedetailes`
```sql
CREATE TABLE employedetailes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255)
);
```

#### Table: `address_table`
```sql
CREATE TABLE address_table (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT,
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    zip_code VARCHAR(255),
    FOREIGN KEY (employee_id) REFERENCES employedetailes(id)
);
```

### Sample Data
```sql
-- Employee data
INSERT INTO employedetailes (name, email, phone) VALUES 
('John Doe', 'john.doe@example.com', '123-456-7890'),
('Jane Smith', 'jane.smith@example.com', '098-765-4321'),
('Bob Johnson', 'bob.johnson@example.com', '555-123-4567');

-- Address data
INSERT INTO address_table (employee_id, street, city, state, zip_code) VALUES 
(1, '123 Main St', 'New York', 'NY', '10001'),
(2, '456 Oak Ave', 'Los Angeles', 'CA', '90001'),
(3, '789 Pine Rd', 'Chicago', 'IL', '60601');
```

## API Endpoints

### EmployeeDetails Service
```
Base URL: http://localhost:8181

GET /employee/v1/getemploye/{id}
├── Description: Get complete employee profile with address
├── Parameters: id (int) - Employee ID
└── Response: Employee object with embedded address
```

**Sample Response:**
```json
{
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "123-456-7890",
    "addressResponse": {
        "id": 1,
        "employeeId": 1,
        "street": "123 Main St",
        "city": "New York",
        "state": "NY",
        "zipCode": "10001"
    }
}
```

### Addressdetailes Service
```
Base URL: http://localhost:9191

GET /address/v1/getaddressbyemplyeeid/{employeeid}
├── Description: Get address information by employee ID
├── Parameters: employeeid (Long) - Employee ID
└── Response: Address object for the employee
```

**Sample Response:**
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

## Service Communication Flow

### Complete Request Flow:
1. **Client Request**: `GET http://localhost:8181/employee/v1/getemploye/1`
2. **EmployeeDetails**: 
   - Fetches employee from database
   - Makes HTTP call to: `http://localhost:9191/address/v1/getaddressbyemplyeeid/1`
3. **Addressdetailes**: 
   - Executes SQL join query
   - Returns address data
4. **EmployeeDetails**: 
   - Combines employee + address data
   - Returns complete profile to client

### Inter-Service Communication:
- **Protocol**: HTTP/REST
- **Format**: JSON
- **Method**: Synchronous calls using RestTemplate/WebClient
- **Error Handling**: Graceful degradation with proper exception handling

## Project Structure

```
Microserives(Resttemplte)Sync/
├── README.md                          # This file
├── EmployeeDetails/                   # Primary microservice
│   ├── README.md                      # Service-specific documentation
│   ├── pom.xml                        # Maven configuration
│   ├── src/main/java/com/example/EmployeeDetails/
│   │   ├── EmployeeDetailsApplication.java
│   │   ├── Model/Employe.java
│   │   ├── Reposoitary/Employeerepo.java
│   │   ├── Service/Employeeservice.java
│   │   ├── Contoller/EmployeController.java
│   │   ├── Respose/EmployeRespose.java
│   │   └── Employeeconfig/Config.java
│   └── src/main/resources/
│       └── application.properties
└── Addressdetailes/                   # Supporting microservice
    ├── README.md                      # Service-specific documentation
    ├── pom.xml                        # Maven configuration
    ├── src/main/java/com/example/Addressdetailes/
    │   ├── AddressdetailesApplication.java
    │   ├── Model/Addressdetails.java
    │   ├── Repo/AddressRepo.java
    │   ├── Service/Employeeservice.java
    │   ├── Controller/Addresscontroller.java
    │   ├── Respose/AddressRespose.java
    │   └── Addresscongif/Config.java
    └── src/main/resources/
        └── application.properties
```

## Key Implementation Details

### 1. Entity-DTO Mapping
- **ModelMapper Integration**: Automatic mapping between entities and DTOs
- **Separation of Concerns**: Database entities separate from API responses
- **Type Safety**: Strong typing with proper Java generics

### 2. Database Design
- **Foreign Key Relationships**: `address_table.employee_id` → `employedetailes.id`
- **Hibernate Naming**: Automatic camelCase → snake_case conversion
- **DDL Auto-Update**: Schema updates handled automatically

### 3. REST API Design
- **RESTful URLs**: Resource-based URL patterns
- **HTTP Methods**: Proper use of GET for data retrieval
- **Response Wrappers**: ResponseEntity for proper HTTP responses
- **Path Variables**: Clean URL parameter binding

### 4. Error Handling
- **Optional Safety**: `.orElseThrow()` instead of `.get()`
- **Custom Exceptions**: Meaningful error messages
- **HTTP Status Codes**: Proper status code responses

## Issues Resolved During Development

### Compilation Issues:
1. **Missing Imports**: Added JPA, Spring, and Lombok imports
2. **Maven Dependencies**: Added ModelMapper dependency
3. **Package Declarations**: Fixed package naming inconsistencies
4. **Annotation Errors**: Corrected typos in annotations

### Runtime Issues:
1. **Database Connections**: Fixed MySQL connection configurations
2. **Table Naming**: Corrected Hibernate table name mappings
3. **SQL Queries**: Resolved native query syntax and alias conflicts
4. **Service Integration**: Fixed URL patterns and HTTP communication

### Architecture Issues:
1. **Repository Patterns**: Changed classes to interfaces for proper Spring Data JPA
2. **Service Layer Logic**: Implemented proper business logic separation
3. **Controller Design**: Fixed REST endpoint patterns and response types
4. **Configuration Management**: Proper Spring configuration classes

## How to Run the Complete System

### Prerequisites:
1. **Java 17** installed
2. **Maven 3.6+** installed
3. **MySQL 8.0** running on localhost:3306
4. **Database Setup**: Create `employee_db` database

### Step-by-Step Setup:

#### 1. Database Setup
```sql
-- Create database
CREATE DATABASE employee_db;
USE employee_db;

-- Tables will be created automatically by Hibernate
-- Insert sample data after first run
```

#### 2. Start Addressdetailes Service
```bash
cd Addressdetailes/
mvn clean install
mvn spring-boot:run
```
- Service starts on **port 9191**
- Verify: `http://localhost:9191/address/v1/getaddressbyemplyeeid/1`

#### 3. Start EmployeeDetails Service
```bash
cd EmployeeDetails/
mvn clean install
mvn spring-boot:run
```
- Service starts on **port 8181**
- Verify: `http://localhost:8181/employee/v1/getemploye/1`

#### 4. Test the Complete Flow
```bash
# Test individual services
curl http://localhost:9191/address/v1/getaddressbyemplyeeid/1
curl http://localhost:8181/employee/v1/getemploye/1

# Test with Postman or browser
http://localhost:8181/employee/v1/getemploye/1
```

## Current Status

### ✅ Completed Features:
- Both microservices running independently
- Database connectivity and schema creation
- REST API endpoints working
- Entity-DTO mapping implemented
- Custom SQL queries with joins
- Exception handling for missing data

### 🔄 Pending Integration:
- **RestTemplate Configuration**: Add HTTP client in EmployeeDetails
- **Service-to-Service Calls**: Implement address service integration
- **Complete Response**: Populate `addressResponse` field in employee data

### 🚀 Future Enhancements:
- Service discovery (Eureka)
- Load balancing
- Circuit breaker patterns (Hystrix)
- Centralized configuration (Spring Cloud Config)
- API Gateway (Spring Cloud Gateway)
- Monitoring and logging (Sleuth, Zipkin)

## Testing

### Manual Testing:
1. **Postman Collections**: Test individual endpoints
2. **Browser Testing**: Direct URL access for GET endpoints
3. **Database Verification**: Check data consistency

### Recommended Test Cases:
- Valid employee IDs (1, 2, 3)
- Invalid employee IDs (404 handling)
- Database connection failures
- Service unavailability scenarios

## Microservice Benefits Demonstrated

1. **Separation of Concerns**: Employee vs Address data management
2. **Independent Scaling**: Services can be scaled separately
3. **Technology Diversity**: Different configurations per service
4. **Fault Isolation**: Address service failure doesn't break employee service
5. **Development Autonomy**: Teams can work on services independently

This architecture provides a solid foundation for building larger microservice ecosystems with proper service boundaries, data management, and communication patterns.
