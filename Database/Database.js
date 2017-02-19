/* "use strict" */

var eb = vertx.eventBus();
var JDBCClient = require("vertx-jdbc-js/jdbc_client");

eb.consumer("database", function (message) {
    console.log(message.body());
});


var client = JDBCClient.createShared(vertx, {
    "url" : "jdbc:postgres://kmydlwouqrtjdi:29e7b27e9a90397c3ee68f90a5aa9719fa07f4dd19c2febb67df6412d4bd7f8a@ec2-54-235-168-152.compute-1.amazonaws.com:5432/dc5ct0d40vf5sg",
    "driver_class" : "org.postgresql.Driver",
    "max_pool_size" : 30
});

client.getConnection(function (conn, conn_err) {
    if (conn_err != null) {
    console.error(conn_err.getMessage());
    return
    }

    var connection = conn;
//    connection.execute("create table ");
    connection.close(function (done, done_err) {
      if (done_err != null) {
        throw done_err;
      }
    });
/*  connection.execute("create table test(id int primary key, name varchar(255))", function (res, res_err) {
    if (res_err != null) {
      throw res_err;
    }
    // insert some test data
    connection.execute("insert into test values(1, 'Hello')", function (insert, insert_err) {
      // query some data
      connection.query("select * from test", function (rs, rs_err) {
        Array.prototype.forEach.call(rs.results, function(line) {
          console.log(JSON.stringify(line));
        });

        // and close the connection
      });
    });
  });*/
});