package ar.edu.unrc.game2048;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;
import uk.org.webcompere.systemstubs.stream.SystemIn;
import uk.org.webcompere.systemstubs.stream.SystemOut;
import uk.org.webcompere.systemstubs.stream.input.LinesAltStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    /*Private void for creating a winning board looking like this:*/
    // _ _ _ _
    // _ 2048 _ _
    // _ _ _ _
    // _ _ _ _
    private Board createBoardTest2(){
        Board board = createEmptyBoard();
        Cell cell1 = new Cell(2048);

        board.setCell(1, 1, cell1);
        
        return board;
    }

    /*Tablero con una única celda "2" en la esquina superior izquierda, el resto vacío:*/
    // 2 _ _ _
    // _ _ _ _
    // _ _ _ _
    // _ _ _ _
    // Moverla para arriba o izquierda la deja igual (no se mueve),
    // moverla para abajo o derecha sí la reubica (se mueve).
    private Board createBoardWithSingleTopLeftTile(){
        Board board = createEmptyBoard();
        board.setCell(0, 0, new Cell(2));

        return board;
    }

    /*Tablero con una celda "2048" y celdas libres, para que el juego esté ganado pero no terminado todavía:*/
    // 2048 _ _ _
    // _ _ _ _
    // _ _ _ _
    // _ _ _ _
    private Board createWinningBoardWithFreeCells(){
        Board board = createEmptyBoard();
        board.setCell(0, 0, new Cell(2048));

        return board;
    }

    /*Tablero 4x4 lleno sin merges posibles, para que el juego termine enseguida:*/
    // 2 4 2 4
    // 4 2 4 2
    // 2 4 2 4
    // 4 2 4 2
    private Board createFullLosingBoard(){
        Board board = createEmptyBoard();
        Cell cell1 = new Cell(2);
        Cell cell2 = new Cell(4);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i + j) % 2 == 0) {
                    board.setCell(i, j, cell1);
                } else {
                    board.setCell(i, j, cell2);
                }
            }
        }

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

    @Test
    void secuenciaDeMovimientosConWinningBoard() {
        systemIn.setInputStream(new LinesAltStream("Q"));
    
        //    _ _ _ _
        // _ 2048 _ _
        // _ _ _ _
        // _ _ _ _
        Board board = createBoardTest2();
        String tableroEsperado = board.toString();

        new MainCLI(board).play();

        String salida = systemOut.getText();
        assertTrue(salida.contains("=== 2048 Game ==="));
        assertTrue(salida.contains("Controls: W(up), S(down), A(left), D(right), Q(quit)"));
        assertTrue(salida.split(System.lineSeparator(), -1)[2].isEmpty());
        
        assertTrue(salida.contains(tableroEsperado));

        assertTrue(salida.contains("Congratulations! You reached 2048!"));
        assertTrue(salida.contains("You can continue playing or quit."));
    }

    @Test
    void muestraMensajePedidoDeMovimiento() {
        systemIn.setInputStream(new LinesAltStream("Q"));

        new MainCLI().play();

        assertTrue(systemOut.getText().contains("Enter move: "));
    }

    @Test
    void tableroGanadorMuestraMensajeDeFelicitaciones() {
        systemIn.setInputStream(new LinesAltStream("Q"));

        Board board = createWinningBoardWithFreeCells();
        new MainCLI(board).play();

        String salida = systemOut.getText();
        assertTrue(salida.contains("🎉 Congratulations! You reached 2048! 🎉"));
        assertTrue(salida.contains("You can continue playing or quit."));
    }

    @Test
    void tableroNoGanadorNoMuestraMensajeDeFelicitaciones() {
        systemIn.setInputStream(new LinesAltStream("Q"));

        Board board = createBoardTest1();
        new MainCLI(board).play();

        assertFalse(systemOut.getText().contains("Congratulations"));
    }

    @Test
    void tableroPerdedorMuestraGameOverYPuntajeFinal() {
        Board board = createFullLosingBoard();

        new MainCLI(board).play();

        String salida = systemOut.getText();
        assertTrue(salida.contains("💀 Game Over! No more moves available. 💀"));
        assertTrue(salida.contains("Final score: " + board.getScore()));
    }

    @Test
    void moverArribaConCeldaEnEsquinaSuperiorIzquierdaNoMueve() {
        systemIn.setInputStream(new LinesAltStream("W", "Q"));

        new MainCLI(createBoardWithSingleTopLeftTile()).play();

        String salida = systemOut.getText();
        assertTrue(salida.contains("No tiles moved. Try a different direction." + System.lineSeparator() + System.lineSeparator()));
        assertFalse(salida.contains("Tile moved!"));
    }

    @Test
    void moverIzquierdaConCeldaEnEsquinaSuperiorIzquierdaNoMueve() {
        systemIn.setInputStream(new LinesAltStream("A", "Q"));

        new MainCLI(createBoardWithSingleTopLeftTile()).play();

        String salida = systemOut.getText();
        assertTrue(salida.contains("No tiles moved. Try a different direction." + System.lineSeparator() + System.lineSeparator()));
        assertFalse(salida.contains("Tile moved!"));
    }

    @Test
    void moverAbajoConCeldaEnEsquinaSuperiorIzquierdaMueve() {
        systemIn.setInputStream(new LinesAltStream("S", "Q"));

        new MainCLI(createBoardWithSingleTopLeftTile()).play();

        String salida = systemOut.getText();
        assertTrue(salida.contains("Tile moved!" + System.lineSeparator() + System.lineSeparator()));
        assertFalse(salida.contains("No tiles moved"));
    }

    @Test
    void moverDerechaConCeldaEnEsquinaSuperiorIzquierdaMueve() {
        systemIn.setInputStream(new LinesAltStream("D", "Q"));

        new MainCLI(createBoardWithSingleTopLeftTile()).play();

        String salida = systemOut.getText();
        assertTrue(salida.contains("Tile moved!" + System.lineSeparator() + System.lineSeparator()));
        assertFalse(salida.contains("No tiles moved"));
    }

    @Test
    void getBoardDevuelveElTableroUsado() {
        Board board = createBoardTest1();
        MainCLI game = new MainCLI(board);

        assertNotNull(game.getBoard());
        assertTrue(game.getBoard() == board);
    }

    @Test
    void scannerSeCierraLuegoDeSalir() {
        systemIn.setInputStream(new LinesAltStream("Q", "Q"));

        MainCLI game = new MainCLI();
        game.play();

        assertThrows(IllegalStateException.class, game::play);
    }
}
