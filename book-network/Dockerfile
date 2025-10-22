# ===== Build stage =====
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build

# 👇 Ajustamos rutas porque Koyeb usa la raíz del repo como contexto
COPY book-network/pom.xml .
RUN mvn dependency:go-offline
COPY book-network/src ./src

RUN mvn clean package -DskipTests

# ===== Runtime stage =====
FROM amazoncorretto:17
ARG PROFILE=prod
ARG APP_VERSION=1.0.1

WORKDIR /app
COPY --from=build /build/target/book-network-*.jar /app/

EXPOSE 8080

ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}

CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} book-network-${JAR_VERSION}.jar

