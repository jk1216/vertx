FROM vertx/vertx3

ADD . /code

WORKDIR /code

# Set the name of the verticle to deploy
ENV VERTICLE_NAME Resize.Resize

# Set the location of the verticles
ENV VERTICLE_HOME .

EXPOSE 8080

# Copy your verticle to the container
COPY $VERTICLE_NAME $VERTICLE_HOME/

# Launch the verticle
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["vertx run $VERTICLE_NAME -cluster -cp $VERTICLE_HOME/*"]
