package ar.edu.unrc.game2048.Strategy;

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
    List<Cell> extractLine(Cell[][] grid, int size, int index);    
    void writeLine(Cell[][] grid, int size, List<Cell> line);
}
