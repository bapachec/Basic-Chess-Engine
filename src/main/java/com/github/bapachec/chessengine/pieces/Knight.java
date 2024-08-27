package com.github.bapachec.chessengine.pieces;

public class Knight extends Piece {

    public Knight(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }

    @Override
    public String toString() { return "N"; }
}
