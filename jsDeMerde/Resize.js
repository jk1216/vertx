var Cropper = require("cropper");

var resizeToMini = function (original) {};
var resizeToSmall = function (original) {};
var eb = vertx.eventBus();

eb.consumer("resizer", function (message) {

    console.log("Receive");
    var cropper = new Cropper(message.body(), {
        aspectRatio: 16 / 9,
        crop: function(e) {
            console.log(e.detail.x);
            console.log(e.detail.y);
            console.log(e.detail.width);
            console.log(e.detail.height);
            console.log(e.detail.rotate);
            console.log(e.detail.scaleX);
            console.log(e.detail.scaleY);
        }
    });
    console.log("end");

    //  var mini = resizeToMini(original);
//  var small = resizeToSmall(original);

     //console.log("Messag recu: " + src.height);
    //eb.send("Database", { type: "image", orig: original, mini: mini, small: small });
});
