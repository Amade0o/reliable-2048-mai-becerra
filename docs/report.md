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

- Las dos herramientas crean pruebas de forma automática. Para ello, combinan
  diferentes constructores y métodos públicos de las clases, sin necesidad de
  escribir las pruebas una por una.
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

### Para cobertura de código

Los tests manuales del Assignment 1 siguen
  siendo, por lejos, los más efectivos (93% líneas / 96% ramas / 91%
  mutation score), porque fueron escritos entendiendo la especificación del
  juego y apuntando deliberadamente a los casos límite del dominio (bordes
  del tablero, fusiones de fichas, condiciones de fin de juego).
### Entre las técnicas automáticas

La comparación depende de la métrica.
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
### Como técnica de *bug finding*

El fuzzer es cualitativamente distinto
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
### En conjunto

Las tres técnicas son complementarias: EvoSuite/Randoop
  son mejores para maximizar cobertura de código rápido y sin esfuerzo
  manual, mientras que el fuzzer es mejor para validar invariantes de
  extremo a extremo sobre secuencias largas de uso real del programa. Para
  este programa en particular, dado lo simple del dominio y lo bien cubierto
  que ya estaba por los tests manuales y repOK(), ninguna de las técnicas
  automáticas encontró bugs nuevos, pero Randoop fue la más "efectiva" en
  términos de costo/beneficio (cobertura razonable sin configuración extra).


## REPORT PARA EL ASSIGNMENT 4. 

### Assignment 4 — Invariantes definidos por daikon
Estos tests solo fueron ejecutados sobre los tests manuales

#### Cell

| Invariante | Dónde | Clasificación |
|---|---|---|
| `this.value >= 0` | `OBJECT` | Correcta y útil |
| `this.value == orig(value)` | `Cell(int):::EXIT` | Correcta y útil |
| `(this.value == 0) <==> (return == true)` | `isEmpty():::EXIT` | Correcta y útil |
| `this.value == other.value` | `mergeWith:::ENTER` | Correcta y útil |
| `return >= 0` / `return == this.value` | `getValue():::EXIT` | Correcta pero trivial |
| `this.value == 2` | `mergeWith:::ENTER` y `canMergeWith` con `return == true` | Demasiado fuerte |
| `return.value == 4` | `mergeWith:::EXIT` | Falsa |
| `this.value one of { 0, 2, 4 }` | `equals`, `toString` | Falsa |

#### Board

| Invariante | Dónde | Clasificación |
|---|---|---|
| `this.size == size(this.grid[])` | `OBJECT` | Correcta y útil |
| `this.grid != null`, `this.grid[]` elements `!= null` | `OBJECT` | Correcta y útil |
| `row >= 0`, `col >= 0`, `row <= size-1`, `col <= size-1` | `getCell:::ENTER` | Correcta y útil |
| `return != null` | `getCell:::EXIT` | Correcta y útil |
| `this.score == return` | `getScore():::EXIT` | Correcta pero trivial |
| `this.score one of { 0, 4 }` | `OBJECT` | Falsa |
| `this.score % this.size == 0` | `OBJECT` | Falsa |
| `this.size one of { 2, 4, 5 }` | `OBJECT` | Demasiado fuerte |
| `randomGeneration.getClass() == MathRandom` | `OBJECT` | Falsa |
| `DEFAULT_SIZE >= this.score`, `WINNING_VALUE > this.size` | `OBJECT` | Falsa |

### Ampliación de la traza (punto 3.3)

Primero se infirió con `BoardTest` y `CellTest` solamente (`daikon-output.txt`). Después se amplió la traza con los tests de EvoSuite (`Board_ESTest`, `Cell_ESTest`) y los de Randoop (`RegressionTest0`), sin `MainCLITest` (`daikon-output-completo.txt`). Se excluyeron `Board_ESTest#test25` y `RegressionTest0#test45`, `test49` y `test54`, porque crean tableros enormes (de 633 x 633 hasta 2048 x 2048) y hacen inviable la traza.

