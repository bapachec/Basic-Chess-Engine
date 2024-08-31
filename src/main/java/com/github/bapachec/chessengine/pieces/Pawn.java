package com.github.bapachec.chessengine.pieces;

public class Pawn extends Piece{

    private boolean isFirstMove = true;


    public Pawn(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }


    @Override
    public boolean isLegalMove(Piece[][] board, int row, int column) {
        if (!super.isLegalMove(board, row, column))
            return false;

        int rowAbs = Math.abs(row - getRow());
        int colAbs = Math.abs(column - getColumn());

        if (rowAbs == 1 && colAbs == 1){
            if (board[row][column] != null)
                return true;
        }

        if (colAbs >= 1)
            return false;

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
        return "P";
    }
}
