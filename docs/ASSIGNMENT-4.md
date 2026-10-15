# Assignment 4: Oracle Generation with Daikon

## Overview

In this assignment, you will continue working with the *Reliable 2048* project and explore **dynamic invariant inference** using Daikon. Rather than writing test oracles by hand or relying on crash detection, Daikon observes the program as it runs and automatically infers likely preconditions, postconditions, and class invariants — the kind of properties you would ideally encode as formal specifications.

You will run Daikon on the `Cell` and `Board` classes, inspect and evaluate the inferred invariants, and reflect on how well they capture the intended behaviour of the program and whether they could serve as useful oracles in your existing test suites.

## Learning Objectives

1. Understand the concept of dynamic invariant inference and how Daikon works
2. Instrument a Java program for Daikon and collect execution traces
3. Infer preconditions, postconditions, and class invariants for `Cell` and `Board`
4. Critically evaluate the quality and usefulness of automatically inferred invariants
5. Reflect on the relationship between inferred invariants and the `repOK()` methods written in Assignment 2

## Getting Started

### Prerequisites
- Assignments 1, 2, and 3 completed
- Java 8 or later on your `PATH`
- The project compiles cleanly:
```bash
mvn clean compile
```

### Repository

This assignment should be completed over the same repository you have been using so far, your team's clone or fork of:
```
https://github.com/Seminario-en-Ciencias-de-la-Computacion/reliable-2048
```
Continue working on the same repository as in previous assignments. Do not create a new one.

---

## Installing Daikon

### 1. Download Daikon

Download the latest Daikon distribution from the official site:
```
https://plse.cs.washington.edu/daikon/download/
```

Download the file `daikon.jar` (the all-in-one jar) and place it in a `lib/` directory at the root of your project:
```
reliable-2048/
  lib/
    daikon.jar
    randoop-all-4.3.4.jar   ← already there from Assignment 2
```

### 2. Verify the Installation

```bash
java -cp lib/daikon.jar daikon.Daikon --version
```

You should see Daikon's version string printed. If you see a `ClassNotFoundException`, the jar is not on the classpath correctly.

### 3. Set the `DAIKONDIR` environment variable (optional but recommended)

If you plan to use Daikon's helper scripts (e.g., `daikon.jar` bundled utilities), setting this variable avoids having to type the full path repeatedly:
```bash
export DAIKONDIR=/path/to/your/lib
```

---

## How Daikon Works

Daikon is a **dynamic** invariant detector. It does not analyze source code statically; instead it works in two steps:

1. **Instrumentation and trace collection:** The program under test is instrumented so that, at every method entry and exit, the values of all visible variables are written to a trace file (`.dtrace`).
2. **Invariant inference:** Daikon reads the trace file and checks a large library of candidate invariant templates (e.g., `x > 0`, `x == y`, `x <= y + 1`, `x != null`) against the observed values. Candidates that hold across *all* observed executions are reported as likely invariants.

The key implication is that **the quality of the inferred invariants depends entirely on the quality and diversity of the executions you collect**. If your test inputs are too narrow, Daikon will infer invariants that are technically true for those runs but do not generalize.

---

## Phase 1: Instrumentation and Trace Collection

Daikon instruments Java programs using **Chicory**, a front-end that runs alongside the JVM and writes the `.dtrace` file.

### 1.1 Compile the Project

Make sure the project is compiled with debug information (the Maven configuration already does this, but verify):
```bash
mvn clean compile
```

### 1.2 Run Chicory on Your Existing Tests

The most convenient way to collect a rich trace is to run your JUnit test suite through Chicory, so that all the exercised method calls are recorded.

```bash
java -cp "lib/daikon.jar:target/classes:target/test-classes:$(mvn dependency:build-classpath -q -DforceStdout)" \
     daikon.Chicory \
     --output-dir=daikon-traces \
     org.junit.runner.JUnitCore \
     ar.edu.unrc.game2048.CellTest
```

> **Note:** Replace `ar.edu.unrc.game2048.CellTest` with the actual fully-qualified names of your test classes. If you have a test suite class, you can pass that instead. Make sure that such test classes are JUnit 4 test classes.

This will create a `daikon-traces/` directory containing one or more `.dtrace` files.

### 1.3 Collect Additional Traces via the CLI (optional)

Daikon's invariants are stronger when more diverse executions are observed. You can supplement your unit test traces by also running the game interactively through Chicory:

```bash
java -cp "lib/daikon.jar:target/classes" \
     daikon.Chicory \
     --output-dir=daikon-traces \
     ar.edu.unrc.game2048.MainCLI
```

Play a few games manually (or pipe the output of your fuzzer from Assignment 3), then quit with `q`. Each run appends observations to the trace.

To pipe the fuzzer's output directly:
```bash
python3 fuzzer.py | java -cp "lib/daikon.jar:target/classes" \
     daikon.Chicory \
     --output-dir=daikon-traces \
     ar.edu.unrc.game2048.MainCLI
```

### 1.4 Limit Instrumentation to Relevant Classes (recommended)

By default Chicory instruments every class it loads, which produces very large traces and clutters the output with invariants for unrelated library classes. Restrict it to the classes you care about:

