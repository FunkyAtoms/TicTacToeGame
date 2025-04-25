// Tic Tac Toe is fun!
// unless otherwise...
// i guess

package TicTacToeGame.src;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tic Tac Toe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLayout(new BorderLayout());

            GameBoard gameBoard = new GameBoard();
            frame.add(gameBoard, BorderLayout.CENTER);

            JButton newGameButton = new JButton("New Game");
            newGameButton.addActionListener(e -> gameBoard.resetGame());
            frame.add(newGameButton, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }
}