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
package logic;

/**
 *
 * @author rochet2_2
 */
public class Position {
    private int x;
    private int y;
    private int orientation;

    /**
     * @return x-koordinaatti
     */
    public int getX() {
        return x;
    }

    /**
     * @param x x-koordinaatti
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return y-koordinaatti
     */
    public int getY() {
        return y;
    }

    /**
     * @param y y-koordinaatti
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return orientaatio, suunta
     */
    public int getOrientation() {
        return orientation;
    }

    /**
     * @param orientation palan orientaatio, eli suunta
     */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
    
}
