package ar.edu.unrc.game2048;

import java.util.*;

/**
 * Represents the 2048 game board.
 * The board is a square grid of Cells, typically 4x4.
 *
 * Representation Invariants:
 * - grid is a non-null square matrix (rows == cols)
 * - all cells in the grid are non-null (they may be EMPTY)
 * - all cell values are valid per Cell invariants
 * - the board is always in a valid game state
 *
 * Thread-safety: This class is not thread-safe.
 */
public class Board {

    /**
     * Board default number of rows/columns (4 x 4)
     */
    public static final int DEFAULT_SIZE = 4;

    /**
     * Default winning value: when board contains this value, the player wins (2048)
     */
    public static final int WINNING_VALUE = 2048;

    /**
     * Board size (i.e., number of rows and columns). Must be > 0.
     */
    private final int size;

    /**
     * Contents of the board: a 2D array of Cells. grid[row][col] represents the cell at (row, col).
     */
    private final Cell[][] grid;

    /**
     * Game accumulated score.
     */
    private int score;

    /**
     * Creates a new board of the default size (4x4) with two random tiles.
     */
    public Board() {
        this(DEFAULT_SIZE);
    }

    /**
     * Creates a new board of the specified size with two random tiles.
     *
     * @param size the board size (must be > 0)
     * @throws IllegalArgumentException if size <= 0
     */
    public Board(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Board size must be positive: " + size);
        }
        this.size = size;
        this.grid = new Cell[size][size];
        this.score = 0;
        initializeEmpty();
        addRandomTile();
        addRandomTile();
    }

    /**
     * Copy constructor - creates a deep copy of another board.
     *
     * @param other the board to copy
     */
    public Board(Board other) {
        this.size = other.size;
        this.grid = new Cell[size][size];
        this.score = other.score;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                this.grid[r][c] = other.grid[r][c];
            }
        }
    }

    /**
     * Initializes the board with all EMPTY cells.
     */
    private void initializeEmpty() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = Cell.EMPTY;
            }
        }
    }

    /**
     * Gets the board size (number of rows/columns).
     *
     * @return the board size
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the current score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the cell at the specified position.
     *
     * @param row the row index (0-based)
     * @param col the column index (0-based)
     * @return the cell at the specified position
     * @throws IndexOutOfBoundsException if row or col is out of bounds
     */
    public Cell getCell(int row, int col) {
        validatePosition(row, col);
        return grid[row][col];
    }

    /**
     * Sets a cell at the specified position.
     *
     * @param row the row index (0-based)
     * @param col the column index (0-based)
     * @param cell the cell to set (must not be null)
     * @throws IndexOutOfBoundsException if row or col is out of bounds
     * @throws IllegalArgumentException if cell is null
     */
    public void setCell(int row, int col, Cell cell) {
        validatePosition(row, col);
        if (cell == null) {
            throw new IllegalArgumentException("Cell cannot be null");
        }
        grid[row][col] = cell;
    }

    /**
     * Validates that a position is within bounds.
     *
     * @param row the row index
     * @param col the column index
     * @throws IndexOutOfBoundsException if the position is out of bounds
     */
    private void validatePosition(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException(
                    String.format("Position (%d, %d) is out of bounds for board size %d",
                            row, col, size)
            );
        }
    }

    /**
     * Gets all empty cells on the board.
     *
     * @return a set of positions of all empty cells
     */
    public Set<Position> getEmptyPositions() {
        Set<Position> empty = new HashSet<>();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c].isEmpty()) {
                    empty.add(new Position(r, c));
                }
            }
        }
        return empty;
    }

    /**
     * Checks if the board has any empty cells.
     *
     * @return true if there is at least one empty cell
     */
    public boolean hasEmptyCells() {
        return !getEmptyPositions().isEmpty();
    }

    /**
     * Checks if the board is in a winning state.
     * A board is winning if it contains a cell with the WINNING_VALUE (2048).
     *
     * @return true if the board contains 2048
     */
    public boolean isWinningBoard() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c].getValue() == WINNING_VALUE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the board is in a losing state (game over).
     * A board is losing if there are no empty cells AND no adjacent cells
     * (horizontal or vertical) can be merged.
     *
     * @return true if the game is over and the player has lost
     */
    public boolean isLosingBoard() {
        if (hasEmptyCells()) {
            return false;
        }

        // Check for possible merges
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Cell current = grid[r][c];
                // Check right neighbor
                if (c + 1 < size && current.canMergeWith(grid[r][c + 1])) {
                    return false;
                }
                // Check down neighbor
                if (r + 1 < size && current.canMergeWith(grid[r + 1][c])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the board is full (no empty cells).
     *
     * @return true if there are no empty cells
     */
    public boolean isFull() {
        return !hasEmptyCells();
    }

    // ==================== MOVE OPERATIONS (WITH DESIGN PROBLEMS) ====================

    /**
     * Moves all tiles upward.
     *
     * @return true if the board changed, false otherwise
     */
    public boolean moveUp() {
        Board previous = new Board(this);

        // For each column, slide up
        for (int col = 0; col < size; col++) {
            // Create a list of cells from top to bottom
            List<Cell> column = new ArrayList<>();
            for (int row = 0; row < size; row++) {
                column.add(grid[row][col]);
            }

            // Remove empty cells (slide up)
            List<Cell> nonEmpty = new ArrayList<>();
            for (Cell cell : column) {
                if (!cell.isEmpty()) {
                    nonEmpty.add(cell);
                }
            }

            // Merge adjacent equal cells
            List<Cell> merged = new ArrayList<>();
            int i = 0;
            while (i < nonEmpty.size()) {
                if (i + 1 < nonEmpty.size() &&
                        nonEmpty.get(i).canMergeWith(nonEmpty.get(i + 1))) {
                    Cell mergedCell = nonEmpty.get(i).mergeWith(nonEmpty.get(i + 1));
                    merged.add(mergedCell);
                    score += mergedCell.getValue();
                    i += 2;
                } else {
                    merged.add(nonEmpty.get(i));
                    i++;
                }
            }

            // Pad with empty cells
            while (merged.size() < size) {
                merged.add(Cell.EMPTY);
            }

            // Put back into the column
            for (int row = 0; row < size; row++) {
                grid[row][col] = merged.get(row);
            }
        }

        boolean moved = !this.equals(previous);
        if (moved) {
            addRandomTile(); // Add new random tile after successful move
        }
        return moved;
    }

    /**
     * Moves all tiles downward.
     *
     * @return true if the board changed, false otherwise
     */
    public boolean moveDown() {
        Board previous = new Board(this);

        // For each column, slide down
        for (int col = 0; col < size; col++) {
            // Create a list of cells from bottom to top (reverse order)
            List<Cell> column = new ArrayList<>();
            for (int row = size - 1; row > 0; row--) {
                column.add(grid[row][col]);
            }

            // Remove empty cells
            List<Cell> nonEmpty = new ArrayList<>();
            for (Cell cell : column) {
                if (!cell.isEmpty()) {
                    nonEmpty.add(cell);
                }
            }

            // Merge adjacent equal cells
            List<Cell> merged = new ArrayList<>();
            int i = 0;
            while (i < nonEmpty.size()) {
                if (i + 1 < nonEmpty.size() &&
                        nonEmpty.get(i).canMergeWith(nonEmpty.get(i + 1))) {
                    Cell mergedCell = nonEmpty.get(i).mergeWith(nonEmpty.get(i + 1));
                    merged.add(mergedCell);
                    score += mergedCell.getValue();
                    i += 2;
                } else {
                    merged.add(nonEmpty.get(i));
                    i++;
                }
            }

            // Pad with empty cells
            while (merged.size() < size) {
                merged.add(Cell.EMPTY);
            }

            // Put back into the column (reverse back to original order)
            for (int row = size - 1; row >= 0; row--) {
                grid[row][col] = merged.get(size - 1 - row);
            }
        }

        boolean moved = !this.equals(previous);
        if (moved) {
            addRandomTile(); // Add new random tile after successful move
        }
        return moved;
    }

    /**
     * Moves all tiles left.
     *
     * @return true if the board changed, false otherwise
     */
    public boolean moveLeft() {
        Board previous = new Board(this);

        // For each row, slide left
        for (int row = 0; row < size; row++) {
            // Create a list of cells from left to right
            List<Cell> rowList = new ArrayList<>();
            for (int col = 0; col < size; col++) {
                rowList.add(grid[row][col]);
            }

            // Remove empty cells
            List<Cell> nonEmpty = new ArrayList<>();
            for (Cell cell : rowList) {
                if (!cell.isEmpty()) {
                    nonEmpty.add(cell);
                }
            }

            // Merge adjacent equal cells
            List<Cell> merged = new ArrayList<>();
            int i = 0;
            while (i < nonEmpty.size()) {
                if (i + 1 < nonEmpty.size() &&
                        nonEmpty.get(i).canMergeWith(nonEmpty.get(i + 1))) {
                    Cell mergedCell = nonEmpty.get(i).mergeWith(nonEmpty.get(i + 1));
                    merged.add(mergedCell);
                    score += mergedCell.getValue();
                    i += 2;
                } else {
                    merged.add(nonEmpty.get(i));
                    i++;
                }
            }

            // Pad with empty cells
            while (merged.size() < size) {
                merged.add(Cell.EMPTY);
            }

            // Put back into the row
            for (int col = 0; col < size; col++) {
                grid[row][col] = merged.get(col);
            }
        }

        boolean moved = !this.equals(previous);
        if (moved) {
            addRandomTile(); // Add new random tile after successful move
        }
        return moved;
    }

    /**
     * Moves all tiles right.
     *
     * @return true if the board changed, false otherwise
     */
    public boolean moveRight() {
        Board previous = new Board(this);

        // For each row, slide right
        for (int row = 0; row < size; row++) {
            // Create a list of cells from right to left (reverse order)
            List<Cell> rowList = new ArrayList<>();
            for (int col = size - 1; col >= 0; col--) {
                rowList.add(grid[row][col]);
            }

            // Remove empty cells
            List<Cell> nonEmpty = new ArrayList<>();
            for (Cell cell : rowList) {
                if (!cell.isEmpty()) {
                    nonEmpty.add(cell);
                }
            }

            // Merge adjacent equal cells
            List<Cell> merged = new ArrayList<>();
            int i = 0;
            while (i < nonEmpty.size()) {
                if (i + 1 < nonEmpty.size() &&
                        nonEmpty.get(i).canMergeWith(nonEmpty.get(i + 1))) {
                    Cell mergedCell = nonEmpty.get(i).mergeWith(nonEmpty.get(i + 1));
                    merged.add(mergedCell);
                    score += mergedCell.getValue();
                    i += 2;
                } else {
                    merged.add(nonEmpty.get(i));
                    i++;
                }
            }

            // Pad with empty cells
            while (merged.size() < size) {
                merged.add(Cell.EMPTY);
            }

            // Put back into the row (reverse back to original order)
            for (int col = size - 1; col >= 0; col--) {
                grid[row][col] = merged.get(size - 1 - col);
            }
        }

        boolean moved = !this.equals(previous);
        if (moved) {
            addRandomTile(); // Add new random tile after successful move
        }
        return moved;
    }

    // ==================== RANDOM TILE ADDITION (PRIVATE) ====================

    /**
     * Adds a random tile (2 or 4) to a random empty cell.
     * This method is private to maintain encapsulation - tiles are only added
     * during initialization or after successful moves.
     *
     * @return true if a tile was added, false if the board was full
     */
    private boolean addRandomTile() {
        Set<Position> empty = getEmptyPositions();
        if (empty.isEmpty()) {
            return false;
        }

        // Choose random position
        int randomIndex = (int) (Math.random() * empty.size());
        Position pos = empty.stream().skip(randomIndex).findFirst().get();

        // 90% chance of 2, 10% chance of 4 (standard 2048 rules)
        int value = Math.random() < 0.9 ? 2 : 4;
        grid[pos.row][pos.col] = new Cell(value);

        return true;
    }

    // ==================== UTILITY METHODS ====================

    /**
     * Checks if this board is structurally identical to another.
     * Uses deep equality including score.
     *
     * @param o the object to compare
     * @return true if the boards are identical
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return size == board.size &&
                score == board.score &&
                Arrays.deepEquals(grid, board.grid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, Arrays.deepHashCode(grid), score);
    }

    /**
     * Returns a string representation of the board.
     * The board is displayed in a grid format with the current score.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Score: ").append(score).append("\n");
        for (int r = 0; r < size; r++) {
            sb.append("+");
            for (int c = 0; c < size; c++) {
                sb.append("-----+");
            }
            sb.append("\n|");
            for (int c = 0; c < size; c++) {
                String val = grid[r][c].isEmpty() ? "     " :
                        String.format("%5d", grid[r][c].getValue());
                sb.append(val).append("|");
            }
            sb.append("\n");
        }
        sb.append("+");
        for (int c = 0; c < size; c++) {
            sb.append("-----+");
        }
        sb.append("\n");
        return sb.toString();
    }

    // ==================== INNER CLASSES ====================

    /**
     * Represents a direction on the board.
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Represents a position on the board.
     */
    public static class Position {
        public final int row;
        public final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row && col == position.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }
}