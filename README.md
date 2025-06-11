# Task Manager API (Backend)

API REST construida con **Spring Boot** para gestionar usuarios y tareas.

## 📌 Tecnologías

- Java 17+
- Spring Boot
- Maven (o Gradle)
- Base de datos (ej. H2, PostgreSQL, MySQL)
- JWT para autenticación
- Posiblemente Spring Security, Lombok, validaciones

## 🚀 Instalación y ejecución

1. Clona el repositorio:
    ```bash
    git clone https://github.com/SanchezLanchaCarlos/Task-Manager-API.git
    cd Task-Manager-API
    cd backend
    ```

2. Proporciona configuración en `src/main/resources/application.yml` o variables de entorno:
    ```yaml
    spring:
      datasource:
        url: jdbc:...
        username: ...
        password: ...
      jwt.secret: <tu_clave_secreta>
    ```
    O define variables:
    ```bash
    export SPRING_DATASOURCE_URL=jdbc:...
    export SPRING_DATASOURCE_USERNAME=...
    export SPRING_DATASOURCE_PASSWORD=...
    export JWT_SECRET=...
    ```

3. Ejecuta:
    ```bash
    ./mvnw spring-boot:run
    ```

4. La API estará en `http://localhost:8080/api`.

## 📦 Endpoints (Resumen)

- `POST /api/auth/register` – registrar usuario  
- `POST /api/auth/login` – obtener JWT  
- `GET /api/users/me` – obtener datos del usuario autenticado  
- `POST /api/tasks` – crear tarea  
- `GET /api/tasks` – listar tareas (filtrado paginación)  
- `GET /api/tasks/{id}` – obtener tarea por ID  
- `PUT /api/tasks/{id}` – actualizar tarea  
- `DELETE /api/tasks/{id}` – eliminar tarea

> ⚠️ Asegúrate de enviar el JWT en el header `Authorization: Bearer <token>` para los endpoints protegidos.

## 🧪 Tests

Ejecuta:
```bash
./mvnw test