```bash
java -cp "lib/daikon.jar:target/classes:..." \
     daikon.Chicory \
     --ppt-select-pattern="ar.edu.unrc.game2048.Board|ar.edu.unrc.game2048.Cell" \
     --output-dir=daikon-traces \
     org.junit.runner.JUnitCore \
     ar.edu.unrc.game2048.BoardTest ar.edu.unrc.game2048.CellTest
```

---

## Phase 2: Invariant Inference

### 2.1 Run Daikon on the Trace Files

Once you have one or more `.dtrace` files in `daikon-traces/`, run Daikon to infer invariants:

```bash
java -cp lib/daikon.jar daikon.Daikon daikon-traces/*.dtrace
```

Daikon prints inferred invariants to stdout, grouped by **program point**: method entry (`ENTER`), method exit (`EXIT`), and object invariants (`OBJECT`).

Redirect the output to a file for easier inspection:
```bash
java -cp lib/daikon.jar daikon.Daikon daikon-traces/*.dtrace > daikon-output.txt
```

### 2.2 Produce a Human-Readable Report (optional)

Daikon can also write a `.inv` (binary invariant) file that other Daikon tools can post-process:

```bash
java -cp lib/daikon.jar daikon.Daikon \
     --save_inv_to daikon-traces/output.inv \
     daikon-traces/*.dtrace
```

You can then pretty-print it at any time without re-running inference:
```bash
java -cp lib/daikon.jar daikon.PrintInvariants daikon-traces/output.inv
```

---

## Phase 3: Inspect and Evaluate the Inferred Invariants

Open `daikon-output.txt` and read through the invariants reported for `Cell` and `Board`.

### 3.1 Understand the Program Point Structure

Daikon groups invariants under headings like:

```
===========================================================================
ar.edu.unrc.game2048.Cell:::OBJECT
===========================================================================
this.value >= 0
this.value == 0 || this.value >= 2
...

===========================================================================
ar.edu.unrc.game2048.Cell.setValue(int):::ENTER
===========================================================================
value >= 2
...

===========================================================================
ar.edu.unrc.game2048.Cell.setValue(int):::EXIT
===========================================================================
this.value == value
...
```

- `OBJECT` invariants hold at all observable points on an object — these correspond to **class invariants**
- `ENTER` invariants hold whenever the method is called — these are **preconditions**
- `EXIT` invariants hold whenever the method returns — these are **postconditions**

### 3.2 Evaluate the Invariants

For each invariant Daikon reports, consider:

- **Is it correct?** Does it reflect a genuine property of the program, or is it an artefact of the limited inputs you ran?
- **Is it useful?** Would it catch a real bug if violated?
- **Is it already expressed elsewhere?** Compare with your `repOK()` from Assignment 2 — did Daikon rediscover those invariants? Did it find anything you missed?
- **Is it too strong?** An invariant that holds only because your tests happen not to exercise a valid corner case is a *false invariant*. Can you construct a valid execution that would violate it?

Annotate your `daikon-output.txt` (or write your analysis in `report.md`) with a classification of each invariant as: **correct and useful**, **correct but trivial**, **too strong / likely spurious**, or **incorrect**.

### 3.3 Improve Trace Coverage and Re-run

If you find many spurious invariants, it is likely because your traces are too narrow. Try to:
- Add more diverse test cases (edge cases, boundary values, longer game sequences)
- Use the fuzzer from Assignment 3 to generate a large, varied trace
- Re-run Chicory and Daikon, and compare the new output with the original

Does broadening the trace eliminate spurious invariants? Does it reveal new, genuine ones?

---

## Phase 4: Comparison

### 4.1 Daikon vs. `repOK()`

In Assignment 2, you wrote `repOK()` methods manually to capture your understanding of valid object states. Now that you have Daikon's output:

- Which invariants from `repOK()` did Daikon also infer?
- Which did Daikon miss, and why? (Think about what kinds of properties Daikon's template library can and cannot express.)
- Did Daikon infer any invariant that you had *not* included in `repOK()` but that seems correct and useful? If so, consider adding it.

### 4.2 Daikon vs. Manual and Automated Test Oracles

Reflect on the different oracle sources you have now seen across all four assignments:

| Source | How oracles are produced | Strengths | Weaknesses |
|---|---|---|---|
| Manual test assertions | Written by hand | Precise, meaningful | Labour-intensive, may miss cases |
| `repOK()` | Written by hand | Reusable, always checked | Same limitations as manual |
| Randoop regression assertions | Observed from a single run | Automatic | Brittle, not meaningful |
| EvoSuite assertions | Evolved to maximise coverage | Automatic, high coverage | Often regression-style |
| Daikon invariants | Inferred from many runs | Generalise across executions | Depend on trace quality; may be spurious |

Which approach produced the most useful oracles for this particular program? Justify your answer.

---

## Deliverables

Include in your repository:

- `daikon-traces/` — your `.dtrace` and `.inv` files (commit them; they are the evidence of your runs)
- `daikon-output.txt` — the raw Daikon output
- An updated `report.md` (continuing from Assignment 3, or a new section) covering:
  - The commands you ran to instrument and collect traces
  - A discussion of at least **five** inferred invariants for `Board` and at least **five** for `Cell`, with your classification and reasoning
  - A comparison of Daikon's invariants with your `repOK()` implementations
  - Your answer to the reflection question in Phase 4.2
  - Any updates you made to `repOK()` based on Daikon's output

---

