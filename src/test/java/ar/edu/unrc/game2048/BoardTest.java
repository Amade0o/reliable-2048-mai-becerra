package ar.edu.unrc.game2048;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

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
 
        //AC
        int gettedSize = board.getSize();
        
        //ASSERT
        assertTrue(gettedSize == expectedSize);
    }

    @Test
    public void getScore(){
        //ARRANGE
        int expectedScore = 0;
        Board board = new Board();
 
        //AC
        int gettedScore = board.getScore();
        
        //ASSERT
        assertTrue(gettedScore == expectedScore);
    }
}


