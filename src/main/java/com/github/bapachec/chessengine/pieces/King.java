package com.github.bapachec.chessengine.pieces;

public class King extends Piece {
    private boolean notMoved = true;

    public King(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }

    @Override
    public boolean isLegalMove(Piece[][] board, int row, int column) {
        if (!super.isLegalMove(board, row, column))
            return false;

        if (Math.abs(row - getRow()) > 1 || Math.abs(column - getColumn()) > 1) {
            if (board[row][column] instanceof Rook rook) {
                return rook.getNotMoved() && isRowClear(board, this, column);
            }

            return false;
        }
        notMoved = false;
        return true;
    }

    @Override
    public String toString() { return "K"; }
}
