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

import retris.logic.piece.Position;
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
public class PositionTest {

    public PositionTest() {
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
    
    public void positionHelper(Position position) {
        assertTrue(position.isWithinRange(100, 100));
        assertFalse(position.isWithinRange(-1, 100));
        assertFalse(position.isWithinRange(100, -1));
        assertFalse(position.isWithinRange(-100, -100));
    }

    @Test
    public void testIsWithinRange() {
        Position position = new Position();
        positionHelper(position);
        assertTrue(position.isWithinRange(0, 0));
        position.setX(1);
        position.setY(1);
        positionHelper(position);
        assertTrue(position.isWithinRange(1, 1));
        position.setX(100);
        position.setY(100);
        positionHelper(position);
        position.setX(-1);
        position.setY(-1);
        assertFalse(position.isWithinRange(100, 100));
        assertFalse(position.isWithinRange(-100, -100));
        position.setX(-20);
        position.setY(10);
        assertFalse(position.isWithinRange(100, 100));
        assertFalse(position.isWithinRange(-100, -100));
        position.setX(10);
        position.setY(-10);
        assertFalse(position.isWithinRange(100, 100));
        assertFalse(position.isWithinRange(-100, -100));
    }
}
