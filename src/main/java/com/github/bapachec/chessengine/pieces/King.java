package com.github.bapachec.chessengine.pieces;

public class King extends Piece {

    public King(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }

    @Override
    public String toString() { return "K"; }
}
