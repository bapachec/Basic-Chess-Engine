package com.github.bapachec.chessengine.pieces;

public class Bishop extends Piece {

    public Bishop(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }

    @Override
    public boolean isLegalMove(Piece[][] board, int row, int column) {
        if (!super.isLegalMove(board, row, column))
            return false;


        if (Math.abs(row - getRow()) != Math.abs(column - getColumn()))
            return false;

        return isDagClear(board, this, row, column);
        //return Math.abs(row - getRow()) == Math.abs(column - getColumn()); //simplified by ide
    }

    @Override
    public String toString() { return "B"; }
}
