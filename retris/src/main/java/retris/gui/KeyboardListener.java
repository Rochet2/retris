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
import retris.logic.Game;

/**
 * Näppäinkuuntelija
 * 
 * @author rochet2_2
 */
public class KeyboardListener implements KeyListener {

    /**
     * Peli-ikkuna jolle näppäimistä viestitään
     */
    private final GameWindow gameWindow;

    public KeyboardListener(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent ev) {
        try {
            Game game = gameWindow.getGame();
            if (game == null) {
                return;
            }
            int keyCode = ev.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    game.rotatePiece();
                    break;
                case KeyEvent.VK_DOWN:
                    game.movePieceDown();
                    break;
                case KeyEvent.VK_LEFT:
                    game.movePieceLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    game.movePieceRight();
                    break;
                case KeyEvent.VK_SPACE:
                    game.movePieceUp();
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
