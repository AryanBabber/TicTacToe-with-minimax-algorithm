package com.example.tictactoe;

public class TicTacToeGame {
    private static final int SIZE = 3;
    private char[][] board = new char[SIZE][SIZE];
    private boolean playerTurn = true;
    private boolean gameOver = false;
    private char winner = '\0';

    public TicTacToeGame() {
        initializeBoard();
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char getWinner() {
        return winner;
    }

    private void initializeBoard() {
        for ( int row = 0; row < SIZE; row++ ) {
            for ( int col = 0; col < SIZE; col++ ) {
                board[row][col] = '\0';
            }
        }
    }

    public boolean makeMove(int row, int col, char player) {
        if (gameOver || row < 0 || row >= SIZE || col < 0 || col >= SIZE ||
            board[row][col] != '\0' || !playerTurn) {
            return false;
        }

        board[row][col] = player;
        playerTurn = false;
        checkGameStatus();
        return true;
    }

    private int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = null;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == '\0') {
                    board[row][col] = 'O';
                    int score = minimax(board, 0, false);
                    board[row][col] = '\0';

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{row, col};
                    }
                }
            }
        }

        if (bestMove == null) {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    if (board[row][col] == '\0') {
                        return new int[]{row, col};
                    }
                }
            }
            return null;
        }

        return bestMove;
    }

    public void aiMove() {
        if (gameOver) return;

        int[] bestMove = findBestMove();
        if (bestMove != null) {
            board[bestMove[0]][bestMove[1]] = 'O';
            playerTurn = true;
            checkGameStatus();
        }
    }

    private int minimax(char[][] board, int depth, boolean isMaximizing) {
        char res = checkWinner();

        if ( res != '\0' ) {
            return res == 'O' ? 10 - depth : depth - 10;
        } else if ( isBoardFull() ) {
            return 0;
        }

        if ( isMaximizing ) {
            int bestScore = Integer.MIN_VALUE;
            for ( int row = 0; row < SIZE; row++ ) {
                for ( int col = 0; col < SIZE; col++ ) {
                    if ( board[row][col] == '\0' ) {
                        board[row][col] = 'O';
                        int score = minimax(board, depth + 1, false);
                        board[row][col] = '\0';
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for ( int row = 0; row < SIZE; row++ ) {
                for ( int col = 0; col < SIZE; col++ ) {
                    if ( board[row][col] == '\0' ) {
                        board[row][col] = 'X';
                        int score = minimax(board, depth + 1, true);
                        board[row][col] = '\0';
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    private void checkGameStatus() {
        winner = checkWinner();
        gameOver = (winner != '\0' || isBoardFull());
    }

    private char checkWinner() {
        for ( int row = 0; row < SIZE; row++ ) {
            if (
                    board[row][0] != 0 &&
                    board[row][0] == board[row][1] &&
                    board[row][0] == board[row][2]
            ) {
                return board[row][0];
            }
        }

        for ( int col = 0; col < SIZE; col++ ) {
            if (
                    board[0][col] != '\0' &&
                    board[0][col] == board[1][col] &&
                    board[0][col] == board[2][col]
            ) {
                return board[0][col];
            }
        }

        if (
                board[0][0] != '\0' &&
                board[0][0] == board[1][1] &&
                board[0][0] == board[2][2]
        ) {
            return board[0][0];
        }
        if (
                board[0][2] != '\0' &&
                board[0][2] == board[1][1] &&
                board[0][2] == board[2][0]
        ) {
            return board[0][2];
        }

        return '\0';
    }

    private boolean isBoardFull() {
        for ( int r = 0; r < SIZE; r++ ) {
            for ( int c = 0; c < SIZE; c++ ) {
                if ( board[r][c] == '\0' ) {
                    return false;
                }
            }
        }
        return true;
    }
}
