# capital-gains

## Technical & architectural decisions

One of the main ideas when starting this challenge was to keep business logic separated and isolated.\
Thats why I decided to use Hexagonal Architecture and Domain Driven Design.\

1. Infrastructure (Framework) hexagon:\
    Provides the outside world interface. For this challenge in particular is exposed through a cli interface (adapter).\
    If necessary we can add output adapters (DB, cache, file) or add/replace input adapters (gRPC, REST, SOAP).
2. Application hexagon:\
    It is where business rules are processed and orchestrated, serving as a middleman to work between Infrastructure and Domain hexagon.
3. Domain hexagon:\
    We define the value objects (immutable) and entities (not used for this challenge) for describing the capital gains problems.\
    For the service package in particular is where business logic is applied.\
    And for this challenge I decided to apply Pipes and Filters pattern to process json input into different sequences to process every ouput, from previous step.

## Libraries & frameworks used

In order to achieve challenge expected goals:

1. Libraries:\
    `jackson`: To serialize and deserialize input and outputs from cli application.\
    `vavr`: To manage exception handling through `Try.of()` in order to write simple code.
2. Frameworks:\
    `spock`: To test code and ease coverage.

## Instructions to compile & run

Open your Terminal and follow next steps.
1. Download SDKMan.
```shell
curl -s "https://get.sdkman.io" | bash
```
![SDKMan install](screenshots/23-ago-2023_21-49-13.png)

2. Install java jdk 17.
```shell
sdk install java 17.0.8-zulu
```
![jdk install](screenshots/23-ago-2023_21-51-27.png)

3. Install Gradle.
```shell
sdk install gradle 7.6.1
```
![jdk install](screenshots/23-ago-2023_21-52-44.png)

4. Move to project location.
```shell
cd capital-gains
```
![move to project](screenshots/23-ago-2023_22-22-07.png)

5. Clean & build `capital-gains` project.
```shell
gradle clean build
```
Or
```shell
./gradlew clean build
```
![gradle](screenshots/23-ago-2023_22-30-44.png)
![gradlew](screenshots/23-ago-2023_22-25-25.png)

6. Give execute perms to `capital-gains` bash script for your user.
```shell
chmod u+x capital-gains
```
![chmod](screenshots/23-ago-2023_22-27-12.png)

7. Execute the cli application.
```shell
./capital-gains < input3.txt
```
![chmod](screenshots/23-ago-2023_22-27-43.png)

## Instruction to run test

This project uses `JaCoCo`(Java Code Coverage) plugin to generate html test reports.

1. Run and generate test reports.
```shell
./gradlew test
```
![gradlew test](screenshots/29-ago-2023_11-26-35.png)

2. Go to and locate `index.html` files and open them in your browser.
    1. For Test Summary report go to.
    ```shell
   capital-gains/build/reports/tests/test/index.html
    ```
   ![test-summary index.html](screenshots/29-ago-2023_11-27-39.png)
   ![test-summary index.html opened](screenshots/29-ago-2023_11-28-16.png)

    2. For JaCoCo report go to.
    ```shell
   capital-gains/build/reports/jacoco/test/html/index.html
    ```
   ![jacoco index.html](screenshots/29-ago-2023_11-28-59.png)
   ![jacoco index.html opened](screenshots/29-ago-2023_11-29-29.png)
