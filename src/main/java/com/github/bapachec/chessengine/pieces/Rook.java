package com.github.bapachec.chessengine.pieces;

import com.github.bapachec.chessengine.Board;

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
    public boolean hasAnyLegalMoves(Piece[][] board, boolean whiteTurn, int king_row, int king_col) {
        // directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        return super.hasAnyLegalMovesContinuous(board, whiteTurn, directions, king_row, king_col);
    }

    @Override
    public String toString() {
        if (isWhite())
            return "R";
        else
            return "r";
    }

}
