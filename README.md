
# Assignment 2: Advanced Testing - Coverage, Mutation, and Test Generation

## Overview

In this assignment, you will extend your work from Assignment 1 by applying advanced testing techniques. You will measure and improve your test quality using code coverage and mutation analysis, then explore automated test generation with Randoop. Finally, you will implement `repOK()` invariants to catch additional bugs.

## Learning Objectives

1. Measure test quality using code coverage (JaCoCo) and mutation analysis (PITest)
2. Improve test suites to achieve high coverage and mutation scores
3. Use automated test generation (Randoop) to find bugs
4. Implement representation invariants (`repOK()`) for defensive programming
5. Use invariants to improve testing and find additional issues

## Getting Started

### Prerequisites
- Assignment 1 completed

### Setup

1. Create a new repository from the updated template
2. Merge your Assignment 1 progress into this new repository
3. Verify the setup
```bash
mvn clean compile
mvn test
```
## Phase 1

1. Establish baseline code coverage metrics of your tests by running jacoco:
```bash
mvn clean test jacoco:report
```
Report can be found in target/site/jacoco/index.html

2. Establish baseline mutation analysis metrics of your tests by running PITest:
```bash
mvn pitest:mutationCoverage
```
Report can be found in target/pit-reports/index.html

3. Record your baseline, and commit it to the repository with an appropriate tag.

## Phase 2: Improve Test Quality

1. Open the JaCoCo report. Look for:
- Red lines (completely untested)
- Yellow lines (partially tested)
- Methods with low coverage
- Branch coverage gaps

2. Analyze Mutation Report. Open the PITest report. Look for:
- Surviving mutants (tests didn't detect the change)

3. Improve Your Tests.
- Add further test cases
- Improve test oracles of existing test cases

Aim at at least 90% branch coverage and 90% mutation score.

4. Record the improvement, and commit it to the repository with an appropriate tag.

## Phase 3: Automated Test Generation with Randoop

1. Run Randoop to generate test cases automatically:
```bash
java -cp "lib/randoop-all-4.3.4.jar:target/classes" randoop.main.Main gentests --testclass=ar.edu.unrc.game2048.Cell --time-limit=10 --junit-output-dir=src/test/java --junit-package-name=randoopTests
```
Run Randoop for all the relevant classes.

2. Measure code coverage and compare with your test suites.

3. Record the results, including, potentially, bugs found. Commit to repository with appropriate tag.

4. Implement repOK() Invariants for Cell and Board.

5. Run Randoop again.

6. Record the results, including, potentially, bugs found. Commit to repository with appropriate tag.
