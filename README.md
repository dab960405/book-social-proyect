# 📚 Book Social Network

**Book Social Network** es una red social orientada al intercambio de libros entre usuarios. Permite registrar y autenticar usuarios (usando JWT), gestionar libros (crear, compartir, prestar y devolver), y dejar feedback sobre libros. 

El sistema está construido bajo el patrón arquitectónico **Modelo-Vista-Controlador (MVC)**. El backend está desarrollado con **Spring Boot**, y el frontend con **Angular**. La comunicación se realiza a través de una **API RESTful** segura, documentada con **Swagger/OpenAPI**.

---

## 🚀 Tecnologías utilizadas

### Backend
- Java 18
- Spring Boot 3.2.x
- Spring Security (JWT)
- Spring Data JPA
- Maven
- Lombok
- Swagger (springdoc)
- SendGrid

### Frontend
- Angular 16
- Bootstrap
- TypeScript
- RxJS

### Base de datos
- PostgreSQL

### Infraestructura y DevOps
- Docker
- Docker Compose
- GitHub Actions
- Nginx
- Koyeb
- Vercel

---

## 🧩 Estructura del proyecto

```
book-social-project/
├── book-network/           # Backend (Spring Boot)
│   ├── src/                # Código fuente Java
│   ├── Dockerfile
│   └── pom.xml
├── book-network-ui/        # Frontend (Angular)
│   ├── src/                # Código fuente Angular
│   ├── nginx.conf
│   └── package.json
├── docker/                 # Dockerfile para frontend (Angular + Nginx)
│   └── frontend/
├── .github/workflows/      # Pipelines de CI/CD para frontend y backend
├── docker-compose.yml      # Orquestación de servicios
└── diagrams.drawio         # Diagramas de arquitectura
```

---

## 🛠 Instalación y ejecución local

### ✅ Requisitos previos

- Java 17+
- Node.js 16+
- Docker y Docker Compose
- Cuenta y API Key de SendGrid

### 🔄 Opción 1: Usando Docker Compose

```bash
git clone https://github.com/tu-usuario/book-social-network.git
cd book-social-network
docker-compose up --build
```

**URLs de acceso:**
- Frontend: http://localhost:8080
- Backend: http://localhost:8088/api/v1

### 🔧 Opción 2: Manual

#### Backend

```bash
cd book-network
./mvnw clean package
java -jar target/book-network-*.jar
```

#### Frontend

```bash
cd book-network-ui
npm install
ng build --configuration production
```

Sirve el contenido generado en `/dist` con Nginx o `ng serve` en desarrollo.

---

## 📡 Endpoints principales

### 🔐 Autenticación

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/auth/register` | Registrar usuario |
| `POST` | `/auth/authenticate` | Autenticación (login) |
| `GET` | `/auth/activate-account?token={token}` | Activar cuenta por correo |

### 📚 Libros

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/books` | Crear libro |
| `GET` | `/books` | Listar libros públicos |
| `GET` | `/books/{id}` | Ver detalles de libro |
| `GET` | `/books/owner` | Mis libros |
| `GET` | `/books/borrowed` | Libros prestados |
| `GET` | `/books/returned` | Libros devueltos |
| `PATCH` | `/books/shareable/{id}` | Activar/desactivar compartición |
| `PATCH` | `/books/archived/{id}` | Archivar libro |
| `POST` | `/books/borrow/{id}` | Solicitar préstamo |
| `PATCH` | `/books/borrow/return/{id}` | Solicitar devolución |
| `PATCH` | `/books/borrow/return/approve/{id}` | Aprobar devolución |
| `POST` | `/books/cover/{id}` | Subir portada (multipart/form-data) |
| `DELETE` | `/books/{id}` | Eliminar libro |

### 💬 Feedback

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/feedbacks` | Enviar feedback de libro |
| `GET` | `/feedbacks/book/{id}` | Obtener feedbacks de un libro |

### ✉️ Email (Testing)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/v1/test/email` | Enviar email de prueba (requiere configuración SendGrid) |

> 📖 Para más detalles, consulta la documentación Swagger disponible en `/swagger-ui.html`.

---

## 🧪 Pruebas

- **Backend:** Pruebas unitarias con JUnit (`BookNetworkApiApplicationTests`)
- **Frontend:** Pruebas unitarias con Jasmine/Karma (`*.spec.ts`)
- Ejecutadas automáticamente en CI (GitHub Actions)

---

## 🔄 Integración Continua

Configurada con **GitHub Actions**:

- `pipeline-backend.yml`: Build, test y deploy en Koyeb
- `pipeline-frontend.yml`: Build y deploy en Vercel (o estático)

Automatización completa del flujo de desarrollo.

---

## 🚀 Despliegue

| Componente | Plataforma |
|------------|-----------|
| **Backend** | Koyeb |
| **Frontend** | Vercel |
| **Local/Testing** | Docker Compose |

---

## 📝 Licencia

Este proyecto está bajo la Licencia MIT.

---

## 👥 Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o pull request para sugerencias y mejoras.

---

## 📧 Contacto

Para más información, contacta a [tu-email@ejemplo.com](mailto:tu-email@ejemplo.com)

