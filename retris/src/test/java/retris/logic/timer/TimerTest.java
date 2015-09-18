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

/**
 *
 * @author rimi
 */
public class TimerTest {

    public TimerTest() {
    }

    /**
     * testaa ajastimeen asetettua aikaa
     */
    @Test
    public void testGetTimerDelay() {
        long delay = 123L;
        Timer instance = new Timer(delay);
        assertEquals(delay, instance.getTimerDelay());
        instance.updateAndCheckPassed(100);
        assertEquals(delay, instance.getTimerDelay());
    }

    /**
     * testaa ajastimen raukeamista
     */
    @Test
    public void testUpdateAndCheckPassed() {
        long delay = 100L;
        Timer instance = new Timer(delay);
        assertFalse(instance.updateAndCheckPassed(0));
        assertFalse(instance.updateAndCheckPassed(10));
        assertTrue(instance.updateAndCheckPassed(90));
        assertTrue(instance.updateAndCheckPassed(100));
        assertFalse(instance.updateAndCheckPassed(5));
    }

    /**
     * testaa ajastimen käsitystä kuluneesta ajasta
     */
    @Test
    public void testGetTimeCounter() {
        Timer instance = new Timer(100);
        assertEquals(0, instance.getTimeCounter());
        instance.updateAndCheckPassed(10);
        assertEquals(10, instance.getTimeCounter());
        instance.updateAndCheckPassed(934);
        assertEquals(944, instance.getTimeCounter());
        instance.updateAndCheckPassed(-44);
        assertEquals(900, instance.getTimeCounter());
        instance.updateAndCheckPassed(-1000);
        assertEquals(-100, instance.getTimeCounter());
    }

    /**
     * testaa ajastimen ajastettua aikaa
     */
    @Test
    public void testGetTriggerTime() {
        Timer instance = new Timer(100);
        assertEquals(100, instance.getTriggerTime());
        instance.updateAndCheckPassed(10);
        assertEquals(100, instance.getTriggerTime());
    }

}
