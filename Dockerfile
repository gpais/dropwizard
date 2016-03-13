FROM java:8-jre
COPY example.yml /opt/dropwizard/
COPY build/libs/LoteryApllication-1.0-SNAPSHOT.jar /opt/dropwizard/
EXPOSE 8080
WORKDIR /opt/dropwizard
RUN java -jar LoteryApllication-1.0-SNAPSHOT.jar db migrate example.yml

CMD ["java", "-jar", "-Done-jar.silent=true", "LoteryApllication-1.0-SNAPSHOT.jar", "server", "example.yml"]
