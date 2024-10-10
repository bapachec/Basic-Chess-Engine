package com.github.bapachec.chessengine;

public interface UserInterface {

    void run(ChessEngine engine);

    int promotionRequest();

    void kingInCheckWarning(boolean isWhitesTurn);

    void checkmate(boolean whiteWon, char[][] data);

}
