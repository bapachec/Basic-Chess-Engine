package com.github.bapachec.chessengine.pieces;

import com.github.bapachec.chessengine.Board;

public class Pawn extends Piece{

    private boolean isFirstMove = true;


    public Pawn(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
        if (row != 1 && row != 6)
            isFirstMove = false;
    }


    @Override
    public boolean isLegalMove(Piece[][] board, int row, int column) {
        if (!super.isLegalMove(board, row, column))
            return false;

        if (!isWhite()) {
            if (row - getRow() < 0)
                return false;

        }
        else {
            if (getRow() - row < 0)
                return false;
        }

        int rowAbs = Math.abs(row - getRow());
        int colAbs = Math.abs(column - getColumn());

        //capture
        if (rowAbs == 1 && colAbs == 1){
            if (board[row][column] != null)
                return true;
            else {
                int r = getRow();
                if (r == 3) {
                    if (board[3][column] != null) {
                        board[3][column] = null;
                        return true;
                    }
                }
                if (r == 4) {
                    if (board[4][column] != null) {
                        board[4][column] = null;
                        return true;
                    }

                }

            }

        }

        //blocked
        if (board[row][column] != null) {
            return false;
        }

        //if move is more than one column
        if (colAbs >= 1)
            return false;

        //if pawn's first and wants to move two spaces
        if (rowAbs > 1) {
            if (isFirstMove && Math.abs(row - getRow()) <= 2) {
                isFirstMove = false;
                return true;
            }
            return false;
        }


        isFirstMove = false;
        return true;
    }

    @Override
    public String toString() {
        if (isWhite())
            return "P";
        else
            return "p";
    }
}
