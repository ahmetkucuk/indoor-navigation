README for OneHopDG
Author/Contact: akucuk1@student.gsu.edu

Description:
This a simple application written for GSU SensorWeb Course Homework

Tools:

Known bugs/limitations:

None.

How to use:

Prequistes:
	Make sure you have one node that is running default BaseStation
	app that is provided with tinyos distribution

1. run comple.sh to generate necessary binary files.

2. Use telosb installation
	make telosb reinstall bsl,/dev/ttyUSB2 # Linux example (pick appropriate USB)

3. Compile Java SDK with provided Listen.java file
	Replace file attached to this homework "java/Listen.java" with 
	"tinyos-main/support/sdk/java/net/tinyos/tools".
	
	Compile using make command

	Start Listener:
		export MOTECOM=serial@/dev/ttyUSB0:telos
		java net.tinyos.tools.Listen
5. Listen.java should print light intensity value coming from AkucukOneHopDG through BaseStation


Notes:
An image of sensors (Left: Base station, Right: OneHopDG) is attached with homework.

Test:
I checked values coming from sensor using flash light, covering sensor, and regular light.
I looked like it is giving correct results.
