package com.github.bapachec.chessengine.pieces;

public class Bishop extends Piece {

    public Bishop(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }
    @Override
    public String toString() { return "B"; }
}
