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

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author rochet2_2
 */
public class ShapeFactory {
    private static final Map<Shape, int[][][]> shapeForms;
    static {
        EnumMap<Shape, int[][][]> tempMap = new EnumMap<Shape, int[][][]>(Shape.class);
        tempMap.put(Shape.TETROMINO_L, new int[][][] {
            {
                {0,0,1},
                {1,1,1},
                {0,0,0},
            },
            {
                {1,1,0},
                {0,1,0},
                {0,1,0},
            },
            {
                {0,0,0},
                {1,1,1},
                {1,0,0},
            },
            {
                {0,1,0},
                {0,1,0},
                {0,1,1},
            },
        });
        tempMap.put(Shape.TETROMINO_J, new int[][][] {
            {
                {0,0,0},
                {1,1,1},
                {0,0,1},
            },
            {
                {0,1,1},
                {0,1,0},
                {0,1,0},
            },
            {
                {1,0,0},
                {1,1,1},
                {0,0,0},
            },
            {
                {0,1,0},
                {0,1,0},
                {1,1,0},
            },
        });
        tempMap.put(Shape.TETROMINO_T, new int[][][] {
            {
                {1},
            },
        });
        tempMap.put(Shape.TETROMINO_O, new int[][][] {
            {
                {1},
            },
        });
        tempMap.put(Shape.TETROMINO_I, new int[][][] {
            {
                {1},
            },
        });
        tempMap.put(Shape.TETROMINO_S, new int[][][] {
            {
                {1},
            },
        });
        tempMap.put(Shape.TETROMINO_Z, new int[][][] {
            {
                {1},
            },
        });
        
        checkIntegrity(tempMap);
        shapeForms = Collections.unmodifiableMap(tempMap);
    }

    private static void checkIntegrity(Map<Shape, int[][][]> tempMap) throws ExceptionInInitializerError {
        for (Shape shape : Shape.getShapes()) {
            if (!tempMap.containsKey(shape)) {
                throw new ExceptionInInitializerError("Missing form definition(s) from shape " + shape);
            }
            for (int[][] shapeForm : tempMap.get(shape)) {
                boolean formHasForm = false;
                for (int[] row : shapeForm) {
                    for (int value : row) {
                        if (value != 0 && value != 1) {
                            throw new ExceptionInInitializerError("Invalid value (" + value + ") in a form for shape " + shape);
                        }
                        if (!formHasForm && value == 1) {
                            formHasForm = true;
                        }
                    }
                }
                if (!formHasForm) {
                    throw new ExceptionInInitializerError("A Form is completely invisible for shape " + shape);
                }
            }
        }
    }

    public static int[][][] getShape(Shape shape) {
        return shapeForms.get(shape);
    }

    public static Map<Shape, int[][][]> getShapeForms() {
        return shapeForms;
    }

    /**
     * @return palan muodon leveys tällä hetkellä
     */
    public static int getMaxWidth(Shape shape) {
        int[][][] shapeForms = getShape(shape);
        int width = 0;
        for (int[][] form : shapeForms) {
            for (int[] row : form) {
                width = Math.max(width, row.length);
            }
        }
        return width;
    }

    /**
     * @return palan muodon korkeus tällä hetkellä
     */
    public static int getMaxHeight(Shape shape) {
        int[][][] shapeForms = getShape(shape);
        int height = 0;
        for (int[][] form : shapeForms) {
            height = Math.max(height, form.length);
        }
        return height;
    }
}
