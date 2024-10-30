package com.github.bapachec.BasicChessEngine;

import com.github.bapachec.chessengine.ChessEngine;
import com.github.bapachec.chessengine.ChessUI;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FastestCheckmateTest {
    private ChessEngine engine;
    private ChessUI mockUIListener;

    @BeforeEach
    void setUp() {
        engine = new ChessEngine();
        mockUIListener = mock(ChessUI.class);
        engine.addListener(mockUIListener);
        engine.start();
    }

    //##################################################################################################################
    //##################################################################################################################
    //=====================================================TestCase=====================================================
    //##################################################################################################################
    //##################################################################################################################
    @Test
    void testFoolsMate() {
        //White Turn ==================================================================================================
        boolean result = engine.validPiece("f2");
        assertTrue(result, "White should be able to select f2 pawn");
        result = engine.makeMove("f2", "f3");
        assertTrue(result, "white should be able to move f2 pawn to f3");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', 'P', '_', '_'},
                {'P', 'P', 'P', 'P', 'P', '_', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //Black Turn ==================================================================================================
        result = engine.validPiece("e7");
        assertTrue(result, "Black should be able to select e7 pawn after white moved");
        result = engine.makeMove("e7", "e5");
        assertTrue(result, "Black should be able to move e7 pawn to e5");

        //Board state after black turn
        char[][] expectedBoardAfterBlackTurn = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', '_', 'p', 'p', 'p'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', 'p', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', 'P', '_', '_'},
                {'P', 'P', 'P', 'P', 'P', '_', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        assertArrayEquals(expectedBoardAfterBlackTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackTurn));

        //White Turn ==================================================================================================
        result = engine.validPiece("g2");
        assertTrue(result, "White should be able to select g2 pawn after black moved");
        result = engine.makeMove("g2", "g4");
        assertTrue(result, "white should be able to move g2 pawn to g4");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteSecondTurn = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', '_', 'p', 'p', 'p'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', 'p', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', 'P', '_'},
                {'_', '_', '_', '_', '_', 'P', '_', '_'},
                {'P', 'P', 'P', 'P', 'P', '_', '_', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterWhiteSecondTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteSecondTurn));

        //Black Turn ==================================================================================================
        result = engine.validPiece("d8");
        assertTrue(result, "Black should be able to select d8 queen after white moved");
        result = engine.makeMove("d8", "h4");
        assertTrue(result, "Black should be able to move d8 queen to h4");

        //Board state after black turn
        char[][] expectedBoardAfterBlackSecondTurn = {
                {'r', 'n', 'b', '_', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', '_', 'p', 'p', 'p'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', 'p', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', 'P', 'q'},
                {'_', '_', '_', '_', '_', 'P', '_', '_'},
                {'P', 'P', 'P', 'P', 'P', '_', '_', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterBlackSecondTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackSecondTurn));

        //Game over after black turn and black won =====================================================================
        result = engine.isGameOver();
        assertTrue(result);
        boolean whiteWon = false;
        verify(mockUIListener).checkmate(eq(whiteWon));
    }

    //##################################################################################################################
    //##################################################################################################################
    //=====================================================TestCase=====================================================
    //##################################################################################################################
    //##################################################################################################################
    @Test
    void testScholarsMate() {
        //White Turn ==================================================================================================
        boolean result = engine.validPiece("e2");
        assertTrue(result, "White should be able to select e2 pawn");
        result = engine.makeMove("e2", "e4");
        assertTrue(result, "white should be able to move e2 pawn to e4");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', 'P', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', 'P', '_', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //Black Turn ==================================================================================================
        result = engine.validPiece("e7");
        assertTrue(result, "Black should be able to select e7 pawn after white moved");
        result = engine.makeMove("e7", "e5");
        assertTrue(result, "Black should be able to move e7 pawn to e5");

        //Board state after black turn
        char[][] expectedBoardAfterBlackTurn = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', '_', 'p', 'p', 'p'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', 'p', '_', '_', '_'},
                {'_', '_', '_', '_', 'P', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', 'P', '_', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        assertArrayEquals(expectedBoardAfterBlackTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackTurn));

        //White Turn ==================================================================================================
        result = engine.validPiece("f1");
        assertTrue(result, "White should be able to select f1 bishop after black moved");
        result = engine.makeMove("f1", "c4");
        assertTrue(result, "white should be able to move f1 bishop to c4");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteSecondTurn = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', '_', 'p', 'p', 'p'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', 'p', '_', '_', '_'},
                {'_', '_', 'B', '_', 'P', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', 'P', '_', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', '_', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterWhiteSecondTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteSecondTurn));

        //Black Turn ==================================================================================================
        result = engine.validPiece("b8");
        assertTrue(result, "Black should be able to select b8 knight after white moved");
        result = engine.makeMove("b8", "c6");
        assertTrue(result, "Black should be able to move b8 knight to c6");

        //Board state after black turn
        char[][] expectedBoardAfterBlackSecondTurn = {
                {'r', '_', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', '_', 'p', 'p', 'p'},
                {'_', '_', 'n', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', 'p', '_', '_', '_'},
                {'_', '_', 'B', '_', 'P', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', 'P', '_', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', '_', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterBlackSecondTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackSecondTurn));

        //White Turn ==================================================================================================
        result = engine.validPiece("d1");
        assertTrue(result, "White should be able to select d1 queen after black moved");
        result = engine.makeMove("d1", "h5");
        assertTrue(result, "white should be able to move d1 queen to h5");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteThirdTurn = {
                {'r', '_', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', '_', 'p', 'p', 'p'},
                {'_', '_', 'n', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', 'p', '_', '_', 'Q'},
                {'_', '_', 'B', '_', 'P', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', 'P', '_', 'P', 'P', 'P'},
                {'R', 'N', 'B', '_', 'K', '_', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterWhiteThirdTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteThirdTurn));

        //Black Turn ==================================================================================================
        result = engine.validPiece("g8");
        assertTrue(result, "Black should be able to select g8 knight after white moved");
        result = engine.makeMove("g8", "f6");
        assertTrue(result, "Black should be able to move g8 knight to f6");

        //Board state after black turn
        char[][] expectedBoardAfterBlackThirdTurn = {
                {'r', '_', 'b', 'q', 'k', 'b', '_', 'r'},
                {'p', 'p', 'p', 'p', '_', 'p', 'p', 'p'},
                {'_', '_', 'n', '_', '_', 'n', '_', '_'},
                {'_', '_', '_', '_', 'p', '_', '_', 'Q'},
                {'_', '_', 'B', '_', 'P', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', 'P', '_', 'P', 'P', 'P'},
                {'R', 'N', 'B', '_', 'K', '_', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterBlackThirdTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackThirdTurn));

        //White Turn ==================================================================================================
        result = engine.validPiece("h5");
        assertTrue(result, "White should be able to select h5 queen after black moved");
        result = engine.makeMove("h5", "f7");
        assertTrue(result, "white should be able to move h5 queen to f7");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteFourthTurn = {
                {'r', '_', 'b', 'q', 'k', 'b', '_', 'r'},
                {'p', 'p', 'p', 'p', '_', 'Q', 'p', 'p'},
                {'_', '_', 'n', '_', '_', 'n', '_', '_'},
                {'_', '_', '_', '_', 'p', '_', '_', '_'},
                {'_', '_', 'B', '_', 'P', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', 'P', '_', 'P', 'P', 'P'},
                {'R', 'N', 'B', '_', 'K', '_', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterWhiteFourthTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteFourthTurn));

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
    @Test
    void testCaro_KannDefSmotheredMate() {
        //White Turn ==================================================================================================
        boolean result = engine.validPiece("e2");
        assertTrue(result, "White should be able to select e2 pawn");
        result = engine.makeMove("e2", "e4");
        assertTrue(result, "white should be able to move e2 pawn to e4");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteTurn = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', 'P', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', 'P', '_', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        assertArrayEquals(expectedBoardAfterWhiteTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteTurn));

        //Black Turn ==================================================================================================
        result = engine.validPiece("c7");
        assertTrue(result, "Black should be able to select c7 pawn after white moved");
        result = engine.makeMove("c7", "c6");
        assertTrue(result, "Black should be able to move c7 pawn to c6");

        //Board state after black turn
        char[][] expectedBoardAfterBlackTurn = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', '_', 'p', 'p', 'p', 'p', 'p'},
                {'_', '_', 'p', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', 'P', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', 'P', '_', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        assertArrayEquals(expectedBoardAfterBlackTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackTurn));

        //White Turn ==================================================================================================
        result = engine.validPiece("d2");
        assertTrue(result, "White should be able to select d2 pawn after black moved");
        result = engine.makeMove("d2", "d4");
        assertTrue(result, "white should be able to move d2 pawn to d4");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteSecondTurn = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', '_', 'p', 'p', 'p', 'p', 'p'},
                {'_', '_', 'p', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', 'P', 'P', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', '_', '_', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterWhiteSecondTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteSecondTurn));

        //Black Turn ==================================================================================================
        result = engine.validPiece("d7");
        assertTrue(result, "Black should be able to select d7 pawn after white moved");
        result = engine.makeMove("d7", "d5");
        assertTrue(result, "Black should be able to move d7 pawn to d5");

        //Board state after black turn
        char[][] expectedBoardAfterBlackSecondTurn = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', '_', '_', 'p', 'p', 'p', 'p'},
                {'_', '_', 'p', '_', '_', '_', '_', '_'},
                {'_', '_', '_', 'p', '_', '_', '_', '_'},
                {'_', '_', '_', 'P', 'P', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', '_', '_', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterBlackSecondTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackSecondTurn));

        //White Turn ==================================================================================================
        result = engine.validPiece("b1");
        assertTrue(result, "White should be able to select b1 knight after black moved");
        result = engine.makeMove("b1", "c3");
        assertTrue(result, "white should be able to move b1 knight to c3");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteThirdTurn = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', '_', '_', 'p', 'p', 'p', 'p'},
                {'_', '_', 'p', '_', '_', '_', '_', '_'},
                {'_', '_', '_', 'p', '_', '_', '_', '_'},
                {'_', '_', '_', 'P', 'P', '_', '_', '_'},
                {'_', '_', 'N', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', '_', '_', 'P', 'P', 'P'},
                {'R', '_', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterWhiteThirdTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteThirdTurn));

        //Black Turn ==================================================================================================
        result = engine.validPiece("d5");
        assertTrue(result, "Black should be able to select d5 pawn after white moved");
        result = engine.makeMove("d5", "e4");
        assertTrue(result, "Black should be able to move d5 pawn to e4");

        //Board state after black turn
        char[][] expectedBoardAfterBlackThirdTurn = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', '_', '_', 'p', 'p', 'p', 'p'},
                {'_', '_', 'p', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', 'P', 'p', '_', '_', '_'},
                {'_', '_', 'N', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', '_', '_', 'P', 'P', 'P'},
                {'R', '_', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterBlackThirdTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackThirdTurn));

        //White Turn ==================================================================================================
        result = engine.validPiece("c3");
        assertTrue(result, "White should be able to select c3 knight after black moved");
        result = engine.makeMove("c3", "e4");
        assertTrue(result, "white should be able to move c3 knight to e4");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteFourthTurn = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', '_', '_', 'p', 'p', 'p', 'p'},
                {'_', '_', 'p', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', 'P', 'N', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', '_', '_', 'P', 'P', 'P'},
                {'R', '_', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterWhiteFourthTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteFourthTurn));

        //Black Turn ==================================================================================================
        result = engine.validPiece("b8");
        assertTrue(result, "Black should be able to select b8 knight after white moved");
        result = engine.makeMove("b8", "d7");
        assertTrue(result, "Black should be able to move b8 knight to d7");

        //Board state after black turn
        char[][] expectedBoardAfterBlackFourthTurn = {
                {'r', '_', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', '_', 'n', 'p', 'p', 'p', 'p'},
                {'_', '_', 'p', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', 'P', 'N', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', '_', '_', 'P', 'P', 'P'},
                {'R', '_', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterBlackFourthTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackFourthTurn));

        //White Turn ==================================================================================================
        result = engine.validPiece("d1");
        assertTrue(result, "White should be able to select d1 queen after black moved");
        result = engine.makeMove("d1", "e2");
        assertTrue(result, "white should be able to move d1 queen to e2");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteFifthTurn = {
                {'r', '_', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', '_', 'n', 'p', 'p', 'p', 'p'},
                {'_', '_', 'p', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', 'P', 'N', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', '_', 'Q', 'P', 'P', 'P'},
                {'R', '_', 'B', '_', 'K', 'B', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterWhiteFifthTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteFifthTurn));

        //Black Turn ==================================================================================================
        result = engine.validPiece("g8");
        assertTrue(result, "Black should be able to select g8 knight after white moved");
        result = engine.makeMove("g8", "f6");
        assertTrue(result, "Black should be able to move g8 knight to f6");

        //Board state after black turn
        char[][] expectedBoardAfterBlackFifthTurn = {
                {'r', '_', 'b', 'q', 'k', 'b', '_', 'r'},
                {'p', 'p', '_', 'n', 'p', 'p', 'p', 'p'},
                {'_', '_', 'p', '_', '_', 'n', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', 'P', 'N', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', '_', 'Q', 'P', 'P', 'P'},
                {'R', '_', 'B', '_', 'K', 'B', 'N', 'R'},
        };

        assertArrayEquals(expectedBoardAfterBlackFifthTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterBlackFifthTurn));

        //White Turn ==================================================================================================
        result = engine.validPiece("e4");
        assertTrue(result, "White should be able to select e4 knight after black moved");
        result = engine.makeMove("e4", "d6");
        assertTrue(result, "white should be able to move e4 knight to d6");

        //Board state after white turn
        char[][] expectedBoardAfterWhiteSixthTurn = {
                {'r', '_', 'b', 'q', 'k', 'b', '_', 'r'},
                {'p', 'p', '_', 'n', 'p', 'p', 'p', 'p'},
                {'_', '_', 'p', 'N', '_', 'n', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', 'P', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'P', 'P', 'P', '_', 'Q', 'P', 'P', 'P'},
                {'R', '_', 'B', '_', 'K', 'B', 'N', 'R'},
        };
        assertArrayEquals(expectedBoardAfterWhiteSixthTurn, engine.boardData());
        verify(mockUIListener).onBoardUpdated(eq(expectedBoardAfterWhiteSixthTurn));

        //Game over after white turn and white won =====================================================================
        result = engine.isGameOver();
        assertTrue(result);
        boolean whiteWon = true;
        verify(mockUIListener).checkmate(eq(whiteWon));
    }
    
}
