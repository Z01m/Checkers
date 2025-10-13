package org.example.game.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameModeDialog extends JDialog {
    private JButton pvcButton;
    private JButton pvpButton;
    private JButton backButton;
    private JPanel contentPane;
    private JLabel titleLabel;
    private JButton cvcButton;

    public GameModeDialog() {
        setupDialog();
        setupButtonActions();
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
        openBoardWindow("Игрок vs Игрок");
    }

    private void startPlayerVsComputer() {
        openBoardWindow("Игрок vs Бот");
    }

    private void startComputerVsComputer() {
        openBoardWindow("Бот vs Бот");
    }

    private void openBoardWindow(String gameMode) {
        dispose();
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowBoardWindow(gameMode);
            }
        });
    }

    private void createAndShowBoardWindow(String gameMode) {
        JFrame boardFrame = new JFrame("Шашки - " + gameMode);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        BoardPanel boardPanel = new BoardPanel();
        mainPanel.add(boardPanel.getBoardPanel(), BorderLayout.CENTER);
        
        JLabel modeLabel = new JLabel("Режим: " + gameMode, SwingConstants.CENTER);
        modeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        modeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(modeLabel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton backButton = new JButton("Назад в меню");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardFrame.dispose(); // Закрываем окно с доской
                openMainMenu(); // Открываем главное меню
            }
        });
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Настраиваем окно
        boardFrame.setContentPane(mainPanel);
        boardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        boardFrame.pack();
        boardFrame.setLocationRelativeTo(null);
        boardFrame.setVisible(true);
    }

    private void openMainMenu() {
        System.out.println("Возврат в главное меню");
    }

    private void onBack() {
        dispose();
    }

    public static void main(String[] args) {
        GameModeDialog dialog = new GameModeDialog();
        dialog.setVisible(true);
    }
}