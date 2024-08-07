package com.github.bapachec.chessengine;
import com.github.bapachec.chessengine.pieces.*;

public class Board {
    private Piece[][] board = new Piece[8][8];

    public void play() {
        populateBoard();
        board[7][0] = new Rook();
        display();
    }

    private void populateBoard() {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                board[i][j] = null;

    }

    private void display() {
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                if (board[i][j] == null) {
                    System.out.print("[ ]");
                }
                else {
                    System.out.print("[" + board[i][j] + "]");
                }
            System.out.println();
        }
    }

}
