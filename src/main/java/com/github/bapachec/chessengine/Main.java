package com.github.bapachec.chessengine;

public class Main {
    public static void main (String[] args) {
        UserInterface cli = new PlayChess();
        ChessEngine engine = new ChessEngine(cli);
        cli.run(engine);

    }
}
