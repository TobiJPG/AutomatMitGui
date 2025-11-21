package Apps.Minispiele.MineSweeper.Debug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MinesweeperDebug extends JFrame {
    private int GRID_SIZE_ROW; // Gittergröße: 10x10
    private int GRID_SIZE_COL; // Gittergröße: 10x10
    private int NUM_MINES; // Anzahl der Minen
    private JButton[][] buttons;
    private boolean[][] mines; // Minenpositionen
    private boolean[][] revealed; // Aufgedeckte Felder
    private boolean[][] flagged; // Mit Flagge markierte Felder
    private JFrame mainFrame; // Referenz auf den Main-Frame
    private int flagCount  = 0;
    private JLabel Flags = new JLabel();

    private JLabel timerLabel; // Label für die Zeit
    private Timer timer; // Timer für die Zeitmessung
    private int elapsedTime = 0; // Verstrichene Zeit in Sekunden


    // Konstruktor mit Übergabe des Main-Frames
    public MinesweeperDebug(JFrame mainFrame, int row, int col, int Mines) {

        this.mainFrame = mainFrame; // Speichere Main-Frame
        GRID_SIZE_ROW = row;
        GRID_SIZE_COL = col;
        NUM_MINES = Mines;

        buttons = new JButton[GRID_SIZE_ROW][GRID_SIZE_COL];
        mines = new boolean[GRID_SIZE_ROW][GRID_SIZE_COL];
        revealed = new boolean[GRID_SIZE_ROW][GRID_SIZE_COL];
        flagged = new boolean[GRID_SIZE_ROW][GRID_SIZE_COL];

        initUI();

    }

    private void initUI() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(50 * GRID_SIZE_COL, 50 * GRID_SIZE_ROW);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel für die Uhr hinzufügen
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.GRAY);
        topPanel.setLayout(new BorderLayout());

        JButton Verlassen = new JButton("Verlassen");
        Verlassen.setBackground(Color.RED);
        Verlassen.setForeground(Color.WHITE);

        Verlassen.addActionListener(e -> {
            dispose();
            mainFrame.setVisible(true);
        });
        topPanel.add(Verlassen, BorderLayout.EAST);


        // Uhr anzeigen
        timerLabel = new JLabel("Zeit: 0s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setForeground(Color.WHITE);
        topPanel.add(timerLabel, BorderLayout.CENTER);

        JLabel flagge = new JLabel();
        Flags.setFont(new Font("Arial", Font.BOLD, 16));
        Flags.setForeground(Color.WHITE);
        Flags.setHorizontalAlignment(SwingConstants.CENTER);
        Flags.setText("Flaggen platziert: " + flagCount);
        topPanel.add(Flags, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH); // Füge das Panel oben hinzu

        // Gitter (Buttons) erstellen
        JPanel gridPanel = new JPanel();
        gridPanel.setBackground(Color.LIGHT_GRAY);
        gridPanel.setLayout(new GridLayout(GRID_SIZE_ROW, GRID_SIZE_COL));

        for (int row = 0; row < GRID_SIZE_ROW; row++) {
            for (int col = 0; col < GRID_SIZE_COL; col++) {
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                button.setFocusable(false);
                button.setFont(new Font("Arial", Font.BOLD, 16));
                button.setSize(50, 50);
                button.addMouseListener(new CellClickListener(row, col));
                buttons[row][col] = button;
                gridPanel.add(button);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        // Minen zufällig platzieren
        placeMines();

        // Timer initialisieren
        initTimer();
    }

    private void placeMines() {
        int minesPlaced = 0;
        while (minesPlaced < NUM_MINES) {
            int row = (int) (Math.random() * GRID_SIZE_ROW);
            int col = (int) (Math.random() * GRID_SIZE_COL);
            if (!mines[row][col]) {
                mines[row][col] = true;
                buttons[row][col].setIcon(new ImageIcon("src/Bilder/bombe.png"));
                minesPlaced++;
            }
        }
    }


    private void disableButton(JButton button) {
        button.setEnabled(false); // Button deaktivieren (kein Klick möglich)
        button.setDisabledIcon(button.getIcon()); // Icon bleibt sichtbar
        button.setOpaque(true); // Macht den Hintergrund sichtbar (kein Verblassen)
        button.setBackground(button.getBackground()); // Beibehaltung der Hintergrundfarbe
    }


    private void revealCell(int row, int col) {
        if (!timer.isRunning()) { // Timer nur starten, wenn er nicht schon läuft
            timer.start();
        }

        if (revealed[row][col] || flagged[row][col]) {
            return; // Wenn bereits aufgedeckt oder markiert, nichts tun
        }

        revealed[row][col] = true; // Markiere die Zelle als aufgedeckt

        if (mines[row][col]) {
            buttons[row][col].setBackground(Color.RED); // Bei Mine: Hintergrund mit Rot markieren
            buttons[row][col].setIcon(new ImageIcon("src/Bilder/bombe.png")); // Setze das Mine-Icon
            disableButton(buttons[row][col]); // Button deaktivieren
            timer.stop(); // Timer stoppen
            gameOver(); // Hauptmenü anzeigen
            return;
        }

        int mineCount = countAdjacentMines(row, col);

        if (mineCount > 0) {
            buttons[row][col].setIcon(new ImageIcon("src/Bilder/" + mineCount + ".png")); // Zahl-Icon setzen

        } else {
            // Rekursives Aufdecken von leeren Nachbarfeldern
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    int newRow = row + dr;
                    int newCol = col + dc;
                    if (isInBounds(newRow, newCol)) {
                        revealCell(newRow, newCol);
                    }
                }
            }
        }

        disableButton(buttons[row][col]); // Deaktiviere den aktuellen Button
        checkForWin(); // Gewinnbedingung prüfen
    }


    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int newRow = row + dr;
                int newCol = col + dc;
                if (isInBounds(newRow, newCol) && mines[newRow][newCol]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void toggleFlag(int row, int col) {
        if (revealed[row][col]) {
            return;
        }

        ImageIcon flagIcon = new ImageIcon("src/Bilder/flagge.png"); // Rundes Flaggen-Icon // Bild laden
        flagged[row][col] = !flagged[row][col];
        buttons[row][col].setIcon(flagged[row][col] ? flagIcon : null);
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < GRID_SIZE_ROW && col >= 0 && col < GRID_SIZE_COL;
    }

    private void gameOver() {
        timer.stop(); // Timer stoppen

        // Dialog anzeigen und Spieler nach Neustart fragen
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Du hast eine Mine getroffen! Zeit: " + elapsedTime + " Sekunden.\nMöchtest du ein neues Spiel starten?",
                "Game Over",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            restartGame(); // Neues Spiel starten
        } else {
            this.dispose(); // Fenster schließen
            mainFrame.setVisible(true); // Hauptmenü anzeigen
        }
    }

    private class CellClickListener extends MouseAdapter {
        private int row, col;

        public CellClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
                // Toggle nur, wenn erlaubt, und Zähler korrekt anpassen
                boolean wasFlagged = flagged[row][col];
                toggleFlag(row, col);
                boolean isFlagged = flagged[row][col];

                if (!wasFlagged && isFlagged) {
                    flagCount++;
                } else if (wasFlagged && !isFlagged) {
                    flagCount--;
                }
                Flags.setText("Flaggen platziert: " + flagCount);

                checkForWin();

            } else if (SwingUtilities.isLeftMouseButton(e)) {
                revealCell(row, col);
                checkForWin();
            }
        }
    }

    private void initTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime++;
                timerLabel.setText("Zeit: " + elapsedTime + "s");
            }
        });
    }

    private void checkForWin() {
        for (int row = 0; row < GRID_SIZE_ROW; row++) {
            for (int col = 0; col < GRID_SIZE_COL; col++) {
                if (mines[row][col] && !flagged[row][col]) {
                    return; // Eine Mine wurde nicht markiert
                }
                if (!mines[row][col] && !revealed[row][col]) {
                    return; // Es gibt noch nicht aufgedeckte freie Felder
                }

            }
        }

        // Da alle Bedingungen erfüllt sind, Spieler hat gewonnen
        timer.stop();

        // Dialog für Neustart oder Beenden
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Herzlichen Glückwunsch! Du hast gewonnen!\nDu hast " + elapsedTime + " Sekunden gebraucht.\nMöchtest du ein neues Spiel starten?",
                "Gewonnen!",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            restartGame(); // Neues Spiel starten
        } else if(choice == JOptionPane.NO_OPTION) {
            this.dispose(); // Fenster schließen
            mainFrame.setVisible(true); // Hauptmenü zurückbringen
        }else{
            this.dispose(); // Fenster schließen
            mainFrame.setVisible(true); // Hauptmenü zurückbringen //Todo In priv Pc
        }
    }


    private void restartGame() {
        dispose(); // Aktuelles Fenster schließen
        MinesweeperDebug newGame = new MinesweeperDebug(mainFrame, GRID_SIZE_ROW, GRID_SIZE_COL, NUM_MINES);
        newGame.setVisible(true); // Neues Minesweeper-Spiel starten
    }

}