package org.example.game.UI;

import org.example.game.Board.Board;
import org.example.game.Board.King;
import org.example.game.Board.Pawn;
import org.example.game.Board.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardPanel {
    private JPanel boardPanel;

    public BoardPanel() {
        // Инициализируем boardPanel перед использованием
        boardPanel = new JPanel();
        
        setupBoardAppearance();
    }

    private void setupBoardAppearance() {
        
        boardPanel.setLayout(new GridLayout(8, 8));
        boardPanel.setPreferredSize(new Dimension(480, 480));
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Создаем шахматную доску
        createChessboard();
    }

    private void createChessboard() {
        // Очищаем панель перед созданием
        boardPanel.removeAll();
        Board board = Board.CreateBoard();
        updateChessboard();

        // Обновляем панель
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    private void updateChessboard() {
        // Очищаем панель перед обновлением
        boardPanel.removeAll();
        Board board = Board.CreateBoard(); // Или используйте существующий экземпляр доски
        Color lightColor = new Color(240, 217, 181); // Светлый
        Color darkColor = new Color(181, 136, 99);   // Темный

        for (int x = 0; x < board.BOARD_SIZE; x++) {
            for (int y = 0; y < board.BOARD_SIZE; y++) {
                JPanel cell = new JPanel(new BorderLayout());
                cell.setPreferredSize(new Dimension(60, 60));

                // Устанавливаем цвет клетки (только темные клетки для шашек)
                if ((x + y) % 2 == 0) {
                    cell.setBackground(lightColor);
                } else {
                    cell.setBackground(darkColor);
                }

                Piece piece = board.getPiece(x, y);
                if (piece != null && (x + y) % 2 != 0) { // Шашки только на темных клетках
                    String imagePath = getImagePathForChecker(piece);

                    if (imagePath != null) {
                        try {
                            // Загружаем изображение из resources
                            ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
                            Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon = new ImageIcon(scaledImage);

                            JLabel pieceLabel = new JLabel(scaledIcon);
                            pieceLabel.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.add(pieceLabel, BorderLayout.CENTER);
                            
                        } catch (Exception e) {
                            // Fallback на символы или цветные шашки
                            System.err.println("Не удалось загрузить изображение: " + imagePath);
                            addFallbackChecker(cell, piece);
                        }
                    } else {
                        // Если путь к изображению не определен, используем fallback
                        addFallbackChecker(cell, piece);
                    }
                }

                // Добавляем обработчик клика для клетки
                final int cellX = x;
                final int cellY = y;
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Шашки ходят только по темным клеткам
                        if ((cellX + cellY) % 2 != 0) {
                            handleCellClick(cellX, cellY);
                        }
                    }
                });

                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                boardPanel.add(cell);
            }
        }

        // Обновляем панель
        boardPanel.revalidate();
        boardPanel.repaint();
    }


    private String getImagePathForChecker(Piece piece) {
        if (piece == null) return null;

        if (piece.getType() == Piece.PieceType.PAWN) {
            return piece.isWhite() ? "/Images/WhitePawn.png" : "/Images/BlackPawn.png";
        } else if (piece.getType() == Piece.PieceType.KING) {
            return piece.isWhite() ? "/Images/WhiteKing.png" : "/Images/BlackKing.png";
        }

        return null;
    }

    private void addFallbackChecker(JPanel cell, Piece piece) {
        JLabel fallbackLabel = new JLabel(getCheckerSymbol(piece));
        fallbackLabel.setForeground(piece.isWhite() ? Color.WHITE : Color.BLACK); // Используем piece.isWhite()
        fallbackLabel.setFont(new Font("Arial", Font.BOLD, 24));
        fallbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cell.add(fallbackLabel, BorderLayout.CENTER);
    }

    // Вспомогательный метод для получения символа шашки
    private String getCheckerSymbol(Piece piece) {
        if (piece == null) return "";

        if (piece.getType() == Piece.PieceType.PAWN) {
            // Обычная шашка
            return "●";
        } else if (piece.getType() == Piece.PieceType.KING) {
            // Дамка
            return piece.isWhite() ? "♕" : "♛";
        }
        return "●";
    }

    // Обработчик клика по клетке
    private void handleCellClick(int x, int y) {
        System.out.println("Кликнута клетка: " + x + ", " + y);
        // Логика для выбора шашки, показа возможных ходов и т.д.
    }

    // Дополнительный метод для подсветки возможных ходов
   
    
}