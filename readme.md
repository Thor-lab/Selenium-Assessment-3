# BDD: Cucumber Selenium Webdriver Java Test Automation Framework

This project is a test automation framework, which provides structured and standard way of creating automated test scripts.  
The framework incorporates design principle of BDD (Behaviour driven development) which promotes writing acceptance tests by describing behaviour of application under test in simple english language from the perspective of its stakeholders. Having test written in Natural language helps the Project Team (Product Owners, Business Analysts, Development and QA team) to understand and track the requirements  

Supports Custom Page Object model which represents the screens of AUT as a series of objects and encapsulates the fields represented by a page which ultimately avoids duplication and improves code maintainability and readability.
Sonar Continuous Code Quality Management: - conventions (Checkstyle), bad practices (PMD) and potential bugs (FindBugs).


## Tools & libraries
The test automation framework is comprised of following tools and libraries

- Cucumber-JVM:- BDD Framework
- Custom Page Object Pattern and utility functions
- Selenium WebDriver: - Browser automation framework 
- JAVA: - Programming language
- TestNg: - TestNg Java testing framework
- Maven: - Build tool
- Git OR SVN: - Version Control
- Github or Local Git Server: - Git repository hosted server
- Intellij Or Eclipse: - Integrated Development Environment
- AssertJ: - Matcher's
- Loggers: - Simple Logging Facade for Java
- SonarQube (optional): - Code Quality and Code Coverage


## Test Automation framework support
Dolly Padyar  
Test Analyst  
dolly1994b@gmail.com

## Machine Configuration
Configure Ubuntu / Windows and setup:
- Java 8
- Git / SVN
- Maven

## Get the latest Source Code
Open Terminal or command line cd to the desired folder where the test automation source code needs to be checkout  

Run command `git clone https://github.com/Thor-lab/Selenium-Assessment-3.git`  

This will download the latest template source code

## IDE Configuration

### Eclipse plugins
Configure and Install Following Plugins
Help>>Install new software   
- Cucumber for Java http://cucumber.github.com/cucumber-eclipse/update-site  
- TestNg  
- Git Integration  
- SonarQube (optional)

Plugin configuration for Cucumber Feature Open Run Configurations Select Cucumber Feature and create one new configuration Project: *automationFramework* Feature: *src/automationFramework/src/test/resources/features* Glue: *com.gk.test.step_definitions* Reports: *monochrome, pretty*

File >> Setting >>
Search for Annotation Processing
(Java Compiler ... Annotation Processing>> Enable the check box

### Import Project into Eclipse
File>Import>Maven>Existing Maven Projects>Next> Browse to automationFramework Ensure pom.xml is found Finish

open terminal cd to test root directory run `mvn clean eclipse:eclipse`

### Compile Build or Run Tests
Command Line

cd to root ot automationFramework project directory

#### To clean and compile the build
`mvn clean install -DskipTests`

#### To run all tests parallel
`mvn clean install`

OR

`mvn clean install -P dev`

> Note -P dev is default profile hence doesn't need to be specified for every run

#### To run a single test with tags
`mvn clean install -Dcucumber.options="--tags @gui --tags ~@api" -P single`

> Note `~` before tag means this specific tag will not run


## Getting Started
### Feature Files
These files contains the acceptance criteria which are written in Gherkin Language and contains various scenarios.
The feature files are tagged with "@tagname" to group common feature files

File Extension: *.feature
Location: `/src/test/resources/features`


### Page Objects
PageObjects are used to store the WebElements for a Web Page. A good practice is to have a separate class for every single WebPage. To avoid duplication for multiple pages which have common web page elements a Parent class can be created and the child class can then inherit.
Every Page class extends "PageObject.class" to make use of the WebDriver Object and utility functions.
In case of Parent and Child Class, Parent class extends PageObject class and child class extends Parent class


### Step Definitions
Every steps defined in Feature file needs to be implemented in Step Definitions Class

Location: /src/test/java/stepdefs:
File Conventions:Every Class file ends with Steps.class (HomePageSteps.java)

Future Scops:
Screenshots of the failed testcase in the HTML report at the point of failure in the application would be added