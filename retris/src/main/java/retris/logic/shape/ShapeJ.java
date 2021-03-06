/*
 * Copyright (C) 2015 rimi
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
package retris.logic.shape;

/**
 * J muotoisen mudon ulkonäön määrittely
 *
 * @author rimi
 */
public class ShapeJ extends Shape {

    /**
     * Muodon eri käännösten määrittely
     */
    public static final int[][][] form = {
        {
            {0, 0, 0},
            {2, 2, 2},
            {0, 0, 2}
        },
        {
            {0, 2, 2},
            {0, 2, 0},
            {0, 2, 0}
        },
        {
            {2, 0, 0},
            {2, 2, 2},
            {0, 0, 0}
        },
        {
            {0, 2, 0},
            {0, 2, 0},
            {2, 2, 0}
        }
    };

    /**
     * Asettaa muodon eri käännösvaiheiden muodot
     */
    public ShapeJ() {
        setShapeFormRotations(form);
    }
}
