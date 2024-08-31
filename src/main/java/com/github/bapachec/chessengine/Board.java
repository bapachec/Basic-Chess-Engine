package com.github.bapachec.chessengine;
import com.github.bapachec.chessengine.pieces.*;

public class Board {
    private final Piece[][] BOARD = new Piece[8][8];

    public void populateBoard() {

        for (int i = 0; i <8; i++) {
            BOARD[1][i] = new Pawn(false, 1, i);
            BOARD[6][i] = new Pawn(true, 6, i);
        }

        //Rooks
        BOARD[0][0] = new Rook(false, 0, 0);
        BOARD[0][7] = new Rook(false, 0, 7);
        BOARD[7][0] = new Rook(true, 7, 0);
        BOARD[7][7] = new Rook(true, 7, 7);

        //Knights
        BOARD[0][1] = new Knight(false, 0, 1);
        BOARD[0][6] = new Knight(false, 0, 6);
        BOARD[7][1] = new Knight(true, 7, 1);
        BOARD[7][6] = new Knight(true, 7, 6);

        //Bishops
        BOARD[0][2] = new Bishop(false, 0, 2);
        BOARD[0][5] = new Bishop(false, 0, 5);
        BOARD[7][2] = new Bishop(true, 7, 2);
        BOARD[7][5] = new Bishop(true, 7, 5);

        //Queens
        BOARD[0][3] = new Queen(false, 0, 3);
        BOARD[7][3] = new Queen(true, 7, 3);

        //Kings
        BOARD[0][4] = new King(false, 0, 4);
        BOARD[7][4] = new King(true, 7, 4);

    }

    //to check if chosen piece is player's color
    public boolean samePiece(byte[] location, boolean isWhitesTurn) {
        Piece piece = BOARD[location[0]][location[1]];
        if (piece == null)
            return false;

        if (isWhitesTurn){
            return piece.isWhite();
        }
        else {
            return !piece.isWhite();
        }
    }


    public boolean movePiece(byte[] location, byte row, byte col) {
        Piece piece = BOARD[location[0]][location[1]];
        //if (piece == null) not needed i think
        //    return false;
        Piece targetPiece = BOARD[row][col];
        if (targetPiece != null)
            if (piece.isWhite() == targetPiece.isWhite())
                return false;

        if (!piece.isLegalMove(BOARD, row, col))
            return false;

        BOARD[location[0]][location[1]] = null;
        piece.setRow(row);
        piece.setColumn(col);
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
