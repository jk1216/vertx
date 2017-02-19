package Translator;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

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
                vertx.createHttpClient(new HttpClientOptions().setSsl(true).setTrustAll(true))
                        .getAbs("https://translate.googleapis.com/translate_a/single?client=gtx&sl="
                        + sourceLang + "&tl=" + targetLang + "&dt=t&q=" + URLEncoder.encode(message.body(), StandardCharsets.UTF_8.toString()),
                        response -> {
                        response.bodyHandler(buffer -> {
                            String data = buffer.toString();

                            data = data.replace(",,,,", ",");
                            data = data.replace(",,,", ",");
                            data = data.replace(",,", ",");

                            JsonArray ja = new JsonArray(data);

                            JsonObject toSend = new JsonObject();
                            JsonArray words = new JsonArray();
                            words.add(ja.getJsonArray(0).getJsonArray(0).getString(1));
                            words.add(ja.getJsonArray(0).getJsonArray(0).getString(0));
                            toSend.put("Words", words);
                            System.out.println("Received response with status code " + words.toString());
                            //eb.send("dataBaseString", toSend);
                        });
                    }).end();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
    }
}
