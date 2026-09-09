package ar.edu.unrc.game2048.strategy;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unrc.game2048.Board;
import ar.edu.unrc.game2048.Cell;

/*Moves the tiles upwards: the line is a column, read from top to bottom */
public class UpMoveStrategy implements MoveStrategy{

    @Override
    public List<Cell> extractLine(Board board, int index){
        List<Cell> column = new ArrayList<>();
            for (int row = 0; row < board.getSize(); row++) {
                column.add(board.getCell(row, index));
            }
        return column;
    }

    @Override
    public void writeLine(Board board, int column, List<Cell> line){
        for (int row = 0; row < board.getSize(); row++) {
            board.setCell(row, column, line.get(row));
        }
    }
}
