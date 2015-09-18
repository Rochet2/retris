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
public class GameTest {
    
    public GameTest() {
    }

    @Test
    public void testAddShapeToGame() {
        int[][][] expected = {{{1,1}}};
        Shape shape = new Shape();
        shape.setShapeFormRotations(expected);
        Game instance = new Game(10, 10, 10);
        assertNotNull(instance.selectRandomGameShape());
        instance.addShapeToGame(shape);
        assertArrayEquals(expected, instance.selectRandomGameShape().getShapeFormRotations());
    }

    /* Laukaisee GUIn. Siirrä GUI?
    @Test
    public void testRunGame() {
        Game instance = new Game(10, 10, 10);
        instance.runGame();
    }
    */
    
}
