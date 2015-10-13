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
package retris.logic.gamestate;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author rochet2_2
 */
public class GameStateTest {

    private GameState gameState;

    @Before
    public void setUp() {
        this.gameState = new GameState();
    }

    @Test
    public void testHasEnded() {
        assertFalse(gameState.hasEnded());
        gameState.setHasEnded(true);
        assertTrue(gameState.hasEnded());
        gameState.setHasEnded(false);
        assertFalse(gameState.hasEnded());
    }

    @Test
    public void testModifyScore() {
        assertEquals(0, gameState.getScore());
        gameState.modifyScore(-100);
        assertEquals(-100, gameState.getScore());
        gameState.modifyScore(200);
        assertEquals(100, gameState.getScore());
    }

    @Test
    public void testBoardState() {
        assertArrayEquals(new int[][]{{}}, gameState.getBoardState());
        int[][] boardState = {
            {1, 2, 3},
            {8, 1, 3},
            {1, 3, 2},
            {3, 2, 7}
        };
        gameState.setBoardState(boardState);
        assertArrayEquals(boardState, gameState.getBoardState());
        gameState.setBoardState(new int[][]{null});
        assertArrayEquals(boardState, gameState.getBoardState());
    }

    @Test
    public void testGetHasChanged() {
        assertFalse(gameState.getHasChanged().getAndSet(false));
        gameState.setBoardState(new int[][]{{}});
        assertTrue(gameState.getHasChanged().getAndSet(false));
        gameState.modifyScore(50);
        assertTrue(gameState.getHasChanged().getAndSet(false));
    }

    @Test
    public void testGameActions() {
        assertTrue(gameState.getActionQueue().isEmpty());
        gameState.addAction(GameActions.MOVE_UP);
        assertTrue(gameState.getActionQueue().contains(GameActions.MOVE_UP));
        assertTrue(gameState.getActionQueue().isEmpty());

        gameState.addAction(GameActions.MOVE_DOWN);
        gameState.addAction(GameActions.MOVE_LEFT);
        gameState.addAction(GameActions.MOVE_DOWN);
        gameState.addAction(GameActions.MOVE_ROTATE_LEFT);
        ArrayList<GameActions> actions = gameState.getActionQueue();
        assertEquals(4, actions.size());
        assertEquals(GameActions.MOVE_DOWN, actions.get(0));
        assertEquals(GameActions.MOVE_LEFT, actions.get(1));
        assertEquals(GameActions.MOVE_DOWN, actions.get(2));
        assertEquals(GameActions.MOVE_ROTATE_LEFT, actions.get(3));
        assertTrue(gameState.getActionQueue().isEmpty());
    }

}
