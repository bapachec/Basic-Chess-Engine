package com.github.bapachec.chessengine.pieces;

public class Rook extends Piece {
    private boolean notMoved = true;

    public Rook(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }

    @Override
    public boolean isLegalMove(Piece[][] board, int row, int column) {
        if (!super.isLegalMove(board, row, column))
            return false;

        int rowAbs = Math.abs(row - getRow());
        int colAbs = Math.abs(column - getColumn());

        if (rowAbs != 0 && colAbs != 0)
            return false;

        if (rowAbs == 0) {
            if (!isRowClear(board, this, column))
                return false;
        } else if (!isColClear(board, this, row)) {
            return false;
        }
            //return (isRowClear(board, this, column));


        notMoved = false;
        return true;
    }

    public boolean getNotMoved() {
        return notMoved;
    }

    @Override
    public String toString() {
        return "R";
    }

}
