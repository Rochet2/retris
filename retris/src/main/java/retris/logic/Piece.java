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
 * Luokka joka ilmaisee jotakin palaa jolla on muoto ja paikka
 *
 * @author rochet2_2
 */
public class Piece {

    private int formIndex;
    private final Shape shape;
    private Position position;

    /**
     * Luo uuden palan oletusmuodolla ja koordinaateilla
     */
    public Piece() {
        this(new Shape(), 0, 0);
    }

    /**
     * Luo uuden palan annetulla muodolla ja oletus koordinaateilla
     *
     * @param shape muoto, perusmuoto jos null
     */
    public Piece(Shape shape) {
        this(shape, 0, 0);
    }

    /**
     * Luo uuden palan annetulla muodolla ja koordinaateilla
     *
     * @param shape muoto, perusmuoto jos null
     * @param x X-koordinaatti
     * @param y Y-koordinaatti
     */
    public Piece(Shape shape, int x, int y) {
        if (shape == null) {
            shape = new Shape();
        }
        this.shape = shape;
        this.formIndex = 0;
        this.position = new Position(x, y);
    }

    /**
     * Palauttaa tämänhetkisen käännösvaiheen muodon
     *
     * @return vaiheen muoto
     */
    public boolean[][] getCurrentForm() {
        boolean[][][] shapeFormRotations = getShape().getShapeFormRotations();
        return shapeFormRotations[getFormIndex()];
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
    public int getFormIndex() {
        return formIndex;
    }

    /**
     * Asettaa palan käännösvaiheen.
     *
     * @param formIndex käännösvaiheen numero - skaalataan vaiheiden määrään
     */
    public void setFormIndex(int formIndex) {
        boolean[][][] shapes = getShape().getShapeFormRotations();
        formIndex = formIndex % shapes.length;
        if (formIndex < 0) {
            formIndex += shapes.length;
        }
        this.formIndex = formIndex;
    }

    /**
     * Palauttaa palan paikan
     *
     * @return paikka
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Asettaa paikan koordinaatit
     *
     * @param x
     * @param y
     */
    void relocate(int x, int y) {
        getPosition().relocate(x, y);
    }

}
