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

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import retris.logic.gamestate.GameState;
import retris.logic.timer.TimeDifferenceCounter;
import retris.logic.timer.Updateable;

/**
 * Päivittäjä olio johon lisätään päivitettäviä Updateable olioita.
 *
 * @author rochet2_2
 */
public class Updater implements Runnable {

    /**
     * Pelin tilaolio
     */
    private final GameState gameState;
    /**
     * Päivitettävät oliot
     */
    private final ArrayList<Updateable> toUpdate = new ArrayList<>();

    /**
     * Luo uuden päivittäjän
     *
     * @param gameState kertoo tilan
     */
    public Updater(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Lisää uuden päivitettävän olion päivittäjään.
     *
     * @param updated päivitettävä olio
     */
    public synchronized void addUpdateable(Updateable updated) {
        toUpdate.add(updated);
    }

    /**
     * Päivittää päivitettävät oliot
     *
     * @param diff kulunut aika millisekunteina viime päivityksestä
     */
    private synchronized void updateObjects(long diff) {
        for (Updateable updated : toUpdate) {
            updated.update(diff);
        }
    }

    /**
     * Päivittäjämetodi
     */
    @Override
    public void run() {
        try {
            TimeDifferenceCounter diffTime = new TimeDifferenceCounter();
            while (!gameState.hasEnded()) {
                long diff = diffTime.getTimeSinceLastCall();
                if (diff > 0) {
                    updateObjects(diff);
                }
            }
            showGameEndMessage();
        } catch (Exception e) {
            System.exit(1);
        }
    }

    /**
     * Näyttää pelin loppumisilmoituksen
     */
    private void showGameEndMessage() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "Game over\nScore: " + gameState.getScore());
            }
        });
    }

}
