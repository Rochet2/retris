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

import retris.logic.piece.Position;
import retris.logic.piece.Piece;
import java.util.ArrayList;
import java.util.Random;
import retris.logic.shape.Shape;
import retris.logic.shape.ShapeFactory;

/**
 *
 * @author rochet2_2
 */
public class Board {

    private final ArrayList<Piece> piecesOnBoard;
    private Piece currentPiece;
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
        dropNewPiece();
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

    /**
     * @return laudan korkeus
     */
    public void dropNewPiece() {
        if (currentPiece != null) {
            piecesOnBoard.add(currentPiece);
        }
        Piece piece = new Piece(Shape.getRandomShape());
        int center = Math.round(getWidth() / 2.0f - ShapeFactory.getMaxWidth(piece.getShape()) / 2.0f);
        piece.getPosition().setX(center);
        currentPiece = piece;
    }

    /**
     * @return laudan korkeus
     */
    public boolean isOnBoard(int x, int y) {
        return x >= 0 && y >= 0 && x <= getWidth() && y <= getHeight();
    }

    public boolean isOnBoard(Piece piece) {
        Position offset = piece.getPosition();
        int[][] form = piece.getForm();
        for (int y = 0; y < form.length; ++y) {
            for (int x = 0; x < form[y].length; ++x) {
                if (form[y][x] == 1) {
                    if (!isOnBoard(offset.getX() + x, offset.getY() + y)) {
                        return true;
                    }
                }
            }
        }
        return false;
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
