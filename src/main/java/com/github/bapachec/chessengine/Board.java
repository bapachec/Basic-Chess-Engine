package com.github.bapachec.chessengine;
import com.github.bapachec.chessengine.pieces.*;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private static final Piece[][] BOARD = new Piece[8][8];
    private static boolean whiteTurn = true;
    private boolean promotionFlag = false;
    private boolean checkFlag = false;
    private Piece lastPieceMoved = null;
    //private static LineOfAttack attackingLine;
    private King blackKing;
    private King whiteKing;
    private King blackKing_N1;
    private King whiteKing_N1;
    private ArrayList<Piece> blackPieceList = new ArrayList<>();
    private ArrayList<Piece> whitePieceList = new ArrayList<>();

    public void populateBoard() {

    /*
        for (int i = 0; i <8; i++) {
            Piece blackPawn = new Pawn(false, 1, i);
            BOARD[1][i] = blackPawn;
            Piece whitePawn = new Pawn(true, 6, i);
            BOARD[6][i] = whitePawn;

            blackPieceList.add(blackPawn);
            whitePieceList.add(whitePawn);

        }
    */


        //Rooks=============================================================
        Piece[] blackRooks = new Piece[2];
        Piece[] whiteRooks = new Piece[2];
        Piece blackRookPiece;
        Piece whiteRookPiece;

        //blackRooks

        blackRookPiece = new Rook(false, 0, 0);
        BOARD[0][0] = blackRookPiece;
        blackRooks[0] = blackRookPiece;

        blackRookPiece = new Rook(false, 3, 2);
        BOARD[3][2] = blackRookPiece;
        blackRooks[1] = blackRookPiece;

        //whiteRooks
        whiteRookPiece = new Rook(true, 7, 0);
        BOARD[7][0] = whiteRookPiece;
        whiteRooks[0] = whiteRookPiece;

        whiteRookPiece = new Rook(true, 7, 7);
        BOARD[7][7] = whiteRookPiece;
        whiteRooks[1] = whiteRookPiece;

        Collections.addAll(blackPieceList, blackRooks);
        //blackPieceList.add((Piece) Arrays.asList(blackRooks));
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

        whiteKnight = new Knight(true, 4, 6);
        BOARD[4][6] = whiteKnight;
        whiteKnights[1] = whiteKnight;

        Collections.addAll(blackPieceList, blackKnights);
        //remove this below
        //blackPieceList.remove(null);
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

        whiteBishop = new Bishop(true, 4, 5);
        BOARD[4][5] = whiteBishop;
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

        //this is for testing only, delete after=============================================================
        //pawns to place
        //Piece[] blackPawns = new Pawn[2];
        //Piece[] whitePawns = new Pawn[3];
        //Piece whitePawn;
        //Piece blackPawn;

        //blackPawn = new Pawn(false, 1, 3);
        //BOARD[1][3] = blackPawn;
        //blackPawns[0] = blackPawn;

        //blackPawn = new Pawn(false, 1, 7);
        //BOARD[1][7] = blackPawn;
        //blackPawns[1] = blackPawn;

        //whitePawn = new Pawn(true, 4, 2);
        //BOARD[4][2] = whitePawn;
        //whitePawns[0] = whitePawn;

        //whitePawn = new Pawn(true, 4, 6);
        //BOARD[4][6] = whitePawn;
        //whitePawns[1] = whitePawn;

        //Collections.addAll(blackPieceList, blackPawns);
        //Collections.addAll(whitePieceList, whitePawns);
        //this is for testing only, delete above=============================================================
    }

    //to check if chosen piece is player's color
    public boolean samePiece(byte[] location, boolean isWhitesTurn) {
        Piece piece = BOARD[location[0]][location[1]];
        if (piece == null)
            return false;

        if (isWhitesTurn){
            return piece.isWhite();
        }
        else {
            return !piece.isWhite();
        }
    }


    public boolean movePiece(byte[] location, int row, int col) {
        boolean friendlyPiece = false;
        Piece piece = BOARD[location[0]][location[1]];
        //if (piece == null) not needed i think
        //    return false;
        Piece targetPiece = BOARD[row][col];
        boolean switchPieces = false;
        isTargetRook: {
            if (targetPiece != null)
                if (piece.isWhite() == targetPiece.isWhite()) {
                    if (targetPiece instanceof Rook) {
                        switchPieces = true;
                        break isTargetRook;
                    }
                    return false;
                }
        }


        if (!piece.isLegalMove(BOARD, row, col))
            return false;
        //could reduce both king ifs to one if but planning on highlighting the king in trouble

        //if (checkFlag) {
        Piece[][] copyBoard = boardCopyWithPieceMoved(location, piece, row, col);
            if (!whiteTurn) {
                if (blackKing_N1.isKingInCheck(copyBoard))
                    return false;
            } else {
                if (whiteKing_N1.isKingInCheck(copyBoard))
                    return false;
            }
        //}

        BOARD[location[0]][location[1]] = null;
        if (switchPieces) {
            //method for swapping rook with king
            BOARD[row][col] = null;
            col = castling(piece, targetPiece);
            friendlyPiece = true;
        }
        piece.setRow(row);
        piece.setColumn(col);
        BOARD[row][col] = piece;
        if (!friendlyPiece) {
            if (targetPiece != null) {
                if (!targetPiece.isWhite())
                    blackPieceList.remove(targetPiece);
                else
                    whitePieceList.remove(targetPiece);
            }
        }

        if (piece instanceof Pawn && (row == 0 || row == 7))
            if ((piece.isWhite() && row == 0) || (!piece.isWhite() && row == 7))
                promotionFlag = true;

        lastPieceMoved = piece;
        whiteTurn = !whiteTurn;
        if (!checkFlag) {
            if (!whiteTurn) {
                //black king
                if (!KingCheck.isKingNotChecked(BOARD, blackKing.getRow(), blackKing.getColumn())) {
                    checkFlag = true;
                }
            }
            else {
                //white king
                if (!KingCheck.isKingNotChecked(BOARD, whiteKing.getRow(), whiteKing.getColumn())) {
                    checkFlag = true;
                }
            }
        }
        else
            checkFlag = false;


        return true;

    }

    private int castling(Piece King, Piece Rook) {
    //BOARD[location[0]][location[1]] = targetPiece;
        //if king castling with right rook
        int new_col;
        if (King.getColumn() < Rook.getColumn()) {
            Rook.setColumn(5);
            BOARD[Rook.getRow()][5] = Rook;
            new_col = 6;
        }
        else {
            Rook.setColumn(3);
            BOARD[Rook.getRow()][3] = Rook;
            new_col = 2;
        }

        return new_col;

    }

    private Piece[][] boardCopyWithPieceMoved(byte[] location, Piece piece, int row, int col) {
        Piece[][] copyBoard = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copyBoard[i][j] = BOARD[i][j];
            }
        }

        Piece new_piece = switch (piece.toString()) {
            case "P" -> new Pawn(piece.isWhite(), row, col);
            case "R" -> new Rook(piece.isWhite(), row, col);
            case "N" -> new Knight(piece.isWhite(), row, col);
            case "B" -> new Bishop(piece.isWhite(), row, col);
            case "Q" -> new Queen(piece.isWhite(), row, col);
            case "K" -> new King(piece.isWhite(), row, col);
            default -> throw new IllegalStateException("Unexpected Error");
        };

        copyBoard[row][col] = new_piece;
        if (new_piece instanceof King) {
            if (!new_piece.isWhite()) {
                blackKing_N1 = (King) new_piece;
                //blackKing_N1.setRow(row);
                //blackKing_N1.setColumn(col);
            }
            else {
                whiteKing_N1 = (King) new_piece;
                //whiteKing_N1.setRow(row);
                //whiteKing_N1.setColumn(col);
            }
        }
        else {
            blackKing_N1 = blackKing;
            whiteKing_N1 = whiteKing;
        }

        copyBoard[location[0]][location[1]] = null;
        return copyBoard;
    }

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

    public boolean getPromotionFlag() { return promotionFlag; }
    public boolean getCheckFlag() { return checkFlag; }

    /*
    public void resetPromotionFlag() {
        promotionFlag = !promotionFlag;
    }
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

        //on same diagonally impossible with just this, need to use arraylist to contain coordinates diagonals
        //if ( lastPieceMoved.getRow() == trappedKing.getRow()) {
        for(int[] space : possibleSpaces) {

            if (space[0] < 0 || space[0] > 7 || space[1] < 0 || space[1] > 7)
                continue;
            //on same row
            //on same col
            //todo bug on this if, ex: knight checking king is preventing king from moving on the row that the knight is from.
            //if (space[0] == p || space[1] == q)
            //    continue;
            Piece holdPiece = BOARD[space[0]][space[1]];
            if (holdPiece != null)
                if (holdPiece.isWhite() == trappedKing.isWhite() )
                    continue;
            King futureKing = new King(trappedKing.isWhite(), space[0], space[1]);
            boardCopy[space[0]][space[1]] = futureKing;
            if(!futureKing.isKingInCheck(boardCopy))
                return false;
            boardCopy[space[0]][space[1]] = holdPiece;
        }

        //}

        ArrayList<Piece> defenderPieceList;
        if (!trappedKing.isWhite())
            defenderPieceList = blackPieceList;
        else
            defenderPieceList = whitePieceList;

        //can any remaining pieces eliminate threat
        for (Piece counterPiece: defenderPieceList) {
            if (counterPiece.isLegalMove(BOARD, p, q))
                return false;
        }

        //can remaining pieces block threat
        if (p == x) {
            //todo not completely correct, ex: knight
            // if attacking piece is left of king
            if (q < y) {
                for (Piece blockingPiece: defenderPieceList) {
                    for (int i = q + 1; i < y; i++) {
                        if (blockingPiece.isLegalMove(BOARD, p, i)) {
                            return false;
                        }
                    }
                }

            }
            else {
                for (Piece blockingPiece: defenderPieceList) {
                    for (int i = q - 1; i > y; i--) {
                        if (blockingPiece.isLegalMove(BOARD, p, i)) {
                            return false;
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
                            return false;
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
                            return false;
                        }
                    }
                }
            }
        }
        else {
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
                        return false;
                    }
                }
            }

        }


        return true;
    }

    public static class KingCheck {

        public static boolean isKingNotChecked(Piece[][] board, int row, int col) {
            //row
            for (int i = 0; i < 8; i++) {
                Piece piece = board[row][i];
                if (piece == null)
                    continue;
                if (piece.isWhite() != whiteTurn)
                    if (piece.isLegalMove(board, row, col)) {
                        return false;
                    }

            }

            //diag x
                for (int i = row + 1, j = col - 1; j >= 0 && i < 8; i++, j--) {
                    Piece piece = board[i][j];
                    if (piece == null)
                        continue;
                    if (piece.isWhite() != whiteTurn)
                        if (piece.isLegalMove(board, row, col)) {
                            return false;
                        }
                }

                for (int i = row + 1, j = col + 1; j < 8 && i < 8; i++, j++) {
                    Piece piece = board[i][j];
                    if (piece == null)
                        continue;
                    if (piece.isWhite() != whiteTurn)
                        if (piece.isLegalMove(board, row, col)) {
                            return false;
                        }
                }

                for (int i = row - 1, j = col - 1; j >= 0 && i >= 0; i--, j--) {
                    Piece piece = board[i][j];
                    if (piece == null)
                        continue;
                    if (piece.isWhite() != whiteTurn)
                        if (piece.isLegalMove(board, row, col)) {
                            return false;
                        }
                }

                for (int i = row - 1, j = col + 1; j < 8 && i >= 0; i--, j++) {
                    Piece piece = board[i][j];
                    if (piece == null)
                        continue;
                    if (piece.isWhite() != whiteTurn)
                        if (piece.isLegalMove(board, row, col)) {
                            return false;
                        }
                }


            //col
            for (int j = 0; j < 8; j++) {
                Piece piece = board[j][col];
                if (piece == null || piece instanceof King)
                    continue;
                if (piece.isWhite() != whiteTurn)
                    if (piece.isLegalMove(board, row, col)) {
                        return false;
                    }
            }

            //knights=======================================================
            //top left
            int i = 0;
            int j = 0;
            //(int i = row - 1, j = col - 1)
            if (((i = row - 1) > 0)  && ((j = col - 1) > 0)) {

                Piece piece;
                if ((piece = board[i][j - 1]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        //attackingLine = null;
                        return false;
                    }

                }

                if ((piece = board[i - 1][j]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        return false;
                    }
                }


            }

            //top right
            if (((i = row - 1) > 0) && ((j = col + 1) < 7)) {

                Piece piece;
                if ((piece = board[i][j + 1]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        return false;
                    }
                }

                if ((piece = board[i - 1][j]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        return false;
                    }
                }

            }

            //bottom left
            if (((i = row + 1) < 7) && ((j = col - 1) > 0)) {

                Piece piece;
                if ((piece = board[i][j - 1]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        return false;
                    }
                }

                if ((piece = board[i + 1][j]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        return false;
                    }
                }

            }

            //bottom right
            if (((i = row + 1) < 7) && ((j = col + 1) < 7)) {

                Piece piece;
                if ((piece = board[i][j + 1]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        return false;
                    }
                }

                if ((piece = board[i + 1][j]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        return false;
                    }
                }

            }

            return true;
        }
    }
}
