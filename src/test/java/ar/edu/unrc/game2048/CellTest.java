package ar.edu.unrc.game2048;

import org.junit.Test;
import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void testEmptyCell() {
        Cell cell = new Cell(0);
        assertTrue(cell.isEmpty());
        assertEquals(0, cell.getValue());
        assertEquals(Cell.EMPTY, cell);
    }

    @Test
    public void testValidCell() {
        Cell cell2 = new Cell(2);
        assertFalse(cell2.isEmpty());
        assertEquals(2, cell2.getValue());
    }

}
