# Spring_maven_Javabased_config - Pure Spring (Java Config) + Maven

## Overview
This project shows how to wire a Spring application using only Java-based configuration (no XML) and Maven, without Spring Boot.

## Stack
- Spring Core/Context
- Java 17
- Maven

## Key Concepts
- `@Configuration` classes define beans with `@Bean`
- Component scanning via `@ComponentScan`
- Property injection via `@PropertySource` and `Environment`

## Structure
```
src/main/java/
  └─ your/package/
     ├─ AppConfig.java           # @Configuration + @ComponentScan
     ├─ DataConfig.java          # optional, DB-related beans
     ├─ Main.java                # AnnotationConfigApplicationContext bootstrap
     └─ components/...           # @Component services
src/main/resources/
  └─ application.properties
```

## Build & Run
```bash
mvn clean package
java -cp target/<artifact>-<version>.jar your.package.Main
```

## Tips
- Prefer constructor injection
- Keep configuration modular (AppConfig, DataConfig, WebConfig)
- Externalize environment-specific properties
