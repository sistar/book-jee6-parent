Spring on Arquillian
====================
To enjoy spring on arquillian you must:

* first decide between on of the following maven profiles:

arq-jbossas-remote
-------------------
An optional Arquillian testing profile that executes tests in a remote JBoss AS instance
Run with: mvn clean test -Parq-jbossas-remote
requires a running JBoss AS

arq-jbossas-managed
-------------------
An optional Arquillian testing profile that executes tests in your JBoss AS instance
This profile will start a new JBoss AS instance, and execute the test, shutting it down when done
Run with: mvn clean test -Parq-jbossas-managed
requires correct path to JBoss AS in src/test/resources/arquillian.xml

arq-glassfish-remote
--------------------
TBD

arq-glassfish-managed
---------------------
TBD