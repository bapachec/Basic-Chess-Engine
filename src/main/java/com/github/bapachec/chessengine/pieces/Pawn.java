package com.github.bapachec.chessengine.pieces;

public class Pawn extends Piece{

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public String toString() {
        return "P";
    }
}
