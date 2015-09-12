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

/**
 *
 * @author rochet2_2
 */
public class Board {

    private final boolean[][] filledBoardSpaces;
    private final int boardWidth;
    private final int boardHeight;

    /**
     * Luo uuden laudan annetulla koolla
     *
     * @param width laudan leveys, min 1
     * @param height laudan korkeus, min 1
     */
    public Board(int width, int height) {
        width = Math.max(0, width);
        height = Math.max(0, height);

        this.boardWidth = width;
        this.boardHeight = height;
        this.filledBoardSpaces = new boolean[width + 1][height + 1];
    }

    /**
     * Palauttaa laudan leveyden.
     *
     * @return laudan leveys
     */
    public int getBoardWidth() {
        return boardWidth;
    }

    /**
     * Palauttaa laudan korkeuden.
     *
     * @return laudan korkeus
     */
    public int getBoardHeight() {
        return boardHeight;
    }

    /**
     * Kertoo onko koordinaattien määrittämä paikka laudalla
     *
     * @param x X-koordinaatti
     * @param y Y-koordinaatti
     * @return paikka on laudalla
     */
    public boolean isOnBoard(int x, int y) {
        return x >= 0 && y >= 0 && x <= boardWidth && y <= boardHeight;
    }

    /**
     * Kertoo onko pala laudalla
     *
     * @param piece pala
     * @return pala on laudalla
     */
    public boolean isOnBoard(Piece piece) {
        if (piece == null) {
            return false;
        }
        int[][] form = piece.GetShape().getCurrentForm();
        Position position = piece.getPosition();
        for (int y = 0; y < form.length; ++y) {
            for (int x = 0; x < form[y].length; ++x) {
                if (form[y][x] != 0) {
                    if (!isOnBoard(position.getX() + x, position.getY() + y)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Kertoo onko pala vapaassa paikassa
     *
     * @param piece pala
     * @return on vapaassa paikassa
     */
    public boolean isInFreeSpaceOnBoard(Piece piece) {
        if (piece == null || !isOnBoard(piece)) {
            return false;
        }
        int[][] form = piece.GetShape().getCurrentForm();
        Position position = piece.getPosition();
        for (int y = 0; y < form.length; ++y) {
            for (int x = 0; x < form[y].length; ++x) {
                if (form[y][x] != 0) {
                    if (isFilledSpaceOnBoard(position.getX() + x, position.getY() + y)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Palauttaa kopion arraysta joka kertoo kaikki vapaat ja täytetyt kohdat
     * laudalla
     *
     * @return täytetyt paikat
     */
    public boolean[][] getFilledBoardSpaces() {
        return filledBoardSpaces.clone();
    }

    /**
     * Kertoo onko koordinaattien määräämässä paikassa jotakin laudalla
     *
     * @param x X-koordinaatti
     * @param y Y-koordinaatti
     * @return paikka ei ole tyhjä
     */
    public boolean isFilledSpaceOnBoard(int x, int y) {
        if (!isOnBoard(x, y)) {
            return false;
        }
        return filledBoardSpaces[x][y];
    }

    public void fillPieceToBoard(Piece piece) {
        if (piece == null) {
            return;
        }
        int[][] form = piece.GetShape().getCurrentForm();
        Position position = piece.getPosition();
        for (int y = 0; y < form.length; ++y) {
            for (int x = 0; x < form[y].length; ++x) {
                if (form[y][x] != 0) {
                    fillSpaceOnBoard(position.getX() + x, position.getY() + y);
                }
            }
        }
    }

    public void fillSpaceOnBoard(int x, int y) {
        if (!isOnBoard(x, y)) {
            return;
        }
        filledBoardSpaces[x][y] = true;
    }
}
