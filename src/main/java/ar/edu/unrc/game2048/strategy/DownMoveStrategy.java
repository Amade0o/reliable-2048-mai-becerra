package ar.edu.unrc.game2048.strategy;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unrc.game2048.Board;
import ar.edu.unrc.game2048.Cell;

/*Moves the tiles downwards: the line is a column, read from bottom to top */
public class DownMoveStrategy implements MoveStrategy{

    @Override
    public List<Cell> extractLine(Board board, int index){
        List<Cell> column = new ArrayList<>();
            for (int row = board.getSize() - 1; row >= 0; row--) {
                column.add(board.getCell(row, index));
            }
        return column;
    }

    @Override
    public void writeLine(Board board, int column, List<Cell> line){
        for (int row = board.getSize() - 1; row >= 0; row--) {
            board.setCell(row, column, line.get(board.getSize() - 1 - row));
        }
    }
}
