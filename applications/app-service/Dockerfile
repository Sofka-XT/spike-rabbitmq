FROM openjdk:13-oracle
WORKDIR /app
COPY build/libs/applications-app-service-0.0.1.jar /app/app.jar
EXPOSE 80
CMD java -XX:OnOutOfMemoryError="kill -9 %p" \
         -jar /app/app.jar