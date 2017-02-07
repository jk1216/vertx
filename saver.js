var eb = vertx.eventBus();

eb.consumer("saver", function (message) {

  console.log("Messag recu: " + message.body());
  // envoyer une reponse 
  //message.reply("Salut! comment vas-tu?");
});