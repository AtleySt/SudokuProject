import java.util.Scanner;

public class SudokuBoard {
    private int[][] board;
    private int boardSize;
    private int boxSize;
    private int[][] compBoard;
    private int mistakes;

    public SudokuBoard(int boardSize) {
        this.boardSize = boardSize;
        board = new int[boardSize][boardSize];
        this.boxSize = (int) Math.sqrt(boardSize);
        this.board = new int[][] {
        {0,0,5,6,0,0,0,8,4},
        {0,2,0,5,4,0,6,0,3},
        {9,0,6,8,0,0,2,0,7},
        {0,7,0,0,2,0,5,0,0},
        {5,0,1,0,0,0,4,0,9},
        {0,6,0,0,0,0,3,0,0},
        {0,0,0,0,0,0,0,9,0},
        {0,1,2,0,0,0,7,0,0},
        {0,0,4,1,0,0,0,3,0}};
        this.compBoard = new int[][] {
            {1,3,5,6,7,2,9,8,4},
            {7,2,8,5,4,9,6,1,3},
            {9,4,6,8,3,1,2,5,7},
            {4,7,3,9,2,8,5,6,1},
            {5,8,1,7,6,3,4,2,9},
            {2,6,9,4,1,5,3,7,8},
            {3,5,7,2,8,4,1,9,6},
            {8,1,2,3,9,6,7,4,5},
            {6,9,4,1,5,7,8,3,2}
        };
    }

    public boolean isValid(int row, int col, int num) {
        // check square
        if (board[row][col] != 0) {
            return false;
        }

        // check col
        for (int r = 0; r < boardSize; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }

        // check row
        for (int c = 0; c < boardSize; c++) {
            if (board[row][c] == num) {
                return false;
            }
        }

        // check 3x3 cell
        int boxRow = row - row % boxSize;
        int boxCol = col - col % boxSize;
        for (int i = boxRow; i < boxRow + boxSize; i++) {
            for (int j = boxCol; j < boxCol + boxSize; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        if (compBoard[row][col] == num) {
            return true;
        } else {
            return false;
        }

    }

    public void makeMove(int row, int col, int num) {
        if (isValid(row, col, num)) {
            this.board[row][col] = num;
        } else {
            System.out.println("invalid move");
            mistakes++;
        }
    }

    public boolean getGameStatus() {
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (board[r][c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        System.out.println("   1 2 3   4 5 6   7 8 9");
        for (int r = 0; r < boardSize; r++) {
            System.out.print(r+1+"  ");
            for (int c = 0; c < boardSize; c++) {
                if (board[r][c] == 0) {
                    System.out.print("* ");
                } else {
                    System.out.print(board[r][c]+" ");
                }
                if (c == 2 || c == 5) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (r == 2 || r == 5) {
                System.out.print("----------------------");
                System.out.println();
            }
        }
    }

    public int getMistakes() {
        return mistakes;
    }

    public static void main(String[] args) {
        SudokuBoard game = new SudokuBoard(9);
        Scanner s = new Scanner(System.in);
        
        while (!game.getGameStatus() && game.getMistakes() < 3) {
            game.printBoard();
            System.out.println("What row");
            int row = s.nextInt();
            row -= 1;
            System.out.println("What column");
            int col = s.nextInt();
            col -= 1;
            System.out.println("What number goes there");
            int num = s.nextInt();
            game.makeMove(row, col, num);
        }
        if (game.getMistakes() < 3) {
            System.out.println("You have completed the Sudoku with " + game.getMistakes() + " mistakes!");
        } else if (game.getMistakes() >= 3) {
            System.out.println("You have failed the Sudoku by committing "+game.getMistakes()+" mistakes...");
        }
    }
}