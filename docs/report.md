# REPORT.MD
## REPORT PARA EL ASSIGNMENT 3. 

### Nota
En el assignment 3 se solicitó crear analiticas con evosuite, pero como debido a que se nos dio la opcion previamente de utilizar evosuite y esas analiticas ya estaban creadas, en esta etapa se utilizó randoop para la generacion de tests. Ambas tecnologias se pueden correr mediante la ejecucion de los scripts de scripts-config/runEvosuite.sh y scripts-config/runRandoop.sh

### Phase 1: (Se utilizó randoop)
#### 1.1
Se ejecutó randoop, los inputs generados 

#### 1.2 
##### What kinds of inputs did RANDOOP (not evosuite) generate?
- **Referencias nulas** son enviadas a constructores y metodos para verificar manejo de excepciones
- **Conversiones de caracteres a enteros** (Casteos de primitivos): Se emplean literales de tipo char casteados implícita o explícitamente a enteros para definir coordenadas o dimensiones
- **Valores Límite e Inválidos:** Dimensiones de tablero no válidas como 0 y -1, o tamaños inusuales como 1 y 97. También coordenadas fuera de rango como Position(2048, 0) o Position(32, 100) 
- **Secuencias de números pseudoaleatorios fijas:** Arreglos de enteros repetitivos como new Integer[] { 100, 1, 1, 10, 1 } utilizados para instanciar la clase DeterministicRandom y controlar las posiciones de aparición de fichas
- **Enumeraciones y tipos incompatibles:** Direcciones del tablero (Direction.UP, RIGHT, LEFT, DOWN), e instancias de objetos no relacionados (String, Board, short) pasados como parámetro a métodos .equals()

##### Are the test oracles (assertions) meaningful, or are they mostly regression assertions?
Son mayormente aserciones de regresión debido a la naturaleza de randoop

##### Are there any tests that seem fragile or hard to understand?
Si, los tests no son para nada faciles de leer, son en muchos casos muy largos, con llamadas complejas y muchas instrucciones.
Ademas son fragiles debido a muchas dependencias de toString() para verificar el buen funcionamiento


#### 1.3 Measure Coverage
1-BaseLine
- Jacoco
  - Instruction coverage: 82%
  - Branch coverage: 77%
- Pitest
  - Line coverage: 79%
  - Mutation coverage: 67%
  - Test strength: 80%

2-Test manuales
- Jacoco
  - Instruction coverage: 94%
  - Branch coverage: 96%
- Pitest
  - Line coverage: 97%
  - Mutation coverage: 91%
  - Test strength: 94%

3-Evosuite-record
- Jacoco
  - Instruction coverage: 61%
  - Branch coverage: 60%
- Pitest
  - Line coverage: 57%
  - Mutation coverage: 46%
  - Test strength: 75%

4- Evosuite-repok-record
  - Instruction coverage: 78%
  - Branch coverage: 72%
- Pitest
  - Line coverage: 76%
  - Mutation coverage: 56%
  - Test strength: 68%

5- Randoop-record
  - Instruction coverage: 72%
  - Branch coverage: 61%
- Pitest
  - Line coverage: 67%
  - Mutation coverage: 56%
  - Test strength: 86%

## Cómo se eliminó el flakiness en los tests de EvoSuite

Los tests generados por EvoSuite para Board eran flaky: dependían de
Math.random() (a través de MathRandom, la implementación por defecto de
IRandom) y las aserciones de regresión que EvoSuite genera capturan el
valor devuelto por el azar en el momento de la generación, no una propiedad
del programa. Al correr esos tests después, Math.random() devuelve otro
valor y la aserción falla — en una corrida real, 16 de 70 tests de
Board_ESTest fallaron por esta razón.

*Por qué Randoop no sufre este problema.* Randoop tiene dos archivos de
configuración (scripts-configs/randoop-omit-classes.txt y
randoop-omit-methods.txt) que excluyen por completo la clase MathRandom
y los constructores Board()/Board(int) de su exploración. Estructuralmente,
Randoop no puede construir un Board con azar real: el único IRandom que
le queda disponible es DeterministicRandom (una implementación que
devuelve una secuencia fija de enteros pasada por parámetro).

