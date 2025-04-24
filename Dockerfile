FROM openjdk:17-alpine

# JAR dosyasını container'a kopyala
COPY target/hospitalapp-0.0.1-SNAPSHOT.jar ./hp.jar

# Uygulama için portu aç
EXPOSE 8080

# Uygulama başlatma komutunu ayarla
ENTRYPOINT ["java", "-jar", "hp.jar"]
