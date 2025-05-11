package com.example.tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static final int SIZE = 3;
    private final TicTacToeGame game = new TicTacToeGame();

    @FXML
    private GridPane gameGrid;

    @FXML
    private Label statusLabel;

    private Button[][] buttons = new Button[SIZE][SIZE];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeUI();
        updateStatus();
    }

    private void initializeUI() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Button button = new Button();
                button.setMinSize(80, 80);
                button.setFont(Font.font(24));
                final int r = row;
                final int c = col;
                button.setOnAction(e -> handleMove(r, c));

                buttons[row][col] = button;
                gameGrid.add(button, col, row);
            }
        }
    }

    private void handleMove(int row, int col) {
        if (!game.isGameOver() && game.makeMove(row, col, 'X')) {
            updateBoard();
            if (!game.isGameOver()) {
                game.aiMove();
                updateBoard();
            }
        }
        updateStatus();
    }

    private void updateBoard() {
        char[][] board = game.getBoard();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col].setText(String.valueOf(board[row][col]));
                buttons[row][col].setDisable(board[row][col] != '\0');
            }
        }
    }

    private void updateStatus() {
        if (game.isGameOver()) {
            statusLabel.setText(game.getWinner() == '\0' ? "It's a draw!"
                    : "Player " + game.getWinner() + " wins!");
        } else {
            statusLabel.setText(game.isPlayerTurn() ? "Your turn (X)" : "AI is thinking...");
        }
    }
}