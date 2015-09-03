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

/**
 *
 * @author rochet2_2
 */
public class Board {

    private ArrayList<Piece> pieces;
    private final int width;
    private final int height;

    /**
     * @param width laudan leveys
     * @param height laudan korkeus
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.pieces = new ArrayList<Piece>();
    }

    /**
     * @return palat laudalla
     */
    public ArrayList<Piece> getPiecesOnBoard() {
        return pieces;
    }

    /**
     * @return laudan leveys
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return laudan korkeus
     */
    public int getHeight() {
        return height;
    }

    void update() {
    }

    /**
     * @param position jokin paikka objekti
     * @return kertoo onko paikka laudalla
     */
    public boolean isOnBoard(Position position) {
        return position.isWithinRange(getWidth(), getHeight());
    }
}
