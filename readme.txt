This version of the code has limit or no documentation at present. I will be updating it as time permits.

----------------------------------------
---- How to run the BBF CharGen program.
----------------------------------------

This requires Java 1.8. From your command line, type "java -version". You should have version 1.8 or greater, as shown below.

$ java -version
java version "1.8.0_45"
Java(TM) SE Runtime Environment (build 1.8.0_45-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.45-b02, mixed mode)

There are two Jar files: BBFCharGen.jar pdfbox-app-1.8.10.jar

Within the directory containing both Jar files, type the command below, which will run the BBF CharGen.

java -cp "BBFCharGen.jar;pdfbox-app-1.8.10.jar" org.kuroneko.bbf.BBFCharGen

If you are on Linux running OpenJDK, then install the openjfx package.

----------------------------------------
---- Change History
----------------------------------------

BBFCharGen 2018-03-18-1118

Update to project files only (for building under OpenJDK) and attempting a release of the JAR files.

BBFCharGen 2015-10-03-1730

Fixed issue where the Descriptor Dialog did not save manually entered descriptors.

BBFCharGen 2015-10-03-0900

Fixed issue where the number of selections allowed per level for a primary skill was being ignored. (Such as where Spellcasters with that as primary skill get 2 instead of 1 spells.)
