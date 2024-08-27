package com.github.bapachec.chessengine.pieces;

public class Queen extends Piece {

    public Queen(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }

    @Override
    public boolean isLegalMove(int row, int column) {
        if (!super.isLegalMove(row, column))
            return false;

        //acts like a bishop
        if (Math.abs(row - getRow()) == Math.abs(column - getColumn())) {
            return true;
        }

        //if acts like a rook
        return Math.abs(row - getRow()) == 0 || Math.abs(column - getColumn()) == 0; //simplified

    }

    @Override
    public String toString() { return "Q"; }
}
