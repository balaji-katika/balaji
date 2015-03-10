#!/bin/bash
#sudo java -jar ./target/kyc-0.9.jar
sudo java -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -classpath ./target/kyc-0.9.jar:./target/dependency-jars/jnetpcap-1.3.b3.jar com.vmware.radio14.App
