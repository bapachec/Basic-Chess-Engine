package com.github.bapachec.chessengine;

/**
 * Defines the user interface for interacting with the chess game.
 * <p>
 *     The {@code ChessUI} interface provides methods for managing user interactions
 *     during a chess game. Implementations of this interface can provide different types of interactions
 *     through GUI or CLI.
 * </p>
 */
public interface ChessUI {

    /**
     * Responsible for running the game by calling the engine's API
     */
    void run();

    /**
     * Requests a promotion choice from the player when a pawn reaches the last rank.
     *
     * @return An integer representing the player's choice for pawn promotion.
     */
    int promotionRequest();

    /**
     * Warns the player whose king is in check
     *
     * @param isWhitesTurn A boolean flag indicating the current turn.
     *                     {@code true} if it's white's turn; {@code false} if it's black's turn.
     */
    void kingInCheckWarning(boolean isWhitesTurn);

    /**
     * Displays checkmate message and winner
     *
     * @param whiteWon A boolean flag indicating the winner.
     *                 {@code true} if white is the winner; {@code false} if black is the winner.
     */
    void checkmate(boolean whiteWon);

    /**
     * Displays the current game board's state.
     * @param boardData A char 2D array representing the board's current state.
     */
    void onBoardUpdated(char[][] boardData);

    /**
     * Displays the stalemate message
     */
    void stalemate();

    /**
     * Displays the draw message
     */
    void draw();

    /**
     * Requests a draw from the opposing player.
     *
     * @param whiteTurn A boolean flag indicating the player asking for draw.
     *                  {@code true} if white is asking for draw; {@code false} if black is asking for draw.
     * @return {@code true} if the opponent accepts draw; {@code false} if opponent declines draw;
     */
    boolean requestingDraw(boolean whiteTurn);

}
