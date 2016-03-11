FROM ubuntu:14.04

RUN apt-get update
RUN apt-get install software-properties-common -y
RUN add-apt-repository ppa:webupd8team/java -y
RUN apt-get update
RUN echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN apt-get install oracle-java8-installer -y
RUN apt-get install oracle-java8-set-default

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# Define working directory.
WORKDIR /data

ADD target/LoteryApllication-1.0-SNAPSHOT.jar  /data/LoteryApllication-1.0-SNAPSHOT.jar

ADD example.yml /data/example.yml

RUN java -jar LoteryApllication-1.0-SNAPSHOT.jar db migrate example.yml

CMD java -jar LoteryApllication-1.0-SNAPSHOT.jar example.yml

EXPOSE 8080