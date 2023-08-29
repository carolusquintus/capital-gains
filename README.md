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