*Por qué EvoSuite sí lo sufre.* EvoSuite no tiene un mecanismo equivalente
configurado en este proyecto (ni runEvosuite.sh ni la versión 1.0.6 traen
una opción tipo "prohibí instanciar esta clase"). Su búsqueda genética
explora libremente los 5 constructores de Board y las dos implementaciones
de IRandom que encuentra en el classpath (MathRandom y
DeterministicRandom). Que exista el constructor Board(IRandom) le da a
EvoSuite la opción de generar tests deterministas, pero no le quita la
opción de seguir usando MathRandom. Además, EvoSuite normalmente evita
este problema mockeando Math.random() con su propio runtime
(org.evosuite.runtime.*) tanto en la generación como en la reproducción de
los tests, pero ese runtime no es compatible con Java 17+ (ver comentario en
pom.xml), así que el proyecto genera los tests con
-Dno_runtime_dependency=true, que desactiva justamente ese mecanismo de
protección.

*Solución aplicada.* Se replicó el enfoque de Randoop pero a nivel de
classpath en lugar de configuración: antes de invocar a EvoSuite sobre
Board, se sacó temporalmente MathRandom.class de target/classes
(dejando DeterministicRandom.class e IRandom.class), se generaron los
tests, y recién después se restauró MathRandom.class para poder compilar y
correr la suite con Maven. Sin MathRandom disponible, EvoSuite no tuvo otra
opción que usar DeterministicRandom para construir todos los Board que
necesitó (0 apariciones de new MathRandom(...) en el Board_ESTest.java
resultante, contra 9 antes de aplicar esto).

Este truco tuvo un efecto secundario: EvoSuite, al no encontrar
MathRandom.class, generó 5 tests que capturaban un NoClassDefFoundError
al invocar new Board()/new Board(int) como si fuera el comportamiento
esperado de la clase (test02, test07, test11, test12, test21).
Ese error es un artefacto del truco de classpath, no un comportamiento real
del programa — una vez restaurado MathRandom.class esos constructores
vuelven a funcionar normalmente y esos 5 tests hubiesen fallado. Se
eliminaron esos 5 métodos del archivo generado, quedando 40 tests en
Board_ESTest (más los 25 de Cell_ESTest, que no usa IRandom y no
necesitó este tratamiento).

*Verificación.* Se corrió la suite resultante dos veces seguidas con
mvn test -Dtest=Board_ESTest,Cell_ESTest: 65/65 tests pasaron en ambas
corridas, 0 fallos, confirmando que ya no depende de Math.random() sin
controlar.

*Resultado.* Con la suite ya determinista, se midió jacoco y PIT
exclusivamente sobre estos 65 tests (excluyendo los tests manuales y los de
Randoop) y se guardaron los tests generados junto con los reportes en
reportes/evosuite-noflaky-record/, sin sobrescribir los records anteriores
(evosuite-record/, evosuite-repok-record/) para mantener el historial de
comparación.









# BORRAR
### DUDAS:
- Hicimos esta etapa con randoop, adjuntamos las pruebas hechas previamente con evosuite por las dudas? O hacemos todo puramente con randoop?

### RECORDATORIOS
- Se cambiaron constructores
- Se crearon clases 

### CONSIGNA REPORTE: borrar

For each phase, include in your repository:
- The generated test files (EvoSuite output, committed to an appropriate testing directory)
- Your completed `fuzzer.py`
- A short written report (`report.md`) covering:
  - Coverage and mutation scores for each technique (tables are encouraged)
  - A comparison of EvoSuite vs. Randoop: similarities, differences, strengths, weaknesses
  - A description of how the fuzzer works and what you implemented in `fuzz()`
  - Any bugs found, with a minimal reproducing input if applicable
  - Reflections: which technique was most effective for this program, and why?