package Database;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.SQLConnection;

public class Saver extends AbstractVerticle {

  // Convenience method so you can run it in your IDE
  public static void main(String[] args) {
    Runner.runExample(Saver.class);
  }

  @Override
  public void start() throws Exception {


    JsonObject mySQLClientConfig = new JsonObject();
    mySQLClientConfig.put("host", "postgres://ec2-54-235-168-152.compute-1.amazonaws.com:5432/dc5ct0d40vf5sg");
    mySQLClientConfig.put("port", 5432);
    mySQLClientConfig.put("maxPoolSize", 10);
    mySQLClientConfig.put("username", "kmydlwouqrtjdi");
    mySQLClientConfig.put("password", "29e7b27e9a90397c3ee68f90a5aa9719fa07f4dd19c2febb67df6412d4bd7f8a");
    mySQLClientConfig.put("database", "DBVERTX");
    mySQLClientConfig.put("charset", "UTF-8");
    mySQLClientConfig.put("queryTimeout", 10000);

    AsyncSQLClient mySQLClient = MySQLClient.createShared(vertx, mySQLClientConfig);

    mySQLClient.getConnection(res -> {
      if (res.succeeded()) {

        SQLConnection connection = res.result();

        System.out.println("Open !");
      } else {
        System.out.println("Failes :( !");
      }
    });

    mySQLClient.close();
  }
}
