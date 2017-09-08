# About This Test Automation Framework

This framework is basically developed and designed to automate Trujet flight booking site test cases. 

# Tools , Approaches and Technologies Used

* Gherkin
* Core Java
* Selenium WebDriver API 2.53.0
* Cucumber
* Page Object Model Design pattern
* Junit
* Maven
* Firefox 47 or lesser 



# How to Setup & Configure

  1. Install Eclipse ( any latest version will do )
  2. Install Maven plugin in ecplise if it is not present
  3. Download / Clone this automation framework to your computer local drive from below GIT URL and extract

  4. Locate pom.XML and right click and choose Run As -> Maven Build

  5. In the run configurations provide below values and click apply and run.

		Goals : generate-resources
		Profiles: <profile-to-be-used>

  6. Check whether the build is successful

  7. Now , locate TestRunner.java under com.trujettestauto.tests package , right click on the java file and choose Run As -> Junit Test

This step would launch the browser and launch the application and runs the scenarios written in <modulename>.feature files under resources/feature_files_trujet/ path.

#How to run feature specific tests

To run feature specific tests , please edit test runner class LyrisLMTest.java and update the tags = "@<feature-name>".

Exmaples :

	tags = "@Login"
	tags = "@Menu" 

To run whole Smoke suite, please edit the test runner class LyrisLMTest.java and update the tags = "@SmokeReady"