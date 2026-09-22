# REPORT.MD
## Notas y consultas

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


## REPORT PARA EL ASSIGNMENT 3. 

### Assignment 3 — Comparación EvoSuite vs. Randoop, Fuzzer y Reflexiones

#### Resumen de métricas por técnica

| Técnica | Line Cov. (JaCoCo) | Branch Cov. (JaCoCo) | Mutation Cov. (PIT) | Test Strength (PIT) |
|---|---|---|---|---|
| Tests manuales (Assignment 1) | 93% (misses 81/1223) | 96% (misses 6/159) | 91% (187/206) | 94% (187/200) |
| EvoSuite (sin repOK()) | 61% (misses 618/1603) | 59% (misses 91/225) | 46% (109/239) | 75% (109/145) |
| EvoSuite + repOK() como oráculo | 78% (misses 346/1603) | 72% (misses 63/225) | 56% (134/239) | 68% (134/197) |
| Randoop | 72% (misses 443/1603) | 61% (misses 87/225) | 56% (135/239) | 86% (135/157) |


## Comparación EvoSuite vs. Randoop

*Similitudes*

- Ambas generan pruebas unitarias automáticamente. Para hacerlo, analizan las
  clases y prueban distintas combinaciones de constructores y métodos públicos,
  sin que el usuario tenga que escribir cada caso manualmente.
- Ninguna de las dos entiende la intención del programa: ambas producen
  mayormente *aserciones de regresión* (assertEquals contra el valor que
  efectivamente devolvió el método en el momento de la generación), no
  aserciones basadas en la especificación. Esto se nota fuerte en los tests
  generados para Board y Cell, donde las aserciones dependen de
  toString() en vez de expresar la propiedad que debería cumplirse.
- Ambas generan tests frágiles y poco legibles para un humano: nombres
  autogenerados (test01, test02...), secuencias de llamadas largas y
  poco relacionadas con un escenario de uso real del juego.

*Diferencias*

- *Estrategia de búsqueda*: EvoSuite usa un algoritmo evolutivo que intenta
  aumentar la cobertura de ramas, instrucciones y excepciones. Randoop genera
  secuencias aleatorias de llamadas, elimina las que fallan o no aportan
  información y conserva las que descubren estados nuevos.
- *Entradas generadas*: Randoop produjo más casos límite, como referencias
  nulas, dimensiones inválidas, posiciones fuera de rango, valores fijos para
  `DeterministicRandom` y objetos incompatibles usados con `equals()`. EvoSuite
  generó secuencias más dirigidas a cubrir condiciones específicas de `Board`
  y `Cell`, aunque también usó datos artificiales.
- *Cobertura*: sin ayuda adicional, EvoSuite obtuvo menos cobertura que Randoop:
  61% frente a 72% en líneas y 59% frente a 61% en ramas. Esto puede deberse a
  que `Board` y `Cell` usan `Math.random()`, mientras que Randoop pudo aprovechar
  `DeterministicRandom` para controlar mejor esos casos.
- *Uso de `repOK()`*: al usar `repOK()` como oráculo, EvoSuite mejoró de 61% a
  78% en líneas y de 59% a 72% en ramas. Así superó a Randoop en cobertura de
  líneas, aunque ambas herramientas obtuvieron un mutation score de 56%.
- *Test strength*: Randoop obtuvo el mejor resultado, con 86%. Esto indica que
  sus aserciones detectaron más cambios en el código que sus pruebas realmente
  ejecutaban.

*Fortalezas y debilidades*

| | Fortalezas | Debilidades |
|---|---|---|
| EvoSuite | Búsqueda dirigida por cobertura, mejora mucho si se le da un oráculo explícito (repOK()); bueno para forzar ramas puntuales | Requiere JDK 8 aparte, runtime no compatible con Java 17+ (hubo que generar sin runtime), tests generados son inestables/flaky con métodos que usan Math.random() |
| Randoop | No necesita JDK aparte, exploración feedback-directed encuentra bugs con entradas inválidas/límite sin configuración extra, buen test strength | Cobertura más pareja pero no maximizada específicamente, tests aún más largos/difíciles de leer, dependencia fuerte de toString() en las aserciones generadas |

## Cómo funciona el fuzzer y qué se implementó en fuzz()

El fuzzer (scripts-configs/fuzzer.py) sigue la estructura propuesta por
The Fuzzing Book: separa la generación de entradas (Fuzzer) de la
ejecución del programa bajo prueba (Runner).

- *CLIRunner* lanza el juego como subproceso
  (java -ea -cp ./target/classes ar.edu.unrc.game2048.MainCLI), le manda
  la entrada fuzzeada por stdin, y clasifica el resultado:
  - PASS si el proceso termina con código 0 y sin salida en stderr.
  - FAIL si el proceso termina con código distinto de 0 o escribe algo en
    stderr (por ejemplo, una excepción no capturada o un AssertionError
    de un assert repOK() fallido).
  - UNRESOLVED si el proceso no termina dentro del timeout (10s), lo que
    se interpreta como colgado.
