var eb = vertx.eventBus();

// -- generate text with Faker.js
// -- send paragraphe to Translator
// -- get image from http://lorempixel.com/800/600/cats/i/
// -- send images to Resize

getImageFromPixel();

/*vertx.setPeriodic(1000, function (id) {
    eb.send("resizer", "hello world!");
});
*/
function getRandom() {
    return Math.floor(Math.random() * 10) + 1;
}

function getImageFromPixel() {
    var options = {
	"defaultHost" : "lorempixel.com"
    };
    var client = vertx.createHttpClient(options);
    client.getNow("/800/600/cats/" + getRandom() + "/", function (response) {
        response.bodyHandler(function (body) {
            console.log("Send");
//            var buff = Buffer.buffer().append(body);
//            console.log("Send");
//            eb.send("resizer", buff);
            console.log("Send");
        });
    });
}
