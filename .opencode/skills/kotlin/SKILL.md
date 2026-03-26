---
name: kotlin
description: This document defines the coding standards and architectural patterns for this project. Use these rules when generating or refactoring code.
license: MIT
compatibility: opencode
metadata:
    audience: maintainers
    workflow: kotlin
---

# Project Skills: Kotlin + Ktor Microservice
This document defines the coding standards and architectural patterns for this project. Use these rules when generating or refactoring code.

## 1. Tech Stack & Preferences
- **Language:** Kotlin 2.x
- **Framework:** Ktor 3.x (Netty)
- **DI:** Koin for dependency injection
- **Serialization:** Kotlinx Serialization (JSON)
- **Database:** Exposed (DAO/DSL) with PostgreSQL
- **Concurrency:** Coroutines (Structured Concurrency)

## 2. Architectural Rules
- **Layered Structure:** 
  - `route/`: Definition of HTTP endpoints.
  - `service/`: Business logic.
  - `repository/`: Data access.
- **Dependency Injection:** Always use Koin. Never use manual instantiation for services. Inject using `val service: MyService by inject()` inside routes or constructor injection in classes.

## 3. Kotlin Idioms & Style
- **Results, not Exceptions:** Use `kotlin.Result` or a custom `Either` type for domain errors. Do not use `try-catch` for business logic flow.
- **Extensions:** Prefer extension functions for mapping (e.g., `UserEntity.toDomain()`).
- **Null Safety:** Avoid `!!` (double bang). Use `requireNotNull()` or `elvis operator` with meaningful error reporting.

## 4. Ktor Specific Patterns
- **Routes:** Always group routes in a `Route` extension function.
  ```kotlin
  fun Route.userRoutes() {
      route("/users") { /* handlers */ }
  }
  ```
- **Validation:** Use a custom validate block or request validation plugin before processing data in the service.
- **Plugin Setup:** All plugins (CORS, ContentNegotiation, etc.) must be initialized in plugins/ directory and called in Application.module().

## 5. Testing
- Use Kotest and MockK.
- Use testApplication from Ktor for integration tests.
- Naming convention: should [do something] when [condition].

## 6. Code Style Guidelines

### Imports
- Group imports in the following order: Java imports, Kotlin imports, Third-party imports
- Use full package names for imports
- Avoid star imports (e.g., `import by.zloy.igniagame.*`)
- Keep imports sorted alphabetically

### Formatting
- Use 4 spaces for indentation (no tabs)
- No trailing whitespace
- Newline at end of file
- Maximum line length of 120 characters
- Use Kotlin's standard formatting conventions

### Naming Conventions
- Use `PascalCase` for classes and interfaces (e.g., `Application`, `SecurityPlugin`)
- Use `camelCase` for functions, variables, and properties
- Use `UPPER_SNAKE_CASE` for constants (e.g., `val MAX_CONNECTIONS = 100`)
- Use descriptive names that clearly indicate purpose
- Avoid abbreviations unless widely known (e.g., `id` instead of `identifier`)

### Types
- Prefer `val` over `var` when possible (use immutable bindings)
- Use explicit type declarations when the type is not obvious from context
- Use nullable types (`?`) for optional values and non-null types where appropriate
- Prefer `data class` for simple data objects

### Error Handling
- Use Kotlin's `try/catch` blocks for exceptional conditions
- Prefer `Result` type when possible for returning success/failure
- Log errors appropriately using provided logger
- Handle exceptions at the appropriate level in the application

### Documentation
- Use KDoc to document public APIs (classes, functions, and properties)
- Comment on complex logic or non-obvious implementations
- Maintain documentation consistency with existing codebase