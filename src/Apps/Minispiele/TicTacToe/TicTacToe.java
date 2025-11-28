package Apps.Minispiele.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TicTacToe {

    static char[][] feld = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    static boolean xTurn = true;
    static boolean botActivated = false;

    public static void TicTacToeSpiel(boolean botJaNein, JFrame mainframe) {


        // Prüfen, ob Bot-Modus aktiviert werden soll
        botActivated = (botJaNein);

        JFrame TicTacToeMenü = new JFrame("Tic Tac Toe");
        TicTacToeMenü.setSize(336, 420);
        TicTacToeMenü.setResizable(false);
        TicTacToeMenü.setLocationRelativeTo(null);
        TicTacToeMenü.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel SpielPanel = new JPanel();
        SpielPanel.setLayout(null);

        JButton Verlassen = new JButton("Verlassen");
        Verlassen.setBackground(Color.WHITE);
        Verlassen.setForeground(Color.RED);

        SpielPanel.setBounds(0, 0, 330, 320);
        Verlassen.setBounds(0, 320, 330, 63);
        SpielPanel.setBackground(Color.DARK_GRAY);


        // Buttons für das Spielfeld erstellen
        JButton[][] buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setBounds(j * 110, i * 110, 100, 100);

                // ActionListener hinzufügen
                int row = i; // Für die innere Klasse
                int col = j;
                buttons[i][j].addActionListener(e -> {
                    try {
                        handleButtonClick(row, col, buttons[row][col], buttons);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                SpielPanel.add(buttons[i][j]);
            }
        }

        Verlassen.addActionListener(e-> {
            TicTacToeMenü.dispose();
            mainframe.setVisible(true);
        });

        //VerlassenPanel.add(Verlassen, BorderLayout.CENTER);

        TicTacToeMenü.add(Verlassen);
        TicTacToeMenü.add(SpielPanel);
        TicTacToeMenü.setVisible(true);
    }

    // Methode für das Spielfeld-Array und Button-Update
    public static void handleButtonClick(int row, int col, JButton button, JButton[][] buttons) throws InterruptedException {
        if (feld[row][col] == ' ') { // Nur setzen, wenn das Feld leer ist
            if (xTurn) {
                feld[row][col] = 'X';
                button.setIcon(new ImageIcon("src/Bilder/kreuz.png")); // Icon für 'X'
            } else if (!botActivated) { // Spieler O macht den Zug (wenn 2 Spieler-Modus aktiv ist)
                feld[row][col] = 'O';
                button.setIcon(new ImageIcon("src/Bilder/kreis.png")); // Icon für 'O'
            }

            // Nach jedem Zug: Gewinn prüfen
            if (checkWin(feld, 'X')) {
                JOptionPane.showMessageDialog(null, "'X' hat gewonnen!");
                resetGame(button.getParent());
                return;
            } else if (checkWin(feld, 'O')) {
                JOptionPane.showMessageDialog(null, "'O' hat gewonnen!");
                resetGame(button.getParent());
                return;
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(null, "Unentschieden!");
                resetGame(button.getParent());
                return;
            }

            xTurn = !xTurn; // Spieler wechseln
            // Wenn Bot aktiviert ist und 'O' an der Reihe ist, macht der Bot einen Zug
            if (botActivated && !xTurn) {
                botMove(buttons);
            }
        }
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (feld[i][j] == ' ') {
                    return false; // Mindestens ein Feld ist noch frei
                }
            }
        }
        return true;
    }


    public static void resetGame(Container parent) {
        for (Component component : parent.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setIcon(null); // Entferne Symbol (Icon)
            }
        }

        // Reset des Spielfeld-Array und Spielerstatus
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                feld[i][j] = ' ';
            }
        }
        xTurn = true;
    }

    public static boolean checkWin(char[][] board, char player) {
        // Prüfe alle Zeilen
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Spieler hat eine Zeile gewonnen
            }
        }

        // Prüfe alle Spalten
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Spieler hat eine Spalte gewonnen
            }
        }

        // Prüfe diagonale von links oben nach rechts unten
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Spieler hat die Hauptdiagonale gewonnen
        }

        // Prüfe diagonale von rechts oben nach links unten
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // Spieler hat die Gegendiagonale gewonnen
        }

        // Kein Gewinner
        return false;
    }





    public static void botMove(JButton[][] buttons) throws InterruptedException {
        Timer timer = new Timer(500, e -> {

            Random random = new Random();

            int row, col;
            do {
                row = random.nextInt(3);
                col = random.nextInt(3);
            } while (feld[row][col] != ' '); // Freies Feld suchen
            feld[row][col] = 'O'; // Bot setzt 'O'

            buttons[row][col].setIcon(new ImageIcon("src/Bilder/kreis.png")); // Icon für 'O'

            // Nach jedem Zug: Gewinn prüfen
            if (checkWin(feld, 'X')) {
                JOptionPane.showMessageDialog(null, "'X' hat gewonnen!");
                resetGame(buttons[0][0].getParent());
            } else if (checkWin(feld, 'O')) {
                JOptionPane.showMessageDialog(null, "'O' hat gewonnen!");
                resetGame(buttons[0][0].getParent());
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(null, "Unentschieden!");
                resetGame(buttons[0][0].getParent());
            }

            xTurn = true; // Nach dem Bot-Zug ist wieder 'X' dran
        });
        timer.setRepeats(false); // Timer nur einmal auslösen
        timer.start();
    }
}
