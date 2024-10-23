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
                //if (isKingNotInCheck(board, getRow(), getColumn()) && notMoved)
                //could be removed?
                if (notMoved) {
                    if (rook.getNotMoved() && isRowClear(board, this, column)) {
                        notMoved = false;
                        return true;
                    }
                }

            }

            return false;
        }
        notMoved = false;
        return true;
    }

    public boolean getNotMoved() {
        return notMoved;
    }

    //method for seeing if n+1 board is legal
    /*
    public boolean isKingInCheck(Piece[][] board, boolean whiteTurn) {
        return !isKingNotInCheck(board, getRow(), getColumn(), whiteTurn);
    }
    */
    @Override
    public String toString() {
        if (isWhite())
            return "K";
        else
            return "k";
    }
}
