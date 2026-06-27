FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8083

ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql-instituto:3306/instituto_matriculas_db
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root
ENV SERVICES_ALUMNO_URL=http://alumno-service:8081/api/alumnos
ENV SERVICES_CURSO_URL=http://curso-service:8082/api/cursos

ENTRYPOINT ["java", "-jar", "app.jar"]