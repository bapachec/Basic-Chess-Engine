package com.github.bapachec.chessengine;
import com.github.bapachec.chessengine.Position.Position;
import com.github.bapachec.chessengine.pieces.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the chess board and manages the state of the game.
 * <p>
 *     The {@code Board} class interacts with pieces on the board through a 2D array
 *     of {@link Piece} objects, each representing a chess piece at a specific position.
 *     Its main function is updating and checking the board's status as the game progresses.
 * </p>
 */
public class Board {
    private final Piece[][] BOARD = new Piece[8][8];
    private boolean whiteTurn = true;
    private boolean promotionFlag = false;
    private boolean checkFlag = false;
    private Piece lastPieceMoved = null;
    private King blackKing;
    private King whiteKing;
    private King blackKing_N1;
    private King whiteKing_N1;
    private ArrayList<Piece> blackPieceList = new ArrayList<>();
    private ArrayList<Piece> whitePieceList = new ArrayList<>();

    /**
     * Populates the chess board with a custom configurations.
     *
     * @param board A 2D char array representing the custom board configuration.
     */
    public void customPopulateBoard(char[][] board) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char spot = board[i][j];
                if (Character.isLetter(spot)) {
                    if (Character.isUpperCase(spot)) {
                        createWhitePiece(spot, i, j);
                    }
                    else if (Character.isLowerCase(spot)) {
                        createBlackPiece(spot, i, j);
                    }
                }
            }
        }

    }

    /**
     * Creates and returns a white chess piece based on specified character.
     * This method supports {@link #customPopulateBoard(char[][])} by converting characters
     * representing white pieces (e.g. 'K', 'Q', 'R') into their respective {@link Piece} objects.
     *
     * @param pieceChar The character representing the type of white chess {@link Piece} to create.
     * @param row The row index representing the chess piece's position on the board.
     * @param col The column index representing the chess piece's position on the board.
     */
    private void createWhitePiece(char pieceChar, int row, int col) {

        Piece new_piece = switch (pieceChar) {
            case 'P' -> new Pawn(true, row, col);
            case 'R' -> new Rook(true, row, col);
            case 'N' -> new Knight(true, row, col);
            case 'B' -> new Bishop(true, row, col);
            case 'Q' -> new Queen(true, row, col);
            case 'K' -> new King(true, row, col);
            default -> throw new IllegalStateException("Unexpected Error");
        };

        if (!(new_piece instanceof King)) {
            whitePieceList.add(new_piece);
        }
        else {
            whiteKing = (King) new_piece;
        }

        BOARD[row][col] = new_piece;
    }

    /**
     * Creates and returns a black chess {@link Piece} based on specified character.
     * This method supports {@link #customPopulateBoard(char[][])} by converting characters
     * representing black pieces (e.g. 'k', 'q', 'r') into their respective {@link Piece} objects.
     *
     * @param pieceChar The character representing the type of black {@link Piece} to create.
     * @param row The row index representing the chess piece's position on the board.
     * @param col The column index representing the chess piece's position on the board.
     */
    private void createBlackPiece(char pieceChar, int row, int col) {

        Piece new_piece = switch (pieceChar) {
            case 'p' -> new Pawn(false, row, col);
            case 'r' -> new Rook(false, row, col);
            case 'n' -> new Knight(false, row, col);
            case 'b' -> new Bishop(false, row, col);
            case 'q' -> new Queen(false, row, col);
            case 'k' -> new King(false, row, col);
            default -> throw new IllegalStateException("Unexpected Error");
        };

        if (!(new_piece instanceof King)) {
            blackPieceList.add(new_piece);
        }
        else {
            blackKing = (King) new_piece;
        }
        BOARD[row][col] = new_piece;
    }

    /**
     * Populates the chess board with default configuration.
     */
    public void populateBoard() {
        for (int i = 0; i <8; i++) {
            Piece blackPawn = new Pawn(false, 1, i);
            BOARD[1][i] = blackPawn;
            Piece whitePawn = new Pawn(true, 6, i);
            BOARD[6][i] = whitePawn;

            blackPieceList.add(blackPawn);
            whitePieceList.add(whitePawn);

        }



        //Rooks=============================================================
        Piece[] blackRooks = new Piece[2];
        Piece[] whiteRooks = new Piece[2];
        Piece blackRookPiece;
        Piece whiteRookPiece;

        //blackRooks

        blackRookPiece = new Rook(false, 0, 0);
        BOARD[0][0] = blackRookPiece;
        blackRooks[0] = blackRookPiece;

        blackRookPiece = new Rook(false, 0, 7);
        BOARD[0][7] = blackRookPiece;
        blackRooks[1] = blackRookPiece;

        //whiteRooks
        whiteRookPiece = new Rook(true, 7, 0);
        BOARD[7][0] = whiteRookPiece;
        whiteRooks[0] = whiteRookPiece;

        whiteRookPiece = new Rook(true, 7, 7);
        BOARD[7][7] = whiteRookPiece;
        whiteRooks[1] = whiteRookPiece;

        Collections.addAll(blackPieceList, blackRooks);
        Collections.addAll(whitePieceList, whiteRooks);


        //Knights=============================================================
        Piece[] blackKnights = new Piece[2];
        Piece[] whiteKnights = new Piece[2];
        Piece blackKnight;
        Piece whiteKnight;

        //blackKnights
        blackKnight = new Knight(false, 0, 1);
        BOARD[0][1] = blackKnight;
        blackKnights[0] = blackKnight;

        blackKnight = new Knight(false, 0, 6);
        BOARD[0][6] = blackKnight;
        blackKnights[1] = blackKnight;

        //whiteKnights
        whiteKnight = new Knight(true, 7, 1);
        BOARD[7][1] = whiteKnight;
        whiteKnights[0] = whiteKnight;

        whiteKnight = new Knight(true, 7, 6);
        BOARD[7][6] = whiteKnight;
        whiteKnights[1] = whiteKnight;

        Collections.addAll(blackPieceList, blackKnights);
        Collections.addAll(whitePieceList, whiteKnights);


        //Bishops=============================================================
        Piece[] blackBishops = new Piece[2];
        Piece[] whiteBishops = new Piece[2];
        Piece blackBishop;
        Piece whiteBishop;

        //blackBishops
        blackBishop = new Bishop(false, 0, 2);
        BOARD[0][2] = blackBishop;
        blackBishops[0] = blackBishop;

        blackBishop = new Bishop(false, 0, 5);
        BOARD[0][5] = blackBishop;
        blackBishops[1] = blackBishop;

        //whiteBishops
        whiteBishop = new Bishop(true, 7, 2);
        BOARD[7][2] = whiteBishop;
        whiteBishops[0] = whiteBishop;

        whiteBishop = new Bishop(true, 7, 5);
        BOARD[7][5] = whiteBishop;
        whiteBishops[1] = whiteBishop;

        Collections.addAll(blackPieceList, blackBishops);
        Collections.addAll(whitePieceList, whiteBishops);

        //Queens=============================================================
        Piece blackQueen;
        Piece whiteQueen;

        //blackQueen
        blackQueen = new Queen(false, 0, 3);
        BOARD[0][3] = blackQueen;

        whiteQueen = new Queen(true, 7, 3);
        BOARD[7][3] = whiteQueen;

        blackPieceList.add(blackQueen);
        whitePieceList.add(whiteQueen);

        //Kings=============================================================
        //skip adding kings to list to white and blacks
        blackKing = new King(false, 0, 4);
        BOARD[0][4] = blackKing;
        whiteKing = new King(true, 7, 4);
        BOARD[7][4] =  whiteKing;

    }

    /**
     * Check if chosen piece is the current player's color.
     * @param row The row index representing the chess piece's position on the board.
     * @param col The column index representing the chess piece's position on the board.
     * @return {@code true} if a chosen chess piece's color matches current player; {@code false} otherwise.
     */
    public boolean samePiece(int row, int col) {

        Piece piece = BOARD[row][col];
        if (piece == null)
            return false;

        return piece.isWhite() == whiteTurn;
    }

    /**
     * Check if chosen chess piece is the current player's color.
     * @param pickedSpace A {@link Position} record representing the chess piece's position on the board.
     * @return {@code true} if a chosen chess piece's color matches current player; {@code false} otherwise.
     */
    private boolean samePiece(Position pickedSpace) {
        int row = pickedSpace.row();
        int col = pickedSpace.col();
        Piece piece = BOARD[row][col];
        if (piece == null)
            return false;

        return piece.isWhite() == whiteTurn;
    }

    /**
     * Moves a specified chess piece from its starting location to a target location on the board.
     *
     * @param start A {@link Position} record representing the piece's current location.
     * @param target A {@link Position} record representing the desired target location.
     * @return {@code true} if the move is successful; {@code false} otherwise.
     */
    public boolean movePiece(Position start, Position target) {
        if (!samePiece(start)) {
            return false;
        }
        boolean friendlyPiece = false;
        Piece piece = BOARD[start.row()][start.col()];
        int targetRow = target.row();
        int targetCol = target.col();

        Piece targetPiece = BOARD[targetRow][targetCol];

        if (targetPiece != null && !(targetPiece instanceof Rook) && targetPiece.isWhite() == piece.isWhite())
            return false;


        boolean castlingValid = false;

       //castling
        if (piece instanceof King && ((King) piece).getNotMoved()) {
            if (targetRow == 0 || targetRow == 7) {
                if (targetCol == 2) {
                    targetPiece = BOARD[targetRow][0];
                }
                else if (targetCol == 6) {
                    targetPiece = BOARD[targetRow][7];
                }
            }
            if (targetPiece instanceof Rook && piece.isWhite() == targetPiece.isWhite()) {
                if (isCastlingValid(targetRow, targetPiece.getColumn())) {
                    targetCol = targetPiece.getColumn();
                    castlingValid = true;
                    friendlyPiece = true;
                }
                else {
                    return false;
                }

            }
        }

        if (!piece.isLegalMove(BOARD, targetRow, targetCol))
            return false;

        if (castlingValid) {
            targetCol = castling(targetPiece, targetRow, targetPiece.getColumn());
        }
        else { //could reduce both king ifs to one if but planning on highlighting the king in trouble
            Piece[][] copyBoard = boardCopyWithPieceMoved(start, piece, target);
            if (!whiteTurn) {
                if (!KingCheck.isKingNotInCheck(copyBoard, blackKing_N1.getRow(), blackKing_N1.getColumn(), whiteTurn))
                    return false;
            } else {
                if (!KingCheck.isKingNotInCheck(copyBoard, whiteKing_N1.getRow(), whiteKing_N1.getColumn(), whiteTurn))
                    return false;
            }
        }

        BOARD[start.row()][start.col()] = null;

        piece.setRow(targetRow);
        piece.setColumn(targetCol);
        BOARD[targetRow][targetCol] = piece;
        if (!friendlyPiece) {
            if (targetPiece != null) {
                if (!targetPiece.isWhite())
                    blackPieceList.remove(targetPiece);
                else
                    whitePieceList.remove(targetPiece);
            }
        }

        if (piece instanceof Pawn) {
            if (targetRow == 0 || targetRow == 7) {
                if ((piece.isWhite() && targetRow == 0) || (!piece.isWhite() && targetRow == 7))
                    promotionFlag = true;
            }
        }
        lastPieceMoved = piece;
        whiteTurn = !whiteTurn;
        //can clean this part up by calling kings check method?
        if (!whiteTurn) {
            //black king
            checkFlag = !KingCheck.isKingNotInCheck(BOARD, blackKing.getRow(), blackKing.getColumn(), whiteTurn);
        }
        else {
            //white king
            checkFlag = !KingCheck.isKingNotInCheck(BOARD, whiteKing.getRow(), whiteKing.getColumn(), whiteTurn);
        }


        return true;

    }

    /**
     * Determines if castling is possible with a specified rook {@link Piece}.
     *
     * @param row The row index representing the rook's current position on the board.
     * @param col The column index representing the rook's current position on the board.
     * @return {@code true} if castling is possible; {@code false} otherwise.
     */
    private boolean isCastlingValid(int row, int col) {

        //castling right else left
        if (4 < col) {
            for (int i = 4; i < 7; i++) {
                if (!KingCheck.isKingNotInCheck(BOARD, row , i, whiteTurn)) {
                    return false;
                }
            }

        }
        else {
            for (int i = 4; i > 1; i--) {
                if (!KingCheck.isKingNotInCheck(BOARD, row, i, whiteTurn)) {
                    return false;
                }
            }

        }

        return true;
    }

    /**
     * Updates the specified rook's position and the board state as part of the castling move.
     *
     * @param Rook The rook {@link Piece} that is participating in the castling move.
     * @param row The row index representing the rook's current position on the board.
     * @param col The column index representing the rook's current position on the board.
     * @return The column index representing the new king's position on the board after castling.
     */
    private int castling(Piece Rook, int row, int col) {
        //if king castling with right rook
        int new_col;
        if (4 < Rook.getColumn()) {
            Rook.setColumn(5);
            BOARD[row][5] = Rook;
            new_col = 6;
        }
        else {
            Rook.setColumn(3);
            BOARD[row][3] = Rook;
            new_col = 2;
        }
        BOARD[row][col] = null;
        return new_col;

    }

    /**
     * Generates a shallow copy of the current 2D piece array, with a specified piece moved to a new location.
     *
     * @param start A {@link Position} record representing the starting location of the specified piece.
     * @param piece {@link Piece} to move from the starting position to the target position.
     * @param target A {@link Position} record representing the target location for the specified piece.
     * @return A 2D {@link Piece} array that is a shallow copy of the current board, with the specified piece moved.
     */
    private Piece[][] boardCopyWithPieceMoved(Position start, Piece piece, Position target) {
        int row = target.row();
        int col = target.col();
        Piece[][] copyBoard = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copyBoard[i][j] = BOARD[i][j];
            }
        }

        Piece new_piece = switch (piece.toString()) {
            case "P","p" -> new Pawn(piece.isWhite(), row, col);
            case "R","r" -> new Rook(piece.isWhite(), row, col);
            case "N","n" -> new Knight(piece.isWhite(), row, col);
            case "B","b" -> new Bishop(piece.isWhite(), row, col);
            case "Q","q" -> new Queen(piece.isWhite(), row, col);
            case "K","k" -> new King(piece.isWhite(), row, col);
            default -> throw new IllegalStateException("Unexpected Error");
        };

        copyBoard[row][col] = new_piece;
        if (new_piece instanceof King) {
            if (!new_piece.isWhite()) {
                blackKing_N1 = (King) new_piece;
            }
            else {
                whiteKing_N1 = (King) new_piece;
            }
        }
        else {
            blackKing_N1 = blackKing;
            whiteKing_N1 = whiteKing;
        }

        copyBoard[start.row()][start.col()] = null;
        return copyBoard;
    }

    /**
     * Generates a shallow copy of the current 2D piece array, with a specified king excluded, representing the board.
     *
     * @param row The row index representing the king's position.
     * @param col The column index representing the king's position.
     * @return A 2D {@link Piece} array that is a shallow copy of the current board, with the specified king missing.
     */
    private Piece[][] boardCopyWithKingMissing(int row, int col) {
        Piece[][] copyBoard = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copyBoard[i][j] = BOARD[i][j];
            }
        }
        copyBoard[row][col] = null;

        return copyBoard;
    }

    /**
     * Generates a shallow copy of the current 2D {@link Piece} array representing the board.
     *
     * @return A 2D {@link Piece} array that is a shallow copy of the current board.
     */
    private Piece[][] boardCopy() {
        Piece[][] copyBoard = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copyBoard[i][j] = BOARD[i][j];
            }
        }

        return copyBoard;
    }

    /**
     * Generates a 2D char array representing the current state of the chess board.
     * @return A 2D char array representing the board state with each element corresponding to a piece or empty space.
     */
    public char[][] boardData() {
        char[][] board = new char[8][8];
        for(int i = 0; i < 8; i ++) {
            for(int j = 0; j < 8; j++) {
                if (BOARD[i][j] != null) {
                    board[i][j] = BOARD[i][j].toString().charAt(0);
                }
                else {
                    board[i][j] = board[i][j] = '_';
                }
            }
        }

        return board;
    }

    /**
     * Returns the promotion status of the current game.
     *
     * @return {@code true} if a promotion condition exists for the current player; {@code false} otherwise.
     */
    public boolean getPromotionFlag() { return promotionFlag; }

    /**
     * Returns the check status of the current game.
     *
     * @return {@code true} if a check condition exists for the opposing player; {@code false} otherwise.
     */
    public boolean getCheckFlag() { return checkFlag; }

