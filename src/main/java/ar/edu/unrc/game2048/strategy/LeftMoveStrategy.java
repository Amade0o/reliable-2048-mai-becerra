package ar.edu.unrc.game2048.strategy;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unrc.game2048.Board;
import ar.edu.unrc.game2048.Cell;

/*Moves the tiles to the left: the line is a row, read from left to right */
public class LeftMoveStrategy implements MoveStrategy{

    @Override
    public List<Cell> extractLine(Board board, int index){
        List<Cell> rowList = new ArrayList<>();
            for (int col = 0; col < board.getSize(); col++) {
                rowList.add(board.getCell(index, col));
            }
        return rowList;
    }

    @Override
    public void writeLine(Board board, int row, List<Cell> line){
        for (int col = 0; col < board.getSize(); col++) {
            board.setCell(row, col, line.get(col));
        }
    }
}
