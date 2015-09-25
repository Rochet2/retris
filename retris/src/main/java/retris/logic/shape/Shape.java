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
package retris.logic.shape;

/**
 * Tämä luokka toimii pohjana eri tetromino palasille. Aliluokat, eli eri
 * palasten luokat, kutsuvat metodia setShapeFormRotations konstruktorissaan
 * asettaakseen palaselle jonkin muodon.
 *
 * @author rochet2_2
 */
public class Shape {

    private int shapeFormIndex;
    private int[][][] shapeFormRotations = {
        {{1}}
    };

    /**
     * Luo kopion annetusta muodosta
     *
     * @param shape kopio muodosta
     */
    public Shape(Shape shape) {
        if (shape != null) {
            this.shapeFormRotations = cloneArray3D(shape.shapeFormRotations);
        }
    }

    /**
     * Luo uuden muodon perusmuodolla
     */
    public Shape() {
    }

    /**
     * Palauttaa muodon eri käännösvaiheet
     *
     * @return käännösvaiheet
     */
    public int[][][] getShapeFormRotations() {
        return shapeFormRotations;
    }

    /**
     * Palauttaa muodon maksimileveyden
     *
     * @return muodon leveys maksimissaan
     */
    public int getMaxWidth() {
        int width = 0;
        for (int[][] form : shapeFormRotations) {
            for (int[] row : form) {
                width = Math.max(width, row.length);
            }
        }
        return width;
    }

    /**
     * Palauttaa muodon maksimikorkeuden
     *
     * @return muodon korkeus maksimissaan
     */
    public int getMaxHeight() {
        int height = 0;
        for (int[][] form : shapeFormRotations) {
            height = Math.max(height, form.length);
        }
        return height;
    }

    /**
     * Asettaa muodon ulkonäön eri käännösvaiheille.
     *
     * @param shapeFormRotations muotoa      <pre>
     * {
     *     {
     *         {0,0,1},
     *         {1,1,1},
     *         {0,0,0}
     *     },
     *     {
     *         {1,1,0},
     *         {0,1,0},
     *         {0,1,0}
     *     },
     *     {
     *         {0,0,0},
     *         {1,1,1},
     *         {1,0,0}
     *     },
     *     {
     *         {0,1,0},
     *         {0,1,0},
     *         {0,1,1}
     *     }
     * }
     * </pre>
     */
    public void setShapeFormRotations(int[][][] shapeFormRotations) {
        if (!arrayDimensionLenghtsAboveZero(shapeFormRotations)
                || !formsAreVisible(shapeFormRotations)) {
            return;
        }
        int[][][] forms = cloneArray3D(shapeFormRotations);
        this.shapeFormRotations = forms;
    }

    /**
     * Kopioi kolmiulotteisen arrayn
     *
     * @param array 3D array
     * @return kopio
     */
    public final int[][][] cloneArray3D(int[][][] array) {
        int[][][] forms = array.clone();
        for (int i = 0; i < array.length; ++i) {
            forms[i] = array[i].clone();
            for (int j = 0; j < array[i].length; ++j) {
                forms[i][j] = array[i][j].clone();
            }
        }
        return forms;
    }

    /**
     * Tarkistaa että joka muodon käännösvaihe sisältää ainakin yhden true
     * arvon, eli ettei mikään vaihe ole näkymätön
     *
     * @param formArray array jossa on muodot
     * @return kaikilla käännösvaiheet ovat näkyviä
     */
    private boolean formsAreVisible(int[][][] formArray) {
        for (int[][] formRotation : formArray) {
            if (!hasOneNotZero(formRotation)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Palauttaa true jos syötteessä olevien tasojen pituudet ovat suurempia tai
     * yhtäsuuria yhden kanssa.
     *
     * @param array
     * @return tasojen pituudet nollaa isompia
     */
    public boolean arrayDimensionLenghtsAboveZero(int[][][] array) {
        return array != null && array.length >= 1
                && array[0].length >= 1 && array[0][0].length >= 1;
    }

    /**
     * Palauttaa true jos syöte sisältää yhden nollasta poikkeavan arvon.
     *
     * @param array
     * @return sisältää ainakin yhden nollasta poikkeavan arvon
     */
    private boolean hasOneNotZero(int[][] array) {
        for (int[] dimension1 : array) {
            for (int value : dimension1) {
                if (value != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Asettaa palan käännösvaiheen.
     *
     * @param formIndex käännösvaiheen numero - skaalataan vaiheiden määrään
     */
    public void setShapeFormIndex(int formIndex) {
        int shapecount = shapeFormRotations.length;
        formIndex = formIndex % shapecount;
        if (formIndex < 0) {
            formIndex += shapecount;
        }
        this.shapeFormIndex = formIndex;
    }

    /**
     * Palauttaa palan käännösvaiheen numeron
     *
     * @return käännösvaiheen numero
     */
    public int getShapeFormIndex() {
        return shapeFormIndex;
    }

    /**
     * Palauttaa tämänhetkisen muodon
     *
     * @return muoto arraynä
     */
    public int[][] getCurrentForm() {
        return shapeFormRotations[shapeFormIndex];
    }

}
