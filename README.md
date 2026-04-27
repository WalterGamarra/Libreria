# 📚 Librería Online — Backend API REST

API REST desarrollada en Java con Spring Boot para la gestión de una tienda de libros online. Incluye autenticación con JWT, sistema de roles y permisos, y documentación interactiva con Swagger UI.

---

## 🚀 Demo

| Recurso | Link |
|---|---|
| 🌐 Aplicación | [libreria-app.netlify.app](https://libreria-app.netlify.app) |
| 📄 Swagger UI | [Ver documentación](https://libreria-production-8cc8.up.railway.app/swagger-ui/index.html) |
| 💻 Repositorio Frontend | [github.com/WalterGamarra/LibreriaFront](https://github.com/WalterGamarra) |

---

## 🛠️ Tecnologías

- **Java 17**
- **Spring Boot 3.2**
- **Spring Security** — autenticación y autorización
- **Spring Data JPA** — persistencia de datos
- **MySQL** — base de datos relacional
- **JWT (Auth0)** — tokens de acceso
- **Swagger / OpenAPI 3** — documentación de la API
- **Railway** — deploy en producción
- **Postman** — testing de endpoints

---

## 🔐 Roles y permisos

| Rol | Permisos |
|---|---|
| `ADMIN` | Leer, Crear, Editar, Eliminar |
| `USER` | Leer |
| `INVITADO` | Leer y Crear |

---

## 📦 Estructura del proyecto

```
src/
└── main/java/com/libreriaApp/libreria/
    ├── controllers/
    ├── services/
    ├── repositories/
    ├── models/
    ├── DTOs/
    ├── configSecurity/
    └── configSwagger/
```

---

## 👤 Usuario demo

Para explorar la API con Swagger podés usar el usuario demo disponible en la pantalla de login de la aplicación.

---

## 👨‍💻 Autor

**Walter Gamarra**
[LinkedIn](https://linkedin.com/in/waltergamarra) · [GitHub](https://github.com/WalterGamarra)
