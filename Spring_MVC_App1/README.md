# Spring_MVC_App1 - Spring MVC (Annotation) Sample

## Overview
Spring_MVC_App1 demonstrates a traditional Spring MVC application (non-Boot) using DispatcherServlet, Controllers, Views, and Model attributes.

## Stack
- Spring MVC
- Java 17
- Maven
- JSP/Thymeleaf (choose one; see below)

## Architecture
```
web.xml (if XML) or Java Config (WebAppInitializer)
DispatcherServlet → Controllers → Services → Repositories
Views (JSP/Thymeleaf) render Model attributes
```

## Structure
```
src/main/java/
  └─ your/package/
     ├─ config/                  # WebMvcConfigurer, ViewResolvers
     ├─ controller/              # @Controller, @GetMapping
     ├─ service/                 # Business logic
     └─ repository/              # Data access
src/main/webapp/WEB-INF/views/   # JSPs or Thymeleaf templates
```

## Thymeleaf (Recommended)
- Add thymeleaf dependencies
- Configure `SpringResourceTemplateResolver` and `ThymeleafViewResolver`
- Place templates under `src/main/resources/templates` (Boot) or `/WEB-INF/views/`

## Build & Run
- Package WAR and deploy to Tomcat/Jetty, or embed server via Boot starter if modernizing.
```bash
mvn clean package
```

## Features to Try
- Form handling with `@ModelAttribute`
- Validation with `@Valid` and `BindingResult`
- Internationalization (i18n)
