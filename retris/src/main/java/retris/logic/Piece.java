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

    private Shape shape;
    private final Position position;

    /**
     * Luo kopion annetusta palasta
     *
     * @param droppingPiece kopio
     */
    Piece(Piece droppingPiece) {
        if (droppingPiece != null) {
            this.shape = new Shape(droppingPiece.shape);
            this.position = new Position(droppingPiece.position);
        } else {
            this.shape = new Shape();
            this.position = new Position();
        }
    }

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
        this.shape = new Shape(shape);
        this.position = new Position(x, y);
    }

    /**
     * Palauttaa palan muodon kopion
     *
     * @return muoto kopio
     */
    public Shape GetShape() {
        return new Shape(shape);
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
     * Palauttaa kopion palan paikasta
     *
     * @return kopio paikasta
     */
    public Position getPosition() {
        return new Position(position);
    }

    /**
     * Asettaa paikan koordinaatit
     *
     * @param x
     * @param y
     */
    void relocate(int x, int y) {
        position.relocate(x, y);
    }

    void moveUp() {
        position.setY(position.getY() - 1);
    }

    void moveDown() {
        position.setY(position.getY() + 1);
    }

    void moveLeft() {
        position.setX(position.getX() - 1);
    }

    void moveRight() {
        position.setX(position.getX() + 1);
    }

}
