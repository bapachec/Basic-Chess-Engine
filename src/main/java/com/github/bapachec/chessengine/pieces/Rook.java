package com.github.bapachec.chessengine.pieces;

public class Rook extends Piece {

    @Override
    public boolean isLegalMove(int row, int column){
        if (super.isLegalMove(row, column))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "R";
    }

}
