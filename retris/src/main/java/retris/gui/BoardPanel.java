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
import java.util.HashMap;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author rochet2_2
 */
public class BoardPanel extends JPanel {

    private final int width = 20;
    private final int height = 20;
    private final int spacing = 1;
    private final HashMap<Integer, Color> colors = new HashMap<>();
    private final Random random = new Random();

    private int[][] boardState = {{}};

    public BoardPanel(int boardWidth, int boardHeight) {
        boardWidth = spacing + (boardWidth) * (width + spacing);
        boardHeight = spacing + (boardHeight) * (height + spacing);
        Dimension dimension = new Dimension(boardWidth, boardHeight);
        setPreferredSize(dimension);
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        Graphics g2 = g.create();
        for (int y = 0; y < boardState.length; ++y) {
            for (int x = 0; x < boardState[y].length; ++x) {
                if (boardState[y][x] == 0) {
                    continue;
                }
                g2.setColor(getColor(boardState[y][x]));
                g2.fillRect(spacing + x * (spacing + width), spacing + y * (spacing + height), width, height);
            }
        }
        g2.dispose();
    }

    private Color getColor(int seed) {
        Color rgb = colors.get(seed);
        if (rgb != null) {
            return rgb;
        }
        int col = seed % 3;
        int r = getSingleColor(col == 0);
        int g = getSingleColor(col == 1);
        int b  = getSingleColor(col == 2);
        rgb = new Color(r, g, b);
        colors.put(seed, rgb);
        return rgb;
    }

    private int getSingleColor(boolean notRandom) {
        int base = 256;
        if (notRandom)
            return (base/2 + random.nextInt(base/2));
        else
            return (random.nextInt(base));
    }

    public synchronized void setDrawnArray(int[][] array) {
        if (array == null) {
            return;
        }
        boardState = array;
        repaint();
    }
}
