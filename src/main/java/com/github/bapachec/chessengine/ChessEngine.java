package com.github.bapachec.chessengine;

import java.util.ArrayList;
import java.util.List;

public class ChessEngine {
    private List<UserInterface> listeners = new ArrayList<>();
    Board board = new Board();
    private boolean whitesTurn = true;

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
                    listener.onBoardUpdated(boardData());
                    listener.checkmate(!whitesTurn, boardData());
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

    public void start() {
        board.populateBoard();
        evokeListenersOnStart();
    }

    public boolean validPiece(int[] piece_location) {
        return board.samePiece(piece_location, whitesTurn);
    }

    //todo make this a boolean and if valid move, true otherwise false
    public void makeMove(int[] piece_location, int new_row, int new_col) {
        if (!board.movePiece(piece_location, new_row, new_col)) {
            evokeListenersOnIllegalMove();
            /*
            if (board.getCheckFlag()) {
                ui.kingInCheckWarning(whitesTurn);
            }
             */
            return;
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

    }

/*    public void promotionChoice(int choice) {
        board.pawnPromotion(choice);
    }
*/
    public char[][] boardData() {
        return board.boardData();
    }


}
