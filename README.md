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

Deploy Sample Application (from your sample project dir):

    bin/asadmin deploy target/cluster.war


You should see messages similar to the following : tail -f glassfish/domains/domain1/logs/server.log

    [#|2013-01-26T10:55:53.474-1000|INFO|glassfish 4.0|org.glassfish.jersey.servlet.init.JerseyServletContainerInitializer|_ThreadID=78;_ThreadName=admin-listener(1);_TimeMillis=1359233753474;_LevelValue=800;|Registering the Jersey servlet application, named resources.MyApplication, at the servlet mapping /*, with the Application class of the same name.|#]

    [#|2013-01-26T10:55:53.484-1000|INFO|glassfish 4.0|javax.enterprise.web|_ThreadID=78;_ThreadName=admin-listener(1);_TimeMillis=1359233753484;_LevelValue=800;_MessageID=AS-WEB-00324;|Loading application [cluster] at [/cluster]|#]

    [#|2013-01-26T10:55:53.517-1000|INFO|glassfish 4.0|javax.enterprise.system.core|_ThreadID=78;_ThreadName=admin-listener(1);_TimeMillis=1359233753517;_LevelValue=800;|cluster was successfully deployed in 384 milliseconds.|#]



List Deployed Applications

    bin/asadmin list-applications
    cluster  <web>
    Command list-applications executed successfully.


Access the URL <http://localhost:8080/cluster/admin/35> to see a sample response.

You should see messages similar to the following :

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

Undeploy the sample application

    bin/asadmin undeploy cluster
    Command undeploy executed successfully.