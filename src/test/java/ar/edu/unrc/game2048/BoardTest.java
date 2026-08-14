package ar.edu.unrc.game2048;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import ar.edu.unrc.game2048.Board.Position;

public class BoardTest {
    /*Necesary method, wich is used to make manual boards */
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

    /*Private void for creating a 4x4 board with "2" and "4" looking like this:*/
    // 2 4 2 4
    // 4 2 4 2
    // 2 4 2 4
    // 4 2 4 2
    private Board createFullLosingBoard(){
        Board board = createEmptyBoard();
        Cell cell1 = new Cell(2);
        Cell cell2 = new Cell(4);

        //Filling the board with a pattern that makes it impossible to merge any cells
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if((i+j) % 2 == 0){
                    board.setCell(i, j, cell1);
                }else{
                    board.setCell(i, j, cell2);
                }
            }
        }

        return board;
    }


    /*First constructor test*/
    @Test
    public void boardBasicConstructor(){
        //ARRANGE
        Board board = new Board();
        int tilesInicialesEsperadas = 2;
        int sizeEsperado = 4;
        int boardSize = board.getSize();

        //ACT
        Set listaVacias = board.getEmptyPositions();
        int cantidadCeldasVacias = listaVacias.size();
        int cantidadCeldas = sizeEsperado * sizeEsperado;
        
        //ASSERT
        assertTrue(boardSize == sizeEsperado);
        assertTrue(cantidadCeldasVacias == cantidadCeldas-tilesInicialesEsperadas);
    }

    /*Second constructor test*/
    @Test
    public void boardInt(){
        //ARRANGE
        int sizeEsperado = 4;
        int tilesInicialesEsperadas = 2;
        Board board = new Board(sizeEsperado); 
        int size = board.getSize();

        //ACT
        Set listaVacias = board.getEmptyPositions();
        int cantidadCeldasVacias = listaVacias.size();
        int cantidadCeldas = sizeEsperado * sizeEsperado;

        //ASSERT
        //Checks if the board is 4x4
        assert(size == sizeEsperado);
        //Checks if there's two random tiles
        assertTrue(cantidadCeldasVacias == cantidadCeldas-tilesInicialesEsperadas);
    } 

    /*Second constructor test*/
    @Test
    public void copyConstructor(){
        //ARRANGE
        Board board1 = new Board();
        Board board2;

        //ACT
        board2 = new Board(board1);

        //ASSERT
        assertTrue(board1.equals(board2));
    }

    @Test
    public void getSize(){
        //ARRANGE
        int expectedSize = 4;
        Board board = new Board();
 
        //ACT
        int gettedSize = board.getSize();
        
        //ASSERT
        assertTrue(gettedSize == expectedSize);
    }

    @Test
    public void getScore(){
        //ARRANGE
        int expectedScore = 0;
        Board board = new Board();
 
        //ACT
        int gettedScore = board.getScore();
        
        //ASSERT
        assertTrue(gettedScore == expectedScore);
    }

    @Test
    public void getCellInBoard(){
        //Arrange
        Board board = createBoardTest1();
        Cell expectedCell = new Cell(2);
 
        //Act
        Cell gettedCell = board.getCell(1, 1);
        
        //Assert
        assertTrue(gettedCell.equals(expectedCell));
    }

    @Test
    public void getCellOutOfBoard(){
        //Arrange
        Board board = createBoardTest1();
        
        //Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            board.getCell(5, 5);
        });
    }

    @Test
    public void setCellInBoard(){
        //Arrange
        Board board = createBoardTest1();
        Cell expectedCell = new Cell(4);
 
        //Act
        board.setCell(1, 1, expectedCell);
        Cell gettedCell = board.getCell(1, 1);
        
        //Assert
        assertTrue(gettedCell.equals(expectedCell));
    }

    @Test
    public void setCellOutOfBoard(){
        //Arrange
        Board board = createBoardTest1();
        Cell expectedCell = new Cell(4);
        
        //Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            board.setCell(5, 5, expectedCell);
        });
    }

    @Test
    public void setCellNull(){
        //Arrange
        Board board = createBoardTest1();
        
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            board.setCell(1, 1, null);
        });
    }

    @Test
    public void getEmptyPositions(){
        //Arrange
        Board board = createBoardTest1();
        Set<Position> expectedEmptyPositions = new HashSet<>();
        //Primera fila
        expectedEmptyPositions.add(new Position(0, 0));
        expectedEmptyPositions.add(new Position(0, 1));
        expectedEmptyPositions.add(new Position(0, 2));
        expectedEmptyPositions.add(new Position(0, 3));
        //Segunda fila
        expectedEmptyPositions.add(new Position(1, 0));
        expectedEmptyPositions.add(new Position(1, 2));
        expectedEmptyPositions.add(new Position(1, 3));
        //Tercera fila
        expectedEmptyPositions.add(new Position(2, 0));
        expectedEmptyPositions.add(new Position(2, 1));
        expectedEmptyPositions.add(new Position(2, 3));
        //Cuarta fila
        expectedEmptyPositions.add(new Position(3, 0));
        expectedEmptyPositions.add(new Position(3, 1));
        expectedEmptyPositions.add(new Position(3, 2));
        expectedEmptyPositions.add(new Position(3, 3));

        //Act
        Set<Position> gettedEmptyPositions = board.getEmptyPositions();
        
        //Assert
        assertTrue(gettedEmptyPositions.equals(expectedEmptyPositions));
    }

    @Test
    public void hasEmptyCells(){
        //Arrange
        Board board = createBoardTest1();
 
        //Act
        boolean hasEmptyCells = board.hasEmptyCells();
        
        //Assert
        assertTrue(hasEmptyCells);
    }

    @Test
    public void hasNoEmptyCells(){
        //Arrange
        Board board = new Board();
        int size = board.getSize();

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                Cell cell = new Cell(2);
                board.setCell(i, j, cell);
            }
        }

        //Act
        boolean hasEmptyCells = board.hasEmptyCells();
        
        //Assert
        assertFalse(hasEmptyCells);
    }

    @Test
    public void isWinningBoard(){
        //Arrange
        Board board = createEmptyBoard();
        Cell cell1 = new Cell(2048);
        board.setCell(1, 1, cell1);

        //Act
        boolean isWinningBoard = board.isWinningBoard();
        
        //Assert
        assertTrue(isWinningBoard);
    }

    @Test
    public void isNotWinningBoard(){
        //Arrange
        Board board = createEmptyBoard();
        Cell cell1 = new Cell(2);
        board.setCell(1, 1, cell1);

        //Act
        boolean isWinningBoard = board.isWinningBoard();
        
        //Assert
        assertFalse(isWinningBoard);
    }

    @Test
    public void emptyIsNotLosingBoard(){
        //Arrange
        Board board = createEmptyBoard();
        //Act
        boolean isLosingBoard = board.isLosingBoard();
        //Assert
        assertFalse(isLosingBoard);
    }

    @Test
    public void upMergeIsNotLosingBoard(){
        //Arrange
        Board board = createFullLosingBoard();
        Cell cell1 = new Cell(2);
        Cell cell2 = new Cell(8);
        //Set this board to look like this (there is only an up merge possible):
        // 2 4 2 4
        // 2 8 4 2
        // 8 4 2 4
        // 4 2 4 2
        board.setCell(1, 0, cell1);
        board.setCell(2, 0, cell2);
        board.setCell(1, 1, cell2);

        //Act
        boolean isLosingBoard = board.isLosingBoard();

        //Assert
        assertFalse(isLosingBoard);
    }

    @Test
    public void rightMergeIsNotLosingBoard(){
        //Arrange
        Board board = createFullLosingBoard();
        Cell cell1 = new Cell(2);
        Cell cell2 = new Cell(8);
        //Set this board to look like this (there is only an right merge possible):
        // 2 4 2 2
        // 4 2 4 8
        // 2 4 2 4
        // 4 2 4 2
        board.setCell(0, 3, cell1);
        board.setCell(1, 3, cell2);

        //Act
        boolean isLosingBoard = board.isLosingBoard();

        //Assert
        assertFalse(isLosingBoard);
    }

    @Test
    public void downMergeIsNotLosingBoard(){
        //Arrange
        Board board = createFullLosingBoard();
        Cell cell1 = new Cell(2);
        Cell cell2 = new Cell(8);
        //Set this board to look like this (there is only an down merge possible):
        // 2 4 2 4
        // 4 2 4 2
        // 2 4 2 4
        // 2 8 4 2
        board.setCell(3, 0, cell1);
        board.setCell(3, 1, cell2);

        //Act
        boolean isLosingBoard = board.isLosingBoard();

        //Assert
        assertFalse(isLosingBoard);
    }

    @Test
    public void leftMergeIsNotLosingBoard(){
        //Arrange
        Board board = createFullLosingBoard();
        Cell cell1 = new Cell(2);
        Cell cell2 = new Cell(8);
        //Set this board to look like this (there is only an left merge possible):
        // 2 4 2 4
        // 4 2 4 2
        // 8 4 2 4
        // 2 2 4 2
        board.setCell(3, 0, cell1);
        board.setCell(2, 0, cell2);

        //Act
        boolean isLosingBoard = board.isLosingBoard();

        //Assert
        assertFalse(isLosingBoard);
    }
}