//todo switch IllegalStateException to IllegalArgumentException

    /**
     * Processes, create and return a new {@link Piece} to replace the pawn being promoted.
     *
     * @param choice The number (1-4) representing one of the four pieces to be promoted to.
     * @throws IllegalStateException if the choice is not between 1 and 4.
     */
    public void pawnPromotion(int choice) {

        Piece new_piece = switch (choice) {
            case 1 -> new Queen(lastPieceMoved.isWhite(), lastPieceMoved.getRow(), lastPieceMoved.getColumn());
            case 2 -> new Bishop(lastPieceMoved.isWhite(), lastPieceMoved.getRow(), lastPieceMoved.getColumn());
            case 3 -> new Rook(lastPieceMoved.isWhite(), lastPieceMoved.getRow(), lastPieceMoved.getColumn());
            case 4 -> new Knight(lastPieceMoved.isWhite(), lastPieceMoved.getRow(), lastPieceMoved.getColumn());
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
        BOARD[new_piece.getRow()][new_piece.getColumn()] = new_piece;

        if (!lastPieceMoved.isWhite()) {
            blackPieceList.remove(lastPieceMoved);
            blackPieceList.add(new_piece);
        }
        else {
            whitePieceList.remove(lastPieceMoved);
            whitePieceList.add(new_piece);
        }

        promotionFlag = false;

    }

    /**
     * Checks if a checkmate condition has occurred in the game.
     *
     * @return {@code true} if a checkmate condition exists; {@code false} otherwise.
     */
    public boolean isCheckMate() {
        King trappedKing;
        if (!whiteTurn) {
            trappedKing = blackKing;
        }
        else {
            trappedKing = whiteKing;
        }
        int x = trappedKing.getRow();
        int y = trappedKing.getColumn();

        int p = lastPieceMoved.getRow();
        int q = lastPieceMoved.getColumn();

        //Piece attackingPiece = lastPieceMoved;
        int[][] possibleSpaces = {
                {x-1, y-1}, {x-1, y}, {x-1, y+1},
                {x, y-1}, {x, y+1},
                {x+1, y-1}, {x+1, y}, {x+1, y+1},
        };


        //can the king escape
        Piece[][] boardCopy = boardCopyWithKingMissing(x, y);

        for(int[] space : possibleSpaces) {

            if (space[0] < 0 || space[0] > 7 || space[1] < 0 || space[1] > 7)
                continue;
            Piece holdPiece = BOARD[space[0]][space[1]];
            if (holdPiece != null)
                if (holdPiece.isWhite() == trappedKing.isWhite() )
                    continue;
            King futureKing = new King(trappedKing.isWhite(), space[0], space[1]);
            boardCopy[space[0]][space[1]] = futureKing;
            if (KingCheck.isKingNotInCheck(boardCopy, futureKing.getRow(), futureKing.getColumn(), whiteTurn))
                return false;
            boardCopy[space[0]][space[1]] = holdPiece;
        }


        ArrayList<Piece> defenderPieceList;
        if (!trappedKing.isWhite())
            defenderPieceList = blackPieceList;
        else
            defenderPieceList = whitePieceList;

        //can any remaining pieces eliminate threat

        for (Piece counterPiece: defenderPieceList) {
            if (counterPiece.isLegalMove(BOARD, p, q)) {
                Position target = new Position(p, q);
                Position start = new Position(counterPiece.getRow(), counterPiece.getColumn());
                Piece[][] copyBoard = boardCopyWithPieceMoved(start, counterPiece, target);
                if (!whiteTurn) {
                    if (KingCheck.isKingNotInCheck(copyBoard, blackKing_N1.getRow(), blackKing_N1.getColumn(), whiteTurn))
                        return false;
                } else {
                    if (KingCheck.isKingNotInCheck(copyBoard, whiteKing_N1.getRow(), whiteKing_N1.getColumn(), whiteTurn))
                        return false;
                }
            }
        }

        //early test case because knights cannot be blocked
        if (lastPieceMoved instanceof Knight)
            return true;

        //todo this can be improved if the piece is adjacent to king, this means it cannot be blocked so searching is pointless
        //can remaining pieces block threat
        if (p == x) {
            // if attacking piece is left of king
            if (q < y) {
                for (Piece blockingPiece: defenderPieceList) {
                    for (int i = q + 1; i < y; i++) {
                        if (blockingPiece.isLegalMove(BOARD, p, i)) {
                            Position target = new Position(p, i);
                            Position start = new Position(blockingPiece.getRow(), blockingPiece.getColumn());
                            Piece[][] copyBoard = boardCopyWithPieceMoved(start, blockingPiece, target);
                            if (!whiteTurn) {
                                if (KingCheck.isKingNotInCheck(copyBoard, blackKing_N1.getRow(), blackKing_N1.getColumn(), whiteTurn))
                                    return false;
                            } else {
                                if (KingCheck.isKingNotInCheck(copyBoard, whiteKing_N1.getRow(), whiteKing_N1.getColumn(), whiteTurn))
                                    return false;
                            }
                        }
                    }
                }

            }
            else {
                for (Piece blockingPiece: defenderPieceList) {
                    for (int i = q - 1; i > y; i--) {
                        if (blockingPiece.isLegalMove(BOARD, p, i)) {
                            Position target = new Position(p, i);
                            Position start = new Position(blockingPiece.getRow(), blockingPiece.getColumn());
                            Piece[][] copyBoard = boardCopyWithPieceMoved(start, blockingPiece, target);
                            if (!whiteTurn) {
                                if (KingCheck.isKingNotInCheck(copyBoard, blackKing_N1.getRow(), blackKing_N1.getColumn(), whiteTurn))
                                    return false;
                            } else {
                                if (KingCheck.isKingNotInCheck(copyBoard, whiteKing_N1.getRow(), whiteKing_N1.getColumn(), whiteTurn))
                                    return false;
                            }
                        }
                    }
                }

            }

        }
        else if (q == y) {

            if (p < x) {
                for (Piece blockingPiece: defenderPieceList) {
                    if (p + 1 == x) {
                        break;
                    }
                    for (int i = p + 1; i < x; i++) {
                        if (blockingPiece.isLegalMove(BOARD, i, q)) {
                            Position target = new Position(i, q);
                            Position start = new Position(blockingPiece.getRow(), blockingPiece.getColumn());
                            Piece[][] copyBoard = boardCopyWithPieceMoved(start, blockingPiece, target);
                            if (!whiteTurn) {
                                //if (blackKing_N1.isKingInCheck(copyBoard, whiteTurn))
                                if (KingCheck.isKingNotInCheck(copyBoard, blackKing_N1.getRow(), blackKing_N1.getColumn(), whiteTurn))
                                    return false;
                            } else {
                                //if (whiteKing_N1.isKingInCheck(copyBoard, whiteTurn))
                                if (KingCheck.isKingNotInCheck(copyBoard, whiteKing_N1.getRow(), whiteKing_N1.getColumn(), whiteTurn))
                                    return false;
                            }
                            //return false;
                        }
                    }
                }
            }
            else {
                for (Piece blockingPiece: defenderPieceList) {
                    if (p - 1 == x) {
                        break;
                    }
                    for (int i = p - 1; i > x; i--) {
                        if (blockingPiece.isLegalMove(BOARD, i, q)) {
                            Position target = new Position(i, q);
                            Position start = new Position(blockingPiece.getRow(), blockingPiece.getColumn());
                            Piece[][] copyBoard = boardCopyWithPieceMoved(start, blockingPiece, target);
                            if (!whiteTurn) {
                                //if (blackKing_N1.isKingInCheck(copyBoard, whiteTurn))
                                if (KingCheck.isKingNotInCheck(copyBoard, blackKing_N1.getRow(), blackKing_N1.getColumn(), whiteTurn))
                                    return false;
                            } else {
                                //if (whiteKing_N1.isKingInCheck(copyBoard, whiteTurn))
                                if (KingCheck.isKingNotInCheck(copyBoard, whiteKing_N1.getRow(), whiteKing_N1.getColumn(), whiteTurn))
                                    return false;
                            }
                            //return false;
                        }
                    }
                }
            }
        }
        else { // check for one diagonal direction only based on lastPiece's location
            int r, c;
            //bottom right
            if (x < p && y < q) {
                r = 1;
                c = 1;
            }
            //bottom left
            else if (x < p) {
                r = 1;
                c = -1;
            }
            //top right
            else if (y < q) {
                r = -1;
                c = 1;
            }
            //top left
            else {
                r = -1;
                c = -1;
            }

            int i, j;
            for (Piece blockingPiece: defenderPieceList) {
                for (i = x + r, j = y + c; i != p && j != q; i+= r, j += c) {
                    if (blockingPiece.isLegalMove(BOARD, i, j)) {
                        Position target = new Position(i, j);
                        Position start = new Position(blockingPiece.getRow(), blockingPiece.getColumn());
                        Piece[][] copyBoard = boardCopyWithPieceMoved(start, blockingPiece, target);
                        if (!whiteTurn) {
                            if (KingCheck.isKingNotInCheck(copyBoard, blackKing_N1.getRow(), blackKing_N1.getColumn(), whiteTurn))
                                return false;
                        } else {
                            if (KingCheck.isKingNotInCheck(copyBoard, whiteKing_N1.getRow(), whiteKing_N1.getColumn(), whiteTurn))
                                return false;
                        }
                    }
                }
            }

        }


        return true;
    }

    /**
     * Checks if the game is in a stalemate.
     *
     * @return {@code true} if the game is in a stalemate; {@code false} otherwise.
     */
    public boolean isStaleMate() {
        King king;
        ArrayList<Piece> pieceList;

        //black player
        if (!whiteTurn) {
            king = blackKing;
            pieceList = blackPieceList;
        }
        else {
            king = whiteKing;
            pieceList = whitePieceList;
        }

        //deepcopy
        Piece[][] boardCopy = boardCopy();
        //king calling has any legal moves
        if (king.hasAnyLegalMoves(boardCopy, whiteTurn)) {
            return false;
        }
        //for loop on list calling on has any legal moves
        for (Piece piece: pieceList) {
            boardCopy = boardCopy();
            if (piece.hasAnyLegalMoves(boardCopy, whiteTurn, king.getRow(), king.getColumn()))
                return false;
        }

        return true;
    }

    /**
     * Checks if the game is in a draw state.
     *
     * @return {@code true} if the game is a draw; {@code false} otherwise.
     */
    public boolean isDraw() {

        //insufficientMaterial
        return whitePieceList.isEmpty() && blackPieceList.isEmpty();
    }

    /**
     * This inner static class provides methods to determine if a specified king is in check.
     */
    public static class KingCheck {
        //row and col is the location of the king being processed

        /**
         * Determines if a specified king is in check.
         *
         * @param board A 2D array of {@link Piece} objects representing the game's current state.
         * @param row The row index representing the king's position.
         * @param col The column index representing the king's position.
         * @param whiteTurn A boolean flag indicating if it's white player's turn, used for excluding friendly pieces.
         * @return {@code true} if the specified king is in check; {@code false} otherwise.
         */
        public static boolean isKingNotInCheck(Piece[][] board, int row, int col, boolean whiteTurn) {

            //row=======================================================
            for (int i = 0; i < 8; i++) {
                Piece piece = board[row][i];
                if (piece == null)
                    continue;
                if (piece.isWhite() != whiteTurn)
                    if (piece.isLegalMove(board, row, col)) {
                        return false;
                    }

            }

            //diag x=======================================================

            int[][] directions = {
                    {-1, -1}, {-1, 1}, //top left, top right
                    {1, -1}, {1, 1} //bottom left, bottom right
            };

            for (int[] dir: directions) {

                if (row+dir[0] < 0 || row+dir[0] > 7 || col+dir[1] < 0 || col+dir[1] > 7) {
                    continue;
                }

                for (int i = row + dir[0], j = col + dir[1]; (j >= 0 && j < 8) && (i >= 0 && i < 8); i+=dir[0], j+=dir[1]) {
                    Piece piece = board[i][j];
                    if (piece == null)
                        continue;
                    if (piece.isWhite() != whiteTurn)
                        if (piece.isLegalMove(board, row, col)) {
                            return false;
                        }
                }
            }

            //col=======================================================
            for (int j = 0; j < 8; j++) {
                Piece piece = board[j][col];
                if (piece == null)
                    continue;
                if (piece.isWhite() != whiteTurn)
                    if (piece.isLegalMove(board, row, col)) {
                        return false;
                    }
            }

            //knights=======================================================

            int[][] knight_directions = {
                    {-1, -2}, {-2, -1}, //top left
                    {-2, 1}, {-1, 2}, //top right
                    {1, -2}, {2, -1}, //lower left
                    {2, 1}, {1, 2} //lower right
            };

            for (int[] dir: knight_directions) {
                int i = row + dir[0];
                int j = col + dir[1];



                if (i < 0 || i > 7 || j < 0 || j > 7)
                    continue;

                Piece target = board[i][j];

                if (!(target instanceof Knight))
                    continue;

                if (target.isWhite() != whiteTurn)
                    return false;

            }


            return true;
        }
    }
}
