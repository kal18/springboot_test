About
=====

The simplest Web Application to Post Message to file
and to store the history of already posted messages

Requirement 
============

* `JDK 1.8 or later`
* `Gradle 4`
* `IDE IntelliJ community edition or professional edition`

Run
===

`./gradlew bootRun` or from IDE's run command (Grandle task)

Checks
=====
Post message
* `http://localhost:8080`

View history
* `http://localhost:8080/history`

Health check  
* `http://localhost:8080/actuator/health`

