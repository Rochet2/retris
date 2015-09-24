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

/**
 *
 * @author rochet2_2
 */
public class ShapeTest {

    public ShapeTest() {
    }

    /**
     * testaa muodon oikeiden käännösvaiheiden asetusta
     */
    @Test
    public void testSetShapeFormRotationsValid() {
        int[][][] expResult;
        int[][][] result;
        Shape shape = new Shape();

        expResult = new int[][][]{{{1}}};
        result = shape.getShapeFormRotations();
        assertArrayEquals(expResult, result);

        expResult = new int[][][]{
            {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
            },
            {
                {0, 0},
                {0, 1},
                {0, 0}
            },
            {
                {0, 0, 0},
                {0, 1, 0}
            },
            {
                {1}
            }
        };
        shape.setShapeFormRotations(expResult);
        result = shape.getShapeFormRotations();
        assertArrayEquals(expResult, result);
    }

    /**
     * testaa muodon väärien käännösvaiheiden asetusta
     */
    @Test
    public void testSetShapeFormRotationsInvalid() {
        int[][][] expResult = new int[][][]{{{1}}};
        int[][][] result;
        Shape shape = new Shape();
        shape.setShapeFormRotations(expResult);

        shape.setShapeFormRotations(new int[][][]{{{0}}});
        result = shape.getShapeFormRotations();
        assertArrayEquals(expResult, result);

        shape.setShapeFormRotations(new int[][][]{
            {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
            },
            {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
            }
        });
        result = shape.getShapeFormRotations();
        assertArrayEquals(expResult, result);

        shape.setShapeFormRotations(new int[][][]{
            {
                {0, 0},
                {0, 0},
                {0, 0}
            }
        });
        result = shape.getShapeFormRotations();
        assertArrayEquals(expResult, result);

        shape.setShapeFormRotations(new int[][][]{
            {
                {0}
            }
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
                {0, 1, 0}
            }
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
                {0, 0}
            }
        });
        expResult = 3;
        result = shape.getMaxHeight();
        assertEquals(expResult, result);
    }

    /**
     * testaa muodon kopiointia
     */
    @Test
    public void testCopyConstructor() {
        int[][][] expResult = {
            {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
            },
            {
                {0, 0},
                {0, 1},
                {0, 0}
            },
            {
                {0, 0, 0},
                {0, 1, 0}
            },
            {
                {1}
            }
        };
        Shape shape = new Shape();
        shape.setShapeFormRotations(expResult);
        int[][][] result = new Shape(shape).getShapeFormRotations();
        assertArrayEquals(expResult, result);
    }

    /**
     * testaa arrayn kopiointia
     */
    @Test
    public void testCloneArray3D() {
        int[][][] expResult = {
            {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
            },
            {
                {0, 0},
                {0, 1},
                {0, 0}
            },
            {
                {0, 0, 0},
                {0, 1, 0}
            },
            {
                {1}
            }
        };
        Shape shape = new Shape();
        int[][][] result = shape.cloneArray3D(expResult);
        assertArrayEquals(expResult, result);
    }

    /**
     * testaa arrayn pituuksia
     */
    @Test
    public void testArrayDimensionLengthsValid() {
        int[][][] expResult = {
            {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
            },
            {
                {0, 0},
                {0, 1},
                {0, 0}
            },
            {
                {0, 0, 0},
                {0, 1, 0}
            },
            {
                {1}
            }
        };
        Shape shape = new Shape();
        boolean result = shape.arrayDimensionLenghtsAboveZero(expResult);
        assertEquals(true, result);
    }

    /**
     * testaa arrayn pituuksia
     */
    @Test
    public void testArrayDimensionLengthsValid2() {
        int[][][] expResult = {{{1}}};
        Shape shape = new Shape();
        boolean result = shape.arrayDimensionLenghtsAboveZero(expResult);
        assertTrue(result);
    }

    /**
     * testaa arrayn pituuksia
     */
    @Test
    public void testArrayDimensionLengthsInvalid() {
        int[][][] expResult = {{{}}};
        Shape shape = new Shape();
        boolean result = shape.arrayDimensionLenghtsAboveZero(expResult);
        assertFalse(result);
    }

    /**
     * testaa arrayn pituuksia
     */
    @Test
    public void testArrayDimensionLengthsInvalid2() {
        int[][][] expResult = {{}};
        Shape shape = new Shape();
        boolean result = shape.arrayDimensionLenghtsAboveZero(expResult);
        assertFalse(result);
    }

    /**
     * testaa arrayn pituuksia
     */
    @Test
    public void testArrayDimensionLengthsInvalid3() {
        int[][][] expResult = {};
        Shape shape = new Shape();
        boolean result = shape.arrayDimensionLenghtsAboveZero(expResult);
        assertFalse(result);
    }

    /**
     * testaa muodon käännösvaiheita
     */
    @Test
    public void testSetShapeFormIndex() {
        int expResult = 0;
        Shape shape = new Shape();
        shape.setShapeFormIndex(expResult);
        int result = shape.getShapeFormIndex();
        assertEquals(expResult, result);
    }

    /**
     * testaa muodon käännösvaiheita
     */
    @Test
    public void testSetShapeFormIndex2() {
        int expResult = 2;
        Shape shape = new Shape();
        shape.setShapeFormRotations(new int[][][]{{{1}}, {{1}}, {{1}}, {{1}}});
        shape.setShapeFormIndex(expResult);
        int result = shape.getShapeFormIndex();
        assertEquals(expResult, result);
    }

    /**
     * testaa muodon käännösvaiheita
     */
    @Test
    public void testSetShapeFormIndex3() {
        int expResult = 0;
        Shape shape = new Shape();
        shape.setShapeFormRotations(new int[][][]{{{1}}, {{1}}, {{1}}});
        shape.setShapeFormIndex(3);
        int result = shape.getShapeFormIndex();
        assertEquals(expResult, result);
    }

}
