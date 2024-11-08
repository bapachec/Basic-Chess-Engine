# Basic Chess Engine

A basic chess engine capable of running chess games.

## Features
This chess engine supports the following rules:
* Valid piece movements
* Check and checkmate detection
* Castling and pawn promotion
* Request a draw

## Building

### From source

```shell
$ git clone https://github.com/bapachec/Basic-Chess-Engine.git
$ cd Basic-Chess-Engine
$ ./gradlew build
```

## Usage

### Implementing a Front End Interface
This chess engine requires a front end (GUI or CLI) that implements 'ChessUI' interface to interact with the engine.
The interface includes methods to run the engine, and display data sent from the engine.

### Interface Methods
The front end should implement the following methods:
* void run(): Starts and manages the game flow using the engine's API.
* int promotionRequest(): Asks the user to select a piece for pawn promotion.
* void kingInCheckWarning(boolean isWhitesTurn): Displays a warning message when a king is in check.
*  void checkmate(boolean whiteWon): Displays a message when checkmate occurs, declaring a winner.
* void onBoardUpdated(char[][] boardData): Displays the board state.
* void stalemate(): Displays a message when stalemate occurs.
* void draw(): Displays a message when draw occurs.
* boolean requestingDraw(boolean isWhitesTurn): Asks current player's opponent if they would like to accept a draw.
### Example Front End Integration
Here is an example of how to implement the 'ChessUI' interface in a front end class:

```java
public class PlayChess implements ChessUI {

    private ChessEngine engine;

    public ChessFrontEnd(ChessEngine engine) {
        this.engine = engine;
        engine.addListener(this);
    }

    @Override
    public void run() {
        //code for engine interactions, such as starting the game, checking turns,
        //validating picked piece, validating moves, and checking if game ended.

    }

    @Override
    public void onBoardUpdated(char[][] board) {
        //code to display the board to the user
    }

    @Override
    public int promotionRequest() {
        //code to ask the user to pick a piece for pawn promotions
    }

    @Override
    public void kingInCheckWarning(boolean isWhitesTurn) {
        //code to display a check warning message
    }

    @Override
    public void checkmate(boolean whiteWon) {
        //code to display winning message
    }

    @Override
    public void stalemate() {
        //code to display stalemate message
    }
    
    @Override
    public void draw() {
        //code to display draw message
    }
    
    @Override
    public boolean requestingDraw(boolean isWhitesTurn) {
        //code to ask current player's opponent if they accept a draw
    }
}
```

### Engine API
The 'ChessEngine' provides methods for move validation, checking game status, tracking turns, and starting the game. 
It also calls the listener's output methods, which are responsible for displaying messages and the board state. 
<br/>A listener is a front end class that implements the 'ChessUI' interface.
<br/>This is why the front end constructor should include:
```java
//In front end's constructor
    engine.addListener(this);
```
Here's an overview of API methods:
* void start(): Sets up the board and begins the game.
* void start(char[][] customBoard): Begins the game with a custom board configuration based on the provided character matrix.


* boolean isWhitesTurn(): Indicates if it's White's turn.


* boolean validPiece(int row, int col): Checks if a chosen piece at given coordinates (row, col) belongs to the player whose turn it is.
* boolean validPiece(String selectedPosition): Checks if a piece at the given board position (e.g. "a2" or "b1") belongs to the current player.


* boolean makeMove(int startRow, int startCol, int endRow, int endCol): Moves a piece from the start position (row, col) to the target position (row, col).
* boolean makeMove(String start, String end): Moves a piece from start position to target position based on board notation (e.g. "b1" to "c3").


* boolean isGameOver(): Returns true if the game has ended by checkmate or stalemate.

* void requestDraw(): Checks if opponent would like to accept current player's request to draw.
### Running The Game
Once the front end is implemented using 'ChessUI', integrate it with the chess engine as shown below:
```java
public class Main {
    public static void main (String[] args) {
        ChessEngine engine = new ChessEngine();
        
        //assuming front end is called 'PlayChess'
        PlayChess playChess = new PlayChess(engine);
        playChess.run();
    }
}
```