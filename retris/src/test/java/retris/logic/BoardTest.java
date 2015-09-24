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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rochet2_2
 */
public class BoardTest {

    public BoardTest() {
    }

    @Test
    public void testIsOnBoardCoordinatesWithValidCoordinates() {
        int width = 10;
        int height = 16;
        Board board = new Board(width, height);
        assertTrue(board.isOnBoard(0, 0));
        assertTrue(board.isOnBoard(1, 1));
        assertTrue(board.isOnBoard(width - 1, height - 1));
        assertTrue(board.isOnBoard(width - 2, height - 2));
        assertTrue(board.isOnBoard(width / 2, height / 2));
    }

    @Test
    public void testIsOnBoardCoordinatesWithInvalidCoordinates() {
        int width = 10;
        int height = 16;
        Board board = new Board(width, height);
        assertFalse(board.isOnBoard(-1, -1));
        assertFalse(board.isOnBoard(width, height));
        assertFalse(board.isOnBoard(-width, -height));
    }

    @Test
    public void testIsOnBoardPieceWithValidCoordinates() {
        int width = 10;
        int height = 16;
        Board board = new Board(width, height);
        Piece piece = new Piece();
        assertTrue(board.isOnBoard(piece));
        piece.relocate(0, 0);
        assertTrue(board.isOnBoard(piece));
        piece.relocate(1, 1);
        assertTrue(board.isOnBoard(piece));
        piece.relocate(width - 1, height - 1);
        assertTrue(board.isOnBoard(piece));
        piece.relocate(width - 2, height - 2);
        assertTrue(board.isOnBoard(piece));
        piece.relocate(width / 2, height / 2);
        assertTrue(board.isOnBoard(piece));
    }

    @Test
    public void testIsOnBoardPieceWithInvalidCoordinates() {
        int width = 10;
        int height = 16;
        Board board = new Board(width, height);
        Piece piece = new Piece();
        piece.relocate(-1, -1);
        assertFalse(board.isOnBoard(piece));
        piece.relocate(width, height);
        assertFalse(board.isOnBoard(piece));
        piece.relocate(-width, -height);
        assertFalse(board.isOnBoard(piece));
    }

    @Test
    public void testIsOnBoardPieceWithNull() {
        int width = 10;
        int height = 16;
        Board board = new Board(width, height);
        assertFalse(board.isOnBoard(null));
    }

    @Test
    public void testIsOnBoardPieceBoundaries() {
        int width = 2;
        int height = 2;
        Board board = new Board(width, height);
        Piece piece = new Piece();
        piece.relocate(width, 0);
        assertFalse(board.isOnBoard(piece));
        piece.relocate(0, height);
        assertFalse(board.isOnBoard(piece));
        piece.relocate(-1, 0);
        assertFalse(board.isOnBoard(piece));
        piece.relocate(0, -1);
        assertFalse(board.isOnBoard(piece));
    }

    @Test
    public void testIsOnBoardPieceBoundaries2() {
        int width = 2;
        int height = 2;
        Board board = new Board(width, height);
        Piece piece = new Piece();
        piece.GetShape().setShapeFormRotations(new int[][][]{
            {
                {1, 1},
                {1, 1}
            }
        });
        assertTrue(board.isOnBoard(piece));
        piece.relocate(-1, 0);
        assertFalse(board.isOnBoard(piece));
        piece.relocate(0, -1);
        assertFalse(board.isOnBoard(piece));
        piece.relocate(width, 0);
        assertFalse(board.isOnBoard(piece));
        piece.relocate(0, height);
        assertFalse(board.isOnBoard(piece));
    }

    @Test
    public void testFillSpaceOnBoardWithValidSpaces() {
        int width = 10;
        int height = 16;
        Board board = new Board(width, height);
        board.setSpaceStateOnBoard(0, 0, 1);
        board.setSpaceStateOnBoard(1, 0, 1);
        board.setSpaceStateOnBoard(0, 1, 1);
        board.setSpaceStateOnBoard(width - 1, height - 1, 1);
        board.setSpaceStateOnBoard(width - 2, height - 1, 1);
        board.setSpaceStateOnBoard(width - 1, height - 2, 1);
        assertTrue(board.isFilledSpaceOnBoard(0, 0));
        assertTrue(board.isFilledSpaceOnBoard(1, 0));
        assertTrue(board.isFilledSpaceOnBoard(0, 1));
        assertTrue(board.isFilledSpaceOnBoard(width - 1, height - 1));
        assertTrue(board.isFilledSpaceOnBoard(width - 2, height - 1));
        assertTrue(board.isFilledSpaceOnBoard(width - 1, height - 2));
    }

