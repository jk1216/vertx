FROM ubuntu:14.04

MAINTAINER Luc Georges

# Install Java.
RUN DEBIAN_FRONTEND=noninteractive \
  apt-get -y update && \
  apt-get -y upgrade && \
  apt-get install -y  software-properties-common && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get -y update && \
  echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
  apt-get install -y oracle-java8-installer && \
  apt-get clean && \
  wget https://bintray.com/artifact/download/vertx/downloads/vert.x-3.3.3-full.tar.gz && \
  tar -xvzf vert.x-3.3.3-full.tar.gz && \
  mv vertx /usr/local/share && \
  ln -s /usr/local/share/vertx/bin/vertx /usr/bin/vertx


# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# Set the name of the verticle to deploy
ENV VERTICLE_NAME Saver.java
ENV VERTICLE_HELPER Runner.java

# Set the location of the verticles
ENV VERTICLE_HOME Database

ENV VERTICLE_DB_ASYNC db-async-common_2.11-0.2.20.jar
ENV VERTICLE_MYSQL_ASYNC mysql-async_2.11-0.2.20.jar
ENV VERTICLE_SCALA scala-library-2.11.7.jar

EXPOSE 8080

# Copy your verticle to the container
COPY $VERTICLE_NAME $VERTICLE_HOME/
COPY $VERTICLE_HELPER $VERTICLE_HOME/
COPY $VERTICLE_DB_ASYNC $VERTICLE_HOME/
COPY $VERTICLE_MYSQL_ASYNC $VERTICLE_HOME/
COPY $VERTICLE_SCALA $VERTICLE_HOME/

# Launch the verticle
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["vertx run $VERTICLE_NAME -cluster -cp vertx-mysql-postgresql-client-3.3.3.jar:scala-library-2.11.7.jar:db-async-common_2.11-0.2.20.jar:mysql-async_2.11-0.2.20.jar"]
