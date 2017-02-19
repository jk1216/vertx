package Dispatcher;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

import com.github.javafaker.*;

import java.util.Locale;

public class Dispatcher extends AbstractVerticle {
    private EventBus eb;

    public static void main(String[] args) {
        Runner.runClusteredExample(Dispatcher.class);
    }

    @Override
    public void start() throws Exception {
        eb = vertx.eventBus();
        vertx.setPeriodic(3000, aLong -> {
            getAndSendImage();
            getAndSendFakerText();
        });
    }

    private void getAndSendImage() {
        vertx.createHttpClient().getAbs("http://lorempixel.com/800/600/cats/" + getNumber() + "/", response -> {
            System.out.println("Received response with status code " + response.statusCode());
            response.bodyHandler(buffer ->
                eb.publish("resizer", buffer)
            );
        }).end();
    }

    private int getNumber() {
        return (1 + (int)(Math.random() * ((10 - 1) + 1)));
    }

    private void getAndSendFakerText() {
        Faker faker = new Faker(Locale.ENGLISH);
        String sourceText = "Hi, do you think we should go to " + faker.address().city() + "? Or should we stay in " + faker.address().city() + "? I think we " + faker.hacker().verb() + " " + faker.hacker().noun() + " no ? " + faker.chuckNorris().fact();
        System.out.println(sourceText);
        eb.publish("translator", sourceText);
    }
}
