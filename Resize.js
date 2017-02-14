var resizeToMini = function (original) {};
var resizeToSmall = function (original) {};

var eb = vertx.eventBus();

eb.consumer("resizer", function (message) {

  var original = message;
  var mini = resizeToMini(original);
  var small = resizeToSmall(original);

  console.log("Messag recu: " + message.body());
  eb.send("Database", { type: "image", orig: original, mini: mini, small: small });
});