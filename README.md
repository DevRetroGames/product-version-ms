# product-version-ms
Microservicio desarrollado como parte de una prueba técnica, orientado a la gestión de productos y sus versiones. Implementado con Java 17, Spring Boot 3, PostgreSQL y autenticación JWT. Incluye API REST, validaciones, pruebas automatizadas y documentación OpenAPI.

# Product Version Microservice

Prueba técnica.

Microservicio de gestión de productos y versiones, parte de un sistema distribuido registrado en Eureka.

---

## 🧱 Estructura del Proyecto

- `eureka-server/`: servidor de descubrimiento con Spring Cloud Netflix Eureka.
- `product-version-ms/`: microservicio Spring Boot con PostgreSQL y Eureka Client.
- `docker-compose.yml`: configuración para levantar PostgreSQL.
- `init_db.sql`: script de inicialización de base de datos para PostgreSQL.

---

## 🚀 Requisitos

- Java 17
- Maven 3.6.3
- Docker + Docker Compose
- Git

---

## 📥 Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/product-version-ms.git
cd product-version-ms

## Docker-compose
Levantar contenedor
docker-compose up -d

## Eureka Server
Levantar eureka-server
cd dev-app/eureka-server
mvn spring-boot:run

## product-version-ms
Levantar product-version-ms
cd dev-app/productversionms
mvn spring-boot:run