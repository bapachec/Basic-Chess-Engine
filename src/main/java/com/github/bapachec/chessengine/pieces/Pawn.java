package com.github.bapachec.chessengine.pieces;

public class Pawn extends Piece{

    private boolean isFirstMove = true;


    public Pawn(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }


    @Override
    public String toString() {
        return "P";
    }
}
