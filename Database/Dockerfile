FROM vertx/vertx3

ADD . /code

WORKDIR /code

# Set the name of the verticle to deploy    (2)
ENV VERTICLE_NAME Saver.java

# Set the location of the verticles         (3)
ENV VERTICLE_HOME ./

EXPOSE 8080

# Copy your verticle to the container       (4)
COPY $VERTICLE_NAME $VERTICLE_HOME/

# Launch the verticle                       (5)
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["vertx run $VERTICLE_NAME -cp $VERTICLE_HOME/*"]
