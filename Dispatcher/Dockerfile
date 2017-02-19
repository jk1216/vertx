FROM vertx/vertx3

ENV JAVA_OPTS "-Dfoo=bar"

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
