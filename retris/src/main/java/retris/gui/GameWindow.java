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
import javax.swing.JFrame;
import retris.logic.Game;

/**
 *
 * @author rochet2_2
 */
public class GameWindow {

    private final JFrame gameWindow;
    private final BoardPanel boardPanel;

    public GameWindow(final Game game, int width, int height) {
        this.gameWindow = new JFrame("Retris");
        this.boardPanel = new BoardPanel(width, height);
        gameWindow.add(boardPanel);

        gameWindow.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
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
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        showGameWindow();
    }

    private void showGameWindow() {
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
    }

    public void updateGUI(int[][] boardState) {
        boardPanel.setDrawnArray(boardState);
    }

}
