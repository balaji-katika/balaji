Dev Env Setup :
--------------
* Point your maven to the 3.1.1 version in toolchain as below
alias mvn='build/toolchain/noarch/apache-maven-3.1.1/bin/mvn'
Place this in your .bashrc file

Eclipse Settings:
-----------------
* Add <proj-dir>/dev/CodeFormat.xml to the java code Window->Preferences->Java->Code Style->Formatter->Import
* Right Click on project. Configure Build path->Libraries->Maven Dependencies->jnetpcap*.jar->javadoc location->Edit. 
  Chose <proj-dir>/dev/jnetpcap*.zip as the location

Build Instructions:
------------------
Run build.sh located in the project root folder

Install Instructions:
-------------------
* Make sure libpcap is installed in the machine
* Install libjnetpcap. Alternately you can do the below step
* Copy the file third-party/libjnetpcap.so.1.3.b3 to /usr/lib on your machine
  Create a symbolic link to /usr/lib/libjnetpcap.so.1.3.b3 called
  libjnetpcap.so and place it in /usr/lib


Run Instructions:
----------------
* You must run this program as root as only root user will be able to query
  data about interfaces
* Run build-run.sh if you want to build and run the application
  Execute run.sh if you only want to run an already built application
