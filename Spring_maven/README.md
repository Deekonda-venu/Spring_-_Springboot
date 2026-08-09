# Spring_maven - Spring (XML/Annotation) + Maven Starter

## Overview
This module is a foundational Spring (core/container) project built with Maven. It demonstrates configuring beans, building with Maven, and running simple components without Spring Boot.

## Stack
- Spring Framework (Core/Context)
- Java 17
- Maven

## Structure
```
src/main/java/
  └─ your/package/
     ├─ AppConfig.java (if annotation config)
     ├─ Main.java      (bootstrap Spring context)
     └─ components/... (@Component beans)
src/main/resources/
  └─ applicationContext.xml (if XML-based)
```

## Configuration Styles
- XML-based: `applicationContext.xml`
- Java-based: `@Configuration` class (`@Bean` methods)

## Build & Run
```bash
mvn clean package
java -cp target/<artifact>-<version>.jar your.package.Main
```

## Examples
- Define services as `@Component`
- Inject with `@Autowired` / constructor injection
- Externalize properties in `*.properties` and load via `@PropertySource`
