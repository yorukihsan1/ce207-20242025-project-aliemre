@echo off

:: Enable necessary extensions
@setlocal enableextensions

echo Get the current directory
set "currentDir=%CD%"

echo Change the current working directory to the script directory
@cd /d "%~dp0"

echo Delete the "docs" folder and its contents
rd /S /Q "publictransportationscheduler-app\target\site\coverxygen"
rd /S /Q "publictransportationscheduler-app\target\site\coveragereport"
rd /S /Q "publictransportationscheduler-app\target\site\doxygen"

echo Delete and Create the "release" folder and its contents
rd /S /Q "release"
mkdir release

echo Change directory to publictransportationscheduler-app
cd publictransportationscheduler-app

echo Perform Maven clean, test, and packaging
call mvn clean test package

echo Return to the previous directory
cd ..

echo Create Required Folders coverxygen/coveragereport/doxygen
cd publictransportationscheduler-app
mkdir target
cd target
mkdir site
cd site
mkdir coverxygen
mkdir coveragereport
mkdir doxygen
cd ..
cd ..
cd ..

echo Generate Doxygen HTML and XML Documentation
call doxygen Doxyfile

echo Change directory to publictransportationscheduler-app
cd publictransportationscheduler-app

echo Generate ReportGenerator HTML Report
call reportgenerator "-reports:target\site\jacoco\jacoco.xml" "-sourcedirs:src\main\java" "-targetdir:target\site\coveragereport" -reporttypes:Html

echo Generate ReportGenerator Badges
call reportgenerator "-reports:target\site\jacoco\jacoco.xml" "-sourcedirs:src\main\java" "-targetdir:target\site\coveragereport" -reporttypes:Badges

echo Display information about the binary file
echo Our Binary is a Single Jar With Dependencies. You Do Not Need to Compress It.

echo Return to the previous directory
cd ..

echo Run Coverxygen
call python -m coverxygen --xml-dir ./publictransportationscheduler-app/target/site/doxygen/xml --src-dir ./ --format lcov --output ./publictransportationscheduler-app/target/site/coverxygen/lcov.info --prefix %currentDir%/publictransportationscheduler-app/

echo Run lcov genhtml
call perl C:\ProgramData\chocolatey\lib\lcov\tools\bin\genhtml --legend --title "Documentation Coverage Report" ./publictransportationscheduler-app/target/site/coverxygen/lcov.info -o publictransportationscheduler-app/target/site/coverxygen

echo Copy badge files to the "assets" directory
call copy "publictransportationscheduler-app\target\site\coveragereport\badge_combined.svg" "assets\badge_combined.svg"
call copy "publictransportationscheduler-app\target\site\coveragereport\badge_combined.svg" "assets\badge_combined.svg"
call copy "publictransportationscheduler-app\target\site\coveragereport\badge_branchcoverage.svg" "assets\badge_branchcoverage.svg"
call copy "publictransportationscheduler-app\target\site\coveragereport\badge_linecoverage.svg" "assets\badge_linecoverage.svg"
call copy "publictransportationscheduler-app\target\site\coveragereport\badge_methodcoverage.svg" "assets\badge_methodcoverage.svg"

call copy "assets\rteu_logo.jpg" "publictransportationscheduler-app\src\site\resources\images\rteu_logo.jpg"

echo Copy the "assets" folder and its contents to "maven site images" recursively
call robocopy assets "publictransportationscheduler-app\src\site\resources\assets" /E

echo Copy the "README.md" file to "publictransportationscheduler-app\src\site\markdown\readme.md"
call copy README.md "publictransportationscheduler-app\src\site\markdown\readme.md"

cd publictransportationscheduler-app
echo Perform Maven site generation
call mvn site
cd ..

echo Package Output Jar Files
tar -czvf release\application-binary.tar.gz -C publictransportationscheduler-app\target '*.jar'

echo Package Jacoco Test Coverage Report (Optional)
call tar -czvf release\test-jacoco-report.tar.gz -C publictransportationscheduler-app\target\site\jacoco .

echo Package ReportGenerator Test Coverage Report
call tar -czvf release\test-coverage-report.tar.gz -C publictransportationscheduler-app\target\site\coveragereport .

echo Package Code Documentation
call tar -czvf release\application-documentation.tar.gz -C publictransportationscheduler-app\target\site\doxygen .

echo Package Documentation Coverage
call tar -czvf release\doc-coverage-report.tar.gz -C publictransportationscheduler-app\target\site\coverxygen .

echo Package Product Site
call tar -czvf release\application-site.tar.gz -C publictransportationscheduler-app\target\site .

echo ....................
echo Operation Completed!
echo ....................
pause
