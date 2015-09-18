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

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rimi
 */
public class TimeDifferenceCounterTest {

    public TimeDifferenceCounterTest() {
    }

    /**
     * Testaa aikaerolaskurin aikaeroja
     */
    @Test
    public void testGetTimeSinceLastCall() {
        int delta = 10;
        TimeDifferenceCounter instance = new TimeDifferenceCounter();
        long result = instance.getTimeSinceLastCall();
        assertEquals(0, result, delta);
        instance.getTimeSinceLastCall();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimeDifferenceCounterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        long result2 = instance.getTimeSinceLastCall();
        assertEquals(100, result2, delta);
    }

}