- *RandomFuzzer.fuzz()* genera un input válido: elige una longitud
  aleatoria entre min_length y max_length (por defecto 10 a 50), y por
  cada posición elige una tecla al azar de manera uniforme entre a, s,
  w, d (izquierda, abajo, arriba, derecha). Al final agrega q para
  salir prolijamente. Cada tecla queda en su propia línea, terminando en
  \n, tal como requiere el CLI:

  python
  def fuzz(self) -> str:
      length = random.randint(self.min_length, self.max_length)
      moves = [random.choice(KEYS) for _ in range(length)]
      moves.append('q')
      return '\n'.join(moves) + '\n'
  

  Es un fuzzer puramente aleatorio, sin mutación de casos previos ni
  gramática más elaborada: cada movimiento es independiente y equiprobable,
  lo que es suficiente para este programa porque el alfabeto de entradas
  válidas es muy chico (4 movimientos + salir) y el estado del juego
  (Board) es lo que realmente varía en cada corrida según el azar interno
  del juego.
- main() corre 20 trials, imprime el resultado de cada uno y un resumen
  final con la cantidad de PASS/FAIL/UNRESOLVED.

## Bugs encontrados

Se corrió el fuzzer con -ea habilitado (assertions activas) y con
assert repOK() integrado en Board, Cell y MainCLI (ver
src/main/java/ar/edu/unrc/game2048/MainCLI.java:37-80, que llama a
repOK() después de cada movimiento).

En 20 corridas de secuencias aleatorias de 10 a 50 movimientos no se
encontró ningún crash ni ninguna violación de repOK():


Summary:
  PASS        : 20/20
  FAIL        : 0/20
  UNRESOLVED  : 0/20


No se identificaron bugs reproducibles con este fuzzer en su configuración
actual. Esto es consistente con que repOK() ya estaba bien integrado desde
el Assignment 2 y con que el espacio de movimientos válidos es reducido:
el fuzzer nunca envía teclas inválidas (solo a/s/w/d/q), así que no ejercita
las rutas de manejo de entrada inválida del CLI. Una extensión natural sería
agregar ocasionalmente teclas fuera del alfabeto válido o inputs mal
formados (líneas vacías, mayúsculas, EOF anticipado) para estresar también
el parsing de comandos del MainCLI, no solo la lógica del tablero.

## Reflexiones: ¿qué técnica fue más efectiva para este programa?

- *Para cobertura de código*, los tests manuales del Assignment 1 siguen
  siendo, por lejos, los más efectivos (93% líneas / 96% ramas / 91%
  mutation score), porque fueron escritos entendiendo la especificación del
  juego y apuntando deliberadamente a los casos límite del dominio (bordes
  del tablero, fusiones de fichas, condiciones de fin de juego).
- *Entre las técnicas automáticas*, la comparación depende de la métrica.
  En cobertura pura, EvoSuite con repOK() como oráculo superó a Randoop
  (78% líneas / 72% ramas frente a 72% líneas / 61% ramas), y ambas
  quedaron empatadas en mutation score (56%). Randoop, en cambio, ganó
  claramente en test strength (86% frente a 68%), y lo logró sin depender
  de un runtime incompatible con la versión de Java del proyecto ni
  necesitar que se le indicara un oráculo explícito, algo que a EvoSuite
  sin repOK() le costó bastante (61% líneas / 59% ramas). Esto sugiere
  que la búsqueda dirigida por fitness de EvoSuite se beneficia mucho más
  que Randoop de tener una señal adicional de "estado inválido" para
  guiar la generación, mientras que Randoop es más efectivo cuando no se
  cuenta con esa señal.
- *Como técnica de *bug finding**, el fuzzer es cualitativamente distinto
  a EvoSuite/Randoop: en vez de maximizar cobertura de código a nivel de
  unidad, ejercita el programa completo a través de su interfaz real (stdin
  del CLI), con secuencias largas de interacciones que sí pueden disparar
  bugs de integración (por ejemplo, un repOK() roto tras muchos
  movimientos encadenados) que un test unitario aislado difícilmente
  reproduciría. En este proyecto no encontró fallas porque Board/Cell ya
  estaban bien defendidos por repOK() desde el Assignment 2, pero es la
  única de las tres técnicas capaz de detectar errores que solo aparecen
  por la combinación de muchos movimientos en secuencia, algo que ni
  EvoSuite ni Randoop prueban (ellos llaman a los métodos de forma aislada,
  no a través de una sesión completa de juego).
- *En conjunto*, las tres técnicas son complementarias: EvoSuite/Randoop
  son mejores para maximizar cobertura de código rápido y sin esfuerzo
  manual, mientras que el fuzzer es mejor para validar invariantes de
  extremo a extremo sobre secuencias largas de uso real del programa. Para
  este programa en particular, dado lo simple del dominio y lo bien cubierto
  que ya estaba por los tests manuales y repOK(), ninguna de las técnicas
  automáticas encontró bugs nuevos, pero Randoop fue la más "efectiva" en
  términos de costo/beneficio (cobertura razonable sin configuración extra).