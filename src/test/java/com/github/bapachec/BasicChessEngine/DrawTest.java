package com.github.bapachec.BasicChessEngine;

import com.github.bapachec.chessengine.ChessEngine;
import com.github.bapachec.chessengine.ChessUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class DrawTest {

    private ChessEngine engine;
    private ChessUI mockUIListener;

    @BeforeEach
    void setUp() {
        engine = new ChessEngine();
        mockUIListener = mock(ChessUI.class);
        engine.addListener(mockUIListener);
    }


    @Test
    void testDrawOne() {
        char[][] board = {
                {'_','_','_','_','_','_','_','_'},
                {'_','r','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','k','_','_'},
                {'p','r','_','_','_','_','_','R'},
                {'K','_','_','_','_','_','_','_'},
        };

        char[][] expectedBoardAfterStart = {
                {'_','_','_','_','_','_','_','_'},
                {'_','r','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','k','_','_'},
                {'p','r','_','_','_','_','_','R'},
                {'K','_','_','_','_','_','_','_'},
        };

        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //White Turn ==================================================================================================
        boolean result = engine.validPiece("h2");
        assertTrue(result, "White should be able to select h2 rook.");
        result = engine.makeMove("h2", "b2");
        assertTrue(result, "white should be able to move h2 rook to b2.");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'_','_','_','_','_','_','_','_'},
                {'_','r','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','k','_','_'},
                {'p','R','_','_','_','_','_','_'},
                {'K','_','_','_','_','_','_','_'},
        };


        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //black Turn ==================================================================================================
        result = engine.validPiece("b7");
        assertTrue(result, "black should be able to select b7 rook.");
        result = engine.makeMove("b7", "a7");
        assertTrue(result, "white should be able to move b7 rook to a7.");

        //Board state after white turn
        char[][] expectedBoardAfterBlackTurn = {
                {'_','_','_','_','_','_','_','_'},
                {'r','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','k','_','_'},
                {'p','R','_','_','_','_','_','_'},
                {'K','_','_','_','_','_','_','_'},
        };


        assertArrayEquals(expectedBoardAfterBlackTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackTurn));


        //white Turn ==================================================================================================
        result = engine.validPiece("b2");
        assertTrue(result, "black should be able to select b2 rook.");
        result = engine.makeMove("b2", "a2");
        assertTrue(result, "white should be able to move b2 rook to a2.");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteSecondTurn = {
                {'_','_','_','_','_','_','_','_'},
                {'r','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','k','_','_'},
                {'R','_','_','_','_','_','_','_'},
                {'K','_','_','_','_','_','_','_'},
        };


        assertArrayEquals(expectedBoardAfterWhiteSecondTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteSecondTurn));

        //black Turn ==================================================================================================
        result = engine.validPiece("a7");
        assertTrue(result, "black should be able to select a7 rook.");
        result = engine.makeMove("a7", "a2");
        assertTrue(result, "white should be able to move a7 rook to a2.");

        //Board state after white turn
        char[][] expectedBoardAfterBlackSecondTurn = {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','k','_','_'},
                {'r','_','_','_','_','_','_','_'},
                {'K','_','_','_','_','_','_','_'},
        };


        assertArrayEquals(expectedBoardAfterBlackSecondTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackSecondTurn));

        //white Turn ==================================================================================================
        result = engine.validPiece("a1");
        assertTrue(result, "white should be able to select a1 king.");
        result = engine.makeMove("a1", "a2");
        assertTrue(result, "white should be able to move a1 king to a2.");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteThirdTurn = {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','k','_','_'},
                {'K','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
        };


        assertArrayEquals(expectedBoardAfterWhiteThirdTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteThirdTurn));

        //Game over after white turn and stalemate =====================================================================
        result = engine.isGameOver();
        assertTrue(result);
        verify(mockUIListener).draw();

    }

    //test request draw

    @Test
    void testDrawTwo() {
        engine.start();
        boolean isWhitesTurn = true;
        //White Turn ==================================================================================================
        when(mockUIListener.requestingDraw(eq(isWhitesTurn))).thenReturn(true);
        engine.requestDraw();

        verify(mockUIListener).requestingDraw(eq(isWhitesTurn));

        //Game over after white turn and stalemate =====================================================================
        boolean result = engine.isGameOver();
        assertTrue(result);
        verify(mockUIListener).draw();
    }
}
