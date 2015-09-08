/*
 * Copyright (C) 2015 rochet2_2
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package retris.logic;

import java.util.ArrayList;
import retris.logic.shape.Shape;

/**
 *
 * @author rochet2_2
 */
public class Board {

    private final ArrayList<Piece> piecesOnBoard;
    private final int boardWidth;
    private final int boardHeight;

    /**
     * @param width laudan leveys
     * @param height laudan korkeus
     */
    public Board(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;
        this.piecesOnBoard = new ArrayList<Piece>();
    }

    /**
     * @return palat laudalla
     */
    public ArrayList<Piece> getPiecesOnBoard() {
        return piecesOnBoard;
    }

    /**
     * @return laudan leveys
     */
    public int getWidth() {
        return boardWidth;
    }

    /**
     * @return laudan korkeus
     */
    public int getHeight() {
        return boardHeight;
    }

    /*
    public void dropNewPiece() {
        if (currentPiece != null) {
            piecesOnBoard.add(currentPiece);
        }
        Piece piece = new Piece(Shape.getRandomShape());
        int center = Math.round(getWidth() / 2.0f - piece.getShape().getMaxWidth() / 2.0f);
        piece.setX(center);
        currentPiece = piece;
    }
    */

    public boolean isOnBoard(int x, int y) {
        return x >= 0 && y >= 0 && x <= getWidth() && y <= getHeight();
    }

    public boolean isOnBoard(Piece piece) {
        boolean[][] form = piece.getForm();
        for (int y = 0; y < form.length; ++y) {
            for (int x = 0; x < form[y].length; ++x) {
                if (form[y][x]) {
                    if (!isOnBoard(piece.getX() + x, piece.getY() + y)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean collidesWithBoardPieces(Piece piece) {
        for (Piece pieceOnBoard : getPiecesOnBoard()) {
            if (pieceOnBoard.collides(piece)) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesOnBoard(Piece piece) {
        return !isOnBoard(piece) || collidesWithBoardPieces(piece);
    }

    void updateBoard() {
    }
}
