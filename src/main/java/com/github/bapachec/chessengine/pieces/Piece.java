package com.github.bapachec.chessengine.pieces;

import com.github.bapachec.chessengine.validation.MoveValidator;

public class Piece implements MoveValidator {
    private int row;
    private int column;
    private final boolean IS_WHITE;

    public Piece (boolean isWhite, int row, int column) {
        IS_WHITE = isWhite;
        this.row = row;
        this.column = column;
    }

    public boolean isWhite() { return IS_WHITE; }

    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }

    public int getColumn() { return column; }
    public void setColumn(int column) { this.column = column; }


    public boolean isLegalMove(Piece[][] board, int row, int column) {
        return isOnBoard(row,column) && !isCurrentLocation(row, column);
    }

    protected boolean isOnBoard(int row, int column) {
        return row >= 0 && row < 8 && column >= 0 && column < 8;
    }

    protected boolean isCurrentLocation(int row, int column) {
        return row == this.row && column == this.column;
    }



    @Override
    public String toString() {
        return "_";
    }

}
