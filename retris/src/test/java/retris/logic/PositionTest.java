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

/**
 *
 * @author rimi
 */
public class PositionTest {
    
    public PositionTest() {
    }

    @Test
    public void testConstructor() {
        int x = 0;
        int y = 0;
        Position instance = new Position();
        assertEquals(x, instance.getX());
        assertEquals(y, instance.getY());
    }

    @Test
    public void testConstructor2() {
        int x = 123;
        int y = 543;
        Position instance = new Position(x, y);
        assertEquals(x, instance.getX());
        assertEquals(y, instance.getY());
    }

    @Test
    public void testCloneConstructor() {
        int x = 123;
        int y = 543;
        Position instance = new Position(new Position(x, y));
        assertEquals(x, instance.getX());
        assertEquals(y, instance.getY());
    }

    @Test
    public void testGetX() {
        Position instance = new Position();
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetY() {
        Position instance = new Position();
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetX() {
        int expected = 324;
        Position instance = new Position();
        instance.setX(expected);
        int result = instance.getX();
        assertEquals(expected, result);
    }

    @Test
    public void testSetY() {
        int expected = 324;
        Position instance = new Position();
        instance.setY(expected);
        int result = instance.getY();
        assertEquals(expected, result);
    }

    @Test
    public void testRelocate() {
        int x = 123;
        int y = 453;
        Position instance = new Position();
        instance.relocate(x, y);
        assertEquals(x, instance.getX());
        assertEquals(y, instance.getY());
    }
    
}
