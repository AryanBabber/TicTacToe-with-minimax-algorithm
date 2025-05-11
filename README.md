# 🎮 Tic-Tac-Toe with Minimax AI (JavaFX)

A JavaFX implementation of Tic-Tac-Toe featuring an unbeatable AI opponent using the Minimax algorithm.

## 📋 Table of Contents
- [Features](#-features)
- [How It Works](#-how-it-works)
- [Installation](#-installation)
- [Usage](#-usage)

## ✨ Features
✅ **Unbeatable AI** using Minimax algorithm  
✅ Clean JavaFX GUI with responsive design  
✅ Game state tracking (win/draw detection)  

## 🧠 How It Works
The AI uses the **Minimax algorithm** to:
1. Evaluate all possible future moves
2. Assume opponent plays optimally
3. Choose moves that guarantee at least a draw
4. Capitalize on player mistakes to win

**Key Properties:**
- Impossible to beat (will always win or draw)
- Follows perfect Tic-Tac-Toe strategy
- Responds instantly to player moves

## 💻 Installation
1. Ensure you have Java 11+ and JavaFX installed
2. Fork the repository
3. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/TicTacToe-with-minimax-algorithm.git
   ```
4. Open in your IDE
5. Run `Main.java`
   
## 🎮 Usage
1. Launch the application
2. Click any empty cell to place an X
3. The AI will automatically respond with O
4. Game ends when:
  - A player gets 3 in a row
  - All cells are filled (draw)
