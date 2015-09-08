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

import retris.logic.shape.Shape;

/**
 *
 * @author rochet2_2
 */
public class Piece extends Position {

    private int rotation;
    private final Shape shape;

    /**
     * Luo uuden palan oletusmuodolla ja koordinaateilla
     */
    public Piece() {
        this(new Shape(), 0, 0);
    }

    /**
     * Luo uuden palan annetulla muodolla ja oletus koordinaateilla
     *
     * @param shape
     */
    public Piece(Shape shape) {
        this(shape, 0, 0);
    }

    /**
     * Luo uuden palan annetulla muodolla ja koordinaateilla
     *
     * @param shape
     * @param x
     * @param y
     */
    public Piece(Shape shape, int x, int y) {
        if (shape == null) {
            throw new NullPointerException("Piece's shape can not be null");
        }
        this.shape = shape;
        this.rotation = 0;
        relocate(x, y);
    }

    /**
     * Palauttaa tämänhetkisen käännösvaiheen muodon
     *
     * @return vaiheen muoto
     */
    public boolean[][] getCurrentForm() {
        boolean[][][] shapeFormRotations = getShape().getShapeFormRotations();
        return shapeFormRotations[getRotation()];
    }

    /**
     * Palauttaa palan muodon
     *
     * @return muoto
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Palauttaa palan käännösvaiheen numeron
     *
     * @return käännösvaiheen numero
     */
    public int getRotation() {
        return rotation;
    }

    /**
     * Asettaa palan käännösvaiheen.
     *
     * @param rotation käännösvaiheen numero - skaalataan vaiheiden määrään
     */
    public void setRotation(int rotation) {
        boolean[][][] shapes = getShape().getShapeFormRotations();
        rotation = rotation % shapes.length;
        if (rotation < 0) {
            rotation += shapes.length;
        }
        this.rotation = rotation;
    }

    /**
     * Tarkistaa ovatko palaset jossakin kohtaa päällekkäin.
     *
     * @param piece
     * @return palaset ovat toistensa päällä
     */
    public boolean collidesWithPiece(Piece piece) {
        boolean[][] form1 = getCurrentForm();
        boolean[][] form2 = piece.getCurrentForm();
        for (int y1 = 0; y1 < form1.length; ++y1) {
            for (int x1 = 0; x1 < form1[y1].length; ++x1) {
                if (!form1[y1][x1]) {
                    continue;
                }
                for (int y2 = 0; y2 < form1.length; ++y2) {
                    if (getY() + y1 != piece.getY() + y2) {
                        continue;
                    }
                    for (int x2 = 0; x2 < form1[y2].length; ++x2) {
                        if (getX() + x1 != piece.getX() + x2) {
                            continue;
                        }
                        if (form2[y2][x2]) {
                            continue;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
