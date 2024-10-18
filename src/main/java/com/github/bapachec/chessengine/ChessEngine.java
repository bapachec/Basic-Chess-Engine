package com.github.bapachec.chessengine;

import com.github.bapachec.chessengine.Position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessEngine {
    private List<UserInterface> listeners = new ArrayList<>();
    Board board = new Board();
    private boolean whitesTurn = true;
    private boolean gameOver = false;

    private static final Map<Character, Integer> columnMap = Map.of(
            'a', 0, 'b', 1, 'c', 2, 'd', 3, 'e', 4, 'f', 5, 'g', 6, 'h', 7
    );

    public void addListener(UserInterface listener) {
        listeners.add(listener);
    }

    private void evokeListenersOnStart() {
        for (UserInterface listener: listeners) {
            listener.onBoardUpdated(boardData());
        }
    }

    private void evokeListeners() {
        for (UserInterface listener: listeners) {
            if (board.getPromotionFlag())
                board.pawnPromotion(listener.promotionRequest());
            if (board.getCheckFlag()) {
                if (board.isCheckMate()) {
                    gameOver = true;
                    //listener.onBoardUpdated(boardData());
                    listener.checkmate(!whitesTurn);
                }
                listener.kingInCheckWarning(whitesTurn);
            }

            listener.onBoardUpdated(boardData());

        }
    }

    private void evokeListenersOnIllegalMove() {
        for (UserInterface listener: listeners) {
            if (board.getCheckFlag()) {
                listener.kingInCheckWarning(whitesTurn);
            }

            //illegalMove method
        }
    }

    public boolean isWhitesTurn() {
        return whitesTurn;
    }

    public boolean isGameOver() { return gameOver; }

    public void start() {
        board.populateBoard();
        evokeListenersOnStart();
    }

    public boolean validPiece(int row, int col) {
        return board.samePiece(row, col, whitesTurn);
    }

    public boolean validPiece(String selectedPosition) {
        char colChar = selectedPosition.charAt(0);
        int col = columnMap.get(colChar);
        int row = Character.getNumericValue(selectedPosition.charAt(1));
        row = 8 - row;

        return board.samePiece(row, col, whitesTurn);

    }

    //todo make this a boolean and if valid move, true otherwise false
    public boolean makeMove(int startRow, int startCol, int targetRow, int targetCol) {
        Position start = new Position(startRow, startCol);
        Position target = new Position(targetRow, targetCol);
        if (!board.movePiece(start, target)) {
            evokeListenersOnIllegalMove();
            /*
            if (board.getCheckFlag()) {
                ui.kingInCheckWarning(whitesTurn);
            }
             */
            return false;
        }

        /*
        if (board.getPromotionFlag())
            board.pawnPromotion(ui.promotionRequest());
            //promotionChoice(ui.promotionRequest());
        */
        //simplified version of turning on and off every turn
        whitesTurn = !whitesTurn;
        /*
        if (board.getCheckFlag()) {
            if (board.isCheckMate()) {
                ui.checkmate(!whitesTurn, boardData());
                ui.onBoardUpdated(this.boardData());
                System.exit(0);
            }
            ui.kingInCheckWarning(whitesTurn);
        }
        */
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

/*    public void promotionChoice(int choice) {
        board.pawnPromotion(choice);
    }
*/
    public char[][] boardData() {
        return board.boardData();
    }


}
