package com.github.bapachec.chessengine.pieces;

import static com.github.bapachec.chessengine.Board.KingCheck.isKingNotChecked;

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
                //if (isKingNotChecked(board, getRow(), getColumn()) && notMoved)
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
    public boolean isKingInCheck(Piece[][] board) {
        return !isKingNotChecked(board, getRow(), getColumn());
    }

    @Override
    public String toString() { return "K"; }
}
