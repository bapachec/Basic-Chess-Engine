package com.github.bapachec.chessengine.pieces;

public class Queen extends Piece {

    public Queen(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }

    @Override
    public boolean isLegalMove(Piece[][] board, int row, int column) {
        if (!super.isLegalMove(board, row, column))
            return false;

        int rowAbs = Math.abs(row - getRow());
        int colAbs = Math.abs(column - getColumn());

        //if acts like a bishop
        if (rowAbs == colAbs) {
            return isDagClear(board, this, row, column);
        }

        //if acts like a rook
        if (rowAbs != 0 && colAbs != 0)
            return false;

        if (rowAbs == 0)
            return (isRowClear(board, this, column));
        else
            return (isColClear(board, this, row));

    }

    @Override
    public String toString() { return "Q"; }
}
