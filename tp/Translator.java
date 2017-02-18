package tp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;

import java.io.IOException;

public class Translator extends AbstractVerticle {

    public static void main(String[] args) {
	Runner.runClusteredExample(Translator.class);
    }

        @Override
	public void start() throws Exception {
	    EventBus eb = vertx.eventBus();

	    eb.consumer("translator", (Handler<Message<Buffer>>) message -> {
		    String p = message.body();

		    System.out.println(p);
		    Translator translate = Translator.getInstance();
		    String text = translate.translate("Hello!", Language.ENGLISH, Language.ROMANIAN);
		    System.out.println(text);
	    });

	}
}
