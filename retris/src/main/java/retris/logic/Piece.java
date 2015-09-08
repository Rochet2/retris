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
public class Piece {

    private int x;
    private int y;
    private int rotationIndex;
    private final Shape shape;

    public Piece() {
        this(new Shape(), 0, 0);
    }

    public Piece(Shape shape) {
        this(shape, 0, 0);
    }

    public Piece(int x, int y) {
        this(Shape.getRandomShape(), x, y);
    }

    public Piece(Shape shape, int x, int y) {
        if (shape == null) {
            this.shape = Shape.getDefaultShape();
        } else {
            this.shape = shape;
        }
        this.rotationIndex = 0;
        relocate(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public final void relocate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the shape
     */
    public boolean[][] getForm() {
        boolean[][][] shapeFormRotations = shape.getShapeFormRotations();
        return shapeFormRotations[rotationIndex];
    }

    /**
     * @return the shape
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * @return the orientation
     */
    public int getRotationIndex() {
        return rotationIndex;
    }

    /**
     * @param rotationIndex rotationIndex to use
     */
    public void setRotationIndex(int rotationIndex) {
        boolean[][][] shapes = getShape().getShapeFormRotations();
        rotationIndex = rotationIndex % shapes.length;
        if (rotationIndex < 0) {
            rotationIndex += shapes.length;
        }
        this.rotationIndex = rotationIndex;
    }

    public boolean collides(Piece piece) {
        boolean[][] form1 = getForm();
        boolean[][] form2 = piece.getForm();
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
