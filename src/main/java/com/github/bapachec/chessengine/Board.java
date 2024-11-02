package com.github.bapachec.chessengine;
import com.github.bapachec.chessengine.Position.Position;
import com.github.bapachec.chessengine.pieces.*;

import java.util.ArrayList;
import java.util.Collections;

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

        whiteKnight = new Knight(true, 7, 6);
        BOARD[7][6] = whiteKnight;
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

    //to check if chosen piece is player's color
    public boolean samePiece(int row, int col, boolean isWhitesTurn) {
        Piece piece = BOARD[row][col];
        if (piece == null)
            return false;

        if (isWhitesTurn){
            return piece.isWhite();
        }
        else {
            return !piece.isWhite();
        }
    }

    private boolean samePiece(Position pickedSpace) {
        int row = pickedSpace.row();
        int col = pickedSpace.col();
        Piece piece = BOARD[row][col];
        if (piece == null)
            return false;

        return piece.isWhite() == whiteTurn;
    }

    public boolean movePiece(Position start, Position target) {
        if (!samePiece(start)) {
            return false;
        }
        boolean friendlyPiece = false;
        Piece piece = BOARD[start.row()][start.col()];
        int targetRow = target.row();
        int targetCol = target.col();

        //if (piece == null) not needed i think
        //    return false;
        Piece targetPiece = BOARD[targetRow][targetCol];

        boolean didCastling = false;

       //castling
        if (piece instanceof King) {
            //isTargetRook: {
            if (((King) piece).getNotMoved()) {
                if (targetPiece != null) {
                    if (piece.isWhite() == targetPiece.isWhite()) {
                        if (targetRow == 0 || targetRow == 7) {
                            if (targetCol == 2) {
                                targetCol = 0;
                                targetPiece = BOARD[targetRow][0];
                            }
                            else if (targetCol == 6) {
                                targetCol = 7;
                                targetPiece = BOARD[targetRow][7];
                            }

                        }
                        if (targetPiece instanceof Rook) {
                            if (isCastlingValid(targetRow, targetPiece.getColumn())) {
                                didCastling = true;
                                friendlyPiece = true;
                            }
                            else {
                                return false;
                            }

                            //break isTargetRook;
                        }
                        else {
                            return false;
                        }
                    }
                }
            }
            else if (targetPiece != null) {
                if (piece.isWhite() == targetPiece.isWhite())
                    return false;
            }

            //}
        }
        else if (targetPiece != null) {
            if (piece.isWhite() == targetPiece.isWhite())
                return false;
        }

        if (!piece.isLegalMove(BOARD, targetRow, targetCol))
            return false;

        if (didCastling) {
            targetCol = castling(targetPiece, targetRow, targetPiece.getColumn());
        }
        //could reduce both king ifs to one if but planning on highlighting the king in trouble

        //if (checkFlag) {
        if (!didCastling) {
            Piece[][] copyBoard = boardCopyWithPieceMoved(start, piece, target);
            if (!whiteTurn) {
                //if (blackKing_N1.isKingInCheck(copyBoard, whiteTurn))
                if (!KingCheck.isKingNotInCheck(copyBoard, blackKing_N1.getRow(), blackKing_N1.getColumn(), whiteTurn))
                    return false;
            } else {
                //if (whiteKing_N1.isKingInCheck(copyBoard, whiteTurn))
                if (!KingCheck.isKingNotInCheck(copyBoard, whiteKing_N1.getRow(), whiteKing_N1.getColumn(), whiteTurn))
                    return false;
            }
            //}
        }

        BOARD[start.row()][start.col()] = null;
        /*if (switchPieces) {
            //method for swapping rook with king
            BOARD[row][col] = null;
            col = castling(piece, targetPiece);
            friendlyPiece = true;
        }*/

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
        //if (!checkFlag) {
        //can clean this part up by calling kings check method?
        if (!whiteTurn) {
            //black king

            checkFlag = !KingCheck.isKingNotInCheck(BOARD, blackKing.getRow(), blackKing.getColumn(), whiteTurn);
        }
        else {
            //white king
            checkFlag = !KingCheck.isKingNotInCheck(BOARD, whiteKing.getRow(), whiteKing.getColumn(), whiteTurn);
        }
        //}
        //else
        //    checkFlag = false;


        return true;

    }

    private boolean isCastlingValid(int row, int col) {

        //int kingCol = 4;
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

    private int castling(Piece Rook, int row, int col) {
    //BOARD[location[0]][location[1]] = targetPiece;
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

        copyBoard[start.row()][start.col()] = null;
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

    private Piece[][] boardCopy() {
        Piece[][] copyBoard = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copyBoard[i][j] = BOARD[i][j];
            }
        }

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
            //if (space[0] == p || space[1] == q)
            //    continue;
            Piece holdPiece = BOARD[space[0]][space[1]];
            if (holdPiece != null)
                if (holdPiece.isWhite() == trappedKing.isWhite() )
                    continue;
            King futureKing = new King(trappedKing.isWhite(), space[0], space[1]);
            boardCopy[space[0]][space[1]] = futureKing;
            //if(!futureKing.isKingInCheck(boardCopy, whiteTurn))
            if (KingCheck.isKingNotInCheck(boardCopy, futureKing.getRow(), futureKing.getColumn(), whiteTurn))
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
            if (counterPiece.isLegalMove(BOARD, p, q)) {
                Position target = new Position(p, q);
                Position start = new Position(counterPiece.getRow(), counterPiece.getColumn());
                Piece[][] copyBoard = boardCopyWithPieceMoved(start, counterPiece, target);
                if (!whiteTurn) {
                    //if (blackKing_N1.isKingInCheck(copyBoard, whiteTurn))
                    if (KingCheck.isKingNotInCheck(copyBoard, blackKing_N1.getRow(), blackKing_N1.getColumn(), whiteTurn))
                        return false;
                } else {
                    //if (whiteKing_N1.isKingInCheck(copyBoard, whiteTurn))
                    if (KingCheck.isKingNotInCheck(copyBoard, whiteKing_N1.getRow(), whiteKing_N1.getColumn(), whiteTurn))
                        return false;
                }
                //return false
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
                    for (int i = q - 1; i > y; i--) {
                        if (blockingPiece.isLegalMove(BOARD, p, i)) {
                            Position target = new Position(p, i);
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
                        Position target = new Position(i, j);
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


        return true;
    }

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

    public boolean isDraw() {

        //insufficientMaterial
        return whitePieceList.isEmpty() && blackPieceList.isEmpty();
    }

    public static class KingCheck {
        //row and col is the location of the king being processed
        public static boolean isKingNotInCheck(Piece[][] board, int row, int col, boolean whiteTurn) {
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
                //bottom left
                for (int i = row + 1, j = col - 1; j >= 0 && i < 8; i++, j--) {
                    Piece piece = board[i][j];
                    if (piece == null)
                        continue;
                    if (piece.isWhite() != whiteTurn)
                        if (piece.isLegalMove(board, row, col)) {
                            return false;
                        }
                }
                //bottom right
                for (int i = row + 1, j = col + 1; j < 8 && i < 8; i++, j++) {
                    Piece piece = board[i][j];
                    if (piece == null)
                        continue;
                    if (piece.isWhite() != whiteTurn)
                        if (piece.isLegalMove(board, row, col)) {
                            return false;
                        }
                }
                //top left
                for (int i = row - 1, j = col - 1; j >= 0 && i >= 0; i--, j--) {
                    Piece piece = board[i][j];
                    if (piece == null)
                        continue;
                    if (piece.isWhite() != whiteTurn)
                        if (piece.isLegalMove(board, row, col)) {
                            return false;
                        }
                }
                //top right
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
                if (piece == null)
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
            //if (((i = row - 1) > 0)  && ((j = col - 1) > 0)) {
            i = row - 1;
            j = col - 1;
            Piece piece;

            if (i >= 0 && j-1 >= 0) {
                if ((piece = board[i][j - 1]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        //attackingLine = null;
                        return false;
                    }

                }
            }
            if (i - 1 >= 0 && j >= 0) {
                if ((piece = board[i - 1][j]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        return false;
                    }
                }
            }

            //top right

            i = row - 1;
            j = col + 1;
            //Piece piece;
            if (i >= 0 && j+1 <= 7) {
                if ((piece = board[i][j + 1]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        return false;
                    }
                }
            }
            if (i-1 >= 0 && j <= 7) {
                if ((piece = board[i - 1][j]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        return false;
                    }
                }
            }


            //bottom left

            i = row + 1;
            j = col - 1;
            if (i <= 7 && j-1 >= 0) {
                if ((piece = board[i][j - 1]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        return false;
                    }
                }
            }
            if (i+1 <= 7 && j >= 0) {
                if ((piece = board[i + 1][j]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        return false;
                    }
                }
            }


            //bottom right

            i = row + 1;
            j = col + 1;
            if (i <= 7 && j+1 <= 7) {
                if ((piece = board[i][j + 1]) instanceof Knight) {
                    if (piece.isWhite() != whiteTurn) {
                        return false;
                    }
                }
            }
            if (i+1 <= 7 && j <= 7) {
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
