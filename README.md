# Loan Quoter

Command line application to obtain quotes for loans with the following characteristics:

* Amount must be between 1000 and 15000 both inclusive, and be multiple of 100
* Quotes are given for a 36 months loan
* Compound interest is used on repayments
* A file of prospective lenders is used to work out the best rate for the loan amount requested

## Getting Started

Clone the repository at:

https://github.com/carloscambon/loan-quoter

### Prerequisites

It needs to run on Java 1.8 and uses Maven for dependency management

### Release

To release a new version execute the following maven command:

mvn clean install

This will create the executable jar with the release in the target directory

### Running the application
 
 Run the jar generated in "target" folder as:
   ```
 java -jar ./target/loan-quoter-[version].jar [market file} [loan amount]
   ```
 
Sample:
 
  ```
 java -jar ./target/loan-quoter-1.0.jar ./src/test/resources/MarketTestData.csv 1000

  ```
 And you will get the following quote:
 ```
Requested amount: &1000
Rate: 7.0%
Monthly repayment: &30.78
Total payment: &1108.08
 ```
 
## Running the tests

  ```
mvn test
  ```
## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [JUNIT 5](http://junit.org/junit5/) - Testing framework