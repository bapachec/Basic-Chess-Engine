package com.github.bapachec.chessengine.pieces;

public class Rook extends Piece {

    public Rook(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }

    @Override
    public boolean isLegalMove(int row, int column) {
        if (!super.isLegalMove(row, column))
            return false;

        if (Math.abs(row - getRow()) != 0 && Math.abs(column - getColumn()) != 0)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "R";
    }

}
