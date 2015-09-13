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
package retris.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import retris.logic.Board;
import retris.logic.Game;
import retris.logic.Piece;
import retris.logic.Position;

/**
 *
 * @author rochet2_2
 */
public class BoardPanel extends JPanel {

    int width = 20;
    int height = 20;
    int spacing = 1;

    int[][] boardState = {{}};

    public BoardPanel(int boardWidth, int boardHeight) {
        boardWidth = spacing + (boardWidth) * (width + spacing);
        boardHeight = spacing + (boardHeight) * (height + spacing);
        Dimension dimension = new Dimension(boardWidth, boardHeight);
        setPreferredSize(dimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        Graphics g2 = g.create();
        for (int y = 0; y < boardState.length; ++y) {
            for (int x = 0; x < boardState[y].length; ++x) {
                if (boardState[y][x] == 0) {
                    continue;
                }
                g2.setColor(Color.RED);
                g2.fillRect(spacing + x * (spacing + width), spacing + y * (spacing + height), width, height);
            }
        }
        g2.dispose();
    }

    void drawBoard(Game game) {
        Board board = game.getGameBoard();
        Piece piece = game.getDroppingPiece();

        int[][] array = board.getBoardState();
        Position position = piece.getPosition();
        int[][] form = piece.GetShape().getCurrentForm();

        fillPieceFormToBoard(form, position, array);

        boardState = array;
        repaint();
    }

    private void fillPieceFormToBoard(int[][] form, Position position, int[][] array) {
        for (int y = 0; y < form.length; ++y) {
            for (int x = 0; x < form[y].length; ++x) {
                if (form[y][x] == 0) {
                    continue;
                }
                int yCoord = position.getY() + y;
                int xCoord = position.getX() + x;
                if (yCoord >= array.length || xCoord >= array[y].length) {
                    continue;
                }
                array[yCoord][xCoord] = form[y][x];
            }
        }
    }
}
