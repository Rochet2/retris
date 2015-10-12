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

    /**
     * yhden arrayn elementin leveys
     */
    private final int elementWidth = 20;
    /**
     * yhden arrayn elementin korkeus
     */
    private final int elementHeight = 20;
    /**
     * arrayn elementtien välissä oleva tila
     */
    private final int elementSpacing = 1;
    /**
     * eri numeroille arvotut värit
     */
    private final HashMap<Integer, Color> numberColors = new HashMap<>();
    /**
     * satunnaislukugeneraattori
     */
    private final Random random = new Random();

    /**
     * array joka piirretään
     */
    private int[][] drawnArray = {{}};

    /**
     * Luo uuden paneelin joka piirtää arraytä
     *
     * @param panelWidth paneelin leveys
     * @param panelHeight paneelin korkeus
     */
    public ArrayVisualizer(int panelWidth, int panelHeight) {
        panelWidth = elementSpacing + (panelWidth) * (elementWidth + elementSpacing);
        panelHeight = elementSpacing + (panelHeight) * (elementHeight + elementSpacing);
        Dimension dimension = new Dimension(panelWidth, panelHeight);
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
        for (int y = 0; y < drawnArray.length; ++y) {
            for (int x = 0; x < drawnArray[y].length; ++x) {
                if (drawnArray[y][x] == 0) {
                    continue;
                }
                g2.setColor(getColor(drawnArray[y][x]));
                g2.fillRect(elementSpacing + x * (elementSpacing + elementWidth), elementSpacing + y * (elementSpacing + elementHeight), elementWidth, elementHeight);
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
        Color color = numberColors.get(seed);
        if (color != null) {
            return color;
        }
        int col = seed % 3;
        int r = getNumberFromUint8Range(col == 0);
        int g = getNumberFromUint8Range(col == 1);
        int b = getNumberFromUint8Range(col == 2);
        color = new Color(r, g, b);
        numberColors.put(seed, color);
        return color;
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
        drawnArray = array;
        repaint();
    }
}
