package com.github.bapachec.chessengine.pieces;

import com.github.bapachec.chessengine.Board;
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

    //for king to override and for the outside world to call
    public boolean hasAnyLegalMoves(Piece[][] board, boolean whiteTurn) { return false; }

    //for all other pieces (except for king) to override and for the outside world to call
    public boolean hasAnyLegalMoves(Piece[][] board, boolean whiteTurn, int king_row, int king_col) { return false; }

    //todo calls made to kingnotincheck used incorrectly, row and col to the static method should be the king coordinates
    //for rook, bishop, and queen to call
    protected boolean hasAnyLegalMovesContinuous(Piece[][] board, boolean whiteTurn, int[][] directions, int king_row, int king_col) {
        int row = getRow();
        int col = getColumn();

        int newRow = row;
        int newCol = col;

        board[row][col] = null;

        // each loop goes in one direction
        for (int[] dir: directions) {
            while (true) {
                newRow += dir[0];
                newCol += dir[1];

                if (newRow < 0 || newRow > 7 || newCol < 0 || newCol > 7) {
                    newRow = row;
                    newCol = col;
                    break;
                }

                Piece target = board[newRow][newCol];

                setRow(newRow);
                setColumn(newCol);
                board[newRow][newCol] = this;
                //board[row][col] = null;


                if (target == null) {
                    //this.setRow(newRow);
                    //this.setColumn(newCol);
                    if (Board.KingCheck.isKingNotInCheck(board, king_row, king_col, whiteTurn)) {
                        setRow(row);
                        setColumn(col);
                        return true;
                    }

                    board[newRow][newCol] = null;

                }
                else if (target.isWhite() != whiteTurn) {
                    //this.setRow(newRow);
                    //this.setColumn(newCol);
                    if (Board.KingCheck.isKingNotInCheck(board, king_row, king_col, whiteTurn)) {
                        setRow(row);
                        setColumn(col);
                        return true;
                    }
                    board[newRow][newCol] = null;
                    break;

                } else {
                    board[newRow][newCol] = null;
                    break;
                }


            }
        }

        setRow(row);
        setColumn(col);
        return false;
    }

    //knight to call
    protected boolean hasAnyLegalMovesFixed(Piece[][] board, boolean whiteTurn, int[][] directions, int king_row, int king_col) {
        int row = getRow();
        int col = getColumn();

        board[row][col] = null;

        for (int[] dir: directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow < 0 || newRow > 7 || newCol < 0 || newCol > 7)
                continue;

            Piece target = board[newRow][newCol];

            if (target != null && target.isWhite() == whiteTurn)
                continue;

            setRow(newRow);
            setColumn(newCol);
            board[newRow][newCol] = this;

            if (Board.KingCheck.isKingNotInCheck(board, king_row, king_col, whiteTurn)) {
                setRow(row);
                setColumn(col);
                return true;
            }

            board[newRow][newCol] = null;
        }

        setRow(row);
        setColumn(col);
        return false;
    }

    @Override
    public String toString() {
        return "_";
    }

}