    @Test
    public void testFillSpaceOnBoardWithInvalidSpaces() {
        int width = 10;
        int height = 16;
        Board board = new Board(width, height);
        board.setSpaceStateOnBoard(-1, 0, 1);
        board.setSpaceStateOnBoard(0, -1, 1);
        board.setSpaceStateOnBoard(-width, -height, 1);
        board.setSpaceStateOnBoard(width, height - 1, 1);
        board.setSpaceStateOnBoard(width - 1, height, 1);
        assertFalse(board.isFilledSpaceOnBoard(-1, 0));
        assertFalse(board.isFilledSpaceOnBoard(0, -1));
        assertFalse(board.isFilledSpaceOnBoard(-width, -height));
        assertFalse(board.isFilledSpaceOnBoard(width, height - 1));
        assertFalse(board.isFilledSpaceOnBoard(width - 1, height));
    }

    @Test
    public void testIsFilledRow() {
        int width = 10;
        int height = 16;
        Board board = new Board(width, height);
        for (int y = 0; y < height; ++y) {
            assertFalse(board.isFilledRow(y));
        }
        for (int x = 0; x < width; ++x) {
            board.setSpaceStateOnBoard(x, 5, 1);
        }
        for (int y = 0; y < width; ++y) {
            if (y == 5) {
                assertTrue(board.isFilledRow(y));
            } else {
                assertFalse(board.isFilledRow(y));
            }
        }
    }

    @Test
    public void testRemoveRow() {
        int width = 10;
        int height = 16;
        Board board = new Board(width, height);
        for (int x = 0; x < width; ++x) {
            board.setSpaceStateOnBoard(x, 5, 1);
        }
        for (int x = 0; x < width; ++x) {
            board.setSpaceStateOnBoard(x, 7, 1);
        }
        assertTrue(board.isFilledRow(7));
        board.removeRow(7);
        assertFalse(board.isFilledRow(7));
        board.removeRow(7);
        assertTrue(board.isFilledRow(7));
    }

    @Test
    public void testRemoveRow2() {
        int width = 1;
        int height = 5;
        Board board = new Board(width, height);
        for (int y = 0; y < height; ++y) {
            board.setSpaceStateOnBoard(0, y, y+1);
        }
        board.removeRow(3);
        
        int[][] expected = {
            {0},
            {1},
            {2},
            {3},
            {5}
        };
        assertArrayEquals(expected, board.getBoardStateCopy());
    }

    @Test
    public void testRemoveFilledRows() {
        int width = 10;
        int height = 16;
        Board board = new Board(width, height);
        for (int x = 0; x < width; ++x) {
            board.setSpaceStateOnBoard(x, 5, 1);
        }
        for (int x = 0; x < width / 2; ++x) {
            board.setSpaceStateOnBoard(x, 6, 1);
        }
        for (int x = 0; x < width; ++x) {
            board.setSpaceStateOnBoard(x, 7, 1);
        }
        board.removeFilledRows();
        assertFalse(board.isFilledRow(5));
        assertFalse(board.isFilledRow(6));
        assertFalse(board.isFilledRow(7));
        for (int x = 0; x < width / 2; ++x) {
            assertFalse(board.isFilledSpaceOnBoard(x, 6));
        }
    }

    @Test
    public void testRemoveFilledRows2() {
        int width = 1;
        int height = 5;
        Board board = new Board(width, height);
        for (int y = 0; y < height; ++y) {
            board.setSpaceStateOnBoard(0, y, y+1);
        }
        board.removeFilledRows();
        
        int[][] expected = {
            {0},
            {0},
            {0},
            {0},
            {0}
        };
        assertArrayEquals(expected, board.getBoardStateCopy());
    }

    @Test
    public void testRemoveFilledRows3() {
        int width = 2;
        int height = 5;
        Board board = new Board(width, height);
        for (int y = 0; y < height; ++y) {
            board.setSpaceStateOnBoard(0, y, y + 1);
            if (y % 2 != 0) {
                board.setSpaceStateOnBoard(1, y, y + 1);
            }
        }
        board.removeFilledRows();

        int[][] expected = {
            {0, 0},
            {0, 0},
            {1, 0},
            {3, 0},
            {5, 0},
        };
        assertArrayEquals(expected, board.getBoardStateCopy());
    }

