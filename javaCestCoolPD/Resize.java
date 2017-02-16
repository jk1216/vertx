package vertx.tp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;

public class Resize extends AbstractVerticle {

    public static void main(String[] args) {
        Runner.runClusteredExample(Dispatcher.class);
    }

    @Override
    public void start() throws Exception {
        EventBus eb = vertx.eventBus();

        eb.consumer("resizer", (Handler<Message<Buffer>>) message -> {
            Buffer b = message.body();
            System.out.println("To change size : " +  b);
        });
    }
}
