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
import retris.logic.shape.Shape;
import retris.logic.shape.ShapeI;
import retris.logic.shape.ShapeL;

/**
 *
 * @author rimi
 */
public class GameTest {

    private Game game;
    int[][] expected = {
        {1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0}
    };

    @Before
    public void setUp() {
        this.game = new Game(5, 5, 10);
        this.game.addShapeToGame(new ShapeL());
    }

    @Test
    public void testConstructor() {
        game = new Game(5, 6, 10);
        assertEquals(10, game.getPieceDropTimer().getTimerDelay());
        assertEquals(5, game.getGameStateCopy()[0].length);
        assertEquals(6, game.getGameStateCopy().length);
        assertEquals(true, game.isRunning());
    }

    @Test
    public void testStopRunning() {
        assertEquals(true, game.isRunning());
        game.stopRunning();
        assertEquals(false, game.isRunning());
        game.stopRunning();
        assertEquals(false, game.isRunning());
    }

    @Test
    public void testUpdate() {
        game.resetPiece();
        expected = new int[][]{
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());
        game.update(0);
        assertArrayEquals(expected, game.getGameStateCopy());
        game.update(9);
        assertArrayEquals(expected, game.getGameStateCopy());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        game.update(1);
        assertArrayEquals(expected, game.getGameStateCopy());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
        game.update(10);
        assertArrayEquals(expected, game.getGameStateCopy());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
        game.update(-100);
        assertArrayEquals(expected, game.getGameStateCopy());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
        game.update(109);
        assertArrayEquals(expected, game.getGameStateCopy());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0}
        };
        game.update(1);
        assertArrayEquals(expected, game.getGameStateCopy());
    }

    @Test
    public void testResetPiece() {
        expected = new int[][]{
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());

        game.resetPiece();
        expected = new int[][]{
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());
        game.resetPiece();
        assertArrayEquals(expected, game.getGameStateCopy());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        game.movePieceDown();
        game.movePieceLeft();
        game.rotatePiece();
        assertArrayEquals(expected, game.getGameStateCopy());

        expected = new int[][]{
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        game.resetPiece();
        assertArrayEquals(expected, game.getGameStateCopy());
    }

    @Test
    public void testEndGameIfShould() {
        game.resetPiece();
        game.movePieceDown();
        game.movePieceDown();
        game.endGameIfShould();
        assertTrue(game.isRunning());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0}
        };
        game.movePieceDown();
        game.movePieceDown();
        game.movePieceDown();
        game.movePieceDown();
        game.endGameIfShould();
        assertFalse(game.isRunning());
        assertArrayEquals(expected, game.getGameStateCopy());
    }

    @Test
    public void testAddShapeToGame() {
        game = new Game(10, 10, 10);
        assertArrayEquals((new Shape()).getShapeFormRotations(), game.selectRandomGameShape().getShapeFormRotations());
        int[][][] formRotations = {{{1, 1}}};
        Shape shape = new Shape();
        shape.setShapeFormRotations(formRotations);
        game.addShapeToGame(shape);
        assertArrayEquals(formRotations, game.selectRandomGameShape().getShapeFormRotations());
    }

    @Test
    public void testMovePieceUp() {
        game.resetPiece();
        assertFalse(game.movePieceUp());

        expected = new int[][]{
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());

        assertTrue(game.movePieceDown());
        assertTrue(game.movePieceDown());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());

        assertTrue(game.movePieceUp());
        assertTrue(game.movePieceUp());
        assertFalse(game.movePieceUp());

        expected = new int[][]{
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());
    }

    @Test
    public void testMovePieceLeftRight() {
        game.resetPiece();
        assertTrue(game.movePieceLeft());
        assertFalse(game.movePieceLeft());

        expected = new int[][]{
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());

        assertTrue(game.movePieceRight());
        assertTrue(game.movePieceRight());
        assertFalse(game.movePieceRight());

        expected = new int[][]{
            {0, 0, 0, 0, 1},
            {0, 0, 1, 1, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());
    }

    @Test
    public void testMovePieceDown() {
        game.resetPiece();
        assertTrue(game.movePieceDown());
        assertTrue(game.movePieceDown());
        assertTrue(game.movePieceDown());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());

        assertFalse(game.movePieceDown());

        expected = new int[][]{
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());
    }

    @Test
    public void testRotatePiece() {
        game = new Game(5, 5, 10);
        game.addShapeToGame(new ShapeI());
        game.resetPiece();

        game.movePieceUp();

        expected = new int[][]{
            {4, 4, 4, 4, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());

        assertFalse(game.rotatePiece());
        assertArrayEquals(expected, game.getGameStateCopy());

        game.movePieceDown();
        assertTrue(game.rotatePiece());

        expected = new int[][]{
            {0, 4, 0, 0, 0},
            {0, 4, 0, 0, 0},
            {0, 4, 0, 0, 0},
            {0, 4, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());
    }

    @Test
    public void testSimulateGame() {
        expected = new int[][]{
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());

        game.resetPiece();
        expected = new int[][]{
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());

        game.movePieceDown();
        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());

        game.movePieceLeft();
        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());

        game.movePieceLeft();
        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());

        game.movePieceDown();
        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());

        game.movePieceRight();
        game.movePieceRight();
        game.movePieceRight();
        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1},
            {0, 0, 1, 1, 1},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());

        game.rotatePiece();
        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());

        game.movePieceDown();
        expected = new int[][]{
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 1, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0}
        };
        assertArrayEquals(expected, game.getGameStateCopy());
    }
    
    @Test
    public void testScoreMethods() {
        assertEquals(0, game.getScore());
        game.modifyScore(-100);
        assertEquals(-100, game.getScore());
        game.modifyScore(300);
        assertEquals(200, game.getScore());
    }
}
