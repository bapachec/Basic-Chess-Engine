package com.github.bapachec.chessengine.pieces;

public class Queen extends Piece {

    public Queen(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }

    @Override
    public String toString() { return "Q"; }
}
