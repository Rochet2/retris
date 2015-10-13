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
package retris.logic.piece;

import retris.logic.shape.Shape;

/**
 * Luokka joka ilmaisee jotakin palaa jolla on muoto ja paikka
 *
 * @author rochet2_2
 */
public class Piece extends Position {

    /**
     * Palan muodon kuvaus
     */
    private Shape shape;

    /**
     * Luo uuden palan oletusmuodolla ja koordinaateilla
     */
    public Piece() {
        this.shape = new Shape();
    }

    /**
     * Palauttaa palan muodon kopion
     *
     * @return muoto kopio
     */
    public Shape GetShape() {
        return shape;
    }

    /**
     * Asettaa palan muodon jos annettu muoto on oikea
     *
     * @param shape muoto
     */
    public void setShape(Shape shape) {
        if (shape != null) {
            this.shape = new Shape(shape);
        }
    }

    /**
     * Täyttää muodon annettuun arrayyn palan koordinaatteihin
     *
     * @param array muokattava array
     */
    public void fillFormToArray(int[][] array) {
        int[][] form = shape.getCurrentForm();
        for (int y = 0; y < form.length; ++y) {
            for (int x = 0; x < form[y].length; ++x) {
                if (form[y][x] == 0) {
                    continue;
                }
                int yCoord = getY() + y;
                int xCoord = getX() + x;
                if (yCoord < 0 || xCoord < 0
                        || yCoord >= array.length || xCoord >= array[y].length) {
                    continue;
                }
                array[yCoord][xCoord] = form[y][x];
            }
        }
    }

}
