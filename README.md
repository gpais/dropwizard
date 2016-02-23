"# lotery" 
mvn package
java -jar target/dropwizard-example-1.0.0-SNAPSHOT.jar db migrate example.yml
java -jar target/dropwizard-example-1.0.0-SNAPSHOT.jar server example.yml