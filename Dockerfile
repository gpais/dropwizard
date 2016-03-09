FROM gpaishubid/sample

# Install Java.
RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# Define working directory.
WORKDIR /data

ADD target/LoteryApllication-1.0-SNAPSHOT-javadoc.jar  /data/LoteryApllication-1.0-SNAPSHOT-javadoc.jar

ADD example.yml /data/example.yml

RUN java -jar LoteryApllication-1.0-SNAPSHOT-javadoc.jar db migrate /data/example.yml

CMD java -jar LoteryApllication-1.0-SNAPSHOT-javadoc.jar /data/example.yml

EXPOSE 8080