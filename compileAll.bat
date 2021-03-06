@ECHO off

::compiles "calendar related java package files
cd src\calendar
javac -Xlint:all -cp ..\..\class\ -d ..\..\class\ *.java
echo "calendar files compilation done."

::compiles data related java package files
cd ..\data
javac -Xlint:all -cp ..\..\class\ -d ..\..\class\ *.java
echo "data files compilation done."

::compiles interface related java package files
cd ..\ui
javac -Xlint:all -cp ..\..\class\ -d ..\..\class\ *.java
echo "ui files compilation done."

::compiles unitTest related java package files
cd ..\unitTest
javac -Xlint:all -cp ..\..\class\ -d ..\..\class\ *.java
echo "unitTest files compilation done."

::compiles root level java package files
cd ..
javac -Xlint:all -cp ..\class\ -d ..\class\ *.java
echo "root files compilation done."


PAUSE