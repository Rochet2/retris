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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rochet2_2
 */
public class ShapeTest {

    public ShapeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * testaa muodon oikeiden käännösvaiheiden asetus boolean arrayllä
     */
    @Test
    public void testSetShapeFormRotationsBooleanValid() {
        boolean[][][] expResult;
        boolean[][][] result;
        Shape shape = new Shape();

        expResult = new boolean[][][]{{{true}}};
        result = shape.getShapeFormRotations();
        assertArrayEquals(expResult, result);

        expResult = new boolean[][][]{
            {
                {true, true, true},
                {true, false, true},
                {true, true, true},},
            {
                {false, false},
                {false, true},
                {false, false},},
            {
                {true},},};
        shape.setShapeFormRotations(expResult);
        result = shape.getShapeFormRotations();
        assertArrayEquals(expResult, result);
    }

    /**
     * testaa muodon väärien käännösvaiheiden asetus boolean arrayllä
     */
    @Test
    public void testSetShapeFormRotationsBooleanInvalid() {
        boolean[][][] expResult = new boolean[][][]{{{true}}};
        boolean[][][] result;
        Shape shape = new Shape();
        shape.setShapeFormRotations(expResult);

        shape.setShapeFormRotations(new boolean[][][]{{{false}}});
        result = shape.getShapeFormRotations();
        assertArrayEquals(expResult, result);

        shape.setShapeFormRotations(new boolean[][][]{
            {
                {true, true, true},
                {true, false, true},
                {true, true, true},},
            {
                {false, false},
                {false, false},
                {false, false},},
            {
                {true},},});
        result = shape.getShapeFormRotations();
        assertArrayEquals(expResult, result);
    }

    /**
     * testaa muodon väärien käännösvaiheiden asetus int arrayllä
     */
    @Test
    public void testSetShapeFormRotationsIntegerInvalid() {
        boolean[][][] expResult = new boolean[][][]{{{true}}};
        boolean[][][] result;
        Shape shape = new Shape();

        shape.setShapeFormRotations(new int[][][]{{{1}}});
        result = shape.getShapeFormRotations();
        assertArrayEquals(expResult, result);
        
        shape.setShapeFormRotations(new int[][][]{
            {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1},},
            {
                {0, 0},
                {0, 0},
                {0, 0},},});
        result = shape.getShapeFormRotations();
        assertArrayEquals(expResult, result);

        expResult = new boolean[][][]{
            {
                {false, false},
                {false, true},
                {false, false},}
        };
        shape.setShapeFormRotations(new int[][][]{
            {
                {0, 0},
                {0, 1},
                {0, 0},}
        });
        result = shape.getShapeFormRotations();
        assertArrayEquals(expResult, result);
    }

    /**
     * testaa muodon maksimileveyttä
     */
    @Test
    public void testGetMaxWidth() {
        int expResult;
        int result;
        Shape shape = new Shape();

        expResult = 1;
        result = shape.getMaxWidth();
        assertEquals(expResult, result);

        shape.setShapeFormRotations(new int[][][]{
            {
                {0, 0, 0},
                {0, 1, 0},}
        });
        expResult = 3;
        result = shape.getMaxWidth();
        assertEquals(expResult, result);
    }

    /**
     * testaa muodon maksimikorkeutta
     */
    @Test
    public void testGetMaxHeight() {
        int expResult;
        int result;
        Shape shape = new Shape();

        expResult = 1;
        result = shape.getMaxHeight();
        assertEquals(expResult, result);

        shape.setShapeFormRotations(new int[][][]{
            {
                {0, 0},
                {0, 1},
                {0, 0},}
        });
        expResult = 3;
        result = shape.getMaxHeight();
        assertEquals(expResult, result);
    }

}
