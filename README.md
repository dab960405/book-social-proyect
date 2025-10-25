📚 Book Social Network
Book Social Network es una red social orientada al intercambio de libros entre usuarios. Permite registrar y autenticar usuarios (usando JWT), gestionar libros (crear, compartir, prestar y devolver), y dejar feedback sobre libros. El sistema está construido bajo el patrón arquitectónico Modelo-Vista-Controlador (MVC). El backend está desarrollado con Spring Boot, y el frontend con Angular. La comunicación se realiza a través de una API RESTful segura, documentada con Swagger/OpenAPI.

🚀 Tecnologías utilizadas

Backend: Java 18, Spring Boot 3.2.x, Spring Security (JWT), Spring Data JPA, Maven, Lombok, Swagger (springdoc), SendGrid
Frontend: Angular 16, Bootstrap, TypeScript, RxJS
Base de datos: PostgreSQL
Infraestructura y DevOps: Docker, Docker Compose, GitHub Actions, Nginx, Koyeb, Vercel

🧩 Estructura del proyecto

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

