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

import retris.logic.timer.Timer;
import java.util.ArrayList;
import java.util.Random;
import retris.gui.GameWindow;
import retris.logic.shape.Shape;
import retris.logic.timer.TimeDifferenceCounter;

/**
 *
 * @author rochet2_2
 */
public final class Game {

    private final ArrayList<Shape> gameShapes = new ArrayList<Shape>();
    private final Board gameBoard;
    private final Piece droppingPiece;
    private final Random random = new Random();
    private final Timer pieceDropTimer;
    private boolean running = true;

    /**
     * Luo uuden pelin.
     *
     * @param width pelilaudan leveys
     * @param height pelilaudan korkeus
     * @param dropSpeedMs palan putoamisvauhti millisekunneissa
     */
    public Game(int width, int height, long dropSpeedMs) {
        this.gameBoard = new Board(width, height);
        this.droppingPiece = new Piece();
        this.pieceDropTimer = new Timer(Math.max(0, dropSpeedMs));
        resetPiece();
    }

    public boolean isRunning() {
        return running;
    }

    public void stopRunning() {
        this.running = false;
    }

    /**
     * Päivittää pelin asetelman
     *
     * @param diff aika viime päivityksestä millisekunteina
     */
    public void update(long diff) {
        if (pieceDropTimer.updateAndCheckPassed(diff)) {
            movePieceDown();
        }
    }

    /**
     * Siirtää palaa alas yhden pykälän ja tarkistaa onko se pohjalla. Jos pala
     * on pohjalla niin pala "täytetään" pohjaan ja resetoidaan.
     */
    public void movePieceDown() {
        System.out.println("Moving down");
        droppingPiece.moveDown();
        if (!gameBoard.isInFreeSpaceOnBoard(droppingPiece)) {
            System.out.println("Hit bottom at " + droppingPiece.getPosition().getX() + " " + droppingPiece.getPosition().getY());

            droppingPiece.moveUp();
            getGameBoard().fillPieceToBoard(droppingPiece);
            resetPiece();
        }
    }

    /**
     * Asettaa uuden muodon palalle ja asettaa sen laudan keskelle ylös
     */
    public void resetPiece() {
        System.out.println("Resetting piece");
        Shape shape = selectRandomGameShape();
        droppingPiece.setShape(shape);
        droppingPiece.relocate((int)Math.floor(getGameBoard().getBoardWidth() / 2.0 - shape.getMaxWidth() / 2.0), 0);
        if (!gameBoard.isInFreeSpaceOnBoard(droppingPiece)) {
            stopRunning();
        }
    }

    /**
     * Lisää muodon peliin
     *
     * @param shape muoto
     */
    public void addShapeToGame(Shape shape) {
        if (shape == null) {
            return;
        }
        gameShapes.add(new Shape(shape));
    }

    /**
     * Valikoi satunnaisen pelin muodoista tai palauttaa perusmuodon
     *
     * @return muoto
     */
    public Shape selectRandomGameShape() {
        if (gameShapes.isEmpty()) {
            return new Shape();
        }
        return new Shape(gameShapes.get(random.nextInt(gameShapes.size())));
    }

    /**
     * Palauttaa pelilaudan
     *
     * @return pelilauta
     */
    public Board getGameBoard() {
        return gameBoard;
    }

    /**
     * Palauttaa palan joka tippuu
     *
     * @return tippuva pala
     */
    public Piece getDroppingPiece() {
        return droppingPiece;
    }

    /**
     * Suorittaa peliä käyttöliittymineen.
     */
    public void runGame() {
        GameWindow gui = new GameWindow(this);
        TimeDifferenceCounter diffTime = new TimeDifferenceCounter();
        resetPiece();
        while (isRunning()) {
            long diff = diffTime.getTimeSinceLastCall();
            if (diff <= 0)
                continue;
            update(diff);
            gui.updateGUI(this);
        }
    }

}
