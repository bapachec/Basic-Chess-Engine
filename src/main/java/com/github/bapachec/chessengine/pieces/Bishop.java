package com.github.bapachec.chessengine.pieces;

import com.github.bapachec.chessengine.Board;

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
    public boolean hasAnyLegalMoves(Piece[][] board, boolean whiteTurn, int king_row, int king_col) {
        // directions: top left, top right, lower left, lower right
        int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        return super.hasAnyLegalMovesContinuous(board, whiteTurn, directions, king_row, king_col);
    }

    @Override
    public String toString() {
        if (isWhite())
            return "B";
        else
            return "b";
    }
}
