package vertx.tp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;

public class Translator extends AbstractVerticle {
    public static void main(String[] args) {
        Runner.runClusteredExample(Dispatcher.class);
    }

    @Override
    public void start() throws Exception {
        EventBus eb = vertx.eventBus();

        eb.consumer("translator", (Handler<Message<String>>) message -> {
            System.out.println("To Translate : " +  message.body());
        });
    }
}
