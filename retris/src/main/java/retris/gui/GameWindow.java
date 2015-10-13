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

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import retris.logic.gamestate.GameState;
import retris.logic.timer.Updateable;

/**
 * Pelin UI
 *
 * @author rochet2_2
 */
public final class GameWindow implements Updateable {

    /**
     * Pelin UI-ikkuna
     */
    private final JFrame gameWindow;
    /**
     * pelilaudan visualisoija
     */
    private final ArrayVisualizer boardVisualizer;
    /**
     * paneeli jossa peli-ikkunan sisältö on
     */
    private final JPanel windowPanel;
    /**
     * pelin pisteet
     */
    private final JLabel scoreText;
    /**
     * pelin tila
     */
    private final GameState gameState;

    /**
     * Luo uuden peli-ikkunan joka näyttää pelin visuaalisen tilan.
     *
     * @param gameState pelin tilaa kuvaava olio
     */
    public GameWindow(GameState gameState) {
        this.gameState = gameState;
        this.gameWindow = new JFrame("Retris");
        this.windowPanel = new JPanel();
        this.scoreText = new JLabel("Score: 0");
        this.boardVisualizer = new ArrayVisualizer(gameState, 211, 337, 1);

        setContent();
        showGameWindow();
    }

    /**
     * Lisää ikkunan sisällön
     */
    private void setContent() {
        windowPanel.setLayout(null);
        Dimension dim = new Dimension(boardVisualizer.getPreferredSize());
        dim.height += scoreText.getPreferredSize().height;
        windowPanel.setPreferredSize(dim);
        gameWindow.add(windowPanel);

        boardVisualizer.setBounds(0, scoreText.getPreferredSize().height,
                boardVisualizer.getPreferredSize().width, boardVisualizer.getPreferredSize().height);

        scoreText.setBounds(0, 0, boardVisualizer.getPreferredSize().width, scoreText.getPreferredSize().height);

        windowPanel.add(boardVisualizer);
        windowPanel.add(scoreText);

        gameWindow.addKeyListener(new KeyboardListener(gameState));
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
     * Päivittää olion
     *
     * @param diff aika viime päivitysyrityksestä
     */
    @Override
    public void update(long diff) {
        if (!gameState.getHasChanged().getAndSet(false)) {
            return;
        }
        scoreText.setText("Score: " + gameState.getScore());
        boardVisualizer.update();
    }

}
