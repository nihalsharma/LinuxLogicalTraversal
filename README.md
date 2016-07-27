### Problem statement ###

Task is to create an application which does a Linux Path Traversal. 
You would be implementing various Linux commands, as follows.

* cd path
* mkdir path
* rm path
* pwd

There would be an additional command, for application purpose, which is

* session clear


### How do I get set up? ###

* install maven
* mvn clean compile assembly:single - to get the jar
* the jar file is in the target folder

### How do I run the jar? ###

* java -jar target/Playment-1.0-SNAPSHOT-jar-with-dependencies.jar
* java -jar target/Playment-1.0-SNAPSHOT-jar-with-dependencies.jar -f filename_with_total_path
