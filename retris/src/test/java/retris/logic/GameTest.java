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
import retris.logic.shape.ShapeL;
import retris.logic.shape.ShapeO;

/**
 *
 * @author rimi
 */
public class GameTest {

    public GameTest() {
    }

    /**
     * testaa muodon lisäystä peliin
     */
    @Test
    public void testAddShapeToGame() {
        int[][][] expected = {{{1, 1}}};
        Shape shape = new Shape();
        shape.setShapeFormRotations(expected);
        Game game = new Game(null, 10, 10, 10);
        assertNotNull(game.selectRandomGameShape());
        game.addShapeToGame(shape);
        assertArrayEquals(expected, game.selectRandomGameShape().getShapeFormRotations());
    }

    @Test
    public void testRunGame() {
        Game game = new Game(null, 5, 5, 10);
        game.addShapeToGame(new ShapeO());
        game.runGame();
    }

    @Test
    public void testSimulateGame() {
        Game game = new Game(null, 5, 5, 10);
        game.addShapeToGame(new ShapeL());
        int[][] expected = {
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
}
