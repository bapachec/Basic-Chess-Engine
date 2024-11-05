package com.github.bapachec.chessengine;

import com.github.bapachec.chessengine.Position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private void evokeListenersOnStart() {
        for (ChessUI listener: listeners) {
            listener.onBoardUpdated(boardData());
        }
    }

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

    private void evokeListenersOnIllegalMove() {
        for (ChessUI listener: listeners) {
            if (board.getCheckFlag()) {
                listener.kingInCheckWarning(whitesTurn);
            }

        }
    }

    public boolean isWhitesTurn() { return whitesTurn; }

    public boolean isGameOver() { return gameOver; }

    public void requestDraw() {
        for (ChessUI listener: listeners) {
            if (listener.requestingDraw(whitesTurn)) {
                gameOver = true;
                listener.draw();
            }
        }

    }

    public void start() {
        board.populateBoard();
        evokeListenersOnStart();
    }

    public void start(char[][] customBoard) {
        board.customPopulateBoard(customBoard);
        evokeListenersOnStart();
    }

    public boolean validPiece(int row, int col) {
        return board.samePiece(row, col);
    }

    public boolean validPiece(String selectedPosition) {
        char colChar = selectedPosition.charAt(0);
        int col = columnMap.get(colChar);
        int row = Character.getNumericValue(selectedPosition.charAt(1));
        row = 8 - row;

        return board.samePiece(row, col);

    }

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

    public char[][] boardData() {
        return board.boardData();
    }

}
