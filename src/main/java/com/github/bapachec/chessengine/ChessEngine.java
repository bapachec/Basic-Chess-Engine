package com.github.bapachec.chessengine;

public class ChessEngine {
    Board board = new Board();
    private boolean isWhitesTurn = true;

    public boolean isWhitesTurn() {
        return isWhitesTurn;
    }

    public void start() {
        board.populateBoard();
    }

    //todo make this a boolean and if valid move, true otherwise false
    public void makeMove(byte[] piece_location, byte new_row, byte new_col) {
        if (!board.movePiece(piece_location, new_row, new_col, isWhitesTurn)) {
            return;
        }
        //simplified version of turning on and off every turn
        isWhitesTurn = !isWhitesTurn;

    }

    public char[][] boardData() {
        return board.boardData();
    }



}
