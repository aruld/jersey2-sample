Jersey 2 GlassFish 4 Sample App
===============================

Example showing how to implement JSON/XML Service in a Jersey 2 application deployed to Java EE 7 Container.

Setup
-----

1. git clone https://github.com/aruld/jersey2-sample.git
2. mvn package

Testing (Using Grizzly)
--------------------------

Run ClusterTest from your favourite IDE

Testing (Using GlassFish 4)
----------------------

Download latest promoted build here(http://dlc.sun.com.edgesuite.net/glassfish/4.0/promoted/glassfish-4.0-b72.zip).


Start GlassFish server:

    bin/asadmin start-domain
    Waiting for domain1 to start ......
    Successfully started the domain : domain1
    domain  Location: /glassfish3/glassfish/domains/domain1
    Log File: /glassfish3/glassfish/domains/domain1/logs/server.log
    Admin Port: 4848
    Command start-domain executed successfully.

Deploy Sample Application (from your sample project dir):

    bin/asadmin deploy target/cluster.war
    Application deployed with name cluster.
    Command deploy executed successfully.


You should see messages similar to the following on the server-side log :

    [#|2013-01-26T10:55:53.474-1000|INFO|glassfish 4.0|org.glassfish.jersey.servlet.init.JerseyServletContainerInitializer|_ThreadID=78;_ThreadName=admin-listener(1);_TimeMillis=1359233753474;_LevelValue=800;|Registering the Jersey servlet application, named resources.MyApplication, at the servlet mapping /*, with the Application class of the same name.|#]

    [#|2013-01-26T10:55:53.484-1000|INFO|glassfish 4.0|javax.enterprise.web|_ThreadID=78;_ThreadName=admin-listener(1);_TimeMillis=1359233753484;_LevelValue=800;_MessageID=AS-WEB-00324;|Loading application [cluster] at [/cluster]|#]

    [#|2013-01-26T10:55:53.517-1000|INFO|glassfish 4.0|javax.enterprise.system.core|_ThreadID=78;_ThreadName=admin-listener(1);_TimeMillis=1359233753517;_LevelValue=800;|cluster was successfully deployed in 384 milliseconds.|#]



List Deployed Applications

    bin/asadmin list-applications
    cluster  <web>
    Command list-applications executed successfully.


Access the URL <http://localhost:8080/cluster/admin/35> to see a sample XML response.

You should see messages similar to the following on the server-side log :

    [#|2013-01-26T10:56:50.732-1000|INFO|glassfish 4.0|org.glassfish.jersey.server.ApplicationHandler|_ThreadID=19;_ThreadName=http-listener-1(2);_TimeMillis=1359233810732;_LevelValue=800;|Initiating Jersey application, version Jersey: 2.0-m11 2012-12-21 12:34:15...|#]

    [#|2013-01-26T10:56:50.805-1000|INFO|glassfish 4.0|org.glassfish.jersey.filter.LoggingFilter|_ThreadID=19;_ThreadName=http-listener-1(2);_TimeMillis=1359233810805;_LevelValue=800;|1 * LoggingFilter - Request received on thread http-listener-1(2)
    1 > GET http://localhost:8080/cluster/admin/35
    1 > host: localhost:8080
    1 > user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:18.0) Gecko/20100101 Firefox/18.0
    1 > accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
    1 > accept-language: en-US,en;q=0.5
    1 > accept-encoding: gzip, deflate
    1 > connection: keep-alive
    |#]

    [#|2013-01-26T10:56:50.807-1000|INFO|glassfish 4.0|org.glassfish.jersey.filter.LoggingFilter|_ThreadID=19;_ThreadName=http-listener-1(2);_TimeMillis=1359233810807;_LevelValue=800;|2 * LoggingFilter - Response received on thread http-listener-1(2)
    2 < 200
    2 < Content-Type: application/xml
    |#]


Access using CURL to see a sample JSON response:

    curl -i --header "Accept: application/json" http://localhost:8080/cluster/admin/35
    HTTP/1.1 200 OK
    X-Powered-By: Servlet/3.0 JSP/2.2 (GlassFish Server Open Source Edition  4.0  Java/Oracle Corporation/1.7)
    Server: GlassFish Server Open Source Edition  4.0
    Content-Type: application/json
    Date: Sat, 26 Jan 2013 21:06:09 GMT
    Transfer-Encoding: chunked

    {"id":35,"name":"GF Cluster"}


You should see messages similar to the following on the server-side log :

    [#|2013-01-26T11:06:09.122-1000|INFO|glassfish 4.0|org.glassfish.jersey.filter.LoggingFilter|_ThreadID=22;_ThreadName=http-listener-1(5);_TimeMillis=1359234369122;_LevelValue=800;|1 * LoggingFilter - Request received on thread http-listener-1(5)
    1 > GET http://localhost:8080/cluster/admin/35
    1 > user-agent: curl/7.24.0 (x86_64-apple-darwin12.0) libcurl/7.24.0 OpenSSL/0.9.8r zlib/1.2.5
    1 > host: localhost:8080
    1 > accept: application/json
    |#]

    [#|2013-01-26T11:06:09.124-1000|INFO|glassfish 4.0|org.glassfish.jersey.filter.LoggingFilter|_ThreadID=22;_ThreadName=http-listener-1(5);_TimeMillis=1359234369124;_LevelValue=800;|2 * LoggingFilter - Response received on thread http-listener-1(5)
    2 < 200
    2 < Content-Type: application/json
    |#]


Jersey 2 Client (Using GlassFish 4)
----------------------

Run ClusterClient from your IDE to invoke your Jersey 2 resource deployed on GlassFish.

You should see messages similar to the following on the client-side log :

    Jan 26, 2013 11:10:36 AM org.glassfish.jersey.filter.LoggingFilter log
    INFO: 1 * LoggingFilter - Request received on thread main
    1 > GET http://localhost:8080/cluster/admin/35
    1 > Accept: application/json

    Jan 26, 2013 11:10:36 AM org.glassfish.jersey.filter.LoggingFilter log
    INFO: 1 * LoggingFilter - Response received on thread main
    1 < 200
    1 < Transfer-Encoding: chunked
    1 < Date: Sat, 26 Jan 2013 21:10:36 GMT
    1 < Content-Type: application/json
    1 < Server: GlassFish Server Open Source Edition  4.0
    1 < X-Powered-By: Servlet/3.0 JSP/2.2 (GlassFish Server Open Source Edition  4.0  Java/Oracle Corporation/1.7)

    GF Cluster
    Jan 26, 2013 11:10:36 AM org.glassfish.jersey.filter.LoggingFilter log
    INFO: 2 * LoggingFilter - Request received on thread main
    2 > GET http://localhost:8080/cluster/admin/35
    2 > Accept: application/xml

    Jan 26, 2013 11:10:36 AM org.glassfish.jersey.filter.LoggingFilter log
    INFO: 2 * LoggingFilter - Response received on thread main
    2 < 200
    2 < Transfer-Encoding: chunked
    2 < Date: Sat, 26 Jan 2013 21:10:36 GMT
    2 < Content-Type: application/xml
    2 < Server: GlassFish Server Open Source Edition  4.0
    2 < X-Powered-By: Servlet/3.0 JSP/2.2 (GlassFish Server Open Source Edition  4.0  Java/Oracle Corporation/1.7)

    GF Cluster

You should see messages similar to the following on the server-side log :

    [#|2013-01-26T11:10:36.657-1000|INFO|glassfish 4.0|org.glassfish.jersey.filter.LoggingFilter|_ThreadID=21;_ThreadName=http-listener-1(4);_TimeMillis=1359234636657;_LevelValue=800;|3 * LoggingFilter - Request received on thread http-listener-1(4)
    3 > GET http://localhost:8080/cluster/admin/35
    3 > accept: application/json
    3 > user-agent: Jersey/2.0-m11 (HttpUrlConnection 1.7.0_11)
    3 > host: localhost:8080
    3 > connection: keep-alive
    |#]

    [#|2013-01-26T11:10:36.658-1000|INFO|glassfish 4.0|org.glassfish.jersey.filter.LoggingFilter|_ThreadID=21;_ThreadName=http-listener-1(4);_TimeMillis=1359234636658;_LevelValue=800;|4 * LoggingFilter - Response received on thread http-listener-1(4)
    4 < 200
    4 < Content-Type: application/json
    |#]

    [#|2013-01-26T11:10:36.918-1000|INFO|glassfish 4.0|org.glassfish.jersey.filter.LoggingFilter|_ThreadID=20;_ThreadName=http-listener-1(3);_TimeMillis=1359234636918;_LevelValue=800;|5 * LoggingFilter - Request received on thread http-listener-1(3)
    5 > GET http://localhost:8080/cluster/admin/35
    5 > accept: application/xml
    5 > user-agent: Jersey/2.0-m11 (HttpUrlConnection 1.7.0_11)
    5 > host: localhost:8080
    5 > connection: keep-alive
    |#]

    [#|2013-01-26T11:10:36.919-1000|INFO|glassfish 4.0|org.glassfish.jersey.filter.LoggingFilter|_ThreadID=20;_ThreadName=http-listener-1(3);_TimeMillis=1359234636919;_LevelValue=800;|6 * LoggingFilter - Response received on thread http-listener-1(3)
    6 < 200
    6 < Content-Type: application/xml
    |#]



Undeploy the sample application

    bin/asadmin undeploy cluster
    Command undeploy executed successfully.