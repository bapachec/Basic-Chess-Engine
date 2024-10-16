package com.github.bapachec.chessengine;

public class Main {

    public static void main (String[] args) {
        ChessEngine engine = new ChessEngine();

        PlayChess playChess = new PlayChess(engine);

        playChess.run();
    }
}
