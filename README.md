# Quick_Calc


## Project Description:

The Quick_Calc application is a simple desktop calculator application created with Java, using the Swing framework. Quick_Calc allows the user to input two signed integer numbers with length of up to 18 digits into the input fields, select which one of the four basic mathematical operations (addition, subtraction, multiplication, division) they wish to perform, and calculate the result of the desired calculation by pressing the **"Calculate"** button. The integer numbers are entered into the input fields through the user's physical keyboard, and can be edited after they have been entered. The desired mathematical operation is selected by pressing one of the four **"Add"**, **"Sub"**, **"Mul"**, and **"Div"** buttons. Results of addition, subtraction and multiplication are displayed as integers, while those of division are presented as decimals rounded to 10 decimal places, where applicable. Additionally, it is possible to clear the already entered numbers, together with the calculation result (if the calculation has already been performed) by using the corresponding **"Clear"** button in the calculator's UI. All the arithmetic operations are performed by the `CalculatorLogic` class, which utilizes Java's `BigInteger` and `BigDecimal` datatypes, so that the calculations could be performed on large integer values.

---

## Setup Instructions:

### Prerequisites

- **Java 11** or later 
- **Maven 3.6** or later 

### If You Need to Install Java

If you don't have Java installed, the recommended distribution is the **Oracle Java SE Development Kit (JDK)**, downloadable directly from Oracle's official website.

#### 1. Download the installer

Go to the Oracle JDK downloads [page](https://www.oracle.com/java/technologies/downloads/), select **Java SE 25** (or any version after Java SE 11) from the version tabs, then download the installer for your operating system:

| OS | Recommended method |
|---|---|
| **Windows** | `.exe` installer  |
| **Linux** | `.tar.gz` archive |
| **macOS** | `.dmg` installer |

#### 2. Run the installer

- **Windows / macOS:** Run the downloaded installer and follow the on-screen prompts. It will install the JDK and set up the `PATH` and `JAVA_HOME` environment variables automatically.
- **Linux:** Extract the archive and set the variables manually.

#### 3. Verify the installation

Run the following command to verify the installation:

```
java -version
```

The output should be similar to:

```
java version "xx.0.x" 20xx-xx-xx
Java(TM) SE Runtime Environment (build xx.0.x+x-LTS)
Java HotSpot(TM) 64-Bit Server VM (build xx.0.x+x-LTS, mixed mode)
```

### If You Need to Install Maven

#### 1. Download the installer or perform a direct installation

| OS | Recommended method |
|---|---|
| **Windows** | Download from [maven.apache.org](https://maven.apache.org/download.cgi), unzip, and add `bin/` to your `PATH` manually |
| **macOS** | `brew install maven` |
| **Ubuntu / Debian** | `sudo apt install maven` |
| **Fedora** | `sudo dnf install maven` |

#### 2. Verify the installation

Run the following command to verify the installation:

```
mvn -version
```

The output should be similar to:

```
Apache Maven x.x.xx (xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx)
Maven home: path\to\maven\installation
Java version: xx.0.x, vendor: Oracle Corporation, runtime: path\to\java\installation
Default locale: en_US, platform encoding: UTF-8
OS name: "OS_name", version: "xx.x", arch: "architecture", family: "OS_family"
```

### Installing Dependencies

Maven manages all dependencies automatically. No manual downloads of `.jar` are needed. To automatically download the declared dependencies, from the project root directory (where `pom.xml` is located), run the following:

```
mvn compile
```

### Running the Application

In order to run the application and open the calculator window on your desktop, run the following commands after the dependencies have been downloaded:

```
mvn compile
java -cp target/classes Quick_Calc
```

---

## How to Run Tests:

To compile the source and test code, as well as to execute the entire test suite, from the project root directory run the following command:

```
mvn clean test
```

---

## Testing Framework Research

The two frameworks initially considered for running unit and integration tests were **JUnit 5** and **TestNG**. The said two frameworks, despite both being higly capable at handling module and integration tests, differ in their feature sets. 

**JUnit 5** is the more widespread option when it comes to Java testing. JUnit's main strengths are its architecture, which splits the framework into the API, engine, and launcher components, as well as a large number of annotations, such as `@Nested` or `@DisplayName`. Furthermore, JUnit has great support for all the popular build tools, such as Maven or Gradle, as well as IDEs, like IntelliJ or VSCode, meaning that the configuration process can go very smoothly. Additionally, its `assertThrows` method makes the process of testing exceptions quite simple and clean.

**TestNG** was initially created so as to fill in the functionality gaps present in the earlier versions of JUnit, and many of the features introduced by it were later adopted by the broader Java ecosystem. TestNG is mostly suitable for large test suites, as it offers more built-in flexibility. At the same time, though, its configuration is more verbose and its XML files add overhead for smaller project and, while its integration with existing build tools and IDEs is less seamless when compared to JUnit.

For this project, **JUnit 5 was chosen over TestNG** due to the fact that the test suite is quite simple and straightforward, consisting of isolated unit tests and only a few integration tests. Using JUnit's `@Nested` classes allows to keep the file structure well-organized, without excessive XML configuration, while the `assertThrows` method makes it possible to visually separate the division by zero from other cases and keep it readable. In the same conditions, TestNG's additional flexibility would remain unused and would add unnecessary complexity. As such, JUnit 5 was chosen for this project due to its suitability for the project's unique characteristics.

---