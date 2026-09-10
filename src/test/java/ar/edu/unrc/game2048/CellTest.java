package ar.edu.unrc.game2048;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CellTest {
    
    @Test
    public void CellConstructorValid(){
        //arrange
        int value = 2;

        //act
        Cell cell = new Cell(value);

        //assert
        assertNotNull(cell);
    }

    @Test
    public void CellConstructorNegative(){
        //arrange
        int value = -2;

        //assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Cell(value);
        });
    }

    @Test
    public void CellConstructorNotPowerOfTwo(){
        //arrange
        int value = 3;

        //assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Cell(value);
        });
    }

    @Test
    public void isEmpty(){
        //Arrange
        int value = 0;
        Cell cell = new Cell(value);

        //Act
        Boolean isEmpty = cell.isEmpty();

        //Assert
        assertTrue(isEmpty);
    }

    @Test
    public void isNotEmpty(){
        //Arrange
        int value = 2;
        Cell cell = new Cell(value);

        //Act
        boolean isEmpty = cell.isEmpty();

        //Assert
        assertFalse(isEmpty);
    }

    @Test
    public void getValue(){
        //Arrange
        int value = 2;
        Cell cell = new Cell(value);

        //Act
        int obtainedValue = cell.getValue();

        //Assert
        assertEquals(value, obtainedValue);
    }

    @Test
    public void twoCellsCanMerge(){
        //Arrange
        int value1 = 2;
        int value2 = 2;
        Cell cell1 = new Cell(value1);
        Cell cell2 = new Cell(value2);

        //Act
        boolean canMerge = cell1.canMergeWith(cell2);

        //Assert
        assertTrue(canMerge);
    }

    @Test
    public void differentCellsCannotMerge(){
        //Arrange
        int value1 = 2;
        int value2 = 4;
        Cell cell1 = new Cell(value1);
        Cell cell2 = new Cell(value2);

        //Act
        boolean canMerge = cell1.canMergeWith(cell2);

        //Assert
        assertFalse(canMerge);
    } 

    @Test
    public void twoEmptyCellsCannotMerge(){
        //Arrange
        int value1 = 0;
        int value2 = 0;
        Cell cell1 = new Cell(value1);
        Cell cell2 = new Cell(value2);

        //Act
        boolean canMerge = cell1.canMergeWith(cell2);

        //Assert
        assertFalse(canMerge);
    }

    @Test
    public void oneEmptyCellCannotMerge(){
        //Arrange
        int value1 = 0;
        int value2 = 2;
        Cell cell1 = new Cell(value1);
        Cell cell2 = new Cell(value2);

        //Act
        boolean canMerge = cell1.canMergeWith(cell2);

        //Assert
        assertFalse(canMerge);
    }

    @Test
    public void secondEmptyCellsCannotMerge(){
        //Arrange
        int value1 = 2;
        int value2 = 0;
        Cell cell1 = new Cell(value1);
        Cell cell2 = new Cell(value2);

        //Act
        boolean canMerge = cell1.canMergeWith(cell2);

        //Assert
        assertFalse(canMerge);
    }

    @Test
    public void twoValidCellsMerge(){
        //Arrange
        int value1 = 2;
        int value2 = 2;
        Cell cell1 = new Cell(value1);
        Cell cell2 = new Cell(value2);

        //Act
        Cell mergedCell = cell1.mergeWith(cell2);

        //Assert
        assertEquals(cell1.getValue() * 2, mergedCell.getValue());
    }

    @Test
    public void twoDifferentCellsCannotMerge(){
        //Arrange
        int value1 = 2;
        int value2 = 4;
        Cell cell1 = new Cell(value1);
        Cell cell2 = new Cell(value2);

        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cell1.mergeWith(cell2);
        });
    }

    @Test
    public void twoCellsAreEquals(){
        //Arrange
        int value1 = 2;
        int value2 = 2;
        Cell cell1 = new Cell(value1);
        Cell cell2 = new Cell(value2);

        //Act
        boolean areEquals = cell1.equals(cell2);

        //Assert
        assertTrue(areEquals);
    }

    @Test
    public void twoObjectsAreNotEqualCells(){
        //Arrage
        int value1 = 2;
        Cell cell1 = new Cell(value1);
        Board object = new Board();

        //Act
        boolean areEquals = cell1.equals(object);

        //Assert
        assertFalse(areEquals);
    }

    @Test
    public void sameHashCode(){
        //Arrange
        int value1 = 2;
        int value2 = 2;
        Cell cell1 = new Cell(value1);
        Cell cell2 = new Cell(value2);

        //Act
        int hash1 = cell1.hashCode();
        int hash2 = cell2.hashCode();

        //Assert
        assertEquals(hash1, hash2);
    }

    @Test
    public void differentHashCode(){
        //Arrange
        int value1 = 2;
        int value2 = 4;
        Cell cell1 = new Cell(value1);
        Cell cell2 = new Cell(value2);

        //Act
        int hash1 = cell1.hashCode();
        int hash2 = cell2.hashCode();

        //Assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    public void nonEmptyCellToString(){
        //Arrange
        int value = 2;
        Cell cell = new Cell(value);

        //Act
        String cellToString = cell.toString();

        //Assert
        assertEquals(String.valueOf(value), cellToString);
    }

    @Test
    public void emptyCellToString(){
        //Arrange
        int value = 0;
        Cell cell = new Cell(value);

        //Act
        String cellToString = cell.toString();

        //Assert
        assertEquals(".", cellToString);
    }
}
