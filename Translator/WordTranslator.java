package Translator;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonArray;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WordTranslator extends AbstractVerticle {
    public static void main(String[] args) {
        Runner.runClusteredExample(WordTranslator.class);
    }

    @Override
    public void start() throws Exception {
        EventBus eb = vertx.eventBus();

        eb.consumer("translator", (Handler<Message<String>>) message -> {
            String sourceLang = "auto";
            String targetLang = "fr";
            try {
                String msg = message.body();
                System.out.println(msg);
                msg = msg.replace(".", "zeub").replace("?", "zeub").replace("!", "zeub");
                System.out.println(msg);
                vertx.createHttpClient(new HttpClientOptions().setSsl(true).setTrustAll(true))
                        .getAbs("https://translate.googleapis.com/translate_a/single?client=gtx&sl="
                        + sourceLang + "&tl=" + targetLang + "&dt=t&q=" + URLEncoder.encode(msg, StandardCharsets.UTF_8.toString()),
                        response -> {
                        response.bodyHandler(buffer -> {
                            String data = buffer.toString();

                            data = data.replace("zeub", ".").replace("zeub", "?").replace("zeub", "!");
                            data = data.replace(",,,,", ",").replace(",,,", ",").replace(",,", ",");

                            JsonArray ja = new JsonArray(data);

                            JsonArray words = new JsonArray();
                            words.add(ja.getJsonArray(0).getJsonArray(0).getString(1));
                            words.add(ja.getJsonArray(0).getJsonArray(0).getString(0));
                            System.out.println(">> " + words.toString());
                           eb.send("dbText", words);
                        });
                    }).end();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
    }
}
