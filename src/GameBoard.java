package TicTacToeGame.src;

import TicTacToeGame.src.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private JLabel scoreLabel;
    private JLabel turnLabel;
    private JButton newGameButton;

    public GameBoard() {
        setTitle("Tic Tac Toe");
        setLayout(new BorderLayout());

        // Initialize buttons
        buttons = new JButton[3][3];
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                buttonPanel.add(buttons[i][j]);
            }
        }

        // Initialize labels
        scoreLabel = new JLabel("Player X: 0 | Player O: 0");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        turnLabel = new JLabel("Turn: Player X");
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize New Game button
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> resetGame());

        // Add components to the frame
        add(scoreLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(turnLabel, BorderLayout.SOUTH);
        add(newGameButton, BorderLayout.EAST);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (!clickedButton.getText().equals("")) {
            return; // Ignore if button is already clicked
        }

        // Set the button text to the current player's symbol
        String currentPlayer = GameUtils.isPlayerXTurn() ? "X" : "O";
        clickedButton.setText(currentPlayer);

        // Check for a winner
        if (GameUtils.checkWinner(getBoardState())) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            updateScores();
            resetGame();
        } else if (GameUtils.isBoardFull(getBoardState())) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        } else {
            // Toggle turn and update the turn label
            GameUtils.toggleTurn();
            updateTurnLabel();
        }
    }

    private String[][] getBoardState() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = buttons[i][j].getText().isEmpty() ? null : buttons[i][j].getText();
            }
        }
        return board;
    }

    private void updateScores() {
        int playerXScore = GameUtils.getPlayerXScore();
        int playerOScore = GameUtils.getPlayerOScore();
        scoreLabel.setText("Player X: " + playerXScore + " | Player O: " + playerOScore);
    }

    private void updateTurnLabel() {
        String currentPlayer = GameUtils.isPlayerXTurn() ? "Player X" : "Player O";
        turnLabel.setText("Turn: " + currentPlayer);
    }

    public void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        GameUtils.resetBoard(getBoardState());
        GameUtils.toggleTurn(); // Reset turn to Player X
        updateTurnLabel();
    }
}