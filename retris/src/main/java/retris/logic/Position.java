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

/**
 * Luokka joka ilmaisee paikkaa
 *
 * @author rochet2_2
 */
public class Position {

    private int x;
    private int y;

    /**
     * Luo uuden paikan nollakoordinaateilla
     */
    public Position() {
        this(0, 0);
    }

    /**
     * Luo uuden paikan annetuilla koordinaateilla
     *
     * @param x
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Palauttaa paikan X-koordinaatin
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Palauttaa paikan Y-koordinaatin
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Asettaa paikan X-koordinaatin
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Asettaa paikan Y-koordinaatin
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Asettaa paikan koordinaatit
     *
     * @param x
     * @param y
     */
    public void relocate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
