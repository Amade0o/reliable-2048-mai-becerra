[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/AOueBK7V)
# 2048 Game - Software Testing Exercise

## Overview

This is a minimal implementation of the 2048 game designed for a software testing and refactoring exercise. The code contains intentional design flaws and potential bugs that you will discover through systematic testing.

## Learning Objectives

1. **Write comprehensive unit tests** using JUnit 5
2. **Identify bugs** through systematic testing
3. **Report issues** in a structured format
4. **Fix bugs** and verify fixes through regression testing
5. **Identify design problems** and propose improvements
6. **Refactor code** while maintaining functionality through tests

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Build and Run

```bash
# Build the project
mvn clean compile

# Run the game
mvn exec:java -Dexec.mainClass="ar.edu.unrc.game2048.MainCLI"

# Run tests (after you write them)
mvn test
```
## Phase 1: Write Unit Tests

### Task 1.1: Test Cell Class

Create `CellTest.java` in the `src/test/java/ar/edu/unrc/game2048/` directory with at least **one test per public method**.

### Task 1.2: Test Board Class

Create `BoardTest.java` in the `src/test/java/ar/edu/unrc/game2048/` directory with at least **one test per public method**.

## Phase 2: Bug Discovery

### Task 2.1: Run your tests

```bash
mvn test
```
### Task 2.2: Document issues

For each test that fails, create an issue report.

## Phase 3: Bug Fixing

### Task 3.1: Fix each identified bug.

For each issue you discovered, locate the bug in the source code and correct it. Make minimal changes to fix the issue without breaking other functionality.

### Task 3.2: Verify the fix.

Run your tests again to confirm the bug is fixed.

### Task 3.3: Commit the fix. 

Commit your changes with a descriptive message.

### Task 3.4: Update issue reports. 

After fixing each issue, update the corresponding issue report.

### Task 3.5: Regression testing. 

After fixing all bugs, run your entire test suite to ensure that fixes for one issue haven't broken other functionality.

## Phase 4: Design Improvement

### Task 4.1: Identify Design Problems

Look for at least **one significant design problem** in the code. Document each design problem you find with issues. 

### Task 4.2: Perform Refactoring

#### Step 1: Choose a Design Problem to Fix

Select one design problem from your list and plan the refactoring.

#### Step 2: Implement the refactoring

Make the necessary code changes to fix the design problem. Remember:
* Make small, incremental changes
* Keep the code working at all times
* Run tests frequently

#### Step 3: Test the refactoring

Make sure all tests pass. Use it as an acceptance criterion for the refactor.

#### Step 4: Commit the refactoring

Commit your refactoring with an appropriate comment.

### Task 4.3: Update issue reports. 

After completing the refactor, update the corresponding issue report.

