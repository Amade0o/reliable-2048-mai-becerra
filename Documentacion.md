Pendientes:
    1. Correr jacoco y pitest con los test de randoop y guardarlo en un record.
    2. Hacer el report.md (25% de nota!!)
---

# Punto 3.3: re-correr con todos los tests (manuales, EvoSuite y Randoop)

Esto es lo que pasó al correr Chicory sobre `BoardTest`, `CellTest`, `Board_ESTest`, `Cell_ESTest` y `RegressionTest0` (sin `MainCLITest`, porque no queríamos ejercitar el CLI).

## Problemas que aparecieron

1. **Tableros gigantes.** Chicory vuelca la grilla entera del `Board` en cada llamada a un método. Hay tests que crean tableros enormes, y con eso la traza llegó a cientos de MB y los tests se colgaban:
   - Randoop: `test54` (`new Board(2048, ...)`, 4 millones de celdas), y `test45` y `test49` (`new Board((int) 'a', ...)`, 97 x 97).
   - EvoSuite: `test25` (tablero de 633 x 633).
2. **Un test con timeout corrompe la traza.** `test25` de EvoSuite tiene `@Test(timeout = 4000)`. Con la instrumentación no llegaba en 4 segundos, JUnit lo abandonaba pero el hilo seguía escribiendo en la traza mezclado con los demás tests, y Daikon fallaba con `Bad modbit`.
3. **Chicory lanza la JVM del programa como proceso hijo.** Si se mata el proceso padre por timeout, el hijo queda vivo escribiendo el `.dtrace.gz` (y bloqueándolo). Hay que matar los `java.exe` huérfanos antes de seguir.
4. **Chicory no trunca un archivo existente.** Si la traza nueva es más corta, queda la cola del archivo viejo. Conviene borrar el `.dtrace.gz` antes de regenerarlo.

## Qué cambiamos

- `RunTests.java` ahora acepta `-x:paquete.Clase#metodo` para excluir un test puntual.
- Se agregó `--ppt-omit-pattern=repOK` a Chicory, para no trazar las llamadas internas a `repOK()`.
- Una traza por clase de test (`--dtrace-file=...`), así un problema en una no pierde las demás.

```powershell
# Ejemplo: Randoop sin los tests con tableros enormes
java -cp $cp daikon.Chicory '--ppt-select-pattern=ar\.edu\.unrc\.game2048\.(Board|Cell)([^A-Za-z0-9_]|$)' `
     --ppt-omit-pattern=repOK --output-dir=daikon-traces --dtrace-file=Randoop.dtrace.gz `
     RunTests -x:randoopTests.RegressionTest0#test45 `
              -x:randoopTests.RegressionTest0#test49 `
              -x:randoopTests.RegressionTest0#test54 `
              randoopTests.RegressionTest0
```

Y para EvoSuite: `-x:ar.edu.unrc.game2048.Board_ESTest#test25`.

## Resultado

| Traza | Tests | Tamaño |
|---|---|---|
| `Manual-CellTest` | 22 de 22 | ~7 KB |
| `Manual-BoardTest` | 47 de 47 | ~223 KB |
| `EvoSuite-Cell` | 25 de 25 | ~3 KB |
| `EvoSuite-Board` | 39 de 39 (sin `test25`) | ~329 KB |
| `Randoop` | 51 de 51 (sin `test45`, `test49`, `test54`) | ~122 KB |

Daikon sobre las cinco: `daikon-output-completo.txt` y `daikon-traces/output-completo.inv.gz`.
