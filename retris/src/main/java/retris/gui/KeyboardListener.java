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
package retris.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import retris.logic.gamestate.GameActions;
import retris.logic.gamestate.GameState;

/**
 * Näppäinkuuntelija
 *
 * @author rochet2_2
 */
public class KeyboardListener implements KeyListener {

    /**
     * Pelin tilaolio
     */
    private final GameState gameState;

    public KeyboardListener(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent ev) {
        try {
            int keyCode = ev.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    gameState.addAction(GameActions.MOVE_ROTATE_LEFT);
                    break;
                case KeyEvent.VK_DOWN:
                    gameState.addAction(GameActions.MOVE_DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    gameState.addAction(GameActions.MOVE_LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    gameState.addAction(GameActions.MOVE_RIGHT);
                    break;
                case KeyEvent.VK_SPACE:
                    gameState.addAction(GameActions.MOVE_UP);
                    break;
            }
        } catch (Exception e) {
            System.exit(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
