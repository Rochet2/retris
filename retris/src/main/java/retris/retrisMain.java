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

import retris.gui.GameUI;
import retris.gui.GameWindow;
import retris.logic.Game;
import retris.logic.shape.ShapeI;
import retris.logic.shape.ShapeJ;
import retris.logic.shape.ShapeL;
import retris.logic.shape.ShapeO;

/**
 * Ohjelman alkupiste. Luo pelin ja suorittaa sen
 *
 * @author rochet2_2
 */
public class retrisMain {

    /**
     * Ohjelman alkupiste. Luo pelin ja suorittaa sen. Virheet johtavat ohjelman
     * sulkemiseen.
     *
     * @param args ohjelman lisäargumentit
     */
    public static void main(String[] args) {
        try {
            Game game = initializeGame();
            game.runGame();
        } catch (Exception e) {
            try {
                System.exit(1);
            } catch (Exception e2) {

            }
        }
    }

    /**
     * Alustaa pelin
     *
     * @return peli
     */
    public static Game initializeGame() {
        final int width = 10;
        final int height = 16;
        final int speedMs = 500;
        GameUI ui = new GameWindow(width, height);
        Game game = new Game(ui, width, height, speedMs);
        addShapesToGame(game);
        return game;
    }

    /**
     * Lisää muodot peliin
     *
     * @param game peli
     */
    public static void addShapesToGame(Game game) {
        game.addShapeToGame(new ShapeL());
        game.addShapeToGame(new ShapeJ());
        game.addShapeToGame(new ShapeO());
        game.addShapeToGame(new ShapeI());
    }
}
