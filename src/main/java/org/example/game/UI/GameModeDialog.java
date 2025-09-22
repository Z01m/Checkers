package org.example.game.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameModeDialog extends JDialog {
    private JButton pvpButton;
    private JButton pvcButton;
    private JButton cvcButton;
    private JButton backButton;
    private JPanel contentPane;
    private JLabel titleLabel;
    
    public GameModeDialog() {
        
    }
    
    private void setupDialog(){
        setContentPane(contentPane);
        setModal(true);
        setTitle("Game Mode");
        setMinimumSize(new Dimension(350, 300));
        setResizable(false);
        
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onBack();
            }
        });
    }
    
    private void setupButtonActions(){
        pvpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startPlayerVsPlayer();
            }
        });
        pvcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startPlayerVsComputer();
            }
        });
        cvcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startComputerVsComputer();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBack();
            }
        });
    }
    private void startPlayerVsPlayer() {
        JOptionPane.showMessageDialog(this,
                "Режим 'Игрок vs Игрок' запущен!\n\n" +
                        "Этот режим будет реализован позже.",
                "Режим игры",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void startPlayerVsComputer() {
        JOptionPane.showMessageDialog(this,
                "Режим 'Игрок vs Бот' запущен!\n\n" +
                        "Этот режим будет реализован позже.",
                "Режим игры",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void startComputerVsComputer() {
        JOptionPane.showMessageDialog(this,
                "Режим 'Бот vs Бот' запущен!\n\n" +
                        "Этот режим будет реализован позже.",
                "Режим игры",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void onBack() {
        dispose();
    }

    public static void main(String[] args) {
        GameModeDialog dialog = new GameModeDialog();
        dialog.setVisible(true);
    }
}
