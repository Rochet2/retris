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

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import retris.logic.Game;
import retris.logic.shape.ShapeI;
import retris.logic.shape.ShapeJ;
import retris.logic.shape.ShapeL;
import retris.logic.shape.ShapeO;
import retris.logic.timer.TimeDifferenceCounter;

/**
 * Pelin UI
 *
 * @author rochet2_2
 */
public final class GameWindow {

    /**
     * Pelin UI-ikkuna
     */
    private final JFrame gameWindow;
    /**
     * pelilaudan visualisoija
     */
    private final ArrayVisualizer boardVisualizer;
    /**
     * peli loppui teksti
     */
    private final JLabel gameOverText;
    /**
     * paneeli jossa peli-ikkunan sisältö on
     */
    private final JPanel windowPanel;
    /**
     * pelin pisteet
     */
    private final JLabel scoreText;
    /**
     * peliolio
     */
    private final Game game;

    /**
     * Luo uuden peli-ikkunan joka näyttää pelin visuaalisen tilan.
     */
    public GameWindow() {
        this.gameWindow = new JFrame("Retris");
        this.windowPanel = new JPanel();
        this.gameOverText = new JLabel("Game Over");
        this.scoreText = new JLabel("Score: 0");
        this.boardVisualizer = new ArrayVisualizer(211, 337, 1);
        this.game = new Game(10, 16, 500);
        setGameShapes();

        setContent();
        showGameWindow();

        runGame();
    }

    /**
     * Lisää ikkunan sisällön
     */
    private void setContent() {
        windowPanel.setLayout(null);
        Dimension dim = new Dimension(boardVisualizer.getPreferredSize());
        dim.height += gameOverText.getPreferredSize().height + scoreText.getPreferredSize().height;
        windowPanel.setPreferredSize(dim);
        gameWindow.add(windowPanel);

        boardVisualizer.setBounds(0, gameOverText.getPreferredSize().height + scoreText.getPreferredSize().height,
                boardVisualizer.getPreferredSize().width, boardVisualizer.getPreferredSize().height);

        scoreText.setBounds(0, 0, boardVisualizer.getPreferredSize().width, scoreText.getPreferredSize().height);

        gameOverText.setVisible(false);
        gameOverText.setForeground(Color.red);
        gameOverText.setBounds(0, scoreText.getPreferredSize().height,
                boardVisualizer.getPreferredSize().width, gameOverText.getPreferredSize().height);

        windowPanel.add(boardVisualizer);
        windowPanel.add(scoreText);
        windowPanel.add(gameOverText);

        gameWindow.addKeyListener(new KeyboardListener(this));
    }

    /**
     * Viimeistelee ja näyttää ikkunan
     */
    private void showGameWindow() {
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
    }

    /**
     * Asettaa peliin tetromino muodot
     */
    private void setGameShapes() {
        game.addShapeToGame(new ShapeL());
        game.addShapeToGame(new ShapeJ());
        game.addShapeToGame(new ShapeO());
        game.addShapeToGame(new ShapeI());
    }

    /**
     * Suorittaa pelin
     */
    private void runGame() {
        gameOverText.setVisible(false);
        game.resetPiece();
        TimeDifferenceCounter diffTime = new TimeDifferenceCounter();
        while (game.isRunning()) {
            long diff = diffTime.getTimeSinceLastCall();
            if (diff > 0) {
                game.update(diff);
                boardVisualizer.setDrawnArray(game.getGameStateCopy());
                scoreText.setText("Score: " + game.getScore());
            }
        }
        gameOverText.setVisible(true);
    }

    /**
     * Palauttaa pelin
     *
     * @return peliolio
     */
    public Game getGame() {
        return game;
    }

}
