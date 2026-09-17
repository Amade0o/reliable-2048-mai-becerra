# Assignment 3: Automated Test Generation and Fuzzing

## Overview

In this assignment, you will continue working with the *Reliable 2048* project from previous assignments and explore two additional automated testing techniques: **evolutionary test generation** with EvoSuite, and **fuzzing**. You will compare the effectiveness of these approaches against each other and against the test suites you built manually and automatically in Assignments 1 and 2.

## Learning Objectives

1. Use EvoSuite to automatically generate test suites using evolutionary algorithms
2. Measure and compare the coverage achieved by EvoSuite against your own tests and those produced by other tools
3. Understand the principles of fuzzing as an automated bug-finding technique
4. Implement a simple fuzzer for a CLI program, following the fuzzing book approach
5. Enhance bug-finding capability by combining fuzzing with representation invariants (`repOK()`)

## Getting Started

### Prerequisites
- Assignments 1 and 2 completed (your `repOK()` implementations from Assignment 2 will be reused here)

### Setup

1. Create a new repository from the updated template
2. Merge your Assignment 2 progress into this new repository
3. Verify the setup:
```bash
mvn clean compile
mvn test
```

---

## Phase 1: Automated Test Generation with EvoSuite

EvoSuite uses evolutionary algorithms (genetic search) to automatically generate JUnit test suites that maximize code coverage and other testing metrics. This is conceptually similar to Randoop from Assignment 2, but uses a fundamentally different search strategy.

### 1.1 Run EvoSuite

Run EvoSuite on each of the relevant classes. Instructions for running EvoSuite on this project were provided separately.

### 1.2 Inspect the Generated Tests

Open the generated test files and examine them:
- What kinds of inputs did EvoSuite generate?
- Are the test oracles (assertions) meaningful, or are they mostly regression assertions?
- Are there any tests that seem fragile or hard to understand?

### 1.3 Measure Coverage

Run JaCoCo on the EvoSuite-generated tests:
```bash
mvn clean test jacoco:report
```
Report can be found in `target/site/jacoco/index.html`.

Compare the coverage achieved by EvoSuite with:
- Your manually written test suite from Assignment 1
- The Randoop-generated suite from Assignment 2

### 1.4 Record and Commit

Record your findings (coverage numbers, any bugs found, observations about test quality) and commit with an appropriate tag.

---

## Phase 2: Fuzzing

Fuzzing is a dynamic testing technique that automatically generates (often random) inputs and feeds them to a program, looking for crashes, hangs, or violations of invariants. Unlike unit test generation tools like Randoop and EvoSuite, a fuzzer exercises the program through its external interface — in this case, the command-line UI.

You are provided with a starter fuzzer script (`fuzzer.py`) at the root of the repository. Your task is to understand it, complete the missing parts, and extend it.

### 2.1 Understand the Starter Fuzzer

Read `fuzzer.py` carefully. It follows the structure of simple fuzzers from *The Fuzzing Book* (Zeller et al.), with a `Runner` class that wraps the program under test, and a `Fuzzer` class responsible for generating inputs.

The overall flow is:
1. A `Fuzzer` generates a random input string (a sequence of key presses)
2. The `Runner` feeds that string to the Java program via stdin and collects the outcome
3. The outcome is classified and recorded

### 2.2 Complete the Fuzzer

**Your task:** implement the body of `RandomFuzzer.fuzz()`. This method should return a single string that represents a valid sequence of moves to send to the game, formatted so each key is on its own line (i.e., followed by `\n`), and ending with `q\n` to quit gracefully.

The valid move keys are: `a` (left), `s` (down), `w` (up), `d` (right).

Think about:
- How long should the sequence be? Should the length itself be random?
- Should all keys be equally likely?

The method signature and its role in the class are defined in the starter file — do not change them.

### 2.3 Run the Fuzzer

Once `fuzz()` is implemented, run the fuzzer (provided the project is previously compiled with maven):
```bash
python3 fuzzer.py
```

Observe the output. Does the program ever crash (non-zero exit code or stderr output)?

### 2.4 Enhance Bug-Finding with `repOK()`

The fuzzer currently detects bugs only if the Java program crashes outright (exception, non-zero exit code). Subtle logic bugs — invalid board states, wrong scores, etc. — may go undetected.

You can make the fuzzer more sensitive by enabling Java assertions and calling your `repOK()` methods defensively inside the program:
- Ensure your `repOK()` from Assignment 2 is implemented in `Cell` and `Board`
- Add `assert repOK()` calls at key points in the game logic (e.g., after every move in `MainCLI`)
- Run the fuzzer again with assertions enabled by passing `-ea` to the JVM

To enable assertions, update the runner command in `fuzzer.py`:
```python
['java', '-ea', '-cp', './target/classes', 'ar.edu.unrc.game2048.MainCLI']
```

Run the fuzzer again and observe whether any new failures are caught.

### 2.5 Record and Commit

Record your findings:
- Were any crashes or assertion failures found?
- If so, can you reproduce the failing input and diagnose the bug?
- How does the fuzzer compare to EvoSuite and Randoop as a bug-finding technique?

Commit with an appropriate tag.

---

## Deliverables

For each phase, include in your repository:
- The generated test files (EvoSuite output, committed to an appropriate testing directory)
- Your completed `fuzzer.py`
- A short written report (`report.md`) covering:
  - Coverage and mutation scores for each technique (tables are encouraged)
  - A comparison of EvoSuite vs. Randoop: similarities, differences, strengths, weaknesses
  - A description of how the fuzzer works and what you implemented in `fuzz()`
  - Any bugs found, with a minimal reproducing input if applicable
  - Reflections: which technique was most effective for this program, and why?

---

## Grading Criteria

| Criterion | Weight |
|---|---|
| EvoSuite run and coverage measured | 15% |
| Meaningful comparison with Randoop and manual tests | 15% |
| Fuzzer `fuzz()` correctly implemented | 20% |
| Fuzzer runs and produces meaningful output | 10% |
| `repOK()` integrated and fuzzer re-run with `-ea` | 15% |
| Written report: clarity, depth, and accuracy | 25% |

---

## References

- [The Fuzzing Book](https://www.fuzzingbook.org/) — especially the *Fuzzing* and *Mutation-Based Fuzzing* chapters
