package com.github.bapachec.chessengine;

public class ChessEngine {
    Board board = new Board();
    private boolean whitesTurn = true;


    public void play() {
        board.populateBoard();
        board.display(); //display is for testing, look in board.java for more
    }

    public void makeMove(byte[] piece_location, byte new_row, byte new_col) {
        board.movePiece(piece_location, new_row, new_col);
        board.display();
    }

}
