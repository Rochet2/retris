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
public class ShapeFormTest {

    public ShapeFormTest() {
    }

    @Test
    public void testShapeI() {
        Shape shape = new ShapeI();
        assertArrayEquals(ShapeI.form, shape.getShapeFormRotations());
    }

    @Test
    public void testShapeJ() {
        Shape shape = new ShapeJ();
        assertArrayEquals(ShapeJ.form, shape.getShapeFormRotations());
    }

    @Test
    public void testShapeL() {
        Shape shape = new ShapeL();
        assertArrayEquals(ShapeL.form, shape.getShapeFormRotations());
    }

    @Test
    public void testShapeO() {
        Shape shape = new ShapeO();
        assertArrayEquals(ShapeO.form, shape.getShapeFormRotations());
    }

}
