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
import retris.gui.GameUI;
import retris.logic.shape.Shape;
import retris.logic.timer.TimeDifferenceCounter;

/**
 * Suorittaa peliä.
 *
 * @author rochet2_2
 */
public final class Game {

    /**
     * pelissä esiintyvät muodot
     */
    private final ArrayList<Shape> gameShapes = new ArrayList<Shape>();
    /**
     * pelilauta
     */
    private final Board gameBoard;
    /**
     * pelin pala joka tippuu
     */
    private final Piece droppingPiece;
    /**
     * satunnaislukugeneraattori
     */
    private final Random random = new Random();
    /**
     * tippuvan palan tippumisajastin
     */
    private final Timer pieceDropTimer;
    /**
     * pelin tila: käynnissä tai lopeta
     */
    private boolean running = true;
    /**
     * pelin käyttöliittymä jolle annetaan pelin tila sen päivittyessä
     */
    private final GameUI ui;

    /**
     * Luo uuden pelin.
     *
     * @param ui käyttöliittymä tai null
     * @param width pelilaudan leveys
     * @param height pelilaudan korkeus
     * @param dropSpeedMs palan putoamisvauhti millisekunneissa
     */
    public Game(GameUI ui, int width, int height, long dropSpeedMs) {
        this.gameBoard = new Board(width, height);
        this.droppingPiece = new Piece();
        this.pieceDropTimer = new Timer(Math.max(0, dropSpeedMs));
        this.ui = ui;
        if (ui != null) {
            ui.setGame(this);
        }
    }

    /**
     * Kertoo pitäisikö pelin vielä jatkua
     *
     * @return jatka
     */
    public synchronized boolean isRunning() {
        return running;
    }

    /**
     * Kertoo pelille että sen tulisi pysäyttää peli
     */
    public synchronized void stopRunning() {
        this.running = false;
    }

    /**
     * Päivittää pelin asetelman
     *
     * @param diff aika viime päivityksestä millisekunteina
     */
    public synchronized void update(long diff) {
        if (pieceDropTimer.updateAndCheckPassed(diff)) {
            movePieceDown();
        }
        if (ui != null) {
            ui.updateUI(getCurrentBoardState());
        }
    }

    /**
     * Asettaa uuden muodon palalle ja asettaa sen laudan keskelle ylös
     */
    public synchronized void resetPiece() {
        Shape shape = selectRandomGameShape();
        droppingPiece.setShape(shape);
        droppingPiece.relocate((int) Math.floor(gameBoard.getBoardWidth() / 2.0 - shape.getMaxWidth() / 2.0), 0);
        endGameIfShould();
    }

    /**
     * Tarkistaa loppuiko peli ja lopettaa jos loppui
     */
    public synchronized void endGameIfShould() {
        if (!gameBoard.isInFreeSpaceOnBoard(droppingPiece)) {
            droppingPiece.setShape(new Shape());
            droppingPiece.getPosition().setY(-1);
            stopRunning();
        }
    }

    /**
     * Lisää muodon peliin
     *
     * @param shape muoto
     */
    public synchronized void addShapeToGame(Shape shape) {
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
    public synchronized Shape selectRandomGameShape() {
        if (gameShapes.isEmpty()) {
            return new Shape();
        }
        return new Shape(gameShapes.get(random.nextInt(gameShapes.size())));
    }

    /**
     * Suorittaa peliä käyttöliittymineen.
     */
    public void runGame() {
        resetPiece();
        TimeDifferenceCounter diffTime = new TimeDifferenceCounter();
        while (isRunning()) {
            long diff = diffTime.getTimeSinceLastCall();
            if (diff > 0) {
                update(diff);
            }
        }
    }

    /**
     * Palauttaa koko pelin tämänhetkisen tilan arraynä laudasta kaikkine
     * paloineen
     *
     * @return pelilaudan tila arraynä
     */
    private synchronized int[][] getCurrentBoardState() {
        int[][] array = gameBoard.getBoardStateCopy();
        droppingPiece.fillFormToArray(array);
        return array;
    }

    /**
     * Liikuttaa palaa pelissä ylös jos pystyy.
     *
     * @return palaa liikutettiin
     */
    public synchronized boolean movePieceUp() {
        Position pos = droppingPiece.getPosition();
        pos.setY(pos.getY() - 1);
        if (!gameBoard.isInFreeSpaceOnBoard(droppingPiece)) {
            pos.setY(pos.getY() + 1);
            return false;
        }
        return true;
    }

    /**
     * Liikuttaa palaa pelissä alas jos pystyy. Jos ei voi, pala "laskeutuu".
     *
     * @return palaa liikutettiin
     */
    public synchronized boolean movePieceDown() {
        Position pos = droppingPiece.getPosition();
        pos.setY(pos.getY() + 1);
        if (!gameBoard.isInFreeSpaceOnBoard(droppingPiece)) {
            pos.setY(pos.getY() - 1);
            gameBoard.fillPieceToBoard(droppingPiece);
            resetPiece();
            return false;
        }
        return true;
    }

    /**
     * Liikuttaa palaa pelissä vasemmalle jos pystyy.
     *
     * @return palaa liikutettiin
     */
    public synchronized boolean movePieceLeft() {
        Position pos = droppingPiece.getPosition();
        pos.setX(pos.getX() - 1);
        if (!gameBoard.isInFreeSpaceOnBoard(droppingPiece)) {
            pos.setX(pos.getX() + 1);
            return false;
        }
        return true;
    }

    /**
     * Liikuttaa palaa pelissä oikealle jos pystyy.
     *
     * @return palaa liikutettiin
     */
    public synchronized boolean movePieceRight() {
        Position pos = droppingPiece.getPosition();
        pos.setX(pos.getX() + 1);
        if (!gameBoard.isInFreeSpaceOnBoard(droppingPiece)) {
            pos.setX(pos.getX() - 1);
            return false;
        }
        return true;
    }

    /**
     * Kääntää palaa jos voi.
     *
     * @return käännettiin palaa
     */
    public synchronized boolean rotatePiece() {
        Shape shape = droppingPiece.GetShape();
        shape.setShapeFormIndex(shape.getShapeFormIndex() + 1);
        if (!gameBoard.isInFreeSpaceOnBoard(droppingPiece)) {
            shape.setShapeFormIndex(shape.getShapeFormIndex() - 1);
            return false;
        }
        return true;
    }

    /**
     * Palauttaa kopion pelilaudan tilasta
     *
     * @return pelilaudan tila
     */
    public synchronized int[][] getGameStateCopy() {
        return getCurrentBoardState();
    }

    /**
     * Palauttaa palan tippumisajastimen
     *
     * @return the pieceDropTimer
     */
    public synchronized Timer getPieceDropTimer() {
        return pieceDropTimer;
    }

}
