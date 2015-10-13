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
import retris.logic.gamestate.GameActions;
import retris.logic.gamestate.GameState;
import retris.logic.shape.Shape;
import retris.logic.shape.ShapeI;
import retris.logic.shape.ShapeL;
import retris.logic.timer.Timer;

/**
 *
 * @author rimi
 */
public class GameTest {

    private GameState gameState;
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
        this.gameState = new GameState();
        this.game = new Game(this.gameState, 5, 5, 10);
        this.game.addShapeToGame(new ShapeL());
    }

    @Test
    public void testConstructor() {
        game = new Game(gameState, 5, 6, 10);
        assertEquals(10, game.getPieceDropTimer().getTimerDelay());
        assertEquals(5, game.getCurrentBoardStateCopy()[0].length);
        assertEquals(6, game.getCurrentBoardStateCopy().length);
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
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
        game.update(0);
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
        game.update(9);
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        game.update(1);
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
        game.update(10);
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
        game.update(-100);
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
        game.update(109);
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0}
        };
        game.update(1);
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
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
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        game.resetPiece();
        expected = new int[][]{
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
        game.resetPiece();
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

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
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        expected = new int[][]{
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        game.resetPiece();
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
    }

    @Test
    public void testEndGameIfShould() {
        game.resetPiece();
        game.movePieceDown();
        game.movePieceDown();
        game.endGameIfShould();
        assertFalse(gameState.hasEnded());

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
        assertTrue(gameState.hasEnded());
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
    }

    @Test
    public void testAddShapeToGame() {
        game = new Game(gameState, 10, 10, 10);
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
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        assertTrue(game.movePieceDown());
        assertTrue(game.movePieceDown());

        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

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
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
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
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

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
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
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
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        assertFalse(game.movePieceDown());

        expected = new int[][]{
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
    }

    @Test
    public void testRotatePiece() {
        game = new Game(gameState, 5, 5, 10);
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
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        assertFalse(game.rotatePiece());
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        game.movePieceDown();
        assertTrue(game.rotatePiece());

        expected = new int[][]{
            {0, 4, 0, 0, 0},
            {0, 4, 0, 0, 0},
            {0, 4, 0, 0, 0},
            {0, 4, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
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
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        game.resetPiece();
        expected = new int[][]{
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        game.movePieceDown();
        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        game.movePieceLeft();
        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        game.movePieceLeft();
        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        game.movePieceDown();
        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

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
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        game.rotatePiece();
        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());

        game.movePieceDown();
        expected = new int[][]{
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 1, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
    }

    @Test
    public void testRewardScore() {
        assertEquals(0, gameState.getScore());
        game.rewardScore(1);
        assertEquals((long)Math.pow(2, 1)*50, gameState.getScore());
        game.rewardScore(2);
        assertEquals((long)Math.pow(2, 1)*50 + (long)Math.pow(2, 2)*50, gameState.getScore());
    }

    @Test
    public void testInitializeGameState() {
        expected = new int[][]{
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
        assertArrayEquals(new int[][]{{}}, gameState.getBoardState());
        
        game.initializeGameState();
        
        expected = new int[][]{
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
        assertArrayEquals(expected, gameState.getBoardState());
    }

    @Test
    public void testProcessActions() {
        game.resetPiece();
        gameState.addAction(GameActions.MOVE_DOWN);
        gameState.addAction(GameActions.MOVE_LEFT);
        gameState.addAction(GameActions.MOVE_ROTATE_LEFT);
        game.processActions();
        
        expected = new int[][]{
            {0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
        
        gameState.addAction(GameActions.MOVE_RIGHT);
        gameState.addAction(GameActions.MOVE_UP);
        game.processActions();
        
        expected = new int[][]{
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, game.getCurrentBoardStateCopy());
    }
}
