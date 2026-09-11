#!/bin/bash
# generate_evosuite_tests.sh
#
# EvoSuite 1.0.6 solo corre sobre un JDK 8 (necesita lib/tools.jar: un JRE no alcanza).
# Maven y los tests corren con el Java por defecto del sistema (17+).
# Uso: JAVA8_HOME=/ruta/al/jdk8 ./runEvosuite.sh

EVOSUITE_JAR="evosuite-1.0.6.jar"
EVOSUITE_URL="https://github.com/EvoSuite/evosuite/releases/download/v1.0.6/evosuite-1.0.6.jar"
TARGET_CLASS="ar.edu.unrc.game2048.Cell"
SEARCH_BUDGET=60

if [ -z "$JAVA8_HOME" ] || [ ! -f "$JAVA8_HOME/lib/tools.jar" ]; then
    echo "Definí JAVA8_HOME apuntando a un JDK 8 (tiene que tener lib/tools.jar)." >&2
    exit 1
fi  

# Download EvoSuite if not exists
if [ ! -f "$EVOSUITE_JAR" ]; then
    echo "Downloading EvoSuite..."
    wget "$EVOSUITE_URL" || curl -L -o "$EVOSUITE_JAR" "$EVOSUITE_URL"
fi

# Build project first (compila a bytecode Java 8, ver maven.compiler.release en el pom)
mvn clean compile || exit 1

CLASS_PATH=$(pwd)/target/classes

# Generate tests. JAVA_HOME también se fija para que los procesos que lanza EvoSuite usen el JDK 8.
echo "Generating EvoSuite tests..."
JAVA_HOME="$JAVA8_HOME" "$JAVA8_HOME/bin/java" -jar "$EVOSUITE_JAR" -projectCP "$CLASS_PATH" -class $TARGET_CLASS \
    -Dsearch_budget=$SEARCH_BUDGET -Dtest_dir=src/test/java -Dno_runtime_dependency=true

# Run tests
mvn test
