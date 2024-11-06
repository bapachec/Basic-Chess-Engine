package com.github.bapachec.chessengine.Position;

/**
 * Represents a position on the chess board with row and column coordinates.
 * <p>
 *     This record stores the row and column indices for a specific position.
 * </p>
 *
 * @param row The row index of the position on the board
 * @param col The column index of the position on the board
 */
public record Position(int row, int col) { }
