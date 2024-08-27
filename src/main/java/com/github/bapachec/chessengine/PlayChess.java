package com.github.bapachec.chessengine;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayChess {

    public static void main (String[] args) {
        ChessEngine engine = new ChessEngine();
        Scanner scan = new Scanner(System.in);
        boolean stop = false;
        engine.start();
        byte[] piece_location = new byte[2];
        byte row, col;

        while(!stop) {

            try {
                while(true) {
                    displayBoard(engine.boardData());
                    //check player's turn
                    if (engine.isWhitesTurn())
                        System.out.println("It is whites Turn");
                    else
                        System.out.println("It is blacks turn");



                    //pick a piece
                    System.out.println("Pick which piece you want to move.");
                    System.out.print("Row: ");
                    piece_location[0] = scan.nextByte();
                    System.out.print("Col: ");
                    piece_location[1] = scan.nextByte();
                    if (!engine.validPiece(piece_location)) {
                        System.out.println("Not your piece");
                        continue;
                    }


                    scan.nextLine(); //clears input buffer
                    //make a move
                    System.out.println("Pick new location for your chosen piece");
                    System.out.print("New Row: ");
                    row = scan.nextByte();
                    System.out.print("New Col: ");
                    col = scan.nextByte();
                    System.out.println("Row: " + row + " Col: " + col);

                    engine.makeMove(piece_location, row, col);

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

    public static void displayBoard(char[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                System.out.print(board[i][j]);
            System.out.println();
        }
    }

}
