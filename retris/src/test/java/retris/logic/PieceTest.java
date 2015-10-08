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
package retris.logic;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import retris.logic.shape.Shape;

/**
 *
 * @author rimi
 */
public class PieceTest {

    private Shape shape;
    private Piece piece;
    private final int[][][] defaultShapeFormRotations = {{{1}}};
    private final int[][][] validShapeFormRotations = {
        {
            {1, 1, 1, 0},
            {0, 1, 0, 0},
            {0, 1, 1, 0}
        },
        {
            {0, 1, 1},
            {0, 0, 1},
            {0, 0, 1},
            {0, 1, 0}
        },
        {
            {1},
            {0, 0, 1, 0},
            {0, 0, 1},
            {0, 1}
        },
        {
            {1}
        }
    };

    @Before
    public void setUp() {
        this.shape = new Shape();
        this.piece = new Piece();
    }

    @Test
    public void testDefaultConstructor() {
        piece = new Piece();
        assertArrayEquals(defaultShapeFormRotations, piece.GetShape().getShapeFormRotations());
        assertEquals(0, piece.GetShape().getShapeFormIndex());
        assertEquals(0, piece.getPosition().getX());
        assertEquals(0, piece.getPosition().getY());
    }

    @Test
    public void testGetShape() {
        assertArrayEquals(defaultShapeFormRotations, piece.GetShape().getShapeFormRotations());
    }

    @Test
    public void testSetShape() {
        shape.setShapeFormRotations(validShapeFormRotations);
        piece.setShape(shape);
        assertArrayEquals(validShapeFormRotations, piece.GetShape().getShapeFormRotations());
    }

    @Test
    public void testSetShapeInvalid() {
        piece.setShape(null);
        assertArrayEquals(defaultShapeFormRotations, piece.GetShape().getShapeFormRotations());
    }

    @Test
    public void testGetPosition() {
        Position result = piece.getPosition();
        assertEquals(0, result.getX());
        assertEquals(0, result.getY());
    }

    @Test
    public void testRelocate() {
        int x = 123;
        int y = 342;
        piece.relocate(x, y);
        Position result = piece.getPosition();
        assertEquals(x, result.getX());
        assertEquals(y, result.getY());
    }

    @Test
    public void testFillFormToArray() {
        int[][] array = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        int[][] expectedArray = {
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {0, 0, 0, 0}
        };
        piece.GetShape().setShapeFormRotations(new int[][][]{{{1}, {1}}});
        piece.fillFormToArray(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    public void testFillFormToArrayWithOverflow() {
        int[][] array = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        int[][] expectedArray = {
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 1, 1}
        };
        piece.GetShape().setShapeFormRotations(new int[][][]{
            {
                {1},
                {1},
                {1, 0, 1, 1, 1},
                {1}
            }
        });
        piece.fillFormToArray(array);
        assertArrayEquals(expectedArray, array);
    }

}
