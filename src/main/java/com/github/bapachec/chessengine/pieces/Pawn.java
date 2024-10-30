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
    public boolean hasAnyLegalMoves(Piece[][] board, boolean whiteTurn, int king_row, int king_col) {

        int row = getRow();
        int col = getColumn();
        int direction = 0;

        if (!isWhite()) {
            direction = 1;
            //return board[getRow() + 1][getColumn()] != null;
        }
        else {
            direction = -1;
            //return board[getRow() - 1][getColumn()] != null;
        }

        //can it capture tho?

        //this does not include blocking on row 7 or 0
        boolean blocked = false;
        boolean canLeftCap = false;
        boolean canRightCap = false;


        if (board[row + direction][col] != null) {
            blocked = true;
        }

        if(col > 0) {
            Piece target  = board[row + direction][col - 1];
            if( target != null && target.isWhite() != whiteTurn)
                canLeftCap = true;

        }

        if (col < 7) {
            Piece target  = board[row + direction][col + 1];
            if ( target != null && target.isWhite() != whiteTurn)
                canRightCap = true;
        }

        if (blocked && !canRightCap && !canLeftCap) {
            return false;
        }

        int newRow = row + direction;
        //int newCol = col;
        board[row][col] = null;

        if (!blocked) {
            board[newRow][col] = this;
            //board[row][col] = null;
            setRow(newRow);
            if (Board.KingCheck.isKingNotInCheck(board, king_row, king_col, whiteTurn)) {
                setRow(row);
                return true;
            }
            board[newRow][col] = null;
            //board[row][col] = this;
        }

        if (canRightCap) {
            board[newRow][col + 1] = this;
            //board[row][col] = null;
            setRow(newRow);
            setColumn(col + 1);
            if (Board.KingCheck.isKingNotInCheck(board, king_row, king_col, whiteTurn)) {
                setRow(row);
                setColumn(col);
                return true;
            }
            board[newRow][col + 1] = null;
        }

        if (canLeftCap) {
            board[newRow][col - 1] = this;
            setRow(newRow);
            setColumn(col - 1);
            if (Board.KingCheck.isKingNotInCheck(board, king_row, king_col, whiteTurn)) {
                setRow(row);
                setColumn(col);
                return true;
            }
            //board[newRow][col - 1] = null;
        }

//        board[row][col] = null;

        setRow(row);
        setColumn(col);
        return false;
    }

    @Override
    public String toString() {
        if (isWhite())
            return "P";
        else
            return "p";
    }
}
