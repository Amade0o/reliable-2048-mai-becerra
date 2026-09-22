#!/bin/bash
set -e

mvn clean compile
rm -rf src/test/java/randoopTests

java -cp "lib/randoop-all-4.3.4.jar:target/classes" \
  randoop.main.Main gentests \
  --testclass=ar.edu.unrc.game2048.Board \
  --testclass=ar.edu.unrc.game2048.Cell \
  --testclass=ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom \
  --omit-classes-file=scripts-configs \ randoop-omit-classes.txt \
  --omit-methods-file=scripts-configs \ randoop-omit-methods.txt \
  --time-limit=10 \
  --junit-output-dir=src/test/java \
  --junit-package-name=randoopTests

mvn test