| Invariante | Traza chica | Traza completa | Qué pasó |
|---|---|---|---|
| `this.score one of { 0, 4 }` (`Board:::OBJECT`) | Aparece, falsa | Desaparece | Se corrigió al ver más puntajes |
| `this.size one of { 2, 4, 5 }` (`Board:::OBJECT`) | Aparece, demasiado fuerte | Desaparece | Se corrigió al ver más tamaños |
| `this.score >= 0` (`Board:::OBJECT`) | No aparece | Aparece, correcta y útil | Antes la tapaba el `one of`; es parte de `repOK()` |
| `this.size >= 1` (`Board:::OBJECT`) | No aparece | Aparece, correcta y útil | Antes la tapaba el `one of`; es parte de `repOK()` |
| `this.value == 2` (`mergeWith`, `canMergeWith`) | Aparece, falsa | Desaparece | Ahora se mergean 1, 2 y 4 |
| `return.value == 4` (`mergeWith:::EXIT`) | Aparece, falsa | Reemplazada por `this.value < return.value` y `return.value % this.value == 0` | Más cerca de la regla real (`2 * value`) |
| `randomGeneration.getClass() == MathRandom` (`Board:::OBJECT`) | Aparece, falsa | Desaparece | Aparecen los `DeterministicRandom` |
| `this.score % this.size == 0` (`Board:::OBJECT`) | Aparece, falsa | Sigue, falsa | Coincidencia numérica |
| `WINNING_VALUE > this.score` (`Board:::OBJECT`) | Aparece, falsa | Sigue, falsa | El puntaje puede superar 2048 |
| `this.value is a power of 2` (`Cell:::OBJECT`) | No aparece | No aparece | Solo sale en `repOK()` con `value >= 2` |
| `this.value one of { 1, 2, 4 }` (`mergeWith:::ENTER`) | No aparece | Aparece | Muestra que hay tests con `Cell(1)` |

Ampliar la traza sacó varias invariantes falsas, pero no todas, y dejó aparecer invariantes correctas que antes estaban tapadas. Lo que no se resolvió con más tests es lo que depende de la forma de la propiedad (por ejemplo, la disyunción `value == 0 || value es potencia de 2`) y no de los datos.


## Fase 4: Comparación

### 4.1 Daikon contra `repOK()`

Lo primero que miramos fue qué parte de nuestros `repOK()` volvió a aparecer sola en la salida de Daikon. Con la traza completa, casi todo lo "fácil" está:

| `repOK()` | Lo que infirió Daikon |
|---|---|
| `Cell`: `value >= 0` | `this.value >= 0` |
| `Board`: `size > 0` | `this.size >= 1` |
| `Board`: `score >= 0` | `this.score >= 0` |
| `Board`: `grid != null` | `this.grid != null` |
| `Board`: `grid.length == size` | `this.size == size(this.grid[])` |
| `Board`: ninguna celda es `null` | `this.grid[] elements != null` |

Un detalle que nos llamó la atención: con la traza chica (solo `BoardTest` y `CellTest`) `size > 0` y `score >= 0` no aparecían. Daikon había visto `score` valiendo siempre 0 o 4 y `size` valiendo 2, 4 o 5, y se quedó con esos `one of { ... }`, que son más fuertes y esconden a los otros dos. Recién al sumar los tests de EvoSuite y Randoop, con más puntajes y más tamaños, los `one of` se cayeron y aparecieron las condiciones generales.

Lo que Daikon **no** encontró es justamente lo más interesante de nuestro `repOK()`:

- **Que el valor de una celda sea 0 o una potencia de 2.** Es una disyunción, y los templates de Daikon son propiedades simples, no combinaciones con "o". Lo más cerca que llegó fue `this.value is a power of 2` en el punto de `repOK()` donde ya sabe que `value >= 2`. En `Cell:::OBJECT` solo quedó `value >= 0`.
- **Que cada fila del tablero tenga largo `size`.** Daikon ve el largo de la dimensión externa de `grid[][]`, pero no el de cada fila.
- **Que cada celda cumpla su propio `repOK()`.** Es una propiedad sobre todos los elementos de una estructura anidada, y eso queda fuera de lo que Daikon sabe expresar.

Hay dos cosas que Daikon infirió y que **no están** en nuestros `repOK()`:

