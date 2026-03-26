# Agent Guidelines for Ignia Game Project

This repository contains a Kotlin/Gradle project using Ktor framework located in the root of this repository. 
When working on the project interactively with an agent (e.g. the Codex CLI) please follow the guidelines below so that the development experience continues to work smoothly.

## Build, Lint, and Test Commands

### Building the project
```bash
./gradlew build
```

### Running tests
```bash
./gradlew test
```

### Linting and formatting
```bash
./gradlew ktlintCheck
./gradlew ktlintFormat
```

### Running the application
```bash
./gradlew run
```

## Project Structure

### Source Code
- Main application code in `src/main/kotlin/by/zloy/igniagame`
- Test code in `src/test/kotlin/by/zloy/igniagame`

### Dependencies
- Kotlin
- Gradle
- Ktor framework 
