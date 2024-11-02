package com.github.bapachec.BasicChessEngine;

import com.github.bapachec.chessengine.ChessEngine;
import com.github.bapachec.chessengine.ChessUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StalemateTest {

    private ChessEngine engine;
    private ChessUI mockUIListener;

    @BeforeEach
    void setUp() {
        engine = new ChessEngine();
        mockUIListener = mock(ChessUI.class);
        engine.addListener(mockUIListener);
    }

    @Test
    void testBlackKingStalemateOne() {
        char[][] board = {
                {'K','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','Q','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','k'},
        };

        char[][] expectedBoardAfterStart = {
                {'K','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','Q','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','k'},
        };

        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //White Turn ==================================================================================================
        boolean result = engine.validPiece("f3");
        assertTrue(result, "White should be able to select f3 queen.");
        result = engine.makeMove("f3", "f2");
        assertTrue(result, "white should be able to move f3 queen to f2.");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'K','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','Q','_','_'},
                {'_','_','_','_','_','_','_','k'},
        };

        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //Game over after white turn and stalemate =====================================================================
        result = engine.isGameOver();
        assertTrue(result);
        verify(mockUIListener).stalemate();
    }

    @Test
    void testBlackKingStalemateTwo() {
        char[][] board = {
                {'k','_','_','_','_','_','_','R'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','p'},
                {'_','_','_','_','_','p','_','P'},
                {'b','_','_','_','_','N','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'R','Q','_','_','_','_','_','K'},
        };

        char[][] expectedBoardAfterStart = {
                {'k','_','_','_','_','_','_','R'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','p'},
                {'_','_','_','_','_','p','_','P'},
                {'b','_','_','_','_','N','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'R','Q','_','_','_','_','_','K'},
        };

        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //White Turn ==================================================================================================
        boolean result = engine.validPiece("h8");
        assertTrue(result, "White should be able to select h8 rook.");
        result = engine.makeMove("h8", "h7");
        assertTrue(result, "white should be able to move h8 rook to h7.");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'k','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','R'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','p'},
                {'_','_','_','_','_','p','_','P'},
                {'b','_','_','_','_','N','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'R','Q','_','_','_','_','_','K'},
        };

        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //Game over after white turn and stalemate =====================================================================
        result = engine.isGameOver();
        assertTrue(result);
        verify(mockUIListener).stalemate();

    }

    @Test
    void testBlackKingStalemateThree() {
        char[][] board = {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'p','k','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };

        char[][] expectedBoardAfterStart = {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'p','k','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };

        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //White Turn ==================================================================================================
        boolean result = engine.validPiece("b1");
        assertTrue(result, "White should be able to select b1 king.");
        result = engine.makeMove("b1", "a1");
        assertTrue(result, "white should be able to move b1 king to a1.");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'p','k','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'K','_','_','_','_','_','_','_'},
        };


        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //black Turn ==================================================================================================
        result = engine.validPiece("a3");
        assertTrue(result, "black should be able to select a3 pawn.");
        result = engine.makeMove("a3", "a2");
        assertTrue(result, "black should be able to move a3 pawn to a2.");

        //Board state after white turn
        char[][] expectedBoardAfterBlackTurn = {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','k','_','_','_','_','_','_'},
                {'p','_','_','_','_','_','_','_'},
                {'K','_','_','_','_','_','_','_'},
        };


        assertArrayEquals(expectedBoardAfterBlackTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackTurn));


        //Game over after white turn and stalemate =====================================================================
        result = engine.isGameOver();
        assertTrue(result);
        verify(mockUIListener).stalemate();

    }

}
