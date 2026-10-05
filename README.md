# RestAssuredgithubactions

[![Rest Assured Automation Tests](https://github.com/anithaswam95-beep/RestAssuredgithubactions/actions/workflows/restAssured.yml/badge.svg)](https://github.com/anithaswam95-beep/RestAssuredgithubactions/actions/workflows/restAssured.yml)
![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)
![Rest Assured](https://img.shields.io/badge/Rest%20Assured-5.5.6-brightgreen)
![TestNG](https://img.shields.io/badge/TestNG-7.11.0-E76F00)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?logo=apachemaven&logoColor=white)

REST API test automation with **Java + Rest Assured + TestNG**, executed on every
push by **GitHub Actions**. The suite covers the full CRUD lifecycle plus token
auth against the public [restful-booker](https://restful-booker.herokuapp.com)
demo API.

## Table of contents
- [What it tests](#what-it-tests)
- [Tech stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Setup and run](#setup-and-run)
- [Example test](#example-test)
- [Continuous Integration](#continuous-integration)
- [Project structure](#project-structure)

## What it tests

| Test class     | HTTP | What it verifies |
|----------------|------|------------------|
| `GetMethod`    | GET  | Booking list returns `200` + body logged |
| `PostMethod`   | POST | Creates a booking; asserts status, `Content-Type`, non-empty body, `bookingid > 0`, `lastname`, `checkin` date |
| `PutMethod`    | PUT  | Full update of a booking |
| `PatchMethod`  | PATCH| Partial update of a booking |
| `DeleteMethod` | DELETE | Deletes a booking by id |
| `AuthMethod`   | POST `/auth` | Exchanges `admin`/`password123` for a bearer token (helper used by write ops) |
| `CreateID`     | —    | Creates a booking and captures its id for follow-up tests |

All tests live in one TestNG suite: [`src/test/resources/testng.xml`](src/test/resources/testng.xml).

## Tech stack

| Tool          | Version | Purpose                    |
|---------------|---------|----------------------------|
| Java          | 21      | Language (Temurin in CI)   |
| Rest Assured  | 5.5.6   | HTTP client + assertions   |
| TestNG        | 7.11.0  | Test framework / suite     |
| Maven         | 3.9+    | Build + Surefire runner    |
| GitHub Actions| —       | CI on every push to master |

## Prerequisites

- **JDK 21** — `java -version`
- **Maven 3.9+** — `mvn -v`
- Internet access (tests call `restful-booker.herokuapp.com`)

## Setup and run

```bash
git clone https://github.com/anithaswam95-beep/RestAssuredgithubactions.git
cd RestAssuredgithubactions

# Run the whole TestNG suite (wired into Surefire via pom.xml)
mvn clean test
```

Real output:

```
[INFO] Building com.RA 0.0.1-SNAPSHOT
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 9.895 s - in TestSuite
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

Other useful commands:

```bash
mvn test -Dtest=GetMethod          # single test class
mvn clean test -DtrimStackTrace=false   # exactly what CI runs
```

HTML report after a run: `target/surefire-reports/` (or `test-output/` when
running the suite from an IDE).

## Example test

```java
public class GetMethod {
    @Test
    public void getUsers() {
        given()                                    // Precondition
            .baseUri("https://restful-booker.herokuapp.com/booking")
        .when()                                    // Action
            .get()
        .then()                                    // Assertion
            .statusCode(200)
            .log().body();
    }
}
```

## Continuous Integration

Workflow: [`.github/workflows/restAssured.yml`](.github/workflows/restAssured.yml)
(**Rest Assured Automation Tests** — badge above = latest run)

Triggers: every **push to `master`**, PRs to `main`, and manual `workflow_dispatch`.

| Step | Details |
|------|---------|
| Runner | `ubuntu-latest` |
| Java | 21 (Temurin) with Maven cache |
| Test command | `mvn clean test -DtrimStackTrace=false` |
| Artifact | `surefire-report` uploaded **always** (HTML + XML reports) |

## Project structure

```
RestAssuredgithubactions/
├── .github/workflows/restAssured.yml   # CI pipeline
├── src/test/java/automation/Rest/      # test classes
│   ├── GetMethod.java   ├── PostMethod.java   ├── PutMethod.java
│   ├── PatchMethod.java ├── DeleteMethod.java
│   ├── AuthMethod.java  └── CreateID.java
├── src/test/resources/testng.xml       # TestNG suite (run by Surefire)
├── pom.xml                             # deps + Surefire suite wiring
└── test-output/                        # IDE-generated TestNG reports
```