1. `this.randomGeneration != null` en `Board:::OBJECT`. Es verdadera de verdad: el constructor `Board(int, IRandom)` tira `IllegalArgumentException` si el generador es `null`. Pero `Board.repOK()` no lo chequea. Es un invariante correcto y útil que nos faltaba, y lo agregamos (ver más abajo).
2. `this.value one of { 1, 2, 4 }` en `mergeWith:::ENTER`. No es un invariante para agregar, pero muestra algo: algún test construye `Cell(1)`, y el constructor lo acepta porque 1 es potencia de 2. En el juego los valores son 0, 2, 4, 8..., así que el 1 es un valor que el programa deja pasar pero que en la práctica no debería existir.

**Cambios en `repOK()`.** A partir de lo que mostró Daikon actualizamos `Board.repOK()`: ahora también devuelve `false` si `randomGeneration == null`. Es exactamente el invariante `this.randomGeneration != null` que Daikon infirió y que el constructor ya exigía, pero que el `repOK()` no verificaba. Con esto, el `repOK()` queda alineado con lo que el constructor garantiza, y las llamadas a `assert repOK()` ahora detectan un `Board` con generador nulo en cualquier punto, no solo al construirlo.

```java
if (randomGeneration == null) {
    return false;
}
```

Después del cambio corrimos toda la suite (`mvn clean test`, 202 tests entre manuales, EvoSuite y Randoop) y siguen pasando todos.

El otro hallazgo, el de `Cell(1)`, **no** lo cambiamos. El `repOK()` de `Cell` pide "cero o potencia de 2", y 1 cumple eso, así que exigir `value >= 2` sería cambiar la especificación del juego y no solo corregir un chequeo olvidado. Queda como una decisión de diseño pendiente.

### 4.2 Daikon contra los otros oráculos

Después de cuatro assignments tenemos cinco fuentes de oráculos, y para este programa en particular se comportaron distinto:

| Fuente | Cómo se produce | Qué nos aportó acá |
|---|---|---|
| Aserciones manuales | A mano | Lo más preciso y con sentido de dominio (`BoardTest`, `CellTest`) |
| `repOK()` | A mano | Reutilizable y siempre chequeado. Es lo único que expresa la potencia de 2 y la forma de la grilla |
| Randoop | Observa una corrida | Aserciones de regresión que dicen "esto fue lo que pasó", sin explicar por qué |
| EvoSuite | Evoluciona para cubrir código | Mucha cobertura, pero también estilo regresión |
| Daikon | Infiere de muchas corridas | Pre y postcondiciones de método casi gratis, y un chequeo que a nosotros se nos había pasado |

Para este programa, lo que más nos sirvió como oráculo fue `repOK()` junto con las aserciones manuales. Son los que dicen cosas con significado, y los que se animan a afirmar propiedades que ninguna ejecución observada alcanza a mostrar.

Daikon quedó en un lugar interesante, como complemento. Sacar contratos de método a partir de los tests existentes fue barato y varios salieron bien (`isEmpty` es verdadero si y solo si `value == 0`, `getCell` exige `0 <= row, col < size`, `mergeWith` exige `this.value == other.value`). Y además sirvió para **cuestionar** nuestro `repOK()`: encontró un chequeo que faltaba y puso en evidencia el caso `Cell(1)`.

Su límite es que todo lo que dice depende de la traza. Con los tests manuales solos infirió cosas que son verdad en esas corridas y falsas en el juego, como `score one of { 0, 4 }` o `this.value == 2` en `mergeWith`. Ampliar la traza ayudó, pero no resolvió todo: `WINNING_VALUE > this.score` sigue apareciendo, y es falso porque el puntaje puede pasar de 2048. Hay que leer la salida de Daikon con ojo crítico, no usarla como especificación.

Un último dato práctico: los tests generados automáticamente fueron un obstáculo para trazar. Algunos de Randoop y de EvoSuite construyen tableros de entre 97 x 97 y 2048 x 2048 celdas, y como Chicory vuelca el estado completo en cada llamada, la traza llegaba a cientos de megas y los tests se colgaban. Tuvimos que excluir cuatro tests para poder trabajar. Es un recordatorio de que un oráculo "automático" no siempre se puede usar tal cual sale de la herramienta.

