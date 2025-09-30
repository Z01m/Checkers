package org.example.game.UI;

import javax.swing.*;
import java.awt.*;

public class BoardPanel {
    private JPanel boardPanel;

    public BoardPanel() {
        // Инициализируем boardPanel перед использованием
        boardPanel = new JPanel();
        setupBoardAppearance();
    }

    private void setupBoardAppearance() {
        // Устанавливаем layout для сетки 8x8
        boardPanel.setLayout(new GridLayout(8, 8));
        boardPanel.setPreferredSize(new Dimension(480, 480));
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Создаем шахматную доску
        createChessboard();
    }

    private void createChessboard() {
        // Очищаем панель перед созданием
        boardPanel.removeAll();

        Color lightColor = new Color(240, 217, 181); // Светлый
        Color darkColor = new Color(181, 136, 99);   // Темный

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel cell = new JPanel();
                cell.setPreferredSize(new Dimension(60, 60));

                if ((row + col) % 2 == 0) {
                    cell.setBackground(lightColor);
                } else {
                    cell.setBackground(darkColor);
                }

                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                boardPanel.add(cell);
            }
        }

        // Обновляем панель
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }
}