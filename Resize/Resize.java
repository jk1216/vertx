package tp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Resize extends AbstractVerticle {

    public static void main(String[] args) {
        Runner.runClusteredExample(Resize.class);
    }

    @Override
    public void start() throws Exception {
        EventBus eb = vertx.eventBus();

        eb.consumer("resizer", (Handler<Message<Buffer>>) message -> {
            Buffer b = message.body();

            try {
                JsonArray ja = new JsonArray();

                ByteArrayOutputStream ba = new ByteArrayOutputStream();
                BufferedImage bf400 = get400x400(b);
                ImageIO.write(bf400, "jpg", ba);
                ja.add(ba.toByteArray());

                ByteArrayOutputStream ba2 = new ByteArrayOutputStream();
                BufferedImage bf100 = get100x100(b);
                ImageIO.write(bf100, "jpg", ba2);
                ja.add(ba2.toByteArray());

                eb.send("dbImage", ja);
                // System.out.println("Image ok");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private BufferedImage get400x400(Buffer b) {
        BufferedImage bf400 = new BufferedImage(400, 400, TYPE_INT_RGB);
        Graphics2D g = bf400.createGraphics();
        try {
            g.drawImage(ImageIO.read(new ByteArrayInputStream(b.getBytes())), 0, 0, 400, 400, null);
            g.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bf400;
    }

    private BufferedImage get100x100(Buffer b) {
        BufferedImage bf100 = new BufferedImage(100, 100, TYPE_INT_RGB);
        Graphics2D g = bf100.createGraphics();
        try {
            g.drawImage(ImageIO.read(new ByteArrayInputStream(b.getBytes())), 0, 0, 100, 100, null);
            g.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bf100;
    }
}
