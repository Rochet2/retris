/*
 * Copyright (C) 2015 rimi
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
package retris.logic.timer;

/**
 * Ajastin joka pitää kirjaa siitä onko ajastimen aika kulunut.
 *
 * @author rimi
 */
public class Timer {

    private long timeCounter;
    private long triggerTime;
    private final long timerDelay;

    /**
     * Luo uuden ajastimen
     *
     * @param delayMs ajastimen aikaväli millisekunneissa
     */
    public Timer(long delayMs) {
        this.timerDelay = delayMs;
        this.timeCounter = 0;
        this.triggerTime = this.timeCounter + this.timerDelay;
    }

    /**
     * Palauttaa ajastimen aikavälin millisekunteina.
     *
     * @return aikaväli
     */
    public long getTimerDelay() {
        return timerDelay;
    }

    /**
     * Palauttaa onko aikavälin aika kulunut aloitusajasta. Aloittaa myös
     * seuraavan ajastuksen.
     *
     * @param diff kulunut aika millisekunteina viime kutsusta
     * @return ajastimen aika on kulunut
     */
    public boolean updateAndCheckPassed(long diff) {
        timeCounter += diff;
        boolean hasPassed = getTimeCounter() >= getTriggerTime();
        if (hasPassed) {
            triggerTime += getTimerDelay();
        }
        return hasPassed;
    }

    /**
     * Palauttaa ajastimen luonnista kuluneen ajan joka on kertynyt
     * päivityksistä
     *
     * @return kulunut aika millisekunteina
     */
    public long getTimeCounter() {
        return timeCounter;
    }

    /**
     * Palauttaa ajastimen alkuhetkestä kuluneen ajan jolloin ajastin raukeaa.
     *
     * @return ajastettu aika millisekunteina
     */
    public long getTriggerTime() {
        return triggerTime;
    }

}
