# Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:17
ARG PROFILE=prod
ARG APP_VERSION=1.0.1

WORKDIR /app
COPY --from=build /build/target/book-network-*.jar /app/

EXPOSE 8080

# ⚠️ Elimina las líneas:
# ENV DB_URL=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
# ENV MAILDEV_URL=localhost

ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}

# Y reemplaza el CMD con esto:
CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} book-network-${JAR_VERSION}.jar
