package com.github.bapachec.BasicChessEngine;

import com.github.bapachec.chessengine.ChessEngine;
import com.github.bapachec.chessengine.ChessUI;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;

public class CastlingTest {
    private ChessEngine engine;
    private ChessUI mockUIListener;

    @BeforeEach
    void setUp() {
        engine = new ChessEngine();
        mockUIListener = mock(ChessUI.class);
        engine.addListener(mockUIListener);
        //engine.start();
    }

    //castling
    @Test
    void testCastlingOne() {
        char[][] board = {
                {'r','n','_','_','k','_','_','r'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'R','_','_','_','K','_','_','R'},
        };

        char[][] expectedBoardAfterStart = {
                {'r','n','_','_','k','_','_','r'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'R','_','_','_','K','_','_','R'},
        };
        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //White Turn ==================================================================================================
        boolean result = engine.validPiece("e1");
        assertTrue(result, "White should be able to select e1 king");
        result = engine.makeMove("e1","c1");
        assertTrue(result, "white should be able to castle");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'r','n','_','_','k','_','_','r'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','K','R','_','_','_','R'},
        };

        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //Black Turn ==================================================================================================

        result = engine.validPiece("e8");
        assertTrue(result, "Black should be able to select e8 king");
        result = engine.makeMove("e8", "c8");
        assertFalse(result, "Black should not be able to castle because of white rook in d1");

        //Board state after black turn
        char[][] expectedBoardAfterBlackTurn = {
                {'r','n','_','_','k','_','_','r'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','K','R','_','_','_','R'},
        };
        assertArrayEquals(expectedBoardAfterBlackTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackTurn));


    }


    @Test
    void testCastlingTwo() {
        char[][] board = {
                {'r','n','_','_','k','_','_','r'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'R','_','_','_','K','_','_','R'},
        };

        char[][] expectedBoardAfterStart = {
                {'r','n','_','_','k','_','_','r'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'R','_','_','_','K','_','_','R'},
        };
        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //White Turn ==================================================================================================
        boolean result = engine.validPiece("e1");
        assertTrue(result, "White should be able to select e1 king");
        result = engine.makeMove("e1","g1");
        assertTrue(result, "white should be able to castle");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'r','n','_','_','k','_','_','r'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'R','_','_','_','_','R','K','_'},
        };

        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //Black Turn ==================================================================================================

        result = engine.validPiece("e8");
        assertTrue(result, "Black should be able to select e8 king");
        result = engine.makeMove("e8", "c8");
        assertFalse(result, "Black should not be able to castle because of knight in b8 blocking black king");

        //Board state after black turn
        char[][] expectedBoardAfterBlackTurn = {
                {'r','n','_','_','k','_','_','r'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'R','_','_','_','_','R','K','_'},
        };
        assertArrayEquals(expectedBoardAfterBlackTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackTurn));

        //Black Turn ==================================================================================================

        //Black continues
        result = engine.validPiece("e8");
        assertTrue(result, "Black should be able to select e8 king again");
        result = engine.makeMove("e8", "c8");
        assertFalse(result, "Black should not be able to castle with h8 rook because of white rook in f1");

        //Board state after black turn
        char[][] expectedBoardAfterBlackTurnAgain = {
                {'r','n','_','_','k','_','_','r'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'R','_','_','_','_','R','K','_'},
        };
        assertArrayEquals(expectedBoardAfterBlackTurnAgain, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackTurnAgain));


    }

    @Test
    void testCastlingThree() {
        char[][] board = {
                {'r','_','_','_','k','_','_','r'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'R','_','_','_','K','B','_','R'},
        };

        char[][] expectedBoardAfterStart = {
                {'r','_','_','_','k','_','_','r'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'R','_','_','_','K','B','_','R'},
        };
        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //White Turn ==================================================================================================
        boolean result = engine.validPiece("e1");
        assertTrue(result, "White should be able to select e1 king");
        result = engine.makeMove("e1","g1");
        assertFalse(result, "white should not be able to castle because of white bishop in f1 blocking");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'r','_','_','_','k','_','_','r'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'R','_','_','_','K','B','_','R'},
        };

        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //White Turn ==================================================================================================
        //white continues
        result = engine.validPiece("e1");
        assertTrue(result, "White should be able to select e1 king again");
        result = engine.makeMove("e1","a1");
        assertTrue(result, "white should be able to castle with a1 rook");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurnAgain = {
                {'r','_','_','_','k','_','_','r'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','K','R','_','B','_','R'},
        };

        assertArrayEquals(expectedBoardAfterWhiteTurnAgain, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurnAgain));

        //Black Turn ==================================================================================================

        result = engine.validPiece("e8");
        assertTrue(result, "Black should be able to select e8 king");
        result = engine.makeMove("e8", "a8");
        assertFalse(result, "Black should not be able to castle with a8 rook because of white rook in d1");

        //Board state after black turn
        char[][] expectedBoardAfterBlackTurn = {
                {'r','_','_','_','k','_','_','r'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','K','R','_','B','_','R'},
        };
        assertArrayEquals(expectedBoardAfterBlackTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackTurn));

        //Black Turn ==================================================================================================

        //Black continues
        result = engine.validPiece("e8");
        assertTrue(result, "Black should be able to select e8 king");
        result = engine.makeMove("e8", "h8");
        assertTrue(result, "Black should not be able to castle with a8 rook because of white rook in d1");

        //Board state after black turn
        char[][] expectedBoardAfterBlackTurnAgain = {
                {'r','_','_','_','_','r','k','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','K','R','_','B','_','R'},
        };
        assertArrayEquals(expectedBoardAfterBlackTurnAgain, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackTurnAgain));


    }

}
