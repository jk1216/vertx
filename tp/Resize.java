package tp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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

            BufferedImage bf400 = get400x400(b);
            eb.send("database", bf400.toString());
            System.out.println("Format -> " + bf400.getWidth() + "x" + bf400.getHeight());
            BufferedImage bf100 = get100x100(b);
            eb.send("database", bf400.toString());
            System.out.println("Format -> " + bf100.getWidth() + "x" + bf100.getHeight());
            eb.send("database", b.toString());
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
