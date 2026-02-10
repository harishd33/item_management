# Item Management API - Getting Started

## Reference Documentation

This project is a RESTful API for managing items built with Spring Boot.

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/4.0.2/maven-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/4.0.2/reference/htmlsingle/index.html#web)
* [Spring Boot Validation](https://docs.spring.io/spring-boot/docs/4.0.2/reference/htmlsingle/index.html#io.validation)

## Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

## Quick Start

1. Run the application:
   ```bash
   mvn spring-boot:run
   ```

2. Test the health endpoint:
   ```bash
   curl http://localhost:8080/api/items/health
   ```

3. Create an item:
   ```bash
   curl -X POST http://localhost:8080/api/items \
     -H "Content-Type: application/json" \
     -d '{"name":"Test Item","description":"Test Description"}'
   ```

For complete documentation, see [README.md](README.md)
