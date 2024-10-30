package com.github.bapachec.chessengine;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayChess implements ChessUI {
    Scanner scan = new Scanner(System.in);
    ChessEngine engine;

    public PlayChess(ChessEngine engine) {
        this.engine = engine;
        engine.addListener(this);
    }

    @Override
    public void run () {


        boolean stop = false;
        engine.start();
        //int[] piece_location = new int[2];
        int startRow, startCol, targetRow, targetCol;

        while(!engine.isGameOver()) {

            try {
                while(true) {
                    //onBoardUpdated(engine.boardData());
                    //check player's turn
                    if (engine.isWhitesTurn())
                        System.out.println("It is whites Turn");
                    else
                        System.out.println("It is blacks turn");



                    //pick a piece
                    System.out.println("Pick which piece you want to move.");
                    System.out.print("Row: ");
                    startRow = scan.nextByte();
                    System.out.print("Col: ");
                    startCol = scan.nextByte();
                    if (!engine.validPiece(startRow, startCol)) {
                        System.out.println("Not your piece");
                        continue;
                    }


                    scan.nextLine(); //clears input buffer
                    //make a move
                    System.out.println("Pick new location for your chosen piece");
                    System.out.print("New Row: ");
                    targetRow = scan.nextByte();
                    System.out.print("New Col: ");
                    targetCol = scan.nextByte();
                    System.out.println("Row: " + targetRow + " Col: " + targetCol);

                    engine.makeMove(startRow, startCol, targetRow, targetCol);
                    scan.nextLine(); //clears input buffer

                    /*
                    //quit option
                    System.out.println("Do you want to quit? Enter q or Q to quit");
                    String input = scan.nextLine();

                    if (input.equalsIgnoreCase("q")) {
                        stop = true;
                        break;
                    }
                    */

                }
            }
            catch (NumberFormatException e) {
                System.out.println("Convert error");
                scan.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println("Input error");
                scan.nextLine();
            }

        }

        scan.close();


    }


    @Override
    public void onBoardUpdated(char[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                System.out.print(board[i][j]);
            System.out.println();
        }
    }

    @Override
    public int promotionRequest() {
        String[] list = new String[] {"1: Queen", "2: Bishop", "3: Rook", "4: Knight"};
        System.out.println("The pawn you just moved needs to be promoted.\nPlease choose a new piece for promotion");
        System.out.println("List of pieces to promote to:");

        for(String pieceType: list){
            System.out.println(pieceType);
        }

        return scan.nextInt();

    }

    @Override
    public void kingInCheckWarning(boolean isWhitesTurn) {
        String player;
        if (isWhitesTurn)
            player = "White";
        else
            player = "Black";

        System.out.println("The king is in check");
        System.out.println(player + " should rescue their king!");
    }

    @Override
    public void checkmate(boolean whiteWon) {

        if (whiteWon) {
            System.out.println("White checkmates Black. White Wins!");
        }
        else {
            System.out.println("Black checkmates White. Black Wins!");
        }

    }

    @Override
    public void stalemate() {
        System.out.println("Stalemate.");
    }


}
