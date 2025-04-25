# Tic Tac Toe Game

This is a simple Tic Tac Toe game implemented in Java using a graphical user interface (GUI). The game allows two players to take turns playing on a 3x3 grid, and it checks for a winner after each turn. A "New Game" button is provided to reset the game.

## Features

- 3x3 grid of buttons for player moves
- Tracks player turns (X and O)
- Checks for a winner after each turn
- Option to start a new game
- Option to reset saved score
- Total Score saving, stored in file

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed on your machine.
- Gradle installed for building the project.

### Running the Game

1. Clone the repository to your local machine:
   ```
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```
   cd TicTacToeGame
   ```

3. Build the project using Gradle:
   ```
   gradle build
   ```

4. Run the application:
   ```
   gradle run
   ```
## or

1. Download .ZIP file

![image](https://github.com/user-attachments/assets/d4a01464-e06f-4dcf-bae9-a787412a1d0d)

2. extract the zip file on any accessible destination

3. Open the extracted folder with any compilers and run

## Project Structure

```
TicTacToeGame
├── src
│   ├── Main.java          # Entry point of the application
│   ├── GameBoard.java     # Class for the game board and logic
│   ├── Player.java        # Class representing a player
│   └── utils
│       └── GameUtils.java # Utility functions for the game
├── .gitignore             # Files and directories to ignore by Git
├── README.md              # Project documentation
└── build.gradle           # Gradle build configuration
```

## License

This project is open-source and available under the MIT License.
