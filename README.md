# Login Page Automated Tests

This project contains automated tests for the login page using Java, Gradle, JUnit 5, and Selenide.

## Prerequisites

- Java 11 or higher
- Gradle
- Chrome browser (latest version)

## Project Structure

```
├── build.gradle          # Gradle build configuration
├── src
│   └── test
│       └── java
│           └── LoginPageTest.java  # Test cases
└── application.html      # Application under test
```

## Test Cases

1. Verify login page elements are present
2. Successful login with valid credentials
3. Failed login with invalid credentials
4. Empty username validation
5. Empty password validation
6. Successful logout
7. Verify persistence of login state

## Running the Tests

1. Clone the repository
2. Navigate to the project directory
3. Run the tests using Gradle:

```bash
./gradlew test
```

## Test Reports

After running the tests, you can find the test reports in:
- HTML report: `build/reports/tests/test/index.html`
- XML report: `build/test-results/test/`

## Configuration

The tests are configured to run with the following settings:
- Browser window size: 1920x1080
- Base URL: Local file system path to application.html

You can modify these settings in the `LoginPageTest.java` file under the `setUp()` method. 