package com.github.bapachec.chessengine.validation;
import com.github.bapachec.chessengine.pieces.Piece;

public interface MoveValidator {

    default public boolean isColClear(Piece[][] board, Piece piece, int row) {

        int col = piece.getColumn();
        //if negative, it means go up the board
        if (row - piece.getRow() < 0)
            //will search the column
            for (int i = piece.getRow() - 1; i > row; i--){
                if (board[i][col] != null)
                    return false;
            }
        else
            for (int i = piece.getRow() + 1; i < row; i++){
                if (board[i][col] != null)
                    return false;
            }


        return true;
    }

    default public boolean isRowClear(Piece[][] board, Piece piece, int col) {

        int row = piece.getRow();

        if (col - piece.getColumn() < 0)
            for (int i = piece.getColumn() - 1; i > col; i--){
                if (board[row][i] != null)
                    return false;
            }
        else
            for (int i = piece.getColumn() + 1; i < col; i++){
                if (board[row][i] != null)
                    return false;
            }


        return true;
    }


    default public boolean isDagClear(Piece[][] board, Piece piece, int row, int col) {
        if (row - piece.getRow() < 0) {
            int n = 0;
            if (col - piece.getColumn() < 0)
                n = -1;
            else
                n = 1;
            for (int i = piece.getRow() - 1, j = piece.getColumn() + n; i > row ; i--, j += n) {
                if (board[i][j] != null)
                    return false;
            }
        }
        else {
            int n = 0;
            if (col - piece.getColumn() < 0)
                n = -1;
            else
                n = 1;
            for (int i = piece.getRow() + 1, j = piece.getColumn() + n; i < row ; i++, j += n) {
                if (board[i][j] != null)
                    return false;
            }
        }

        return true;
    }
}
