package com.github.bapachec.chessengine;

public interface UserInterface {

    void run();

    int promotionRequest();

    void kingInCheckWarning(boolean isWhitesTurn);

    void checkmate(boolean whiteWon, char[][] data);

    void onBoardUpdated(char[][] boardData);

}
