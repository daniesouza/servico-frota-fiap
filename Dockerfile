FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/servico-frota-*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]


#docker build -t fiap/spring-servico-frota-app .