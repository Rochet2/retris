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

    private boolean[][][] shapeFormRotations = {
        {{true}}
    };

    /**
     * Palauttaa muodon eri käännösvaiheet
     *
     * @return
     */
    public boolean[][][] getShapeFormRotations() {
        return shapeFormRotations;
    }

    /**
     * Palauttaa muodon maksimileveyden
     *
     * @return muodon leveys maksimissaan
     */
    public int getMaxWidth() {
        int width = 0;
        for (boolean[][] form : shapeFormRotations) {
            for (boolean[] row : form) {
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
        for (boolean[][] form : shapeFormRotations) {
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
     *         {0,0,0},
     *     },
     *     {
     *         {1,1,0},
     *         {0,1,0},
     *         {0,1,0},
     *     },
     *     {
     *         {0,0,0},
     *         {1,1,1},
     *         {1,0,0},
     *     },
     *     {
     *         {0,1,0},
     *         {0,1,0},
     *         {0,1,1},
     *     },
     * }
     * </pre>
     */
    public void setShapeFormRotations(int[][][] shapeFormRotations) {
        if (!arrayDimensionLenghtsAboveZero(shapeFormRotations)) {
            return;
        }
        boolean[][][] boolArray = integerArrayToBooleanArray(shapeFormRotations);
        if (!formsHaveOneTrue(boolArray)) {
            return;
        }
        this.shapeFormRotations = boolArray;
    }

    /**
     * Tarkistaa että joka muodon käännösvaihe sisältää ainakin yhden true
     * arvon, eli ettei mikään vaihe ole näkymätön
     *
     * @param formArray
     * @return kaikilla käännösvaiheet ovat näkyviä
     */
    private boolean formsHaveOneTrue(boolean[][][] formArray) {
        for (boolean[][] formRotation : formArray) {
            if (!hasOneTrue(formRotation)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Asettaa muodon ulkonäön eri käännösvaiheille.
     *
     * @param shapeFormRotations muotoa      <pre>
     * {
     *     {
     *         {false,false,true },
     *         {true ,true ,true },
     *         {false,false,false},
     *     },
     *     {
     *         {true ,true ,false},
     *         {false,true ,false},
     *         {false,true ,false},
     *     },
     *     {
     *         {false,false,false},
     *         {true ,true ,true },
     *         {true ,false,false},
     *     },
     *     {
     *         {false,true ,false},
     *         {false,true ,false},
     *         {false,true ,true },
     *     },
     * }
     * </pre>
     */
    public void setShapeFormRotations(boolean[][][] shapeFormRotations) {
        if (!arrayDimensionLengthsAboveZero(shapeFormRotations)) {
            return;
        }
        if (!formsHaveOneTrue(shapeFormRotations)) {
            return;
        }
        this.shapeFormRotations = shapeFormRotations;
    }

    /**
     * Palauttaa true jos syötteessä olevien tasojen pituudet ovat suurempia tai
     * yhtäsuuria yhden kanssa.
     *
     * @param integerArray
     * @return tasojen pituudet nollaa isompia
     */
    private boolean arrayDimensionLenghtsAboveZero(int[][][] integerArray) {
        if (integerArray == null) {
            return false;
        }
        if (integerArray.length < 1) {
            return false;
        }
        if (integerArray[0].length < 1) {
            return false;
        }
        if (integerArray[0][0].length < 1) {
            return false;
        }
        return true;
    }

    /**
     * Palauttaa true jos syötteessä olevien tasojen pituudet ovat suurempia tai
     * yhtäsuuria yhden kanssa.
     *
     * @param integerArray
     * @return tasojen pituudet nollaa isompia
     */
    private boolean arrayDimensionLengthsAboveZero(boolean[][][] integerArray) {
        if (integerArray == null) {
            return false;
        }
        if (integerArray.length < 1) {
            return false;
        }
        if (integerArray[0].length < 1) {
            return false;
        }
        if (integerArray[0][0].length < 1) {
            return false;
        }
        return true;
    }

    /**
     * Palauttaa uuden arrayn jossa syötteen arvot ovat totuutensa mukaan
     * muutettu booleaneiksi.
     *
     * @param intArray
     * @return
     */
    private boolean[][][] integerArrayToBooleanArray(int[][][] intArray) {
        boolean[][][] boolArray = new boolean[intArray.length][intArray[0].length][intArray[0][0].length];
        for (int i = 0; i < intArray.length; ++i) {
            for (int j = 0; j < intArray[i].length; ++j) {
                for (int k = 0; k < intArray[i][j].length; ++k) {
                    boolArray[i][j][k] = intArray[i][j][k] != 0;
                }
            }
        }
        return boolArray;
    }

    /**
     * Palauttaa true jos syöte sisältää yhden true arvon.
     *
     * @param boolArray
     * @return sisältää ainakin yhden tosi arvon
     */
    private boolean hasOneTrue(boolean[][] boolArray) {
        for (boolean[] dimension1 : boolArray) {
            for (boolean value : dimension1) {
                if (value) {
                    return true;
                }
            }
        }
        return false;
    }
}
