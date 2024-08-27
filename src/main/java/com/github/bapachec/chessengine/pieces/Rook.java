package com.github.bapachec.chessengine.pieces;

public class Rook extends Piece {

    public Rook(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }


    @Override
    public String toString() {
        return "R";
    }

}
