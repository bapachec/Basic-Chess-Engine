package com.github.bapachec.chessengine.pieces;

import com.github.bapachec.chessengine.Board;

public class Knight extends Piece {

    public Knight(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }

    @Override
    public boolean isLegalMove(Piece[][] board, int row, int column) {
        if (!super.isLegalMove(board, row, column))
            return false;


        if (Math.abs(row - getRow()) != 2 && Math.abs(column - getColumn()) != 2)
            return false;

        if (Math.abs(column - getColumn()) != 1 && Math.abs(row - getRow()) != 1)
            return false;

        return true;
    }

    @Override
    public boolean hasAnyLegalMoves(Piece[][] board, boolean whiteTurn, int king_row, int king_col) {

        //directions
        int[][] directions = {
                {-1, -2}, {-2, -1}, //top left
                {-2, 1}, {-1, 2}, //top right
                {1, -2}, {2, -1}, //lower left
                {2, 1}, {1, 2} //lower right
        };

        return super.hasAnyLegalMovesFixed(board, whiteTurn, directions, king_row, king_col);
    }

    @Override
    public String toString() {
        if (isWhite())
            return "N";
        else
            return "n";
    }
}
