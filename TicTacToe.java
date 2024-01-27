package pkg;
import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
	private static char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    private static char currentPlayer;
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    

    public static void main(String[] args) {
    	 Scanner scanner = new Scanner(System.in);

         System.out.println("Welcome to Tic Tac Toe!");

         // Choose opponent
         System.out.println("Select your opponent: ");
         System.out.println("1. Play against a friend");
         System.out.println("2. Play against the computer");
         int choice = scanner.nextInt();

         if (choice == 1) {
             playAgainstFriend();
         } else if (choice == 2) {
             playAgainstComputer();
         } else {
             System.out.println("Invalid choice. Exiting the game.");
         }

         scanner.close();
     }

     private static void playAgainstFriend() {
         currentPlayer = PLAYER_X;

         while (true) {
             printBoard();
             makeMove();
             if (checkWin()) {
                 printBoard();
                 System.out.println("Player " + currentPlayer + " wins!");
                 break;
             }
             if (isBoardFull()) {
                 printBoard();
                 System.out.println("It's a draw!");
                 break;
             }
             switchPlayer();
         }
     }

     private static void playAgainstComputer() {
         currentPlayer = PLAYER_X;

         while (true) {
             if (currentPlayer == PLAYER_X) {
                 printBoard();
                 makeMove();
             } else {
                 makeComputerMove();
             }

             if (checkWin()) {
                 printBoard();
                 if (currentPlayer == PLAYER_X) {
                     System.out.println("You win!");
                 } else {
                     System.out.println("Computer wins!");
                 }
                 break;
             }
             if (isBoardFull()) {
                 printBoard();
                 System.out.println("It's a draw!");
                 break;
             }
             switchPlayer();
         }
     }

     private static void printBoard() {
         System.out.println("-------------");
         for (int i = 0; i < 3; i++) {
             System.out.print("| ");
             for (int j = 0; j < 3; j++) {
                 System.out.print(board[i][j] + " | ");
             }
             System.out.println();
             System.out.println("-------------");
         }
     }

     private static void makeMove() {
         Scanner scanner = new Scanner(System.in);
         int row, col;

         do {
             System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
             row = scanner.nextInt() - 1;
             col = scanner.nextInt() - 1;
         } while (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ');

         board[row][col] = currentPlayer;
     }

     private static void makeComputerMove() {
         System.out.println("Computer's move:");

         // Simple computer AI: Randomly choose an empty cell
         Random random = new Random();
         int row, col;

         do {
             row = random.nextInt(3);
             col = random.nextInt(3);
         } while (board[row][col] != ' ');

         board[row][col] = currentPlayer;
     }

     private static void switchPlayer() {
         currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
     }

     private static boolean checkWin() {
         // Check rows, columns, and diagonals for a win
         for (int i = 0; i < 3; i++) {
             if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                 return true; // Check rows
             }
             if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                 return true; // Check columns
             }
         }

         if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
             return true; // Check diagonal (top-left to bottom-right)
         }

         return board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer; // Check diagonal (top-right to bottom-left)
     }

     private static boolean isBoardFull() {
         // Check if the board is full
         for (int i = 0; i < 3; i++) {
             for (int j = 0; j < 3; j++) {
                 if (board[i][j] == ' ') {
                     return false;
                 }
             }
         }
         return true;
     }
 }