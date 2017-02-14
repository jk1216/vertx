var eb = vertx.eventBus();


// loop get info

// -- generate text with Faker.js
// -- send paragraphe to Translator
// -- get image from http://lorempixel.com/800/600/cats/i/
// -- send images to Resize

vertx.setPeriodic(1000, function (id) {
    eb.send("translater", "hello world!");
});
