# API Test Automation IDE

This project is built for Web API functional testing of Calculate Premium (Data Driven), Validation Rules (Data Driven), Benefit Definition, Occupations, Products and Benefits in Environments DEV, FST and QA.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

#### You Need To
  - download and install <a href="https://smartbear.com/product/ready-api/free-trial"> <img src="https://smartbear.com/SmartBear/media/images/Logos/Products/Ready_API-horiz-blue.svg?ext=.svg" width=120> </a>.*
  - clone this repository to your local directory.
  - import the project into your ReadyAPI.
  - go to Preferences -> Proxy, and input your Username and Password (e.g. Sovereign\DEV.userName).
  - click 'Start' with magic:)

## Project Overview 

### Test Suites
The project consists of three main test suites, Pricing (Calculate Premium), Validation Rules and Products & Benefits. There is also a Smoke test suite following for quick kickoffs.

### File Layouts
The file structure is basically made up of forms in xlsx, groovy and xml. All Source Data used for test cases are stored in xlsx in Data folder. Templates that generates test results in xlsx are in the folder Results. All test automation scripts were written in groovy form and exist in different folders in Scripts. The file Quote\_Integration\_API.xml is the project file ReadyAPI loads. There are not binary files in this project.

### How It Works
The project runs test steps sequentially for each test case.

#### Request Tokens
Before you access to the API endpoints, you need to request an authorization (or Token) and visit APIs with the Token throughout. At the time of writing, each Token lasts for 60 minutes, and no need to renew it within the time. Visit [IAM OAuth cheat sheet](http://webappspsovzone.asbbank.co.nz/sites/Business_Technology/architecture/Architecture%20Wiki/IAM%20OAuth%20cheat%20sheet.aspx) for how to get a Token and more info.

#### Get Test Data
The test suites Calculate Premium and Validation Rules are data driven, which read test cases data  before sending request payloads. Test data is provided in xlsx form prior new benefits coming available. Test data will be iterated by rows all the way to the end of file.

#### Access To Endpoints
As test data are provided in the way could differ from API required, some formatting stuff may be needed in advance. To access to the endpoints, HTTPS request head needs to obtain tokens for security. Both request and response payloads are in Json via REST.

#### Compare The Results
12-cent tolerance applies to each benefit/sub-benefits, and pass criteria does NOT take total premiums into consideration.

#### Iterations
Test case iterates steps (get test data, access to endpoints and compare the results) until finish all test data, and writes test results into xlsx files and highlights the rows failed during the test.
 
## Running On Jenkins
For the reason of CI/CD, the project is able to run on Jenkins in Full Regression and Smoke mode. Parameter EnvJ=Jenkins will be passed into for launch in Jenkins, and when SMOKE=YES, Jenkins will run Smoke (50 rows for each benefits) only.

Schedule is set on Jenkins to run the tasks automatically on a regular basis.

### API Definitions

* [Calculate Premium​](http://webappspsovzone.asbbank.co.nz/sites/Business_Technology/architecture/Architecture%20Wiki/OAG%20-%20Calculate%20Premium%20API.aspx)
* [Benefit Definition](http://webappspsovzone.asbbank.co.nz/sites/Business_Technology/architecture/Architecture%20Wiki/OAG%20-%20Benefit%20Definition%20API.aspx)
* [Occupations​](http://webappspsovzone.asbbank.co.nz/sites/Business_Technology/architecture/Architecture%20Wiki/OAG%20-%20Occupations%20API.aspx)
* [Products Benefits​](http://webappspsovzone.asbbank.co.nz/sites/Business_Technology/architecture/Architecture%20Wiki/OAG%20-%20Products%20Benefits%20API.aspx)

### Test Cases
* [API Test Cases Folder](http://webappspsovzone.asbbank.co.nz/sites/pd/6889/Shared%20Documents/Forms/AllItems.aspx?RootFolder=%2fsites%2fpd%2f6889%2fShared%20Documents%2fSupporting%20Docs%2fFull%20Quote%2fProduct%20specifications%2fTest%20cases&FolderCTID=0x01200040F59A3AC2DF6444926681048E80EC3B)

***
*You may need a licence to run for a long term as ReadyAPI runs for charges.