This project is a step-by-step Spring-GWT tutorial.
It is inspired from the article written by Alex Tretyakov on 2011-10-23
[Spring and GWT tutorial. Part 1 - Spring Configuration](http://alextretyakov.blogspot.fr/2011/10/spring-gwt-part-1-setting-spring.html).

The project was created as a "Maven Project" in Eclipse.
Then files from hellogwt were added.

The source code is licensed under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

= Run the application =

== In command line ==

1. Build the project by executing `mvn clean install` command.
   Now we have application war-file.
2. If Tomcat is running, stop/kill it.
   `~/bin/apache-tomcat-6.0.43/bin/shutdown.sh`
3. Go to the tomcat installation folder
4. Delete the temp and work folders. They only contain temporary files.
5. Copy the war file `target/hellogwt.war` to `<tomcat>/webapps/` folder.
6. Start the server
   `~/bin/apache-tomcat-6.0.43/bin/startup.sh`.
7. Go to http://localhost:8080/hellogwt/.

== In Eclipse ==

You need to install those plug-ins:
- Eclipse Git Team Provider

Right-click on project and click on `Run As > Run on Server`.

== In NetBeans ==

You need to install those plugins:
- GWT4NB
- JavaEE Base
- Maven

Click on `Run project` or type F6.