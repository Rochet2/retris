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
package retris.logic.gamestate;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import retris.logic.ArrayUtil;

/**
 * Pelin tilaolio joka pitää kirjaa pelin tilasta. Olio on täysin synkronoitu,
 * eli sitä voi käyttää miten vain eri säikeissä
 *
 * @author rochet2_2
 */
public class GameState {

    /**
     * Pelilaudan tilanne
     */
    private int[][] boardState = {{}};
    /**
     * Nykyiset pisteet
     */
    private final AtomicLong score = new AtomicLong();
    /**
     * Peli on loppunut
     */
    private final AtomicBoolean hasEnded = new AtomicBoolean(false);
    /**
     * Pelin tila on muuttunut
     */
    private final AtomicBoolean hasChanged = new AtomicBoolean(false);
    /**
     * Pelin tilanteelle annetut syötteet
     */
    private ArrayList<GameActions> actionQueue = new ArrayList<>();

    /**
     * Asettaa pelin tilan loppuneeksi
     *
     * @param ended peli on loppunut
     */
    public void setHasEnded(boolean ended) {
        hasEnded.set(ended);
    }

    /**
     * Muokkaa pisteitä ja asettaa pelitilan muuttuneeksi
     *
     * @param amount määrä jolla pisteitä muokataan - voi olla negatiivinen
     */
    public void modifyScore(long amount) {
        this.score.addAndGet(amount);
        hasChanged.set(true);
    }

    /**
     * Asettaa uuden pelilaudan tilan ja asettaa pelitilan muuttuneeksi
     *
     * @param boardState pelilaudan uusi tila
     */
    public synchronized void setBoardState(int[][] boardState) {
        if (ArrayUtil.hasNullValue(boardState)) {
            return;
        }
        this.boardState = ArrayUtil.cloneArray(boardState);
        hasChanged.set(true);
    }

    /**
     * Kertoo onko peli loppunut
     *
     * @return peli on loppunut
     */
    public boolean hasEnded() {
        return hasEnded.get();
    }

    /**
     * Palauttaa nykyisen pistemäärän
     *
     * @return pisteet
     */
    public long getScore() {
        return score.get();
    }

    /**
     * Palauttaa pelitilan muuttumisesta kertovan olion
     *
     * @return olio
     */
    public AtomicBoolean getHasChanged() {
        return hasChanged;
    }

    /**
     * Palauttaa pelilaudan tilanteen kopion
     *
     * @return pelilaudan tilanne
     */
    public synchronized int[][] getBoardState() {
        return ArrayUtil.cloneArray(boardState);
    }

    /**
     * Lisää uuden pelaajan tekemän toiminnan
     *
     * @param action toiminta
     */
    public synchronized void addAction(GameActions action) {
        actionQueue.add(action);
    }

    /**
     * Palauttaa listan tehdyistä toiminnoista ja tyhjentää pelitilan
     * toimintalistan.
     *
     * @return toiminnot
     */
    public synchronized ArrayList<GameActions> getActionQueue() {
        ArrayList<GameActions> result = actionQueue;
        actionQueue = new ArrayList<>();
        return result;
    }

}
