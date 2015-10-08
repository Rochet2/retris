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

/**
 *
 * @author rimi
 */
public class PositionTest {

    private Position position;

    @Before
    public void setUp() {
        this.position = new Position();
    }

    @Test
    public void testDefaultConstructor() {
        position = new Position();
        assertEquals(0, position.getX());
        assertEquals(0, position.getY());
    }

    @Test
    public void testConstructorWithCoordinates() {
        int x = 123;
        int y = 543;
        position = new Position(x, y);
        assertEquals(x, position.getX());
        assertEquals(y, position.getY());
    }

    @Test
    public void testGetX() {
        assertEquals(0, position.getX());
        position.setX(20);
        assertEquals(20, position.getX());
        position.setX(-20);
        assertEquals(-20, position.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(0, position.getY());
        position.setY(20);
        assertEquals(20, position.getY());
        position.setY(-20);
        assertEquals(-20, position.getY());
    }

    @Test
    public void testSetX() {
        position.setX(0);
        assertEquals(0, position.getX());
        position.setX(123);
        assertEquals(123, position.getX());
        position.setX(-123);
        assertEquals(-123, position.getX());
    }

    @Test
    public void testSetY() {
        position.setY(0);
        assertEquals(0, position.getY());
        position.setY(123);
        assertEquals(123, position.getY());
        position.setY(-123);
        assertEquals(-123, position.getY());
    }

    @Test
    public void testRelocate() {
        position.relocate(123, 432);
        assertEquals(123, position.getX());
        assertEquals(432, position.getY());
        position.relocate(-123, -432);
        assertEquals(-123, position.getX());
        assertEquals(-432, position.getY());
        position.relocate(0, 0);
        assertEquals(0, position.getX());
        assertEquals(0, position.getY());
    }

}
