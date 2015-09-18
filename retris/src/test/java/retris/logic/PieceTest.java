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
import retris.logic.shape.Shape;

/**
 *
 * @author rimi
 */
public class PieceTest {

    public PieceTest() {
    }

    /**
     * testaa palan muodon hakua
     */
    @Test
    public void testGetShape() {
        Piece instance = new Piece();
        int[][][] expResult = {{{1}}};
        Shape result = instance.GetShape();
        assertArrayEquals(expResult, result.getShapeFormRotations());
    }

    /**
     * testaa palan muodon asetusta
     */
    @Test
    public void testSetShape() {
        int[][][] expected = {{{1, 1}}};
        Shape shape = new Shape();
        shape.setShapeFormRotations(expected);
        Piece instance = new Piece();
        instance.setShape(shape);
        assertArrayEquals(expected, instance.GetShape().getShapeFormRotations());
    }

    /**
     * testaa palan muodon asetusta
     */
    @Test
    public void testSetShapeInvalid() {
        int[][][] expected = {{{1}}};
        Shape shape = new Shape();
        shape.setShapeFormRotations(new int[][][]{{{1, 1}}, {{0}}});
        Piece instance = new Piece();
        instance.setShape(shape);
        assertArrayEquals(expected, instance.GetShape().getShapeFormRotations());
    }

    /**
     * testaa palan paikan hakua
     */
    @Test
    public void testGetPosition() {
        Piece instance = new Piece();
        Position result = instance.getPosition();
        assertEquals(0, result.getX());
        assertEquals(0, result.getY());
    }

    /**
     * testaa palan paikan hakua
     */
    @Test
    public void testGetPosition2() {
        int x = 123;
        int y = 342;
        Piece instance = new Piece();
        instance.relocate(x, y);
        Position result = instance.getPosition();
        assertEquals(x, result.getX());
        assertEquals(y, result.getY());
    }

    /**
     * testaa palan paikan muutosta
     */
    @Test
    public void testRelocate() {
        int x = 123;
        int y = 423;
        Piece instance = new Piece();
        instance.relocate(x, y);
        assertEquals(x, instance.getPosition().getX());
        assertEquals(y, instance.getPosition().getY());
    }

    /**
     * testaa täyttää arraytä arrayyn
     */
    @Test
    public void testFillFormToArray() {
        int[][] array = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},};
        int[][] expectedArray = {
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {0, 0, 0, 0},};
        Piece instance = new Piece();
        instance.GetShape().setShapeFormRotations(new int[][][]{{{1}, {1}}});
        instance.fillFormToArray(array);
        assertArrayEquals(expectedArray, array);
    }

    /**
     * testaa täyttää arraytä arrayyn
     */
    @Test
    public void testFillFormToArray2() {
        int[][] array = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},};
        int[][] expectedArray = {
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 1, 1},};
        Piece instance = new Piece();
        instance.GetShape().setShapeFormRotations(new int[][][]{
            {
                {1},
                {1},
                {1, 0, 1, 1, 1},
                {1},}
        });
        instance.fillFormToArray(array);
        assertArrayEquals(expectedArray, array);
    }

}
