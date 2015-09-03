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
import retris.logic.shape.ShapeFactory;

/**
 *
 * @author rochet2_2
 */
public class Piece {

    private Position position;
    private int shapeIndex;
    private Shape shape;
    
    public Piece(Shape shape) {
        this.shape = shape;
        this.shapeIndex = 0;
        this.position = new Position();
    }

    /**
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Position position) {
        if (position == null) {
            return;
        }
        this.position = position;
    }

    /**
     * @return the shape
     */
    public int[][] getShapeArray() {
        int[][][] shapes = ShapeFactory.getShape(getShape());
        if (shapeIndex >= 0 && shapeIndex < shapes.length)
            return shapes[shapeIndex];
        return null;
    }

    /**
     * @return the orientation
     */
    public int getShapeIndex() {
        return shapeIndex;
    }

    /**
     * @param shapeIndex shapeIndex to use
     */
    public void setShapeIndex(int shapeIndex) {
        int[][][] shapes = ShapeFactory.getShape(getShape());
        shapeIndex = shapeIndex % shapes.length;
        if (shapeIndex < 0) {
            shapeIndex += shapes.length;
        }
        this.shapeIndex = shapeIndex;
    }

    /**
     * @return the shape
     */
    public Shape getShape() {
        return shape;
    }

}
