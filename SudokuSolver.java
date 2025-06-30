import java.util.Scanner;

public class SudokuSolver {
    public static final int SIZE = 9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[SIZE][SIZE];

        System.out.println("Enter the Sudoku puzzle (use 0 for empty cells):");

        for (int i = 0; i < SIZE; i++) {
            System.out.print("Enter row " + (i + 1) + " (9 numbers with spaces): ");
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        System.out.println("\nSolving Sudoku...\n");

        if (solve(board)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists for the given Sudoku.");
        }
    }

    public static boolean solve(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;

                            if (solve(board)) {
                                return true;
                            }

                            board[row][col] = 0; // backtrack
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isSafe(int[][] board, int row, int col, int num) {

        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num)
                return false;
        }


        for (int x = 0; x < SIZE; x++) {
            if (board[x][col] == num)
                return false;
        }


        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }

    public static void printBoard(int[][] board) {
        System.out.println("Solved Sudoku:\n");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
