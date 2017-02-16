var translate = function (text) {};

var eb = vertx.eventBus();

eb.consumer("translator", function (message) {

  var french = message.body();
  var english = translate(french);

  console.log("Message reçu: " + french);
  eb.send("Database", { type: "text", french:french, english:english });
});