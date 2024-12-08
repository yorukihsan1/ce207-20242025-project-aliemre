@echo off
@setlocal enableextensions
@cd /d "%~dp0"

cd publictransportationscheduler-app

echo Run Web Site
echo to Exit Use CTRL+Z CTRL+C
start http://localhost:9000/
mvn site:run

echo Operation Completed!
pause