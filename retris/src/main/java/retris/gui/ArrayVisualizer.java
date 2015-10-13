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
import javax.swing.SwingUtilities;
import retris.logic.ArrayUtil;
import retris.logic.gamestate.GameState;

/**
 * Piirtää arraytä ruudulle
 *
 * @author rochet2_2
 */
public class ArrayVisualizer extends JPanel {

    /**
     * arrayn elementtien välissä oleva tila
     */
    private final int elementSpacing;
    /**
     * eri numeroille arvotut värit
     */
    private final HashMap<Integer, Color> numberColors = new HashMap<>();
    /**
     * satunnaislukugeneraattori
     */
    private final Random random = new Random();
    /**
     * pelin tilaa kuvaava olio
     */
    private final GameState gameState;

    /**
     * Luo uuden paneelin joka piirtää arraytä
     *
     * @param gameState pelin tilaolio
     * @param panelWidth paneelin leveys
     * @param panelHeight paneelin korkeus
     * @param elementSpacing array elementtien välinen tila kuvassa
     */
    public ArrayVisualizer(GameState gameState, int panelWidth, int panelHeight, int elementSpacing) {
        this.gameState = gameState;
        this.elementSpacing = elementSpacing;
        setPreferredSize(new Dimension(panelWidth, panelHeight));
    }

    /**
     * Piirtää olion.
     *
     * @param g piirtämiseen käytettävä olio
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        final int[][] drawnArray = gameState.getBoardState();
        if (ArrayUtil.hasNullValue(drawnArray)) {
            return;
        }
        if (drawnArray.length < 1 || drawnArray[0].length < 1) {
            return;
        }

        int elementWidth = (getWidth() - (drawnArray[0].length + 1) * elementSpacing) / drawnArray[0].length;
        int elementHeight = (getHeight() - (drawnArray.length + 1) * elementSpacing) / drawnArray.length;

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
     * Päivittää olion
     */
    public void update() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                repaint();
            }
        });
    }
}
