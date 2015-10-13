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

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author rimi
 */
public class TimeDifferenceCounterTest {

    private TimeDifferenceCounter timeDifferenceCounter;

    @Before
    public void setUp() {
        this.timeDifferenceCounter = new TimeDifferenceCounter();
    }

    @Test
    public void testGetTimeSinceLastCallAfterConstruction() {
        timeDifferenceCounter = new TimeDifferenceCounter();
        assertEquals(0, timeDifferenceCounter.getTimeSinceLastCall());
    }

    @Test
    public void testGetTimeSinceLastCallWithInstantSecondCall() {
        timeDifferenceCounter.getTimeSinceLastCall();
        assertEquals(0, timeDifferenceCounter.getTimeSinceLastCall());
    }

    /**
     * Jos testi ei mene läpi, yritä uudelleen, kasvata deltaa tai poista testi.
     */
    @Test
    public void testGetTimeSinceLastCallWithDelayBetweenCalls() {
        long testedTime = 100;
        long delta = 10;
        // reset timer
        timeDifferenceCounter.getTimeSinceLastCall();
        long startTime = System.currentTimeMillis();
        // wait
        while (System.currentTimeMillis() < startTime + testedTime) {
        }

        long actualPassedTime = System.currentTimeMillis() - startTime;
        long passedTimeSinceLastCall = timeDifferenceCounter.getTimeSinceLastCall();

        // see if timer has increased according to supposed tested time
        assertTrue(passedTimeSinceLastCall >= testedTime);
        assertTrue(passedTimeSinceLastCall <= testedTime + delta);

        // see if timer has increased according to actual passed time
        assertTrue(passedTimeSinceLastCall >= actualPassedTime);
        assertTrue(passedTimeSinceLastCall <= actualPassedTime + delta);
    }

}
