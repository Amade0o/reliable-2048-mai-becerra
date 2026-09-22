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