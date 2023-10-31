FROM openjdk:17-oracle

WORKDIR /app

COPY target/students-cli-app-0.0.1-SNAPSHOT.jar app.jar

ENV GENERATE=false

CMD java -Dapp.students.generate=$GENERATE -jar app.jar
