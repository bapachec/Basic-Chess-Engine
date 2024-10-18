package com.github.bapachec.BasicChessEngine;

import com.github.bapachec.chessengine.ChessEngine;
import com.github.bapachec.chessengine.UserInterface;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BoardTest {

    private ChessEngine engine;
    private UserInterface mockUIListener;

    @BeforeEach
    void setUp() {
        engine = new ChessEngine();
        mockUIListener = mock(UserInterface.class);
        engine.addListener(mockUIListener);
        engine.start();
    }

    @Test
    void testInitialSetup() {
        char[][] expectedBoard = {
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };

        char[][] generatedBoard = engine.boardData();
        assertArrayEquals(expectedBoard, generatedBoard);

        verify(mockUIListener).onBoardUpdated(eq(expectedBoard));
    }

    @Test
    void testWhiteMovesFirst() {
        boolean result = engine.validPiece("f2");
        assertTrue(result, "White should be able to select f2 pawn");
        result = engine.makeMove("f2", "f4");
        assertTrue(result, "white should be able to move f2 pawn to f4");
    }

    @Test
    void testBlackCannotMoveFirst() {
        boolean result = engine.validPiece("b7");
        assertFalse(result, "Black should not be able to select b7 because it's not their turn");
    }

    @Test
    void testAlternateTurns() {
        //White turn
        boolean result = engine.validPiece("e2");
        assertTrue(result, "White should be able to select e2 Pawn");
        result = engine.makeMove("e2", "e4");
        assertTrue(result, "white should be able to move pawn from e2 to e4");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', 'P', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', 'P', '_', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());

        //Black Turn
        result = engine.validPiece("d7");
        assertTrue(result, "Black should be able to select d7 after white just moved");
        result = engine.makeMove("d7", "d6");
        assertTrue(result, "Black should be able to move pawn from d7 to d6");

        char[][] expectedBoardAfterBlackTurn = {
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
                {'P', 'P', 'P', '_', 'P', 'P', 'P', 'P'},
                {'_', '_', '_', 'P', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', 'P', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', 'P', '_', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        assertArrayEquals(expectedBoardAfterBlackTurn, engine.boardData());

        //White turn again
        result = engine.validPiece("d2");
        assertTrue(result, "White should be able to select d2 Pawn");

    }
}