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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import retris.logic.shape.Shape;

/**
 *
 * @author rochet2_2
 */
public class BoardTest {

    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testIsOnBoardCoordinatesWithValidCoordinates() {
        int width = 10;
        int height= 16;
        Board board = new Board(width, height);
        assertTrue(board.isOnBoard(0, 0));
        assertTrue(board.isOnBoard(1, 1));
        assertTrue(board.isOnBoard(width-1, height-1));
        assertTrue(board.isOnBoard(width-2, height-2));
        assertTrue(board.isOnBoard(width/2, height/2));
    }

    @Test
    public void testIsOnBoardCoordinatesWithInvalidCoordinates() {
        int width = 10;
        int height= 16;
        Board board = new Board(width, height);
        assertFalse(board.isOnBoard(-1, -1));
        assertFalse(board.isOnBoard(width, height));
        assertFalse(board.isOnBoard(-width, -height));
    }

    @Test
    public void testIsOnBoardPieceWithValidCoordinates() {
        int width = 10;
        int height= 16;
        Board board = new Board(width, height);
        Piece piece = new Piece(new Shape(), 0, 0);
        assertTrue(board.isOnBoard(piece));
        piece.relocate(0, 0);
        assertTrue(board.isOnBoard(piece));
        piece.relocate(1, 1);
        assertTrue(board.isOnBoard(piece));
        piece.relocate(width-1, height-1);
        assertTrue(board.isOnBoard(piece));
        piece.relocate(width-2, height-2);
        assertTrue(board.isOnBoard(piece));
        piece.relocate(width/2, height/2);
        assertTrue(board.isOnBoard(piece));
    }

    @Test
    public void testIsOnBoardPieceWithInvalidCoordinates() {
        int width = 10;
        int height= 16;
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
    public void testFillSpaceOnBoardWithValidSpaces() {
        int width = 10;
        int height= 16;
        Board board = new Board(width, height);
        board.setSpaceStateOnBoard(0, 0, 1);
        board.setSpaceStateOnBoard(1, 0, 1);
        board.setSpaceStateOnBoard(0, 1, 1);
        board.setSpaceStateOnBoard(width-1, height-1, 1);
        board.setSpaceStateOnBoard(width-2, height-1, 1);
        board.setSpaceStateOnBoard(width-1, height-2, 1);
        assertTrue(board.isFilledSpaceOnBoard(0, 0));
        assertTrue(board.isFilledSpaceOnBoard(1, 0));
        assertTrue(board.isFilledSpaceOnBoard(0, 1));
        assertTrue(board.isFilledSpaceOnBoard(width-1, height-1));
        assertTrue(board.isFilledSpaceOnBoard(width-2, height-1));
        assertTrue(board.isFilledSpaceOnBoard(width-1, height-2));
    }

    @Test
    public void testFillSpaceOnBoardWithInvalidSpaces() {
        int width = 10;
        int height= 16;
        Board board = new Board(width, height);
        board.setSpaceStateOnBoard(-1, 0, 1);
        board.setSpaceStateOnBoard(0, -1, 1);
        board.setSpaceStateOnBoard(-width, -height, 1);
        board.setSpaceStateOnBoard(width, height-1, 1);
        board.setSpaceStateOnBoard(width-1, height, 1);
        assertFalse(board.isFilledSpaceOnBoard(-1, 0));
        assertFalse(board.isFilledSpaceOnBoard(0, -1));
        assertFalse(board.isFilledSpaceOnBoard(-width, -height));
        assertFalse(board.isFilledSpaceOnBoard(width, height-1));
        assertFalse(board.isFilledSpaceOnBoard(width-1, height));
    }
}
