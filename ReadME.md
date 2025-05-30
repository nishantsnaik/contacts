To run this locally:

1. Comment "contacts-app" section in docker.compose.yaml
2. Update application.yaml:
   url: jdbc:postgresql://localhost:5432/contactdb
   bootstrap-servers: localhost:29092
   schema.registry.url: http://localhost:8081
3. Run 'docker-compose up -d'
4. Start the application