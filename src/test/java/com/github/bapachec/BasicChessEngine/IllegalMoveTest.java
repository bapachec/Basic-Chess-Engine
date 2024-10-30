package com.github.bapachec.BasicChessEngine;

import com.github.bapachec.chessengine.ChessEngine;
import com.github.bapachec.chessengine.ChessUI;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IllegalMoveTest {

    private ChessEngine engine;
    private ChessUI mockUIListener;

    @BeforeEach
    void setUp() {
        engine = new ChessEngine();
        mockUIListener = mock(ChessUI.class);
        engine.addListener(mockUIListener);
        //engine.start();
    }

    //##################################################################################################################
    //##################################################################################################################
    //=====================================================TestCase=====================================================
    //##################################################################################################################
    //##################################################################################################################
    //One white rook that is tested
    @Test
    void testIllegalRookMove() {
        char[][] board = {
                {'_','_','k','_','_','_','_','_'},
                {'_','_','_','_','_','p','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','P','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','R','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };

        char[][] expectedBoardAfterStart = {
                {'_','_','k','_','_','_','_','_'},
                {'_','_','_','_','_','p','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','P','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','R','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };
        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //white turn
        boolean result = engine.validPiece("f2");
        assertTrue(result, "white should be able to pick f2 rook.");
        result = engine.makeMove("f2", "h4");
        assertFalse(result, "white should not be able to move f2 rook to h4. diagonal movement not allowed.");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'_','_','k','_','_','_','_','_'},
                {'_','_','_','_','_','p','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','P','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','R','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white tries to move rook again but tries to eliminate black rook at f7
        result = engine.validPiece("f2");
        assertTrue(result, "white should be able to pick f2 rook because it is still white's turn.");
        result = engine.makeMove("f2","f7");
        assertFalse(result, "white should not be able to move f2 rook to f7. White pawn is blocking white rook.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white tries to move rook again but tries to eliminate black rook at f7
        result = engine.validPiece("f2");
        assertTrue(result, "white should be able to pick f2 rook because it is still white's turn.");
        result = engine.makeMove("f2","f4");
        assertFalse(result, "white should not be able to move f2 rook to f4. rook cannot capture friendly pieces.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));
    }

    //##################################################################################################################
    //##################################################################################################################
    //=====================================================TestCase=====================================================
    //##################################################################################################################
    //##################################################################################################################
    //pawn is tested for illegal moves
    @Test
    void testIllegalPawnMoves() {
        char[][] board = {
                {'_','_','_','_','_','_','k','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','p','P','_','_'},
                {'_','_','_','_','P','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','P','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };

        char[][] expectedBoardAfterStart = {
                {'_','_','_','_','_','_','k','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','p','P','_','_'},
                {'_','_','_','_','P','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','P','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };
        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //white turn
        boolean result = engine.validPiece("d2");
        assertTrue(result, "white should be able to pick f2 pawn.");
        result = engine.makeMove("d2", "d1");
        assertFalse(result, "white should not be able to move d2 pawn to d1. pawn cannot move backwards.");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'_','_','_','_','_','_','k','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','p','P','_','_'},
                {'_','_','_','_','P','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','P','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("d2");
        assertTrue(result, "white should be able to pick f2 pawn because it is still white's turn.");
        result = engine.makeMove("d2", "e3");
        assertFalse(result, "white should not be able to move d2 pawn to e3. pawn cannot move diagonally.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("e4");
        assertTrue(result, "white should be able to pick e4 pawn because it is still white's turn.");
        result = engine.makeMove("e4", "e5");
        assertFalse(result, "white should not be able to move e4 pawn to e5. pawn cannot capture pieces in front of them.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("e4");
        assertTrue(result, "white should be able to pick e4 pawn because it is still white's turn.");
        result = engine.makeMove("e4", "e6");
        assertFalse(result, "white should not be able to move e4 pawn to e6. pawn cannot jump over pieces.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("e4");
        assertTrue(result, "white should be able to pick e4 pawn because it is still white's turn.");
        result = engine.makeMove("e4", "f5");
        assertFalse(result, "white should not be able to move e4 pawn to f5. pawn cannot capture friendly pieces.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));
    }

    //##################################################################################################################
    //##################################################################################################################
    //=====================================================TestCase=====================================================
    //##################################################################################################################
    //##################################################################################################################
    //test that would leave player's king in check. Engine flags this as illegal
    @Test
    void testMovingIntoCheck() {

        char[][] board = {
                {'_','_','_','_','_','_','k','_'},
                {'_','r','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','B','_','_','_','_','_','_'},
                {'_','K','p','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
        };

        char[][] expectedBoardAfterStart = {
                {'_','_','_','_','_','_','k','_'},
                {'_','r','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','B','_','_','_','_','_','_'},
                {'_','K','p','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
        };
        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //white turn
        boolean result = engine.validPiece("b4");
        assertTrue(result, "white should be able to pick b4 bishop.");
        result = engine.makeMove("b4", "e6");
        assertFalse(result, "white should not be able to move b4 bishop to e6. Doing so will leave king in check.");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'_','_','_','_','_','_','k','_'},
                {'_','r','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','B','_','_','_','_','_','_'},
                {'_','K','p','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("b3");
        assertTrue(result, "white should be able to pick b3 king because it is still white's turn.");
        result = engine.makeMove("b3", "b2");
        assertFalse(result, "white should not be able to move b3 king to b2. Doing so will leave king in check.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));
    }

    //##################################################################################################################
    //##################################################################################################################
    //=====================================================TestCase=====================================================
    //##################################################################################################################
    //##################################################################################################################
    //King is tested for illegal moves
    @Test
    void testIllegalKingMoves() {

        char[][] board = {
                {'_','_','_','_','_','_','k','_'},
                {'_','r','_','_','_','_','_','_'},
                {'_','_','K','_','_','_','_','_'},
                {'_','_','_','P','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
        };

        char[][] expectedBoardAfterStart = {
                {'_','_','_','_','_','_','k','_'},
                {'_','r','_','_','_','_','_','_'},
                {'_','_','K','_','_','_','_','_'},
                {'_','_','_','P','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
        };
        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //white turn
        boolean result = engine.validPiece("c6");
        assertTrue(result, "white should be able to pick c6 king.");
        result = engine.makeMove("c6", "b6");
        assertFalse(result, "white should not be able to move c6 king to b6. Doing so will leave king in check.");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'_','_','_','_','_','_','k','_'},
                {'_','r','_','_','_','_','_','_'},
                {'_','_','K','_','_','_','_','_'},
                {'_','_','_','P','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("c6");
        assertTrue(result, "white should be able to pick c6 king because it is still white's turn.");
        result = engine.makeMove("c6", "e6");
        assertFalse(result, "white should not be able to move c6 king to e6. king cannot move more than one space.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("c6");
        assertTrue(result, "white should be able to pick c6 king because it is still white's turn.");
        result = engine.makeMove("c6", "d5");
        assertFalse(result, "white should not be able to move c6 king to d5. king cannot capture friendly pieces.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

    }

    //##################################################################################################################
    //##################################################################################################################
    //=====================================================TestCase=====================================================
    //##################################################################################################################
    //##################################################################################################################
    //Bishop is tested for illegal moves
    @Test
    void testIllegalBishopMoves() {

        char[][] board = {
                {'_','_','_','_','_','_','k','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','p','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','N','_','K','_','_'},
                {'_','_','B','_','_','_','_','_'},
        };

        char[][] expectedBoardAfterStart = {
                {'_','_','_','_','_','_','k','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','p','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','N','_','K','_','_'},
                {'_','_','B','_','_','_','_','_'},
        };

        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //white turn
        boolean result = engine.validPiece("c1");
        assertTrue(result, "white should be able to pick c1 bishop.");
        result = engine.makeMove("c1", "c3");
        assertFalse(result, "white should not be able to move c1 bishop to c3. bishop cannot move vertically.");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'_','_','_','_','_','_','k','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','p','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','N','_','K','_','_'},
                {'_','_','B','_','_','_','_','_'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("c1");
        assertTrue(result, "white should be able to pick c1 bishop because it is still white's turn.");
        result = engine.makeMove("c1", "a1");
        assertFalse(result, "white should not be able to move c1 bishop to a1. bishop cannot move horizontally.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("c1");
        assertTrue(result, "white should be able to pick c1 bishop because it is still white's turn.");
        result = engine.makeMove("c1", "e3");
        assertFalse(result, "white should not be able to move c1 bishop to e3. bishop cannot jump over pieces.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("c1");
        assertTrue(result, "white should be able to pick c1 bishop because it is still white's turn.");
        result = engine.makeMove("c1", "d2");
        assertFalse(result, "white should not be able to move c1 bishop to d2. bishop cannot capture friendly pieces.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

    }

    //##################################################################################################################
    //##################################################################################################################
    //=====================================================TestCase=====================================================
    //##################################################################################################################
    //##################################################################################################################
    //Knight is tested for illegal moves
    @Test
    void testIllegalKnightMoves() {

        char[][] board = {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','k','p','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','N','_','_','_','_'},
                {'_','N','_','_','K','_','_','_'},
        };

        char[][] expectedBoardAfterStart = {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','k','p','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','N','_','_','_','_'},
                {'_','N','_','_','K','_','_','_'},
        };

        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //white turn
        boolean result = engine.validPiece("d2");
        assertTrue(result, "white should be able to pick d2 knight.");
        result = engine.makeMove("d2", "d4");
        assertFalse(result, "white should not be able to move d2 knight to d4. knights cannot move two spaces vertically.");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','k','p','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','N','_','_','_','_'},
                {'_','N','_','_','K','_','_','_'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("d2");
        assertTrue(result, "white should be able to pick d2 knight because it is still white's turn.");
        result = engine.makeMove("d2", "f2");
        assertFalse(result, "white should not be able to move d2 knight to f2. knights cannot move two spaces horizontally.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("d2");
        assertTrue(result, "white should be able to pick d2 knight because it is still white's turn.");
        result = engine.makeMove("d2", "b1");
        assertFalse(result, "white should not be able to move d2 knight to b1. knights cannot capture friendly pieces.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

    }

    @Test
    void testIllegalQueenMoves() {

        char[][] board = {
                {'_','k','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','p','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','P','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','Q','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };

        char[][] expectedBoardAfterStart = {
                {'_','k','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','p','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','P','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','Q','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };

        engine.start(board);
        assertArrayEquals(expectedBoardAfterStart, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterStart));

        //white turn
        boolean result = engine.validPiece("c3");
        assertTrue(result, "white should be able to pick c3 queen.");
        result = engine.makeMove("c3", "d1");
        assertFalse(result, "white should not be able to move c3 queen to d1. queens cannot move like L shape.");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'_','k','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','p','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','P','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','Q','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','K','_','_','_','_','_','_'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("c3");
        assertTrue(result, "white should be able to pick c3 queen because it is still white's turn.");
        result = engine.makeMove("c3", "g8");
        assertFalse(result, "white should not be able to move c3 queen to g8. queen cannot move almost diagonal then partially vertical.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("c3");
        assertTrue(result, "white should be able to pick c3 queen because it is still white's turn.");
        result = engine.makeMove("c3", "g7");
        assertFalse(result, "white should not be able to move c3 queen to g7. queen cannot jump over friendly piece.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //white turn
        result = engine.validPiece("c3");
        assertTrue(result, "white should be able to pick c3 queen because it is still white's turn.");
        result = engine.makeMove("c3", "e5");
        assertFalse(result, "white should not be able to move c3 queen to e5. queen cannot capture friendly piece.");

        //board should stay the same.
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

    }
}
