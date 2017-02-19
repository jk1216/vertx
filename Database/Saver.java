package Database;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
// import io.vertx.core.json.JsonObject;
// import io.vertx.example.util.Runner;
// import io.vertx.ext.sql.ResultSet;
// import io.vertx.rxjava.core.AbstractVerticle;
// import io.vertx.rxjava.ext.jdbc.JDBCClient;
// import io.vertx.rxjava.ext.sql.SQLConnection;

// public class Saver extends AbstractVerticle {
//     public static void main(String[] args) {
//         Runner.runClusteredExample(Saver.class);
//     }
//     @Override
// 	public void start() throws Exception {
// 	    EventBus eb = vertx.eventBus();
// 	    mdbConn();

// 	    eb.consumer("Saver", (Handler<Message<Buffer>>) message -> {

// 	        System.out.println("good");
// 	    });
// 	}

// 	public void mdbConn() {
// 		// final JDBCClient client = JDBCClient.createShared(vertx, new JsonObject()
// 		// 	.put("url", "jdbc:hsqldb:mem:test?shutdown=true")
// 		// 	.put("driver_class", "org.hsqldb.jdbcDriver")
// 		// 	.put("max_pool_size", 30)
// 		// 	);
// 		JsonObject config = new JsonObject().put("url", "jdbc:hsqldb:mem:test?shutdown=true")
// 			.put("driver_class", "org.hsqldb.jdbcDriver");

// 		JDBCClient jdbc = JDBCClient.createShared(vertx, config);

// 	}

// // 	public void dbConn() {
// // 	    try {
// // 	    	Class.forName("org.postgresql.Driver");
// // 	    	//org.postgres.Driver driver = new org.postgres.Driver();
// // 	    	System.out.println("Driver O.K.");

// // 	    	String url = "jdbc:postgres://ec2-54-235-168-152.compute-1.amazonaws.com:5432/dc5ct0d40vf5sg";
// // 	    	String user = "kmydlwouqrtjdi";
// // 	    	String passwd = "29e7b27e9a90397c3ee68f90a5aa9719fa07f4dd19c2febb67df6412d4bd7f8a";
// // 	    	// String user = "postgres";
// // 	    	// String passwd = "postgres";

// // 	    	Connection conn = DriverManager.getConnection(url, user, passwd);
// // //	    	driver.Connection conn = DriverManager.getConnection(url, user, passwd);
// // 	    	System.out.println("Connexion effective !"); 
// // 	    } catch (Exception e) {
// // 	      e.printStackTrace();
// // 	    }      
// // 	}
// }

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.example.util.Runner;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;

/*
 * @author <a href="mailto:pmlopes@gmail.com">Paulo Lopes</a>
 */
public class JDBCExample extends AbstractVerticle {

  // Convenience method so you can run it in your IDE
  public static void main(String[] args) {
    Runner.runExample(JDBCExample.class);
  }

  @Override
  public void start() throws Exception {

    final JDBCClient client = JDBCClient.createShared(vertx, new JsonObject()
        .put("url", "jdbc:hsqldb:mem:test?shutdown=true")
        .put("driver_class", "org.hsqldb.jdbcDriver")
        .put("max_pool_size", 30));

    client.getConnection(conn -> {
      if (conn.failed()) {
        System.err.println(conn.cause().getMessage());
        return;
      }

      final SQLConnection connection = conn.result();
      connection.execute("create table test(id int primary key, name varchar(255))", res -> {
        if (res.failed()) {
          throw new RuntimeException(res.cause());
        }
        // insert some test data
        connection.execute("insert into test values(1, 'Hello')", insert -> {
          // query some data
          connection.query("select * from test", rs -> {
            for (JsonArray line : rs.result().getResults()) {
              System.out.println(line.encode());
            }

            // and close the connection
            connection.close(done -> {
              if (done.failed()) {
                throw new RuntimeException(done.cause());
              }
            });
          });
        });
      });
    });
  }
}