var eb = vertx.eventBus();


// loop get info

// -- generate text with Faker.js
// -- send paragraphe to Translator
// -- get image from http://lorempixel.com/800/600/cats/i/
// -- send images to Resize
// -- sleep (5)

eb.send("translater", "hello world!");
