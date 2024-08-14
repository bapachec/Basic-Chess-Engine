package com.github.bapachec.chessengine;
import java.util.Scanner;

public class PlayChess {

    public static void main (String[] args) {
        ChessEngine engine = new ChessEngine();
        //Scanner scan = new Scanner(System.in);
        engine.play();
        byte[] piece_location = {6,0};

        System.out.println("Enter row and column to place piece");
        //scan the players input but for now a placeholder
        byte row = 4;
        byte col = 0;
        engine.makeMove(piece_location, row, col);
    }

}
