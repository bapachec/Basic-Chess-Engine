package com.github.bapachec.chessengine;

public interface ChessUI {

    void run();

    int promotionRequest();

    void kingInCheckWarning(boolean isWhitesTurn);

    void checkmate(boolean whiteWon);

    void onBoardUpdated(char[][] boardData);

    void stalemate();

    void draw();

    boolean requestingDraw(boolean whiteTurn);

}
