package Database;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.SQLConnection;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class Saver extends AbstractVerticle {



  public static void main(String[] args) {
    Runner.runExample(Saver.class);
  }

  @Override
  public void start() throws Exception {
    EventBus eb = vertx.eventBus();

    JsonObject mySQLClientConfig = new JsonObject();
    mySQLClientConfig.put("host", "localhost");
    mySQLClientConfig.put("max_pool_size", 5);
    mySQLClientConfig.put("username", "root");
    mySQLClientConfig.put("password", "n1JjiJYN");
    mySQLClientConfig.put("database", "DBVERTX");

    AsyncSQLClient mySQLClient = MySQLClient.createShared(vertx, mySQLClientConfig);

    mySQLClient.getConnection(res -> {
      if (res.succeeded()) {
        SQLConnection connection = res.result();

        connection.query("CREATE TABLE IF NOT EXISTS translation (" +
                        "id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                        "english TEXT," +
                        "french TEXT)",
                resultSetAsyncResult -> {
          if (resultSetAsyncResult.succeeded())
            System.out.println("Reuslt " + resultSetAsyncResult.result().toString());
          else
            System.out.println("Reuslt " + resultSetAsyncResult.cause());

            connection.query("CREATE TABLE IF NOT EXISTS images (" +
                            "id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                            "small TEXT," +
                            "big TEXT)",
                resultSetAsyncResult2 -> {
                    if (resultSetAsyncResult2.succeeded())
                        System.out.println("Reuslt " + resultSetAsyncResult2.result().toString());
                    else
                        System.out.println("Reuslt " + resultSetAsyncResult2.cause());

                    eb.consumer("dbText", (Handler<Message<JsonArray>>) message -> {
                        putText(message.body(), connection);
                    });

                    eb.consumer("dbImage", (Handler<Message<JsonArray>>) message -> {
                        try {
                            putImage(message.body(), connection);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                });
        });
      } else {
        System.out.println("Fails :( !" + res.cause().toString());
      }
        mySQLClient.close();
        System.out.println("Close !");
    });
  }

  private void putImage(JsonArray b, SQLConnection connection) throws IOException {
      File outputfilebig = new File("image_" + System.currentTimeMillis() + ".jpg");
      ImageIO.write(ImageIO.read(new ByteArrayInputStream(b.getBinary(0))), "jpg", outputfilebig);

      File outputfilesmall = new File("image_" + System.currentTimeMillis() + ".jpg");
      ImageIO.write(ImageIO.read(new ByteArrayInputStream(b.getBinary(1))), "jpg", outputfilesmall);

      JsonArray param = new JsonArray();
      param.add(outputfilesmall.getAbsolutePath());
      param.add(outputfilebig.getAbsolutePath());

      String query = "INSERT INTO images (small, big) VALUES (?, ?)";
      connection.queryWithParams(query, param, resultSetAsyncResult -> {
          if (resultSetAsyncResult.succeeded())
              System.out.println("Reuslt images OK");
          else
              System.out.println("Reuslt images: " + resultSetAsyncResult.cause());
      });
  }

  private void putText(JsonArray param, SQLConnection connection) {
    String query = "INSERT INTO translation (english, french) VALUES (?, ?)";
    connection.queryWithParams(query, param, resultSetAsyncResult -> {
          if (resultSetAsyncResult.succeeded())
              System.out.println("Reuslt translation OK");
          else
              System.out.println("Reuslt translation: " + resultSetAsyncResult.cause());
      });
  }
}