package com.github.bapachec.chessengine.pieces;

import com.github.bapachec.chessengine.Board;

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
    public boolean hasAnyLegalMoves(Piece[][] board, boolean whiteTurn, int king_row, int king_col) {
        // directions
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}, //up, down, left, right
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1} //top left, top right, lower left, lower right
        };

        return super.hasAnyLegalMovesContinuous(board, whiteTurn, directions, king_row, king_col);
    }

    @Override
    public String toString() {
        if (isWhite())
            return "Q";
        else
            return "q";
    }
}
