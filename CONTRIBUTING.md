# Backend Code Standards
This document defines coding specifications for the Spring Boot Java backend of the calculator system.

## 1. Package & Naming Convention
1. Package names: all lowercase, layered by responsibility: `com.calc.controller`, `com.calc.service`, `com.calc.entity`, `com.calc.repository`.
2. Class names: PascalCase (UpperCamelCase), e.g. `CalcController`, `CalcService`.
3. Method and variable names: camelCase (lowerCamelCase), e.g. `calculate`, `expression`.
4. Constants: UPPER_SNAKE_CASE.

## 2. Code Format
1. Use 4 spaces for indentation, no tab characters.
2. Keep blank lines between code blocks to improve readability.
3. Add final newline at the end of each source file.

## 3. Layer Responsibility Principle
- **Controller**: Receive HTTP requests, parse JSON request body, return JSON response. Do NOT implement calculation business logic.
- **Service**: Core business logic, expression calculation and database persistence.
- **Entity**: Map database table fields, store entity data.
- **Repository**: Data access interface for database CRUD operations.
Each layer has single responsibility. Do not mix cross-layer logic.

## 4. Annotation & Exception
1. Add JavaDoc comments for classes and core methods.
2. Add inline comments for complex logic (such as Reverse Polish Notation calculation).
3. Catch business exceptions and return friendly results to frontend. Do not expose raw stack trace to frontend users.

## 5. REST API Rules
1. Use noun-based resource path: `/api/calc`, `/api/history`.
2. Use standard HTTP methods: POST for calculation, GET for query history, DELETE for clear history.
3. Transfer data via JSON format.

## 6. Git Commit Rules for Backend
- `feat: add database entity with device info and time`
- `fix: fix division by zero exception`
- `refactor: rewrite expression calculation with Reverse Polish Notation`
- `docs: update api comment`
