package com.github.bapachec.chessengine.pieces;

public class Pawn extends Piece{

    private boolean isFirstMove = true;


    public Pawn(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }


    @Override
    public boolean isLegalMove(int row, int column) {
        if (!super.isLegalMove(row, column))
            return false;

        if (Math.abs(row - this.getRow()) > 1) {
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
