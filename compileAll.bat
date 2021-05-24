@ECHO off

::compiles "calendar related java package files
cd src\calendar
javac -cp ..\..\bin\ -d ..\..\bin\ *.java
echo "calendar files compilation done."

::compiles data related java package files
cd ..\data
javac -cp ..\..\bin\ -d ..\..\bin\ *.java
echo "data files compilation done."

::compiles interface related java package files
cd ..\ui
javac -cp ..\..\bin\ -d ..\..\bin\ *.java
echo "ui files compilation done."

::compiles unitTest related java package files
cd ..\unitTest
javac -cp ..\..\bin\ -d ..\..\bin\ *.java
echo "unitTest files compilation done."

::compiles root level java package files
cd ..
javac -cp ..\bin\ -d ..\bin\ *.java
echo "root files compilation done."


PAUSE