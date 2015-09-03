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
package retris.logic.shape;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author rochet2_2
 */
public enum Shape {

    TETROMINO_L,
    TETROMINO_J,
    TETROMINO_T,
    TETROMINO_O,
    TETROMINO_I,
    TETROMINO_S,
    TETROMINO_Z;

    private static final List<Shape> allShapes = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int shapeCount = allShapes.size();
    private static final Random random = new Random();

    public static Shape getRandomShape() {
        return allShapes.get(random.nextInt(shapeCount));
    }

    public static List<Shape> getShapes() {
        return allShapes;
    }
}
