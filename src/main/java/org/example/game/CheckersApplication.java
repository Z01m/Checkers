package org.example.game;

import org.example.game.UI.MainMenuForm;

import javax.swing.*;

public class CheckersApplication {
    public static void main(String[] args) {
        // Устанавливаем системный look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Запускаем главное меню
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowMainMenu();
            }
        });
    }

    private static void createAndShowMainMenu() {
        JFrame frame = new JFrame("Шашки");
        MainMenuForm mainMenu = new MainMenuForm();

        frame.setContentPane(mainMenu.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
