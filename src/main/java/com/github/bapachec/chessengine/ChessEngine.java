package com.github.bapachec.chessengine;

public class ChessEngine {
    UserInterface ui;
    Board board = new Board();
    private boolean isWhitesTurn = true;

    public ChessEngine(UserInterface ui) {
        this.ui = ui;
    }

    public boolean isWhitesTurn() {
        return isWhitesTurn;
    }

    public void start() {
        board.populateBoard();
    }

    public boolean validPiece(byte[] piece_location) {
        return board.samePiece(piece_location, isWhitesTurn);
    }

    //todo make this a boolean and if valid move, true otherwise false
    public void makeMove(byte[] piece_location, int new_row, int new_col) {
        if (!board.movePiece(piece_location, new_row, new_col))
            return;

        if (board.getPromotionFlag())
            board.pawnPromotion(ui.promotionRequest());
            //promotionChoice(ui.promotionRequest());

        //simplified version of turning on and off every turn
        isWhitesTurn = !isWhitesTurn;

    }

    public void promotionChoice(int choice) {
        board.pawnPromotion(choice);
    }

    public char[][] boardData() {
        return board.boardData();
    }



}
