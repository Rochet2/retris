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
package retris;

import retris.timer.TimeDifferenceCounter;
import retris.logic.Game;
import retris.logic.shape.ShapeL;

/**
 *
 * @author rochet2_2
 */
public class retrisMain {

    public static void main(String[] args) {
        // TODO: Wrap this in try catch and close program on error
        runGame();
    }

    /**
     * Käynnistää ja suorittaa peliä käyttöliittymineen.
     */
    private static void runGame() {
        Game game = createGame();
        TimeDifferenceCounter diffTime = new TimeDifferenceCounter();
        while (game.isRunning()) {
            long diff = diffTime.getTimeSinceLastCall();
            game.update(diff);
            // TODO: GUI
            // gui.update(diff, game);
        }
    }

    /**
     * Luo halutun pelin. Muokkaa pelin kokoa ja nopeutta Game konstruktorin
     * kutsussa. Lisää pelissä olevia muotoja kutsumalla game.addShapeToGame
     *
     * @return peli
     */
    private static Game createGame() {
        Game game = new Game(20, 20, 500);
        game.addShapeToGame(new ShapeL());
        return game;
    }
}