### Comparación: repOK() vs. invariantes inferidos por Daikon

#### Cell

| Invariantes de repOK() | Invariantes de Daikon |
|---|---|
| `value >= 0` | `this.value >= 0` en `OBJECT` |
| `value == 0` ó `value` es potencia de dos | — (no expresable; infirió `this.value one of { 0, 2, 4 }`, que es falsa) |
| — | `this.value == orig(value)` en `Cell(int):::EXIT` |
| — | `(this.value == 0) <==> (return == true)` en `isEmpty():::EXIT` |
| — | `this.value == other.value` en `mergeWith:::ENTER` |
| — | `return.value == this.value * 2` en `mergeWith:::EXIT` |

#### Board

| Invariantes de repOK() | Invariantes de Daikon |
|---|---|
| `size > 0` | `this.size one of { 2, 4, 5 }` en `OBJECT` (demasiado fuerte) |
| `score >= 0` | — (infirió `DEFAULT_SIZE >= this.score`, no la propiedad directa) |
| `grid != null` y `grid.length == size` | `this.size == size(this.grid[])` en `OBJECT` |
| Cada fila y celda no nula | `this.grid != null`, elementos `!= null` en `OBJECT` |
| Cada celda satisface `cell.repOK()` (potencia de dos) | — (no expresable; Daikon no tiene plantillas recursivas) |
| — | `row >= 0`, `col >= 0`, `row <= size-1`, `col <= size-1` en `getCell:::ENTER` |
| — | `return != null` en `getCell:::EXIT` |

### Phase 4.2 — Comparación de todas las fuentes de oráculos

| Fuente | Cómo se producen los oráculos | Fortalezas | Debilidades |
|---|---|---|---|
| Aserciones manuales | Se escriben a mano según la especificación. | Precisas y significativas. | Requieren tiempo y pueden omitir casos. |
| `repOK()` | Se escribe a mano y se comprueba durante la ejecución. | Reutilizable y se verifica continuamente. | Tiene las mismas limitaciones que las aserciones manuales. |
| Aserciones de regresión de Randoop | Se ejecutó `scripts-configs/runRandoop.sh`, usando Maven, las clases compiladas y `randoop-all-4.3.4.jar`. Randoop generó secuencias de llamadas y guardó los valores observados como aserciones. **No se usó `repOK()` como oráculo de Randoop.** | Automáticas y exploran muchos casos. | Frágiles y no necesariamente significativas. |
| Aserciones de EvoSuite | Se ejecutó `scripts-configs/runEvosuite.sh` con EvoSuite 1.0.6 y un JDK 8. Generó y mutó tests para maximizar la cobertura. Se probaron dos variantes: **sin `repOK()`** y **con `repOK()` como oráculo adicional**. | Automáticas y con buena cobertura; mejoran al contar con `repOK()`. | Suelen ser de regresión y dependen de la calidad de `repOK()`. |
| Invariantes de Daikon | Se instrumentó el programa con Chicory y se generaron trazas `.dtrace`; luego `daikon.jar` infirió invariantes a partir de varias ejecuciones. **Daikon no usó `repOK()` para generar sus invariantes**: sus resultados se compararon con `repOK()` después. | Generalizan el comportamiento observado. | Dependen de la calidad de la traza y pueden ser falsas. |

#### ¿Qué técnica produjo los oráculos más útiles para este programa?

Entre las técnicas estudiadas, **Daikon produjo los oráculos más útiles**, porque infirió invariantes generales y contratos a partir de las trazas de ejecución, en lugar de limitarse a repetir valores observados. Sus resultados deben revisarse, ya que algunos pueden ser demasiado fuertes o falsos si la traza es poco variada. **EvoSuite con `repOK()`** fue el complemento más efectivo para generar tests: la búsqueda evolutiva alcanzó mayor cobertura cuando contó con un invariante semántico. Randoop también fue útil para explorar automáticamente casos límite, aunque sus aserciones de regresión son más frágiles. Los tests manuales y `repOK()` se tomaron como referencia para validar la calidad de estos oráculos.
