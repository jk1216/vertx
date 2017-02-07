
var eb = vertx.eventBus();

eb.consumer("waiter", function (message) {

  console.log("Messag recu: " + message.body());
  // envoyer une reponse 
  //message.reply("Salut! comment vas-tu?");
});



eb.send("translater", "hello world!");
// eb.send("saver", "salut saver!");
// eb.send("resizer", "salut resizer!");
