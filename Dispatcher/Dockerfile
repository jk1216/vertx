FROM openjdk:8u102-jre

COPY ./ /usr/local/
RUN chmod +x /usr/local/vertx/bin/vertx

# Set path
ENV VERTX_HOME /usr/local/vertx
ENV PATH $VERTX_HOME/bin:$PATH

# FROM vertx/vertx3

#RUN echo 'debconf debconf/frontend select Noninteractive' | debconf-set-selections \
#  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
#  add-apt-repository -y ppa:webupd8team/java && \
#  apt-get update && \
#  apt-get install -y oracle-java8-installer && \
#  rm -rf /var/lib/apt/lists/* && \
#  rm -rf /var/cache/oracle-jdk8-installer


# Define commonly used JAVA_HOME variable
# ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# Set the name of the verticle to deploy
ENV VERTICLE_NAME Dispatcher.java

# Set the location of the verticles
ENV VERTICLE_HOME Dispatcher

EXPOSE 8080

# Copy your verticle to the container
COPY $VERTICLE_NAME $VERTICLE_HOME/

# Launch the verticle
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["vertx run $VERTICLE_NAME -cluster -cp $VERTICLE_HOME/*"]
