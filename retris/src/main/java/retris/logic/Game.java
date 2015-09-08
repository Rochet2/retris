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
package retris.logic;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import retris.logic.shape.Shape;
import retris.logic.shape.ShapeL;

/**
 *
 * @author rochet2_2
 */
public class Game {

    private final Board gameBoard;
    private ArrayList<Shape> gameShapes = new ArrayList<Shape>();

    /**
     * Luo uuden pelin.
     *
     * @param width pelilaudan leveys
     * @param height pelilaudan korkeus
     */
    public Game(int width, int height) {
        this.gameBoard = new Board(width, height);
        this.gameShapes.add(new ShapeL());
    }

    public void run() {
        while (true) {
            gameBoard.updateBoard();
            waitForTime(100);
        }
    }

    /**
     * @param timetowait aika jonka ohjelma odottaa ennen jatkamista
     */
    private void waitForTime(int timetowait) {
        try {
            Thread.sleep(timetowait);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
