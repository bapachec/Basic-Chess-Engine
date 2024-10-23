package com.github.bapachec.BasicChessEngine;

import com.github.bapachec.chessengine.ChessEngine;
import com.github.bapachec.chessengine.UserInterface;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BasicCheckmateTest {
    private ChessEngine engine;
    private UserInterface mockUIListener;

    @BeforeEach
    void setUp() {
        engine = new ChessEngine();
        mockUIListener = mock(UserInterface.class);
        engine.addListener(mockUIListener);
        //engine.start();
    }

    //##################################################################################################################
    //##################################################################################################################
    //=====================================================TestCase=====================================================
    //##################################################################################################################
    //##################################################################################################################
    //two rooks working together
    @Test
    void testTwoRooksCheckmate() {
        char[][] board = {
                {'_','_','k','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','R'},
                {'R','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };

        char[][] expectedBoardAfterStart = {
                {'_','_','k','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','R'},
                {'R','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };
        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //White Turn ==================================================================================================
        boolean result = engine.validPiece("a6");
        assertTrue(result, "White should be able to select a6 rook");
        result = engine.makeMove("a6","g6");
        assertTrue(result, "white should be able to move a6 rook to g6");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'_','_','k','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','R'},
                {'_','_','_','_','_','_','R','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //Black Turn ==================================================================================================
        result = engine.validPiece("c8");
        assertTrue(result, "Black should be able to select c8 king after white moved");
        result = engine.makeMove("c8", "b8");
        assertTrue(result, "Black should be able to move c8 king to b8");

        //Board state after black turn
        char[][] expectedBoardAfterBlackTurn = {
                {'_','k','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','R'},
                {'_','_','_','_','_','_','R','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };
        assertArrayEquals(expectedBoardAfterBlackTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackTurn));

        //White Turn ==================================================================================================
        result = engine.validPiece("g6");
        assertTrue(result, "White should be able to select g6 rook after black moved");
        result = engine.makeMove("g6", "g8");
        assertTrue(result, "white should be able to move g6 rook to g8");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteSecondTurn = {
                {'_','k','_','_','_','_','R','_'},
                {'_','_','_','_','_','_','_','R'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };

        assertArrayEquals(expectedBoardAfterWhiteSecondTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteSecondTurn));

        //Game over after white turn and white won =====================================================================
        result = engine.isGameOver();
        assertTrue(result);
        boolean whiteWon = true;
        verify(mockUIListener).checkmate(eq(whiteWon));
    }

    //##################################################################################################################
    //##################################################################################################################
    //=====================================================TestCase=====================================================
    //##################################################################################################################
    //##################################################################################################################
    //King and Queen working together
    @Test
    void testBasicCheckmate() {
        char[][] board = {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','Q','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','K','_','_','_'},
                {'_','_','_','_','_','_','_','k'},
        };

        char[][] expectedBoardAfterStart = {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','Q','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','K','_','_','_'},
                {'_','_','_','_','_','_','_','k'},
        };
        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //White Turn ==================================================================================================
        boolean result = engine.validPiece("e2");
        assertTrue(result, "White should be able to select e2 king");
        result = engine.makeMove("e2","f2");
        assertTrue(result, "white should be able to move e2 king to f2");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','Q','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','K','_','_'},
                {'_','_','_','_','_','_','_','k'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //Black Turn ==================================================================================================
        result = engine.validPiece("h1");
        assertTrue(result, "Black should be able to select h1 king after white moved");
        result = engine.makeMove("h1", "h2");
        assertTrue(result, "Black should be able to move h1 king to h2");

        //Board state after black turn
        char[][] expectedBoardAfterBlackTurn = {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','Q','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','K','_','k'},
                {'_','_','_','_','_','_','_','_'},
        };
        assertArrayEquals(expectedBoardAfterBlackTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackTurn));

        //White Turn ==================================================================================================
        result = engine.validPiece("g4");
        assertTrue(result, "White should be able to select g4 king after black moved");
        result = engine.makeMove("g4", "g2");
        assertTrue(result, "white should be able to move g4 king to g2");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteSecondTurn = {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','K','Q','k'},
                {'_','_','_','_','_','_','_','_'},
        };

        assertArrayEquals(expectedBoardAfterWhiteSecondTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteSecondTurn));

        //Game over after white turn and white won =====================================================================
        result = engine.isGameOver();
        assertTrue(result);
        boolean whiteWon = true;
        verify(mockUIListener).checkmate(eq(whiteWon));
    }

    //##################################################################################################################
    //##################################################################################################################
    //=====================================================TestCase=====================================================
    //##################################################################################################################
    //##################################################################################################################
    //Rook checkmate with help from black's pawns
    @Test
    void testBackRankCheckmate() {
        char[][] board = {
                {'_','_','r','_','r','_','k','_'},
                {'_','_','_','_','_','p','p','p'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','Q','P','P','P'},
                {'_','_','_','_','R','_','K','_'},
        };

        char[][] expectedBoardAfterStart = {
                {'_','_','r','_','r','_','k','_'},
                {'_','_','_','_','_','p','p','p'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','Q','P','P','P'},
                {'_','_','_','_','R','_','K','_'},
        };
        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //White Turn ==================================================================================================
        boolean result = engine.validPiece("e2");
        assertTrue(result, "White should be able to select e2 queen");
        result = engine.makeMove("e2","e8");
        assertTrue(result, "white should be able to move e2 queen to e8");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'_','_','r','_','Q','_','k','_'},
                {'_','_','_','_','_','p','p','p'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','P','P','P'},
                {'_','_','_','_','R','_','K','_'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //Black Turn ==================================================================================================
        //check if game over which should be false
        result = engine.isGameOver();
        assertFalse(result, "The game is in check but its not game over, black rook should be able to save its king");
        boolean whitesTurn = false;
        verify(mockUIListener).kingInCheckWarning(eq(whitesTurn));

        //Black continues
        result = engine.validPiece("c8");
        assertTrue(result, "Black should be able to select c8 rook after white moved");
        result = engine.makeMove("c8", "e8");
        assertTrue(result, "Black should be able to move c8 rook to e8");

        //Board state after black turn
        char[][] expectedBoardAfterBlackTurn = {
                {'_','_','_','_','r','_','k','_'},
                {'_','_','_','_','_','p','p','p'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','P','P','P'},
                {'_','_','_','_','R','_','K','_'},
        };
        assertArrayEquals(expectedBoardAfterBlackTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackTurn));

        //White Turn ==================================================================================================
        result = engine.validPiece("e1");
        assertTrue(result, "White should be able to select e1 rook after black moved");
        result = engine.makeMove("e1", "e8");
        assertTrue(result, "white should be able to move e1 rook to e8");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteSecondTurn = {
                {'_','_','_','_','R','_','k','_'},
                {'_','_','_','_','_','p','p','p'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','P','P','P'},
                {'_','_','_','_','_','_','K','_'},
        };

        assertArrayEquals(expectedBoardAfterWhiteSecondTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteSecondTurn));

        //Game over after white turn and white won =====================================================================
        result = engine.isGameOver();
        assertTrue(result);
        boolean whiteWon = true;
        verify(mockUIListener).checkmate(eq(whiteWon));
    }

    //Knight,Bishop,King checkmate
    @Test
    void testNKB_checkmate() {
        char[][] board = {
                {'_','k','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
                {'_','_','N','_','_','B','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
        };

        char[][] expectedBoardAfterStart = {
                {'_','k','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
                {'_','_','N','_','_','B','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
        };
        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //White Turn ==================================================================================================
        boolean result = engine.validPiece("c5");
        assertTrue(result, "White should be able to select c5 knight");
        result = engine.makeMove("c5","a6");
        assertTrue(result, "white should be able to move c5 knight to a6");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'_','k','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'N','K','_','_','_','_','_','_'},
                {'_','_','_','_','_','B','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //Black Turn ==================================================================================================
        //check if game over which should be false
        result = engine.isGameOver();
        assertFalse(result, "The game is in check but its not game over, black king should move out of harm's way");
        boolean whitesTurn = false;
        verify(mockUIListener).kingInCheckWarning(eq(whitesTurn));

        //Black continues
        result = engine.validPiece("b8");
        assertTrue(result, "Black should be able to select b8 king after white moved");
        result = engine.makeMove("b8", "a8");
        assertTrue(result, "Black should be able to move b8 king to a8");

        //Board state after black turn
        char[][] expectedBoardAfterBlackTurn = {
                {'k','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'N','K','_','_','_','_','_','_'},
                {'_','_','_','_','_','B','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
        };
        assertArrayEquals(expectedBoardAfterBlackTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackTurn));

        //White Turn ==================================================================================================
        result = engine.validPiece("f5");
        assertTrue(result, "White should be able to select f5 bishop after black moved");
        result = engine.makeMove("f5", "e4");
        assertTrue(result, "white should be able to move f5 bishop to e4");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteSecondTurn = {
                {'k','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'N','K','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','B','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
        };

        assertArrayEquals(expectedBoardAfterWhiteSecondTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteSecondTurn));

        //Game over after white turn and white won =====================================================================
        result = engine.isGameOver();
        assertTrue(result);
        boolean whiteWon = true;
        verify(mockUIListener).checkmate(eq(whiteWon));
    }
}
