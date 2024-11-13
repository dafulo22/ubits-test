# Human Talent Management API

Este proyecto es una API REST desarrollada en Spring Boot 3, con Java 17 y Maven, creada para una empresa de gestión de talento humano que permite la administración de módulos de Aprendizaje, Desempeño, Diagnóstico y Encuestas. La API implementa funcionalidades CRUD para gestionar los módulos y cursos, así como estrategias para adaptar las respuestas de acuerdo al canal de solicitud (Web o Mobile).

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3**
- **Maven**
- **MySQL**: Base de datos utilizada para el almacenamiento de datos.
- **Hibernate Validator**: Para validaciones a nivel de entidades.
- **Swagger**: Para la documentación de la API.
- **Lombok**: Para simplificar la generación de código repetitivo.
- **JPA**: Para la gestión de persistencia de datos.

## Diseño de Arquitectura

La aplicación sigue una arquitectura en capas, dividida en:

- **Controllers**: Exponen las funcionalidades de la API.
- **Services**: Contienen la lógica de negocio.
- **Repositories**: Administran la interacción con la base de datos.
- **Mappers**: Convierte entidades a DTOs y viceversa, encapsulando las entidades del dominio.

Además, se implementaron los siguientes patrones de diseño:

1. **Strategy**: Para seleccionar la estrategia de respuesta basada en el canal (`Web` o `Mobile`), especificado en el `@RequestParam channel`.
2. **Builder**: Para la construcción de objetos complejos en los DTOs.
3. **Facade**: Para simplificar el acceso a los servicios desde los controladores.

## Estructura de la Base de Datos

El modelo de datos incluye las siguientes tablas:

- **Module**: Representa los módulos principales, con campos `title` y `description`.
- **Submodule**: Relacionado con `Module` en una relación uno-a-muchos, con campos `title`, `subtitle`, `description` y `button`.
- **Catalog**: Representa los catálogos de cursos con una relación hacia `Level`, `Category` y `Partner`.
- **Level**: Nivel del curso (`Básico`, `Intermedio`, `Difícil`).
- **Category**: Tipo de contenido (`Curso`, `Charla`, `Ideas de Libros`, `Prácticas`, `Bit`).
- **Partner**: Información de la entidad colaboradora (por ejemplo, `Stanford Online`).

### Script de Datos de Prueba

Para poblar la base de datos con datos de prueba, se incluyó el archivo `data.sql` en `src/main/resources`, que se ejecuta automáticamente al iniciar la aplicación.

## Endpoints Principales

1. **Módulos**
    - `POST /modules`: Crea un nuevo módulo y sus submódulos.
    - `GET /modules/{id}`: Obtiene un módulo por ID.
    - `PUT /modules/{id}`: Actualiza un módulo y sus submódulos asociados.
    - `DELETE /modules/{id}`: Elimina un módulo por ID.

2. **Catálogos**
    - `POST /catalogs`: Crea un nuevo catálogo de cursos.
    - `GET /catalogs/{id}`: Obtiene un catálogo de cursos por ID.
    - `PUT /catalogs/{id}`: Actualiza un catálogo de cursos y sus relaciones.
    - `DELETE /catalogs/{id}`: Elimina un catálogo de cursos por ID.

### Validaciones

Se implementaron validaciones a nivel de entidades usando anotaciones de Hibernate Validator. Por ejemplo:
- `@NotNull` y `@Size` para asegurar que campos como `title` y `description` no sean nulos y tengan longitudes adecuadas.

### Manejo de Excepciones

Se configuró un `@ControllerAdvice` global que captura excepciones comunes y devuelve respuestas JSON con mensajes personalizados, tales como:
- **EntityNotFoundException**: Cuando no se encuentra un registro por ID.
- **DataIntegrityViolationException**: Para violaciones de integridad de datos.

## Ejecución del Proyecto

### Requisitos Previos

- **Java 17**
- **MySQL**: Configura las credenciales en `application.properties`.
- **Maven**

### Configuración

En `src/main/resources/application.properties`, configura las variables de entorno para la conexión con MySQL:
```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER_NAME}
spring.datasource.password=${DB_PASSWORD}
```

###  Pruebas
Se implementaron pruebas unitarias básicas para la entidad CatalogMapper.

###  Postman
Se incluye un archivo de colección de Postman (Catalogo_Modulo_API.postman_collection.json) para facilitar la prueba de los endpoints.

###  Contribución
Las contribuciones son bienvenidas. Si deseas colaborar, sigue los pasos a continuación:

Haz un fork del proyecto.
- **Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).**
- **Realiza tus cambios y haz commit (git commit -am 'Agrega nueva funcionalidad').**
- **Haz push a la rama (git push origin feature/nueva-funcionalidad).**
- **Abre un Pull Request.**

### Licencia
Este proyecto está bajo la Licencia MIT.

¡Gracias por utilizar el Human Talent Management API!