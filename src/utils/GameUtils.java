package TicTacToeGame.src.utils;

public class GameUtils {
    private static int playerXScore = 0;
    private static int playerOScore = 0;
    private static boolean isPlayerXTurn = true; // true for Player X, false for Player O

    public static boolean checkWinner(String[][] board) {
        System.out.println("Checking board state:");
        for (int i = 0; i < 3; i++) {
            System.out.println(board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
        }

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2])) {
                System.out.println("Row " + i + " has a winner: " + board[i][0]);
                updateScore(board[i][0]);
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != null && board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i])) {
                System.out.println("Column " + i + " has a winner: " + board[0][i]);
                updateScore(board[0][i]);
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) {
            System.out.println("Diagonal (0,0) to (2,2) has a winner: " + board[0][0]);
            updateScore(board[0][0]);
            return true;
        }
        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])) {
            System.out.println("Diagonal (0,2) to (2,0) has a winner: " + board[0][2]);
            updateScore(board[0][2]);
            return true;
        }

        return false;
    }

    public static boolean isBoardFull(String[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void resetBoard(String[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = null;
            }
        }
    }

    public static void updateScore(String winner) {
        if ("X".equals(winner)) {
            playerXScore++;
        } else if ("O".equals(winner)) {
            playerOScore++;
        }
        System.out.println("Updated scores: Player X = " + playerXScore + ", Player O = " + playerOScore); //debug to check the scores
    }

    public static int getPlayerXScore() {
        return playerXScore;
    }

    public static int getPlayerOScore() {
        return playerOScore;
    }

    public static boolean isPlayerXTurn() {
        return isPlayerXTurn;
    }

    public static void toggleTurn() {
        isPlayerXTurn = !isPlayerXTurn;
    }
}