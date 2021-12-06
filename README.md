Spring Boot application that is used to find long running log events.
=========================

Requirements
------------
* [Java Platform (JDK) 8+](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

Quick start
-----------

Clone this project
Run in console `./mvnw install`, `java -jar target/application-0.0.1-SNAPSHOT.jar ${testfile}`
Check `eventdb` and `eventdb.log` to verify results are as expected

Testing Instructions
--------------------
The program should:
Take the input file path as input argument. Use following example as logfile.txt test file. Example:
```
{"id":"scsmbstgra", "state":"STARTED", "type":"APPLICATION_LOG","host":"12345", "timestamp":1491377495212}
{"id":"scsmbstgrb", "state":"STARTED", "timestamp":1491377495213}
{"id":"scsmbstgrc", "state":"FINISHED", "timestamp":1491377495218}
{"id":"scsmbstgra", "state":"FINISHED", "type":"APPLICATION_LOG","host":"12345", "timestamp":1491377495217}
{"id":"scsmbstgrc", "state":"STARTED", "timestamp":1491377495210}
{"id":"scsmbstgrb", "state":"FINISHED", "timestamp":1491377495216}
...

Flag any long events that take longer than 4ms with a column in the database called "alert"
Write found event details to file-based HSQLDB eventdb in the working folder
The application should create a new table if necessary and enter the following values: 
a. Event id 
b. Event duration
c. Type and Host if applicable 
d. "alert" set to True if applicable

In the example above,
The longest event is scsmbstgrc (1491377495218 - 1491377495210 = 8ms)
