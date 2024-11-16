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


## REST Endpoints

### Create a Nurse

**POST /api/nurses**

#### Request Body Example
```json
{
  "name": "Albert",
  "email": "albert.doe@gmail.com",
  "phone": "1234567890" 
}


