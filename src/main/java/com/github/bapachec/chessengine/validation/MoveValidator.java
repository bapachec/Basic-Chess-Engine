package com.github.bapachec.chessengine.validation;
import com.github.bapachec.chessengine.pieces.Piece;

public interface MoveValidator {

    //todo may not be correct
    default public boolean isColClear(Piece[][] board, Piece piece, int row) {
        byte k;
        int col = piece.getColumn();
        //if negative, it means go up the board
        if (row - piece.getRow() < 0)
            k = -1;
        else
            k = 1;

        //will search the column
        for (int i = piece.getRow(); i < 8 && i >= 0; i = i + k){
            if (board[i][col] != null)
                return false;

        }

        return true;
    }

    default public boolean isRowClear(Piece[][] board, Piece piece, int col) {
        byte k;
        int row = piece.getRow();

        if (col - piece.getColumn() < 0)
            k = -1;
        else
            k = 1;

        for (int i = piece.getColumn(); i < 8 && i >= 0; i = i + k){
            if (board[row][i] != null)
                return false;
        }

        return true;
    }

    //todo implement this
    default public boolean isDiagClear(Piece[][] board, Piece piece, int row, int col) {

        return true;
    }
}
