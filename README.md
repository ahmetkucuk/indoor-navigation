# SensorWeb Homework 3 and indoor-navigation

To run this java project, you need to make sure tinyos-sdk-1.0.0.jar is added into build path.

To add project, one can use following maven command

Use following to install jar in the lib folder [change path to jar] mvn install:install-file -Dfile=//home/ahmet/Documents/Developer/java/indoornavigation/lib/tinyos-sdk-1.0.0.jar -DgroupId=tinyos -DartifactId=tinyos-sdk:jar -Dversion=1.0.0

After this, all you need is running Controller.java

Be aware that your base station needs to be connected to /dev/ttyUSB0 (or change it from Listen.java)

Make sure that you give permission to /dev/ttyUSB0 e.g. chmod 666 /dev/ttyUSB0
