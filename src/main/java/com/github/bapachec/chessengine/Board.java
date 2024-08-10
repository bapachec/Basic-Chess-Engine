package com.github.bapachec.chessengine;
import com.github.bapachec.chessengine.pieces.*;

public class Board {
    private final Piece[][] BOARD = new Piece[8][8];

    public void play() {
        populateBoard();
        display();
    }

    private void populateBoard() {

        for (int i = 0; i <8; i++) {
            BOARD[1][i] = new Pawn();
            BOARD[6][i] = new Pawn();
        }

        BOARD[0][0] = new Rook();
        BOARD[0][7] = new Rook();
        BOARD[7][0] = new Rook();
        BOARD[7][7] = new Rook();

    }

    private void display() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                if (BOARD[i][j] == null) {
                    System.out.print("[ ]");
                }
                else {
                    System.out.print("[" + BOARD[i][j] + "]");
                }
            System.out.println();
        }
    }

}
