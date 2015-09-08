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

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author rochet2_2
 */
public class Shape {

    private static final Random random = new Random();

    private static final boolean[][][] shapeFormRotationsTemplate = {
        {{true}}
    };
    private boolean[][][] shapeFormRotations = {
        {{true}}
    };
    private static ArrayList<Shape> allShapeTemplates;

    static {
        ArrayList<Shape> temp = new ArrayList<Shape>();
        temp.add(new ShapeL());
        allShapeTemplates = temp;
    }

    public static ArrayList<Shape> getAllShapeTemplates() {
        return allShapeTemplates;
    }

    public boolean[][][] getShapeFormRotations() {
        return shapeFormRotations;
    }

    public static boolean[][][] getShapeFormRotationsTemplate() {
        return shapeFormRotationsTemplate;
    }

    public static Shape getDefaultShape() {
        Shape shape = new Shape();
        shape.setShapeFormRotations(shapeFormRotationsTemplate);
        return shape;
    }

    public int getMaxWidth() {
        int width = 0;
        for (boolean[][] form : shapeFormRotations) {
            for (boolean[] row : form) {
                width = Math.max(width, row.length);
            }
        }
        return width;
    }

    public int getMaxHeight() {
        int height = 0;
        for (boolean[][] form : shapeFormRotations) {
            height = Math.max(height, form.length);
        }
        return height;
    }

    public static final Shape getRandomShape() {
        return allShapeTemplates.get(random.nextInt(allShapeTemplates.size()));
    }

    public void setShapeFormRotations(int[][][] shapeFormRotations) {
        if (!arrayHasContent(shapeFormRotations)) {
            return;
        }
        boolean[][][] boolArray = integerArrayToBooleanArray(shapeFormRotations);

        for (boolean[][] formRotation : boolArray) {
            if (!hasOneOrMoreTrue(formRotation)) {
                return;
            }
        }

        this.shapeFormRotations = boolArray;
    }

    public void setShapeFormRotations(boolean[][][] shapeFormRotations) {
        if (!arrayHasContent(shapeFormRotations)) {
            return;
        }

        for (boolean[][] formRotation : shapeFormRotations) {
            if (!hasOneOrMoreTrue(formRotation)) {
                return;
            }
        }

        this.shapeFormRotations = shapeFormRotations;
    }

    private boolean arrayHasContent(int[][][] integerArray) {
        if (integerArray == null) {
            return false;
        }
        if (integerArray.length < 1) {
            return false;
        }
        if (integerArray[0].length < 1) {
            return false;
        }
        if (integerArray[0][0].length < 1) {
            return false;
        }
        return true;
    }

    private boolean arrayHasContent(boolean[][][] integerArray) {
        if (integerArray == null) {
            return false;
        }
        if (integerArray.length < 1) {
            return false;
        }
        if (integerArray[0].length < 1) {
            return false;
        }
        if (integerArray[0][0].length < 1) {
            return false;
        }
        return true;
    }

    private boolean[][][] integerArrayToBooleanArray(int[][][] intArray) {
        boolean[][][] boolArray = new boolean[intArray.length][intArray[0].length][intArray[0][0].length];
        for (int i = 0; i < intArray.length; ++i) {
            for (int j = 0; j < intArray.length; ++j) {
                for (int k = 0; k < intArray.length; ++k) {
                    boolArray[i][j][k] = intArray[i][j][k] != 0;
                }
            }
        }
        return boolArray;
    }

    private boolean hasOneOrMoreTrue(boolean[][] boolArray) {
        for (boolean[] dimension1 : boolArray) {
            for (boolean value : dimension1) {
                if (value) {
                    return true;
                }
            }
        }
        return false;
    }
}
