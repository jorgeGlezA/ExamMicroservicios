# spring-boot-exam-postgresql

Proyecto Spring Boot (Java 17, Maven, JPA) con base de datos Oracle (sin Lombok).

## Endpoints
- `GET /records` -> registros con `phone_numbers` vacío.
- `POST /records/save` -> guarda lista de objetos en la tabla `store`.
- CRUD bajo `/records/customers/test`.

## Configuración
```
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=System
spring.datasource.password=12345
```

Importar en STS4 y ejecutar `ExamApplication`.
