var eb = vertx.eventBus();

eb.consumer("translater", function (message) {

  console.log("Messag recu: " + message.body());
  eb.send("saver", message.body().toUpperCase())
  // envoyer une reponse 
  //message.reply("Salut! comment vas-tu?");
});