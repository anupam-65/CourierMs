@echo off

REM Get the current directory of the batch file
set BASEDIR=%~dp0

REM Set the relative path for the JavaFX libraries
set LIB_DIR=%BASEDIR%CourierMS-main\lib\javafx-sdk-21.0.1\lib

cd out

cd artifacts

cd CourierMS_jar

REM Run the Java command with the relative path
java --module-path "%LIB_DIR%" --add-modules javafx.controls,javafx.fxml -jar "%BASEDIR%\out\artifacts\CourierMS_jar\CourierMS.jar"

pause