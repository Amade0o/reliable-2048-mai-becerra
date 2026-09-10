package ar.edu.unrc.game2048;

import java.util.Scanner;

/**
 * Command-line interface for the 2048 game.
 * 
 * This class handles user input and displays the board state.
 * It demonstrates how to use the Board class and its methods.
 */
public class MainCLI {
    private final Board board;
    private final Scanner scanner;
    
    public MainCLI() {
        this.board = new Board();
        this.scanner = new Scanner(System.in);
    }

    public MainCLI(Board board) {
        this.board = board;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main game loop.
     */
    public void play() {
        System.out.println("=== 2048 Game ===");
        System.out.println("Controls: W(up), S(down), A(left), D(right), Q(quit)");
        System.out.println();
        
        while (true) {
            displayBoard();
            
            if (board.isWinningBoard()) {
                System.out.println("🎉 Congratulations! You reached 2048! 🎉");
                System.out.println("You can continue playing or quit.");
            }
            
            if (board.isLosingBoard()) {
                System.out.println("💀 Game Over! No more moves available. 💀");
                System.out.println("Final score: " + board.getScore());
                break;
            }
            
            System.out.print("Enter move: ");
            String input = scanner.nextLine().trim().toUpperCase();
            
            if (input.equals("Q")) {
                System.out.println("Thanks for playing! Final score: " + board.getScore());
                break;
            }
            
            boolean moved = false;
            switch (input) {
                case "W": moved = board.moveUp(); break;
                case "S": moved = board.moveDown(); break;
                case "A": moved = board.moveLeft(); break;
                case "D": moved = board.moveRight(); break;
                default: 
                    System.out.println("Invalid input! Use W, A, S, D, or Q.");
                    continue;
            }
            
            if (moved) {
                System.out.println("Tile moved!");
            } else {
                System.out.println("No tiles moved. Try a different direction.");
            }
            
            System.out.println();
        }
        
        scanner.close();
    }
    
    /**
     * Displays the current board state.
     */
    private void displayBoard() {
        System.out.println(board); 
    }
    
    public static void main(String[] args) {
        MainCLI game = new MainCLI();
        game.play();
    }

    public Board getBoard(){
        return board;
    }
}
