package ar.edu.unrc.game2048.strategy;

import java.util.List;

import ar.edu.unrc.game2048.*;

/*When we execute a move we always have the same behaviour,
we always extract in a List all the columns of the array
and hthen we rewrite the lines using an algorithm.
 Each movement (Up, Down, Left, Right) has a different order
for the extraction and the writing, so we will divide everything 
in strategys
*/
public interface MoveStrategy {
    /*Each move should have an line extractor */
    List<Cell> extractLine(Board board, int index);
    
    /*Each move should have a line writter */
    void writeLine(Board board, int index, List<Cell> line);
}
