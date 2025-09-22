package org.example.game.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuForm {
    private JLabel label;
    private JButton StartButton;
    private JButton ExitButton;
    private JPanel mainPanel;

    public MainMenuForm(){
    }
    
    private void setupButtonActions(){
        StartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGameModeSelection();
            }
        });
        
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
    
    private void openGameModeSelection(){
        GameModeDialog gameModeDialog = new GameModeDialog();
        gameModeDialog.setVisible(true);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Шашки - Главное меню");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainMenuForm mainMenuForm = new MainMenuForm();
        frame.getContentPane().add(mainMenuForm.getMainPanel());
        frame.setMinimumSize(new Dimension(400, 300));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
}
