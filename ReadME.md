# 📇 Contacts Microservice

This is a Kotlin-based Spring Boot microservice that manages contact information and publishes events to Kafka.

---

### 📚 Tech Stack

- **Kotlin**
- **Spring Boot**
- **Kafka**
- **PostgreSQL**
- **Gradle**
- **Docker & Docker Compose**
---
## 🛠️ Running the Application

### 🔹 Run Locally

1. **Comment out** the `contacts-app` section in `docker-compose.yaml`.

2. **Update `application.yaml`** with the following configuration:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/contactdb
     kafka:
       bootstrap-servers: localhost:29092
       properties:
         schema.registry.url: http://localhost:8081
3. Start required services:
   ```yaml
   docker-compose up -d

4. Run the Spring Boot application from your IDE or using:
   ```yaml
   ./gradlew clean
   ./gradlew bootJar
   java -jar build/libs/contacts-0.0.1-SNAPSHOT.jar


### 🔹 Running in Docker

1. Build the project
   ```yaml
   ./gradlew clean
   ./gradlew bootJar
2. Uncomment the 'contacts-app' section in docker-compose.yaml.
3. Update application.yaml with Docker-specific settings:
   ```yaml
   spring:
      datasource:
        url: jdbc:postgresql://postgres:5432/contactdb
      kafka:
        bootstrap-servers: kafka:29092
        properties:
          schema.registry.url: http://schema-registry:8081
4. Build the Docker image:
   ```yaml
   docker build -t contacts-app .
5. Run the application stack:
   ```yaml
   docker compose up -d

6. Access the application at `http://localhost:8080`
7. Access the schema registry at 6. Access the application at `http://schema-registry:8081`

---
### ⚠️ Schema Registry Note

If the application inside the container tries to connect to the Schema Registry at `http://localhost:8081` instead of `http://schema-registry:8081`, you need to update the environment in `docker-compose.yaml`:
   ```yaml
   environment:
     SPRING_KAFKA_PROPERTIES_SCHEMA_REGISTRY_URL: http://schema-registry:8081```
   ````
### ⚠️ Avro Code Gen Note
This project uses a avro code genetor to generate java files from avro schema files. The project is located at:
https://github.com/nishantsnaik/avro-codegen-plugin