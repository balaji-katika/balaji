#!/bin/bash
#sudo java -jar ./target/kyc-0.9.jar
sudo java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=8765 -classpath ./target/kyc-0.9.jar:./target/dependency-jars/jnetpcap-1.3.b3.jar com.vmware.radio14.App
