package ar.edu.unrc.game2048;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;
import uk.org.webcompere.systemstubs.stream.SystemIn;
import uk.org.webcompere.systemstubs.stream.SystemOut;
import uk.org.webcompere.systemstubs.stream.input.LinesAltStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Ejemplos de cómo simular la entrada por consola de {@link MainCLI} usando System Stubs.
 *
 * La extensión {@link SystemStubsExtension} se encarga de:
 *  - reemplazar System.in / System.out antes de cada test
 *  - restaurar los originales al terminar (aunque el test falle)
 */
@ExtendWith(SystemStubsExtension.class)
class MainCLITest {

    // Los campos anotados con @SystemStub se activan automáticamente para cada test.
    @SystemStub
    private SystemIn systemIn;

    @SystemStub
    private SystemOut systemOut;

    private Board createEmptyBoard(){
        Board board = new Board();
        int size = board.getSize();

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                Cell cell = new Cell(0);
                board.setCell(i, j, cell);
            }
        }

        return board;
    }

    /*Private void for creating a 4x4 board with two "2" looking like this:*/
    // _ _ _ _
    // _ 2 _ _
    // _ _ 2 _
    // _ _ _ _
    private Board createBoardTest1(){
        Board board = createEmptyBoard();
        Cell cell1 = new Cell(2);
        Cell cell2 = new Cell(2);

        board.setCell(1, 1, cell1);
        board.setCell(2, 2, cell2);

        return board;
    }

    @Test
    void salirInmediatamenteConQ() {
        // Cada elemento = una línea que devolverá scanner.nextLine()
        systemIn.setInputStream(new LinesAltStream("Q"));

        new MainCLI().play();

        assertTrue(systemOut.getText().contains("Thanks for playing!"));
    }

    @Test
    void secuenciaDeMovimientosYLuegoSalir() {
        systemIn.setInputStream(new LinesAltStream("A", "S", "D", "W", "Q"));
    
        // _ _ _ _
        // _ 2 _ _
        // _ _ 2 _
        // _ _ _ _
        Board board = createBoardTest1();
        String tableroEsperado = board.toString();

        new MainCLI(board).play();

        String salida = systemOut.getText();
        assertTrue(salida.contains("=== 2048 Game ==="));
        assertTrue(salida.contains("Controls: W(up), S(down), A(left), D(right), Q(quit)"));
        assertTrue(salida.split(System.lineSeparator(), -1)[2].isEmpty());
        
        assertTrue(salida.contains(tableroEsperado));
    }

    @Test
    void entradaInvalidaMuestraMensajeDeError() {
        systemIn.setInputStream(new LinesAltStream("X", "Q"));

        new MainCLI().play();

        assertTrue(systemOut.getText().contains("Invalid input!"));
    }
}
