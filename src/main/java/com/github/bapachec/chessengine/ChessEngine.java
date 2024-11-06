package com.github.bapachec.chessengine;

import com.github.bapachec.chessengine.Position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Manages the core logic and state of a chess game.
 * <p>
 *     The {@code ChessEngine} class controls gameplay, including piece movement,
 *     checking for check and checkmate conditions, and handling pawn promotion. It interacts
 *     with the {@link Board} to update game state and with listeners implementing {@link ChessUI} interface to
 *     notify changes in the board's state, providing a cohesive game experience.
 * </p>
 * <p>
 *     This class includes methods to:
 *     <ul>
 *         <li>Start the game with default or custom board configurations.</li>
 *         <li>Move pieces and validate moves.</li>
 *         <li>Check the game-over conditions. (e.g. checkmate, stalemate, draw)</li>
 *         <li>Manage turns and notify listeners of updates.</li>
 *     </ul>
 * </p>
 */
public class ChessEngine {
    private List<ChessUI> listeners = new ArrayList<>();
    Board board = new Board();
    private boolean whitesTurn = true;
    private boolean gameOver = false;

    private static final Map<Character, Integer> columnMap = Map.of(
            'a', 0, 'b', 1, 'c', 2, 'd', 3, 'e', 4, 'f', 5, 'g', 6, 'h', 7
    );

    public void addListener(ChessUI listener) {
        listeners.add(listener);
    }

    /**
     * Notifies all listeners to update the board at the start of the game.
     * <p>
     *     This method calls {@link ChessUI#onBoardUpdated(char[][])} on each listener to display the initial
     *     board configuration when the game starts, ensuring synchronization with current board state.
     * </p>
     */
    private void evokeListenersOnStart() {
        for (ChessUI listener: listeners) {
            listener.onBoardUpdated(boardData());
        }
    }

    /**
     * Notifies listeners of various game states and updates the board status.
     * <p>
     *     This method checks for game conditions such as pawn promotion, check, checkmate, stalemate,
     *     and draw. Based on the detected condition, it invokes the appropriate listener methods
     *     to inform the user interface of any necessary updates or actions.
     * </p>
     * <ul>
     *     <li>If a pawn promotion is flagged, it prompts the listener for a promotion choice
     *     by calling {@link ChessUI#promotionRequest()} and performs the promotion.</li>
     *     <li>If the king is in check, it issues a warning and checks for checkmate.
     *     If checkmate is confirmed, the game ends,
     *     and listeners are notified by calling {@link ChessUI#checkmate(boolean)}</li>
     *     <li>If stalemate or draw conditions are met, the game is marked as over,
     *     and listeners are notified of the respective outcome by calling {@link ChessUI#stalemate()}.</li>
     *     <li>Finally, updates the board state by calling
     *     {@link ChessUI#onBoardUpdated(char[][])} with the current board data.</li>
     * </ul>
     */
    private void evokeListeners() {
        for (ChessUI listener: listeners) {
            if (board.getPromotionFlag())
                board.pawnPromotion(listener.promotionRequest());
            if (board.getCheckFlag()) {
                if (board.isCheckMate()) {
                    gameOver = true;
                    listener.checkmate(!whitesTurn);
                }
                listener.kingInCheckWarning(whitesTurn);
            }
            else if (board.isStaleMate()) {
                gameOver = true;
                listener.stalemate();
            }
            else if (board.isDraw()) {
                gameOver = true;
                listener.draw();
            }

            listener.onBoardUpdated(board.boardData());

        }
    }

    /**
     * Notifies listeners of an illegal move attempt, issuing a warning if the current player's king is in check.
     * <p>
     *     Iterates through all listeners and, if the king is in check, triggers a warning
     *     for the current player by calling {@link ChessUI#kingInCheckWarning(boolean)}
     * </p>
     */
    private void evokeListenersOnIllegalMove() {
        for (ChessUI listener: listeners) {
            if (board.getCheckFlag()) {
                listener.kingInCheckWarning(whitesTurn);
            }

        }
    }

    /**
     * Indicates if it is currently white's turn to play.
     *
     * @return {@code true} if it's white's turn; {@code false} if it's black's turn.
     */
    public boolean isWhitesTurn() { return whitesTurn; }

    /**
     * Checks if the game has ended.
     *
     * @return {@code true} if the game is over; {@code false} otherwise.
     */
    public boolean isGameOver() { return gameOver; }

