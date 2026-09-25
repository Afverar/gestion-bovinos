# BovinNet — Módulo Gestión de Bovinos

Evidencia **GA7-220501096-AA3-EV01**: codificación de un módulo del
proyecto BovinNet usando **Spring Boot**, framework visto en el
componente formativo *"Frameworks para construcción de aplicaciones con
JAVA"*.

## Artefactos previos considerados
- Requisitos funcionales IEEE 830 — `GA5-220501095-AA1-EV03`.
- Modelo entidad-relación y modelo relacional (normalizado a 3FN) —
  `GA6-220501096-AA1-EV01` / `EV02`.
- Historias de usuario del módulo — ver `HISTORIAS_DE_USUARIO.md`.
- Informe técnico de herramientas de versionamiento (Git/GitHub/CI) —
  `GA7-220501096-AA1-EV01`.

## Requisitos para ejecutar el proyecto
- JDK 17 o superior.
- Maven 3.8 o superior (o usar el wrapper `./mvnw` si lo agrega con
  `mvn -N io.takari:maven:wrapper`).
- Conexión a internet la primera vez, para que Maven descargue las
  dependencias de Spring Boot desde el repositorio central.

## Cómo ejecutar
```bash
cd gestion-bovinos
mvn spring-boot:run
```
La aplicación queda disponible en:
- Interfaz web: http://localhost:8080/bovinos
- API REST: http://localhost:8080/api/bovinos
- Consola de la base de datos H2: http://localhost:8080/h2-console
  (JDBC URL: `jdbc:h2:mem:bovinetdb`, usuario `sa`, sin contraseña)

## Cómo ejecutar las pruebas unitarias
```bash
mvn test
```

## Estándares de codificación aplicados
- Paquetes organizados por responsabilidad: `model`, `repository`,
  `service`, `controller`, `exception` (arquitectura en capas propia de
  Spring Boot).
- Nomenclatura en `camelCase` para variables/métodos y `PascalCase`
  para clases, siguiendo las convenciones oficiales de Java.
- Inyección de dependencias por constructor (recomendada por el propio
  framework frente a la inyección por campo).
- Comentarios JavaDoc en clases y métodos públicos explicando su
  propósito y la historia de usuario/requisito que satisfacen.
- Validaciones declarativas con Bean Validation (`jakarta.validation`)
  en lugar de validación manual dispersa en los controladores.
- Manejo de errores mediante excepciones de negocio propias
  (`AreteDuplicadoException`, `BovinoNoEncontradoException`) en lugar
  de comparar código de error como enteros.

## Control de versiones
Este proyecto se inicializó con Git (`git init`) y su historial de
commits locales queda incluido en la carpeta `.git/` de esta entrega,
como evidencia del uso de herramientas de versionamiento. Para
publicarlo en un repositorio remoto:
```bash
git remote add origin <URL_DE_TU_REPOSITORIO_EN_GITHUB>
git branch -M main
git push -u origin main
```
El enlace del repositorio remoto debe registrarse en el archivo
`enlace_repositorio.txt` de esta misma entrega.
