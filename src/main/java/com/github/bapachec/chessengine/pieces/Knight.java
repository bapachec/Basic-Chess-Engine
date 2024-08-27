package com.github.bapachec.chessengine.pieces;

public class Knight extends Piece {

    public Knight(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }

    @Override
    public boolean isLegalMove(int row, int column) {
        if (!super.isLegalMove(row, column))
            return false;


        if (Math.abs(row - getRow()) != 2 && Math.abs(column - getColumn()) != 2)
            return false;

        if (Math.abs(column - getColumn()) != 1 && Math.abs(row - getRow()) != 1)
            return false;

        return true;
    }

    @Override
    public String toString() { return "N"; }
}
