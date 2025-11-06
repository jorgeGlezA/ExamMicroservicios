# 🧩 Spring Boot Exam API — PostgreSQL

Proyecto REST API desarrollado en **Spring Boot 3**, **Java 17**, **Maven** y **PostgreSQL**.  
Implementa CRUD completo y operaciones personalizadas sobre la entidad `Store` (clientes).

---

## 🚀 Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **RESTful API (JSON)**

---

## 🗄️ Base de Datos

**Motor:** PostgreSQL  
**Nombre:** `examdb`  
**Usuario:** `System`  
**Contraseña:** `12345`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/examdb
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```
---

## ⚙️ Estructura del proyecto

```
src/
 └─ main/java/com/example/springbootexampostgresql/
     ├─ controller/
     │   └─ RecordController.java
     ├─ dto/
     │   └─ StoreDTO.java
     ├─ entity/
     │   └─ Store.java
     ├─ repository/
     │   └─ StoreRepository.java
     ├─ service/
     │   └─ StoreService.java
     └─ ExamApplication.java
```

---

## 🌐 Endpoints disponibles

Base URL por defecto:
```
http://localhost:8080
```

### 📍 1️⃣ Obtener clientes sin teléfonos
**GET** `/records`

📄 **Ejemplo de respuesta:**
```json
[
  {
    "id": 1,
    "firstName": "Juan",
    "lastName": "Perez",
    "email": "juan@example.com",
    "phoneNumbers": []
  },
  {
    "id": 3,
    "firstName": "Luis",
    "lastName": "Gomez",
    "email": "luis@example.com",
    "phoneNumbers": []
  }
]
```

---

### 📍 2️⃣ Guardar una lista de clientes
**POST** `/records/save`

📄 **Ejemplo de cuerpo (Body):**
```json
[
  {
    "firstName": "Juan",
    "lastName": "Perez",
    "email": "juan@example.com",
    "phoneNumbers": []
  },
  {
    "firstName": "Ana",
    "lastName": "Lopez",
    "email": "ana@example.com",
    "phoneNumbers": ["555-1234"]
  },
  {
    "firstName": "Luis",
    "lastName": "Gomez",
    "email": "luis@example.com",
    "phoneNumbers": []
  }
]
```

📄 **Ejemplo de respuesta:**
```json
[
  {
    "id": 1,
    "firstName": "Juan",
    "lastName": "Perez",
    "email": "juan@example.com",
    "phoneNumbers": []
  },
  {
    "id": 2,
    "firstName": "Ana",
    "lastName": "Lopez",
    "email": "ana@example.com",
    "phoneNumbers": ["555-1234"]
  },
  {
    "id": 3,
    "firstName": "Luis",
    "lastName": "Gomez",
    "email": "luis@example.com",
    "phoneNumbers": []
  }
]
```

---

### 📍 3️⃣ Obtener todos los clientes ordenados por nombre
**GET** `/records/customers/test`

📄 **Ejemplo de respuesta:**
```json
[
  {
    "id": 2,
    "firstName": "Ana",
    "lastName": "Lopez",
    "email": "ana@example.com",
    "phoneNumbers": ["555-1234"]
  },
  {
    "id": 3,
    "firstName": "Luis",
    "lastName": "Gomez",
    "email": "luis@example.com",
    "phoneNumbers": []
  }
]
```

---

### 📍 4️⃣ Crear un nuevo cliente
**POST** `/records/customers/test`

📄 **Ejemplo de cuerpo (Body):**
```json
{
  "firstName": "Maria",
  "lastName": "Suarez",
  "email": "maria@example.com",
  "phoneNumbers": ["444-9876", "444-1234"]
}
```

📄 **Ejemplo de respuesta:**
```json
{
  "id": 4,
  "firstName": "Maria",
  "lastName": "Suarez",
  "email": "maria@example.com",
  "phoneNumbers": ["444-9876", "444-1234"]
}
```

---

### 📍 5️⃣ Actualizar un cliente existente
**PUT** `/records/customers/{id}/test`

📄 **Ejemplo de URL:**
```
PUT http://localhost:8080/records/customers/2/test
```

📄 **Ejemplo de cuerpo:**
```json
{
  "firstName": "Ana",
  "lastName": "Lopez",
  "email": "ana.lopez@newmail.com",
  "phoneNumbers": ["555-9999", "555-8888"]
}
```

📄 **Ejemplo de respuesta:**
```json
{
  "id": 2,
  "firstName": "Ana",
  "lastName": "Lopez",
  "email": "ana.lopez@newmail.com",
  "phoneNumbers": ["555-9999", "555-8888"]
}
```

---

### 📍 6️⃣ Eliminar un cliente
**DELETE** `/records/customers/{id}/test`

📄 **Ejemplo de URL:**
```
DELETE http://localhost:8080/records/customers/3/test
```

📄 **Respuesta esperada:**
```
204 No Content
```

---

## 🧠 Ejemplo de flujo completo (Postman o curl)

1. Crear varios registros → `POST /records/save`
2. Consultar todos ordenados → `GET /records/customers/test`
3. Actualizar uno → `PUT /records/customers/2/test`
4. Eliminar uno → `DELETE /records/customers/3/test`
5. Filtrar sin teléfonos → `GET /records`

---

## ⚙️ Pasos para ejecutar el proyecto

### 🔹 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/ExamMicroservicios.git
cd ExamMicroservicios
```

### 🔹 2. Configurar PostgreSQL
Asegúrate de tener PostgreSQL en ejecución y crear la base de datos `examdb`:
```sql
CREATE DATABASE examdb;
```

### 🔹 3. Revisar `application.properties`
Verifica las credenciales de tu BD:
```properties
spring.datasource.username=System
spring.datasource.password=12345
```

### 🔹 4. Compilar y ejecutar
Si usas STS4:
- Click derecho sobre `ExamApplication.java`
- **Run As → Spring Boot App**

O desde terminal:
```bash
mvn clean install
mvn spring-boot:run
```

### 🔹 5. Probar en navegador o Postman
```bash
GET http://localhost:8080/records/customers/test
```

---

## 📁 Estructura básica de datos

La tabla principal `store` y su relación:

```sql
CREATE TABLE store (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(150)
);

CREATE TABLE store_phone_numbers (
    store_id INTEGER REFERENCES store(id) ON DELETE CASCADE,
    phone_number VARCHAR(50)
);
```

---

## ✨ Autor

**Jorge Glez A.**  
Proyecto técnico con Spring Boot + PostgreSQL  
GitHub: [https://github.com/jorgeGlezA](https://github.com/jorgeGlezA)

---

