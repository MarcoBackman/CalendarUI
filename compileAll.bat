@ECHO off

::compiles root level java files
cd src
javac -cp ..\bin\ -d ..\bin\ *.java
echo "root files compilation done."

::compiles interface related java files
cd ui
javac -cp ..\..\bin\ -d ..\..\bin\ *.java
echo "ui files compilation done."

PAUSE