package com.github.bapachec.chessengine.pieces;

import com.github.bapachec.chessengine.Board;

public class King extends Piece {
    private boolean notMoved = true;

    public King(boolean isWhite, int row, int column) {
        super(isWhite, row, column);
    }

    @Override
    public boolean isLegalMove(Piece[][] board, int row, int column) {
        if (!super.isLegalMove(board, row, column))
            return false;

        if (Math.abs(row - getRow()) > 1 || Math.abs(column - getColumn()) > 1) {
            if (board[row][column] instanceof Rook rook) {
                //if (isKingNotInCheck(board, getRow(), getColumn()) && notMoved)
                //could be removed?
                if (notMoved) {
                    if (rook.getNotMoved() && isRowClear(board, this, column)) {
                        notMoved = false;
                        return true;
                    }
                }

            }

            return false;
        }
        notMoved = false;
        return true;
    }

    public boolean getNotMoved() {
        return notMoved;
    }

    @Override
    public boolean hasAnyLegalMoves(Piece[][] board, boolean whiteTurn) {

        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}, //up, down, left, right
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1} //top left, top right, lower left, lower right
        };

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

            if (Board.KingCheck.isKingNotInCheck(board, newRow, newCol, whiteTurn)) {
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
        if (isWhite())
            return "K";
        else
            return "k";
    }
}
