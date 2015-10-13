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

import java.util.Arrays;

/**
 *
 * @author rochet2_2
 */
public class ArrayUtil {

    /**
     * Kopioi annetun arrayn
     *
     * @param array kopioitava
     * @return kopio
     */
    public static int[][] cloneArray(int[][] array) {
        if (array == null) {
            return null;
        }
        final int[][] result = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            result[i] = Arrays.copyOf(array[i], array[i].length);
        }
        return result;
    }

    /**
     * Kopioi annetun arrayn
     *
     * @param array kopioitava
     * @return kopio
     */
    public static int[][][] cloneArray(int[][][] array) {
        if (array == null) {
            return null;
        }
        final int[][][] result = new int[array.length][][];
        for (int i = 0; i < array.length; i++) {
            result[i] = cloneArray(array[i]);
        }
        return result;
    }

    /**
     * Kertoo onko arrayssä null arvoja
     *
     * @param array array
     * @return sisältää null arvoja
     */
    public static boolean hasNullValue(int[][] array) {
        if (array == null) {
            return true;
        }
        for (int[] subArr : array) {
            if (subArr == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Kertoo onko arrayssä null arvoja
     *
     * @param array array
     * @return sisältää null arvoja
     */
    public static boolean hasNullValue(int[][][] array) {
        if (array == null) {
            return true;
        }
        for (int[][] subArr : array) {
            if (subArr == null || hasNullValue(subArr)) {
                return true;
            }
        }
        return false;
    }
}
