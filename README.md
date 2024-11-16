# Hospital Nurse Application

## Introduction

This is a **Spring Boot-based hospital nurse application** that allows you to manage nurse records and validate nurse logins. It exposes several RESTful endpoints to perform CRUD operations on nurses and check their credentials. The purpose of this application is to provide an efficient and well-documented backend solution for nurse data management, complete with integration for automated testing and continuous integration (CI).

---

## Features

- CRUD (Create, Read, Update, Delete) operations for nurse records.
- RESTful endpoints for managing nurses and validating credentials.
- Integration with Continuous Integration (CI) to ensure code quality.
- Built using **Spring Boot** and **Java**.
- Developed with **Eclipse IDE** and **Maven** for dependency management.

---

## Prerequisites

- **Java 11** or later (JDK).
- **Maven** for project building and dependencies.
- **Eclipse IDE** for development.
- A compatible **database** (e.g., MySQL, PostgreSQL).

---

## Installation

### Clone the Repository


git clone https://github.com/your-repository/hospital-nurse-app.git


###REST Endpoints

#####Create a Nurse
######·POST /api/nurses

####Request Body Example
````
{
  "name": "Albert",
  "email": "albert.doe@gmail.com",
  "phone": "1234567890"
}````
- Response: 201 Created on success, 400 Bad Request on validation errors.

#####Get a Nurse by ID
######·GET /api/nurses/{id}

- Response: 200 OK with nurse data, 404 Not Found if not found.

#####Update a Nurse
######·PUT /api/nurses/{id}

Request Body Example
````
{
  "name": "Daniel ",
  "email": "daniel@gmail.com"
}````
- Response: 200 OK on success, 400 Bad Request for validation errors, 404 Not Found if the nurse is missing.

#####Delete a Nurse
######·DELETE /api/nurses/{id}

- Response: 200 OK on success, 404 Not Found if not found.

##Continuous Integration (CI) Setup

###Unit Tests
Tests are written using JUnit and Spring Boot Test for the critical methods in the NurseController class.

###GitHub Actions CI Pipeline
A GitHub Actions workflow can be set up to run tests automatically on each commit and pull request to the main branch, ensuring no regressions.

###Simulate Failures
Commit valid code to verify successful tests.
Introduce an error intentionally to check the CI pipeline's detection capabilities.
