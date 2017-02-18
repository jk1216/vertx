/* "use strict" */

var eb = vertx.eventBus();

eb.consumer("database", function (message) {
    console.log(message.body());
});