/* "use strict" */

var eb = vertx.eventBus();
var httpHandler = vertx.createHttpClient();

console.log("vertx: %j", vertx);
console.log("httpHandler: %j", httpHandler);
eb.consumer("translator", function (message) {

    console.log(message.body());
    httpHandler.getAbs("https://www.google.com", function (res) {
        console.log("Got response " + res.statusCode());
        res.bodyHandler(function (body) {
            console.log("Got data " + body);
        });
    });
});