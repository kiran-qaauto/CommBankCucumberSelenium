# Cucumber-Selenium-Java-Automation-Framework

Built from scratch Web Test Automation Framework with Java, Cucumber and Selenium Web Driver.
Supports cross-browser testing and provides a test report with results.

Prerequisites:
---------------
*	Java jdk-1.8 or higher
*	Apache Maven 3 or higher


Browsers supported:
---------------
*   Google Chrome (default)


Execution:
---------------
*	Go to project root
*   Run following commands:
```sh
$ mvn clean install
$ mvn clean test
```


Reporting:
---------------
*  The framework produce Spark.html report. It resides in the 'reports/htmlReports <currentdate_and_time>/SparkReport/Spark.html' folder. This reports gives the link to all the different component of the Cucumber reports. On clicking these will display detailed descriptions of execution.
