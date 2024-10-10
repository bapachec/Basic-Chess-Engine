package com.github.bapachec.chessengine;

public class ChessEngine {
    UserInterface ui;
    Board board = new Board();
    private boolean whitesTurn = true;

    public ChessEngine(UserInterface ui) {
        this.ui = ui;
    }

    public boolean isWhitesTurn() {
        return whitesTurn;
    }

    public void start() {
        board.populateBoard();
    }

    public boolean validPiece(byte[] piece_location) {
        return board.samePiece(piece_location, whitesTurn);
    }

    //todo make this a boolean and if valid move, true otherwise false
    public void makeMove(byte[] piece_location, int new_row, int new_col) {
        if (!board.movePiece(piece_location, new_row, new_col)) {
            if (board.getCheckFlag()) {
                ui.kingInCheckWarning(whitesTurn);
            }
            return;
        }

        if (board.getPromotionFlag())
            board.pawnPromotion(ui.promotionRequest());
            //promotionChoice(ui.promotionRequest());

        //simplified version of turning on and off every turn
        whitesTurn = !whitesTurn;

        if (board.getCheckFlag()) {
            if (board.isCheckMate()) {
                ui.checkmate(!whitesTurn, boardData());
            }
            ui.kingInCheckWarning(whitesTurn);
        }



    }

    public void promotionChoice(int choice) {
        board.pawnPromotion(choice);
    }

    public char[][] boardData() {
        return board.boardData();
    }


}
