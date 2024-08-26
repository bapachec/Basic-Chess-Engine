package com.github.bapachec.chessengine;
import com.github.bapachec.chessengine.pieces.*;

public class Board {
    private final Piece[][] BOARD = new Piece[8][8];

    public void populateBoard() {

        for (int i = 0; i <8; i++) {
            BOARD[1][i] = new Pawn(false);
            BOARD[6][i] = new Pawn(true);
        }

        //Rooks
        BOARD[0][0] = new Rook(false);
        BOARD[0][7] = new Rook(false);
        BOARD[7][0] = new Rook(true);
        BOARD[7][7] = new Rook(true);

        //Knights
        BOARD[0][1] = new Knight(false);
        BOARD[0][6] = new Knight(false);
        BOARD[7][1] = new Knight(true);
        BOARD[7][6] = new Knight(true);

        //Bishops
        BOARD[0][2] = new Bishop(false);
        BOARD[0][5] = new Bishop(false);
        BOARD[7][2] = new Bishop(true);
        BOARD[7][5] = new Bishop(true);

        //Queens
        BOARD[0][3] = new Queen(false);
        BOARD[7][3] = new Queen(true);

        //Kings
        BOARD[0][4] = new King(false);
        BOARD[7][4] = new King(true);

    }


    public boolean movePiece(byte[] location, byte row, byte col, boolean isWhitesTurn) {
        Piece piece = BOARD[location[0]][location[1]];
        if (piece == null)
            return false;

        if (isWhitesTurn){
            if (!piece.isWhite()) {
                return false;
            }
        }
        else {
            if (piece.isWhite()) {
                return false;
            }
        }

        if (!piece.isLegalMove(row,col))
            return false;

        BOARD[location[0]][location[1]] = null;
        BOARD[row][col] = piece;

        return true;

    }

    public char[][] boardData() {
        char[][] board = new char[8][8];
        for(int i = 0; i < 8; i ++) {
            for(int j = 0; j < 8; j++) {
                if (BOARD[i][j] != null) {
                    board[i][j] = BOARD[i][j].toString().charAt(0);
                }
                else {
                    board[i][j] = board[i][j] = '_';
                }
            }
        }

        return board;
    }
}
