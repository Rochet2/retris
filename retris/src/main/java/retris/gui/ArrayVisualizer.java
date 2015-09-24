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
 * Piirtää arraytä ruudulle
 * 
 * @author rochet2_2
 */
public class ArrayVisualizer extends JPanel {

    private final int width = 20;
    private final int height = 20;
    private final int spacing = 1;
    private final HashMap<Integer, Color> colors = new HashMap<>();
    private final Random random = new Random();

    private int[][] boardState = {{}};

    /**
     * Luo uuden paneelin joka piirtää arraytä
     *
     * @param boardWidth paneelin leveys
     * @param boardHeight paneelin korkeus
     */
    public ArrayVisualizer(int boardWidth, int boardHeight) {
        boardWidth = spacing + (boardWidth) * (width + spacing);
        boardHeight = spacing + (boardHeight) * (height + spacing);
        Dimension dimension = new Dimension(boardWidth, boardHeight);
        setPreferredSize(dimension);
    }

    /**
     * Piirtää olion.
     *
     * @param g piirtämiseen käytettävä olio
     */
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

    /**
     * Palauttaa ei mustan värin annetulle arvolle. Sama arvo palauttaa aina
     * saman värin.
     *
     * @param seed luku jota käytetään värin luonnissa
     * @return annetun arvon väri
     */
    private Color getColor(int seed) {
        Color rgb = colors.get(seed);
        if (rgb != null) {
            return rgb;
        }
        int col = seed % 3;
        int r = getNumberFromUint8Range(col == 0);
        int g = getNumberFromUint8Range(col == 1);
        int b = getNumberFromUint8Range(col == 2);
        rgb = new Color(r, g, b);
        colors.put(seed, rgb);
        return rgb;
    }

    /**
     * Palauttaa arvon väliltä 0-255 tai (256/2)-255 jos notRandom on asetettu.
     *
     * @param halfRange Käytä väliä (256/2)-255
     * @return arvo väliltä 0-255
     */
    private int getNumberFromUint8Range(boolean halfRange) {
        int base = 256;
        if (halfRange) {
            return (base / 2 + random.nextInt(base / 2));
        } else {
            return (random.nextInt(base));
        }
    }

    /**
     * Asettaa paneelin piirtämän arrayn sisällön
     *
     * @param array array joka tulisi piirtää
     */
    public synchronized void setDrawnArray(int[][] array) {
        if (array == null) {
            return;
        }
        boardState = array;
        repaint();
    }
}