    @Test
    public void testRemoveFilledRows4() {
        int width = 2;
        int height = 5;
        Board board = new Board(width, height);
        for (int y = 0; y < height; ++y) {
            board.setSpaceStateOnBoard(0, y, y + 1);
            if (y % 2 == 0) {
                board.setSpaceStateOnBoard(1, y, y + 1);
            }
        }
        board.removeFilledRows();

        int[][] expected = {
            {0, 0},
            {0, 0},
            {0, 0},
            {2, 0},
            {4, 0},
        };
        assertArrayEquals(expected, board.getBoardStateCopy());
    }
    
    @Test
    public void testIsInFreeSpaceOnBoardNull() {
        int width = 2;
        int height = 5;
        Board board = new Board(width, height);
        assertFalse(board.isInFreeSpaceOnBoard(null));
    }
    
    @Test
    public void testIsInFreeSpaceOnBoardPieceValid() {
        int width = 2;
        int height = 5;
        Board board = new Board(width, height);
        Piece piece = new Piece();
        assertTrue(board.isInFreeSpaceOnBoard(piece));
    }
    
    @Test
    public void testIsInFreeSpaceOnBoardPieceInvalid() {
        int width = 2;
        int height = 5;
        Board board = new Board(width, height);
        Piece piece = new Piece();
        piece.relocate(-1, 0);
        assertFalse(board.isInFreeSpaceOnBoard(piece));
    }
    
    @Test
    public void testIsInFreeSpaceOnBoardPieceInvalid2() {
        int width = 1;
        int height = 1;
        Board board = new Board(width, height);
        Piece piece = new Piece();
        piece.GetShape().setShapeFormRotations(new int[][][]{
            {
                {0, 1}
            }
        });
        assertFalse(board.isOnBoard(piece));
        piece.GetShape().setShapeFormRotations(new int[][][]{
            {
                {0},
                {1}
            }
        });
        assertFalse(board.isInFreeSpaceOnBoard(piece));
    }
    
    @Test
    public void testIsInFreeSpaceOnBoardNotOnBoard() {
        int width = 2;
        int height = 5;
        Board board = new Board(width, height);
        Piece piece = new Piece();
        piece.relocate(-1, 0);
        assertFalse(board.isInFreeSpaceOnBoard(piece));
        piece.relocate(width, 0);
        assertFalse(board.isInFreeSpaceOnBoard(piece));
    }
    
    @Test
    public void testIsInFreeSpaceOnBoard_OnFilledSpace() {
        int width = 2;
        int height = 2;
        Board board = new Board(width, height);
        Piece piece = new Piece();
        piece.relocate(1, 1);
        board.fillPieceToBoard(piece);
        assertFalse(board.isInFreeSpaceOnBoard(piece));
    }
    
    @Test
    public void testIsInFreeSpaceOnBoard() {
        int width = 3;
        int height = 3;
        Board board = new Board(width, height);
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < width; ++y) {
                board.setSpaceStateOnBoard(x, y, 1);
            }
        }
        Piece piece = new Piece();
        piece.relocate(1, 1);
        assertFalse(board.isInFreeSpaceOnBoard(piece));
        board.setSpaceStateOnBoard(1, 1, 0);
        board.fillPieceToBoard(piece);
        assertTrue(board.isInFreeSpaceOnBoard(piece));
    }
    
    @Test
    public void testIsInFreeSpaceOnBoard2() {
        int width = 3;
        int height = 3;
        Board board = new Board(width, height);
        Piece piece = new Piece();
        piece.GetShape().setShapeFormRotations(new int[][][]{
            {
                {0, 0},
                {0, 1}
            }
        });
        assertTrue(board.isInFreeSpaceOnBoard(piece));
        board.fillPieceToBoard(piece);
        assertFalse(board.isInFreeSpaceOnBoard(piece));
    }
    
    @Test
    public void testFillPieceToBoardNull() {
        int width = 1;
        int height = 1;
        Board board = new Board(width, height);
        board.fillPieceToBoard(null);
        assertArrayEquals(new int[][]{{0}}, board.getBoardStateCopy());
    }
}
