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
public class TimerTest {

    private final long delay;
    private Timer timer;

    public TimerTest() {
        this.delay = 100;
    }

    @Before
    public void setUp() {
        timer = new Timer(delay);
    }

    @Test
    public void testTimerConstructorSetsCorrectDelay() {
        assertEquals(delay, timer.getTimerDelay());
        timer = new Timer(123);
        assertEquals(123, timer.getTimerDelay());
    }

    @Test
    public void testTimerConstructorSetsCorrectTimeCounter() {
        assertEquals(0, timer.getTimeCounter());
    }

    @Test
    public void testTimerConstructorSetsCorrectTriggerTime() {
        assertEquals(delay, timer.getTriggerTime());
    }

    @Test
    public void testTimerPassedStateAfterInitialization() {
        assertFalse(timer.updateAndCheckPassed(0));
    }

    @Test
    public void testTimerUpdateAndCheckPassedPositiveCornerCases() {
        assertFalse(timer.updateAndCheckPassed(0));
        assertFalse(timer.updateAndCheckPassed(99));
        assertFalse(timer.updateAndCheckPassed(0));
        assertTrue(timer.updateAndCheckPassed(1));
        assertFalse(timer.updateAndCheckPassed(0));
    }

    @Test
    public void testTimerUpdateAndCheckPassedNegative() {
        assertFalse(timer.updateAndCheckPassed(-99));
        assertFalse(timer.updateAndCheckPassed(0));
        assertFalse(timer.updateAndCheckPassed(-1));
        assertFalse(timer.updateAndCheckPassed(-10000));
    }

    @Test
    public void testTimerUpdateAndCheckPassedNegativeAndBackToPositive() {
        assertFalse(timer.updateAndCheckPassed(-10000));
        assertFalse(timer.updateAndCheckPassed(10000));
        assertFalse(timer.updateAndCheckPassed(99));
        assertTrue(timer.updateAndCheckPassed(1));
        assertFalse(timer.updateAndCheckPassed(0));
    }

    @Test
    public void testTimeCounterTimePassingWithCorrectTimeCounterPositive() {
        assertEquals(0, timer.getTimeCounter());
        timer.updateAndCheckPassed(10);
        assertEquals(10, timer.getTimeCounter());
        timer.updateAndCheckPassed(1000);
        assertEquals(1010, timer.getTimeCounter());
    }

    @Test
    public void testTimeCounterTimePassingWithCorrectTimeCounterNegative() {
        timer.updateAndCheckPassed(-50);
        assertEquals(-50, timer.getTimeCounter());
        timer.updateAndCheckPassed(-1000);
        assertEquals(-1050, timer.getTimeCounter());
    }

    @Test
    public void testTimeCounterTimePassingWithCorrectTimeCounterNegativeToPositive() {
        timer.updateAndCheckPassed(-50);
        assertEquals(-50, timer.getTimeCounter());
        timer.updateAndCheckPassed(100);
        assertEquals(50, timer.getTimeCounter());
    }

    @Test
    public void testTriggerTimeNotChanging() {
        assertEquals(100, timer.getTriggerTime());
        timer.updateAndCheckPassed(10);
        assertEquals(100, timer.getTriggerTime());
    }

}
