# Reto Tecnico Sofka

This test auotmation framework is designed to perform functional testing on web and api applications using SerenityBDD,RestAssured,Junit,Cucumber

# Key Features

* **Cross-Browser Support**: Chrome, Firefox, Edge.

* **Gradle Integration** for dependency management and test execution.

* **Detailed Reporting** with SerenityBDD Reports.

* **Screenplay** structure for maintainability and scalability.

# Prerequisites

|                                                                               **IntelliJ**                                                                                |                                                             **JDK 21**                                                              |                                                       **Maven 3.9**                                                       |
|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------:|
| [<img width="50" height="50" src="https://cdn.iconscout.com/icon/free/png-128/intellij-idea-569199.png">](https://www.jetbrains.com/es-es/idea/download/#section=windows) | [<img height="60" src="https://imagedelivery.net/-IT6z0z0Ec5yEiYj3DvVjg/4a05ca76db1fa48640b21658f2659ac656492b82/public">](https://learn.microsoft.com/es-es/java/openjdk/download#openjdk-21) | [<img height="50" src="https://maven.apache.org/images/maven-logo-black-on-white.png">](https://maven.apache.org/download.cgi) |

# Instalation

1. **Clone the repository**
```bash
 git clone https://github.com/LuisCastilloSofka/reto-tecnico-sofka.git
 cd reto-tecnico-sofka
```
2. **Install Dependencies** Run the following command to install the required dependencies:
```bash
 gradle clean build
```

# Project Structure

```plaintext
src
├── main
    ├── java
    │   ├── questions  # Questions in screenplay design pattern
    │   ├── tasks      # Tasks in screenplay design pattern
    │   ├── ui         # Classes representing UI elements or components
    │   ├── utils      # Utility classes for the tests
├── test
    ├── java
    │   ├── hooks      # Hooks for setting up @Before and @After methods
    │   ├── runners    # Classes to execute the tests
    │   ├── steps      # Step definitions for Cucumber
    ├── resources
        ├── schemas     # Schema definition for API responses
        └── features    # Gherkin feature files (.feature)
```

# Running the Scenarios
1.**Run with Gradle**

To run all scenarios by command line
```bash
  ./gradlew clean test --tests "com.automationtest.runners.WebRunnerTest"
```

To run scenarios with a specific tag
```bash
  ./gradlew clean test --tests -Dcucumber.filter.tags="@web" automationtest.runners.WebRunnerTest
```

# Aditional Configurations

**Changing the Browser**

The default browser or browsers can be configured in the `testng.xml` file under the parameter section:

```xml
<parameter name="browser" value="chrome"/>
```

You can change the browser to firefox or edge as needed.

**Parallel Executions**

Paralell execution is allowed and can be easily configured in `testng.xml` in the `thread-count` section

```xml
<suite name="Cross-Browser Testing Suite" parallel="tests" thread-count="3">
```

# Adding new Scenarios

1. Create a `.feature` file under `src/test/resources/features`.
2. Define the steps in a class under `src/test/java/stepdefinitions`
3. Create any specific tasks and questions of actor behavior under 'src/tests/java/tasks'
5. Define a UIs page objects type under `src/test/java/ui`


# Contact

For more information or help,contact:

* Luis Castillo
* Email : luchocd27@hotmail.com
* Phone: +573214539591