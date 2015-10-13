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

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import retris.logic.ArrayUtil;

/**
 *
 * @author rochet2_2
 */
public class ShapeTest {

    private Shape shape;
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
    private final int[][][] invalidShapeFormRotationsSingleZero = {
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
            {0}
        }
    };
    private final int[][][] invalidShapeFormRotationsZeros = {
        {
            {1, 1, 1, 0},
            {0, 1, 0, 0},
            {0, 1, 1, 0}
        },
        {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        }
    };
    private final int[][][] invalidShapeFormRotationsEmpty = {
        {
            {1, 1, 1, 0},
            {0, 1, 0, 0},
            {0, 1, 1, 0}
        },
        {
            {0},
            {}
        }
    };

    @Before
    public void setUp() {
        this.shape = new Shape();
    }

    @Test
    public void testShapeConstructorDefaultForm() {
        shape = new Shape();
        assertArrayEquals(defaultShapeFormRotations, shape.getShapeFormRotations());
    }

    @Test
    public void testShapeCopyConstructorForm() {
        shape.setShapeFormRotations(validShapeFormRotations);
        Shape copy = new Shape(shape);
        assertArrayEquals(validShapeFormRotations, copy.getShapeFormRotations());
    }

    @Test
    public void testShapeNullConstructorForm() {
        shape = new Shape(null);
        assertArrayEquals(defaultShapeFormRotations, shape.getShapeFormRotations());
    }

    @Test
    public void testgetShapeFormRotations() {
        assertArrayEquals(defaultShapeFormRotations, shape.getShapeFormRotations());
        shape.setShapeFormRotations(validShapeFormRotations);
        assertArrayEquals(validShapeFormRotations, shape.getShapeFormRotations());
    }

    @Test
    public void testGetMaxWidthSingle() {
        int[][][] testForm = {
            {{1}}
        };
        shape.setShapeFormRotations(testForm);
        assertEquals(1, shape.getMaxWidth());
    }

    @Test
    public void testGetMaxWidthSingleRow() {
        int[][][] testForm = {
            {{0, 1, 0, 0}}
        };
        shape.setShapeFormRotations(testForm);
        assertEquals(4, shape.getMaxWidth());
    }

    @Test
    public void testGetMaxWidthMultipleRows() {
        int[][][] testForm = {
            {
                {0, 1, 0, 0},
                {0, 1},
                {0, 1, 0, 1},
                {0, 1, 0, 1},
                {1, 1, 0}
            }
        };
        shape.setShapeFormRotations(testForm);
        assertEquals(4, shape.getMaxWidth());
    }

    @Test
    public void testGetMaxWidthMultipleForms() {
        int[][][] testForm = {
            {
                {0, 1, 0},
                {0, 1, 0}
            },
            {
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 1},
                {0, 1},
                {1, 1}
            }
        };
        shape.setShapeFormRotations(testForm);
        assertEquals(4, shape.getMaxWidth());
    }

    @Test
    public void testGetMaxHeightSingle() {
        int[][][] testForm = {
            {{1}}
        };
        shape.setShapeFormRotations(testForm);
        assertEquals(1, shape.getMaxHeight());
    }

    @Test
    public void testGetMaxHeightSingleRow() {
        int[][][] testForm = {
            {{0, 1, 0, 0}}
        };
        shape.setShapeFormRotations(testForm);
        assertEquals(1, shape.getMaxHeight());
    }

    @Test
    public void testGetMaxHeightMultipleRows() {
        int[][][] testForm = {
            {
                {0, 1, 0, 0},
                {0, 1},
                {0, 1, 0, 1},
                {0, 1, 0, 1},
                {1, 1, 0}
            }
        };
        shape.setShapeFormRotations(testForm);
        assertEquals(5, shape.getMaxHeight());
    }

    @Test
    public void testGetMaxHeightMultipleForms() {
        int[][][] testForm = {
            {
                {0, 1, 0},
                {0, 1, 0}},
            {
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 1},
                {0, 1},
                {1, 1}
            }
        };
        shape.setShapeFormRotations(testForm);
        assertEquals(5, shape.getMaxHeight());
    }

    @Test
    public void testSetShapeFormRotationsValid() {
        shape.setShapeFormRotations(validShapeFormRotations);
        assertArrayEquals(validShapeFormRotations, shape.getShapeFormRotations());
    }

    @Test
    public void testSetShapeFormRotationsInvalidEmpty() {
        shape.setShapeFormRotations(invalidShapeFormRotationsEmpty);
        assertArrayEquals(defaultShapeFormRotations, shape.getShapeFormRotations());
    }

    @Test
    public void testSetShapeFormRotationsInvalidSingleZero() {
        shape.setShapeFormRotations(invalidShapeFormRotationsSingleZero);
        assertArrayEquals(defaultShapeFormRotations, shape.getShapeFormRotations());
    }

    @Test
    public void testSetShapeFormRotationsInvalidZeros() {
        shape.setShapeFormRotations(invalidShapeFormRotationsZeros);
        assertArrayEquals(defaultShapeFormRotations, shape.getShapeFormRotations());
    }

    @Test
    public void testCloneArray3DClonesCorrectly() {
        int[][][] result = ArrayUtil.cloneArray(validShapeFormRotations);
        assertArrayEquals(validShapeFormRotations, result);
    }

    @Test
    public void testCloneArray3DDoesRealClone() {
        int[][][] test3dArr = {
            {
                {1, 0},
                {1, 0, 1}
            }};
        int[][][] test3dArrcopy = {
            {
                {1, 0},
                {1, 0, 1}
            }};
        int[][][] result = ArrayUtil.cloneArray(test3dArr);
        test3dArr[0][1][0] = 0;
        assertArrayEquals(test3dArrcopy, result);
    }

    @Test
    public void testFormsAreVisibleValid() {
        assertTrue(shape.formsAreVisible(defaultShapeFormRotations));
        assertTrue(shape.formsAreVisible(validShapeFormRotations));
    }

    @Test
    public void testFormsAreVisibleInvalid() {
        assertFalse(shape.formsAreVisible(invalidShapeFormRotationsEmpty));
        assertFalse(shape.formsAreVisible(invalidShapeFormRotationsSingleZero));
        assertFalse(shape.formsAreVisible(invalidShapeFormRotationsZeros));
    }

    @Test
    public void testArrayDimensionLenghtsAboveZeroValid() {
        assertTrue(shape.arrayDimensionLenghtsAboveZero(defaultShapeFormRotations));
        assertTrue(shape.arrayDimensionLenghtsAboveZero(validShapeFormRotations));
        assertTrue(shape.arrayDimensionLenghtsAboveZero(invalidShapeFormRotationsSingleZero));
        assertTrue(shape.arrayDimensionLenghtsAboveZero(invalidShapeFormRotationsZeros));
    }

    @Test
    public void testArrayDimensionLenghtsAboveZeroInvalid() {
        assertFalse(shape.arrayDimensionLenghtsAboveZero(new int[][][]{}));
        assertFalse(shape.arrayDimensionLenghtsAboveZero(new int[][][]{{}}));
        assertFalse(shape.arrayDimensionLenghtsAboveZero(new int[][][]{{{}}}));
        assertFalse(shape.arrayDimensionLenghtsAboveZero(invalidShapeFormRotationsEmpty));
    }

    @Test
    public void testHasOneNotZeroValid() {
        for (int[][] form : validShapeFormRotations) {
            assertTrue(shape.hasOneNotZero(form));
        }
        assertTrue(shape.hasOneNotZero(new int[][]{{0, 0}, {1}}));
    }

    @Test
    public void testHasOneNotZeroInvalid() {
        assertFalse(shape.hasOneNotZero(new int[][]{{0}}));
        assertFalse(shape.hasOneNotZero(new int[][]{{0, 0}, {0}}));
    }

    @Test
    public void testSetShapeFormIndexWithDefaultShape() {
        shape.setShapeFormIndex(0);
        assertEquals(0, shape.getShapeFormIndex());
        shape.setShapeFormIndex(1);
        assertEquals(0, shape.getShapeFormIndex());
    }

    @Test
    public void testSetShapeFormIndexWithMultipleForms() {
        shape.setShapeFormRotations(new int[][][]{{{1}}, {{1}}, {{1}}, {{1}}});
        shape.setShapeFormIndex(2);
        assertEquals(2, shape.getShapeFormIndex());
        shape.setShapeFormIndex(3);
        assertEquals(3, shape.getShapeFormIndex());
        shape.setShapeFormIndex(4);
        assertEquals(0, shape.getShapeFormIndex());
    }

    @Test
    public void testSetShapeFormIndexWithMultipleFormsNegative() {
        shape.setShapeFormRotations(new int[][][]{{{1}}, {{1}}, {{1}}, {{1}}});
        shape.setShapeFormIndex(-1);
        assertEquals(3, shape.getShapeFormIndex());
        shape.setShapeFormIndex(-2);
        assertEquals(2, shape.getShapeFormIndex());
    }

}
