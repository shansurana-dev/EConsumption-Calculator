FROM adoptopenjdk/openjdk11:alpine-jre

# The artifact path
ARG artifact=target/EconsumptionCalculator.jar

WORKDIR /opt/app

COPY ${artifact} app.jar

# Install the necessary packages
RUN apt-get update && apt-get install -y maven

# Run command to start the application
ENTRYPOINT ["java","-jar","EconsumptionCalculator.jar"]