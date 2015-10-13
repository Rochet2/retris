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

import retris.logic.piece.Piece;
import retris.logic.gamestate.GameActions;
import retris.logic.gamestate.GameState;
import retris.logic.timer.Updateable;
import retris.logic.timer.Timer;
import java.util.ArrayList;
import java.util.Random;
import retris.logic.shape.Shape;

/**
 * Pitää kirjaa pelin tilasta eri olioiden avulla. Hoitaa olioiden välistä
 * keskustelua.
 *
 * @author rochet2_2
 */
public final class Game implements Updateable {

    /**
     * pelissä esiintyvät muodot
     */
    private final ArrayList<Shape> gameShapes = new ArrayList<>();
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
     * pelin tilaolio
     */
    private final GameState gameState;

    /**
     * Luo uuden pelin.
     *
     * @param gameState pelin tilaolio
     * @param width pelilaudan leveys
     * @param height pelilaudan korkeus
     * @param dropSpeedMs palan putoamisvauhti millisekunneissa
     */
    public Game(GameState gameState, int width, int height, long dropSpeedMs) {
        this.gameState = gameState;
        this.gameBoard = new Board(width, height);
        this.droppingPiece = new Piece();
        this.pieceDropTimer = new Timer(Math.max(0, dropSpeedMs));
    }

    /**
     * Päivittää pelin asetelman
     *
     * @param diff aika viime päivityksestä millisekunteina
     */
    @Override
    public void update(long diff) {
        processActions();
        if (pieceDropTimer.updateAndCheckPassed(diff)) {
            movePieceDown();
        }
        gameState.setBoardState(getCurrentBoardStateCopy());
    }

    /**
     * Asettaa uuden muodon palalle ja asettaa sen laudan keskelle ylös
     */
    public void resetPiece() {
        Shape shape = selectRandomGameShape();
        droppingPiece.setShape(shape);
        droppingPiece.relocate((int) Math.floor(gameBoard.getBoardWidth() / 2.0 - shape.getMaxWidth() / 2.0), 0);
        endGameIfShould();
    }

    /**
     * Tarkistaa loppuiko peli ja lopettaa jos loppui
     */
    public void endGameIfShould() {
        if (!gameBoard.isInFreeSpaceOnBoard(droppingPiece)) {
            droppingPiece.setShape(new Shape());
            droppingPiece.setY(-1);
            gameState.setHasEnded(true);
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
     * Palauttaa koko pelin tämänhetkisen tilan arraynä laudasta kaikkine
     * paloineen
     *
     * @return pelilaudan tila arraynä
     */
    public int[][] getCurrentBoardStateCopy() {
        int[][] array = ArrayUtil.cloneArray(gameBoard.getBoardState());
        droppingPiece.fillFormToArray(array);
        return array;
    }

    /**
     * Liikuttaa palaa pelissä ylös jos pystyy.
     *
     * @return palaa liikutettiin
     */
    public boolean movePieceUp() {
        droppingPiece.setY(droppingPiece.getY() - 1);
        if (!gameBoard.isInFreeSpaceOnBoard(droppingPiece)) {
            droppingPiece.setY(droppingPiece.getY() + 1);
            return false;
        }
        return true;
    }

    /**
     * Liikuttaa palaa pelissä alas jos pystyy. Jos ei voi, pala "laskeutuu".
     *
     * @return palaa liikutettiin
     */
    public boolean movePieceDown() {
        droppingPiece.setY(droppingPiece.getY() + 1);
        if (!gameBoard.isInFreeSpaceOnBoard(droppingPiece)) {
            droppingPiece.setY(droppingPiece.getY() - 1);
            gameBoard.fillPieceToBoard(droppingPiece);
            rewardScore(gameBoard.removeAndReturnFilledRows());
            resetPiece();
            return false;
        }
        return true;
    }

    public void rewardScore(int removedRows) {
        if (removedRows > 0) {
            gameState.modifyScore((long) (Math.pow(2, removedRows) * 50));
        }
    }

    /**
     * Liikuttaa palaa pelissä vasemmalle jos pystyy.
     *
     * @return palaa liikutettiin
     */
    public boolean movePieceLeft() {
        droppingPiece.setX(droppingPiece.getX() - 1);
        if (!gameBoard.isInFreeSpaceOnBoard(droppingPiece)) {
            droppingPiece.setX(droppingPiece.getX() + 1);
            return false;
        }
        return true;
    }

    /**
     * Liikuttaa palaa pelissä oikealle jos pystyy.
     *
     * @return palaa liikutettiin
     */
    public boolean movePieceRight() {
        droppingPiece.setX(droppingPiece.getX() + 1);
        if (!gameBoard.isInFreeSpaceOnBoard(droppingPiece)) {
            droppingPiece.setX(droppingPiece.getX() - 1);
            return false;
        }
        return true;
    }

    /**
     * Kääntää palaa jos voi.
     *
     * @return käännettiin palaa
     */
    public boolean rotatePiece() {
        Shape shape = droppingPiece.GetShape();
        shape.setShapeFormIndex(shape.getShapeFormIndex() + 1);
        if (!gameBoard.isInFreeSpaceOnBoard(droppingPiece)) {
            shape.setShapeFormIndex(shape.getShapeFormIndex() - 1);
            return false;
        }
        return true;
    }

    /**
     * Palauttaa palan tippumisajastimen
     *
     * @return the pieceDropTimer
     */
    public Timer getPieceDropTimer() {
        return pieceDropTimer;
    }

    /**
     * Alustaa pelin ja sen tilan
     */
    public void initializeGameState() {
        resetPiece();
        gameState.setBoardState(getCurrentBoardStateCopy());
    }

    /**
     * Suorittaa pelaajan tekemät toimet
     */
    public void processActions() {
        ArrayList<GameActions> actions = gameState.getActionQueue();
        for (GameActions action : actions) {
            switch (action) {
                case MOVE_LEFT:
                    movePieceLeft();
                    break;
                case MOVE_RIGHT:
                    movePieceRight();
                    break;
                case MOVE_UP:
                    movePieceUp();
                    break;
                case MOVE_DOWN:
                    movePieceDown();
                    break;
                case MOVE_ROTATE_LEFT:
                    rotatePiece();
                    break;
            }
        }
    }

}
