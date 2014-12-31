This project is a step-by-step Spring-GWT tutorial.
It is inspired from the article written by Alex Tretyakov on 2012-08-30
[Spring and GWT tutorial. Part 5 - UiBinder](http://alextretyakov.blogspot.fr/2012/08/using-uibinder.html).

The project was created as a "Maven Project" in Eclipse.
Then files from hellogwt were added, commented and modified to run with SQLite instead of MySQL.

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

If you have configured `$HOME/.m2/settings.xml` with a server called Tomcat6:

1. If Tomcat is not running, start it.
   `~/bin/apache-tomcat-6.0.43/bin/startup.sh`
2. Build and deploy the project by executing `mvn tomcat6:deploy` command.
   You can use `mvn tomcat6:redeploy` if the war was deployed.
3. Go to http://localhost:8080/hellogwt/.

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

= Debug the application =

== In Maven ==

- `mvn compile` only compiles Java sources.
- `mvn gwt:compile` only compiles GWT module and writes extras.

== In NetBeans ==

1. Define breakpoints if needed.
2. Click on `Debug Project` or type Ctrl+F5.
3. In the project custom menu, click on `Run GWT code server`.
4. Visit the url said by the code server to install the bookmarklet.
