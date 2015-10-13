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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rochet2_2
 */
public class ArrayUtilTest {

    private final int[][] arr2d = {
        {1, 2, 3},
        {2, 3, 0},
        {4, 4, 2},
        {1, 6, 8}
    };

    private final int[][] arr2ddeformed = {
        {1, 2, 6},
        {3, 2},
        {7, 2, 3},
        {1}
    };

    private final int[][][] arr3d = {
        {
            {1, 2, 3},
            {2, 3, 0},
            {4, 4, 2},
            {1, 6, 8}
        },
        {
            {4, 4, 2},
            {1, 2, 3},
            {2, 3, 0},
            {1, 6, 8}
        }
    };

    private final int[][][] arr3ddeformed = {
        {
            {1, 2, 6},
            {3, 2},
            {7, 2, 3},
            {1}
        },
        {
            {7, 2, 3},
            {1},
            {1, 2, 6},
            {3, 2}
        }
    };

    private final int[][] arr2dnull = {
        {1, 2, 6},
        {3, 2},
        null,
        {1}
    };

    private final int[][][] arr3dnull = {
        {
            {1, 2, 6},
            {3, 2},
            {7, 2, 3},
            {1}
        },
        {
            {7, 2, 3},
            {1},
            null,
            {3, 2}
        }
    };

    @Test
    public void testConstructor() {
        assertNotNull(new ArrayUtil());
    }

    @Test
    public void testCloneArray_intArrArr() {
        int[][] nullarr = null;
        assertNull(ArrayUtil.cloneArray(nullarr));
        assertNotEquals(arr2d, ArrayUtil.cloneArray(arr2d));
        assertArrayEquals(arr2d, ArrayUtil.cloneArray(arr2d));
        assertArrayEquals(arr2ddeformed, ArrayUtil.cloneArray(arr2ddeformed));
    }

    @Test
    public void testCloneArray_intArrArrArr() {
        int[][][] nullarr = null;
        assertNull(ArrayUtil.cloneArray(nullarr));
        assertNotEquals(arr3d, ArrayUtil.cloneArray(arr3d));
        assertArrayEquals(arr3d, ArrayUtil.cloneArray(arr3d));
        assertArrayEquals(arr3ddeformed, ArrayUtil.cloneArray(arr3ddeformed));
    }

    @Test
    public void testHasNullValue_intArrArr() {
        int[][] nullarr = null;
        assertTrue(ArrayUtil.hasNullValue(nullarr));
        assertFalse(ArrayUtil.hasNullValue(arr2d));
        assertFalse(ArrayUtil.hasNullValue(arr2ddeformed));
        assertTrue(ArrayUtil.hasNullValue(arr2dnull));
    }

    @Test
    public void testHasNullValue_intArrArrArr() {
        int[][][] nullarr = null;
        assertTrue(ArrayUtil.hasNullValue(nullarr));
        assertFalse(ArrayUtil.hasNullValue(arr3d));
        assertFalse(ArrayUtil.hasNullValue(arr3ddeformed));
        assertTrue(ArrayUtil.hasNullValue(arr3dnull));
    }

}
