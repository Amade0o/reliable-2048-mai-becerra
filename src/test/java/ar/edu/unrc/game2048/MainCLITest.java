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
    
        new MainCLI().play();

        String salida = systemOut.getText();
        assertTrue(salida.contains("=== 2048 Game ==="));
        assertTrue(salida.contains("Controls: W(up), S(down), A(left), D(right), Q(quit)"));
        assertTrue(salida.split(System.lineSeparator(), -1)[2].isEmpty());
        
    }

    @Test
    void entradaInvalidaMuestraMensajeDeError() {
        systemIn.setInputStream(new LinesAltStream("X", "Q"));

        new MainCLI().play();

        assertTrue(systemOut.getText().contains("Invalid input!"));
    }
}
