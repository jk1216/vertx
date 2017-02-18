/* "use strict" */

var eb = vertx.eventBus();
var httpHandler = vertx.createHttpClient();

console.log("vertx: %j", vertx);
console.log("httpHandler: %j", httpHandler);
eb.consumer("translator", function (message) {

    console.log(message.body());
    eb.send("database", message.body());
});