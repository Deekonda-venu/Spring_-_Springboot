# Spring_MVC_Webapp1 - Classic Spring MVC Web Application

## Overview
This module illustrates a classic Spring MVC webapp structure with Controllers, Views (JSP/Thymeleaf), and static resources.

## Stack
- Spring MVC
- Java 17
- Maven
- JSP or Thymeleaf

## Webapp Layout
```
src/main/webapp/
  ├─ WEB-INF/
  │   ├─ web.xml                    # DispatcherServlet mapping (if XML)
  │   └─ views/                     # JSP/Thymeleaf templates
  └─ resources/                     # CSS/JS/images
```

## Java Config (optional)
- `@EnableWebMvc` with `WebMvcConfigurer`
- ViewResolvers (InternalResourceViewResolver or Thymeleaf)
- ResourceHandlers for static content

## Build & Deploy
```bash
mvn clean package
# Deploy the generated WAR to Tomcat/Jetty
```

## Recommended Practices
- Use Thymeleaf for modern templating
- Validate inputs and show field errors in views
- Use i18n message bundles for labels