    /**
     * Requests a draw from the opponent.
     * <P>
     *     This method iterates through each listener in {@code listeners} and calls
     *     {@link ChessUI#requestingDraw(boolean)} to ask if the opponent accepts the draw.
     *     If any listener accepts the draw, the game ends, and the {@link ChessUI#draw()}
     *     is invoked to signal that the game has concluded in a draw.
     * </P>
     */
    public void requestDraw() {
        for (ChessUI listener: listeners) {
            if (listener.requestingDraw(whitesTurn)) {
                gameOver = true;
                listener.draw();
            }
        }

    }

    /**
     * Initializes the game with the default board configuration.
     * <p>
     *     Calls the {@link Board#populateBoard()} method to set up the pieces on the board,
     *     and notifies listeners that the game has started and displays the initial board state.
     * </p>
     */
    public void start() {
        board.populateBoard();
        evokeListenersOnStart();
    }

    /**
     * Initializes the game with a custom board configuration.
     * <p>
     *     Calls the {@link Board#customPopulateBoard(char[][])} method to set up the pieces on the board
     *     according to the custom configuration provided, notifies listeners that the game has started,
     *     and displays the initial board state.
     * </p>
     * @param customBoard A char 2D array that specifies the custom board configuration.
     */
    public void start(char[][] customBoard) {
        board.customPopulateBoard(customBoard);
        evokeListenersOnStart();
    }

    /**
     * Checks if there is a piece belonging to the current player at the specified board position.
     *
     * @param row The row index of the piece's position on the board.
     * @param col The column index of the piece's position on the board.
     * @return {@code true} if the piece at the specified position belongs to the current player;
     *         {@code false} otherwise.
     */
    public boolean validPiece(int row, int col) {
        return board.samePiece(row, col);
    }

    /**
     * Checks if there is a piece belonging to the current player at the specified board position using algebraic notation.
     *
     * @param selectedPosition A string representing the selected position in algebraic notation.
     * @return {@code true} if the piece at the specified position belongs to the current player;
     *         {@code false} otherwise.
     */
    public boolean validPiece(String selectedPosition) {
        char colChar = selectedPosition.charAt(0);
        int col = columnMap.get(colChar);
        int row = Character.getNumericValue(selectedPosition.charAt(1));
        row = 8 - row;

        return board.samePiece(row, col);

    }

    /**
     * Moves a piece from the starting position to the target position on the board.
     * <p>
     *     This method converts the provided row and column coordinates into {@link Position} objects,
     *     and calls the {@link Board#movePiece(Position, Position)} method to perform the move.
     * </p>
     *
     * @param startRow The row index of the piece's starting position.
     * @param startCol The column index of the piece's starting position.
     * @param targetRow The row index of the piece's target position.
     * @param targetCol The column index of the piece's target position.
     * @return {@code true} if the move was successfully made; {@code false} if the move is invalid or failed.
     */
    public boolean makeMove(int startRow, int startCol, int targetRow, int targetCol) {
        Position start = new Position(startRow, startCol);
        Position target = new Position(targetRow, targetCol);
        if (!board.movePiece(start, target)) {
            evokeListenersOnIllegalMove();
            return false;
        }

        whitesTurn = !whitesTurn;
        evokeListeners();
        return true;
    }

    /**
     * Moves a piece from the starting position to the target position on the board using algebraic notation.
     * <p>
     *     This method converts the algebraic notation of the starting and target positions into the
     *     corresponding row and column indices, and then calls the {@link #makeMove(int, int, int, int)}
     *     method to perform the move.
     * </p>
     *
     * @param start A string representing the starting position in algebraic notation.
     * @param end A string representing the target position in algebraic notation.
     * @return {@code true} if the move was successfully made; {@code false} if the move is invalid or failed.
     */
    public boolean makeMove(String start, String end) {
        char colChar = start.charAt(0);
        int startCol = columnMap.get(colChar);
        int startRow = Character.getNumericValue(start.charAt(1));
        startRow = 8 - startRow;

        colChar = end.charAt(0);
        int targetCol = columnMap.get(colChar);
        int targetRow = Character.getNumericValue(end.charAt(1));
        targetRow = 8 - targetRow;

        return makeMove(startRow, startCol, targetRow, targetCol);
    }

    /**
     * Retrieves the current board state from the {@link Board} as a 2D char array.
     *
     * @return A char 2D array that represents the current state of the board.
     */
    public char[][] boardData() {
        return board.boardData();
    }

}
