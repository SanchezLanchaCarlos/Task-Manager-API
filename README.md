# Task Manager API

Backend de la aplicación Task Manager. API REST desarrollada con **Spring Boot**, utilizando **Maven**, **JWT** para autenticación, **Swagger UI** para documentación, y **Supabase (PostgreSQL)** como base de datos.

---

## 🚀 Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- Supabase (PostgreSQL)
- JWT (JSON Web Tokens)
- Swagger (springdoc-openapi)
- Maven

---

## 🧪 Ejecución local

    ./mvnw spring-boot:run

La API estará disponible en:  

    http://localhost:8080/api

La documentación Swagger está en:

    http://localhost:8080/swagger-ui.html

---

## 🔐 Autenticación

La autenticación se realiza mediante JWT. Para acceder a endpoints protegidos, incluye el token en el encabezado:

    Authorization: Bearer <jwt_token>

---

## 📦 Entidades principales

* User: Registro e inicio de sesión de usuarios.

* Project: Gestión de proyectos personales o colaborativos.

* ProjectMember: Asociación de usuarios a proyectos.

* Task: Gestión de tareas dentro de proyectos.

---

## 🔁 Endpoints principales

### Auth

* POST /api/auth/register → Registrar un nuevo usuario

* POST /api/auth/login → Iniciar sesión y obtener token JWT

### User

* GET /api/users/me → Datos del usuario autenticado

* GET /api/users → Listado de todos los usuarios

* GET /api/users/{id} → Obtener usuario por ID

### Projects

* GET /api/projects → Listar proyectos del usuario

* POST /api/projects → Crear proyecto

* GET /api/projects/{id} → Ver detalles

* PUT /api/projects/{id} → Editar proyecto

* DELETE /api/projects/{id} → Eliminar proyecto

### Project Members

* GET /api/project-members/{projectId} → Listar miembros del proyecto

* POST /api/project-members → Añadir miembro a un proyecto

* DELETE /api/project-members/{id} → Eliminar miembro

### Tasks

* GET /api/tasks?projectId={id} → Listar tareas de un proyecto

* POST /api/tasks → Crear tarea

* GET /api/tasks/{id} → Ver tarea

* PUT /api/tasks/{id} → Editar tarea

* DELETE /api/tasks/{id} → Eliminar tarea

---

## 📁 Estructura del proyecto

    src/
    ├── config/           # Seguridad, configuración JWT
    ├── controller/       # Controladores REST
    ├── dto/              # Clases DTO
    ├── entity/           # Entidades JPA
    ├── repository/       # Interfaces JPA
    ├── service/          # Lógica de negocio
    └── util/             # Utilidades (e.g. JWT utils)

--- 

## 🧑 Autor

Carlos Sánchez – @SanchezLanchaCarlos
