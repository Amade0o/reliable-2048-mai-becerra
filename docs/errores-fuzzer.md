## 1. MainCLI crasheaba con tableros inválidos
Cualquier tablero inválido (filas de menos, celdas que no son potencia de 2, texto raro, tamaño no numérico) tiraba una excepción sin capturar y el programa moría con stack trace. Era el 99% de las pruebas inválidas.

**Arreglo:** `MainCLI` captura `IllegalArgumentException` (incluye `NumberFormatException`), imprime `Invalid board: <mensaje>` y termina. (Arnés)

## 2. Board aceptaba celdas con signo `+`
`+32` pasaba como celda válida porque `Integer.parseInt` acepta el `+`.

**Arreglo:** `Board(int, String)` exige que cada celda cumpla `[0-9]+`.

## 3. Board aceptaba comas o `;` de más al final
Un tablero como `...,0;` o una fila terminada en `,` se aceptaba, porque `split` de Java descarta los vacíos del final.

**Arreglo:** se usa `split(..., -1)` para que cuenten.

## Nota
Los errores 2 y 3 aparecieron recién después de arreglar el 1, porque antes el crash los tapaba.
