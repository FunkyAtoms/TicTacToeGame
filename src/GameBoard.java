package TicTacToeGame.src;

import TicTacToeGame.src.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GameBoard extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private JLabel scoreLabel;
    private JLabel turnLabel;
    private JButton newGameButton;
    private JButton resetScoreButton;
    private static final String SCORE_FILE = "scores.txt";

    public GameBoard() {
        setTitle("Tic Tac Toe");
        setLayout(new BorderLayout());

        // Initialize buttons
        buttons = new JButton[3][3];
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(0x000000)); // Add background color to the grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(new Color(0x4c4e57)); // Button background color
                buttons[i][j].setForeground(new Color(0xc0eaef)); // Button text color
                buttons[i][j].addActionListener(this);
                buttonPanel.add(buttons[i][j]);
            }
        }

        // Initialize labels
        scoreLabel = new JLabel("Player X: 0 | Player O: 0");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(Color.LIGHT_GRAY);

        turnLabel = new JLabel("Turn: Player X");
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 18));
        turnLabel.setOpaque(true);
        turnLabel.setBackground(Color.LIGHT_GRAY);

        // Initialize New Game button
        newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 16));
        newGameButton.setBackground(new Color(0xc0eaef));
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setFocusPainted(false);
        newGameButton.addActionListener(e -> resetGame());

        // Initialize Reset Score button
        resetScoreButton = new JButton("Reset Score");
        resetScoreButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetScoreButton.setBackground(new Color(0xff6f61));
        resetScoreButton.setForeground(Color.WHITE);
        resetScoreButton.setFocusPainted(false);
        resetScoreButton.addActionListener(e -> resetScores());

        // Add components to the frame
        add(scoreLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.add(turnLabel);
        JPanel buttonPanelBottom = new JPanel(new GridLayout(1, 2));
        buttonPanelBottom.add(newGameButton);
        buttonPanelBottom.add(resetScoreButton);
        bottomPanel.add(buttonPanelBottom);
        add(bottomPanel, BorderLayout.SOUTH);

        // Load scores from file
        loadScores();

        setSize(500, 500);
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
            saveScores(); // Save scores to file
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

    private void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            writer.write(GameUtils.getPlayerXScore() + "\n");
            writer.write(GameUtils.getPlayerOScore() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            int playerXScore = Integer.parseInt(reader.readLine());
            int playerOScore = Integer.parseInt(reader.readLine());
            GameUtils.setPlayerXScore(playerXScore);
            GameUtils.setPlayerOScore(playerOScore);
            updateScores();
        } catch (IOException | NumberFormatException e) {
            // If file doesn't exist or is invalid, start with default scores
            GameUtils.setPlayerXScore(0);
            GameUtils.setPlayerOScore(0);
        }
    }

    private void resetScores() {
        GameUtils.setPlayerXScore(0);
        GameUtils.setPlayerOScore(0);
        updateScores();
        saveScores(); // Save reset scores to file
    }
}