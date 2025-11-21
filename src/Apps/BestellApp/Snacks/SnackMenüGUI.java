package Apps.BestellApp.Snacks;

import Apps.BestellApp.Snacks.SnackAuswahl.*;
import Apps.Bank.Konto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SnackMenüGUI {

    public static void SnackMenü(Konto kundeKonto, Konto geschäftKonto, JFrame mainframe) {
        // Liste aller Snacks
        List<Snack> snackListe = new ArrayList<>();
        snackListe.add(new Chips());
        snackListe.add(new Yogurette());
        snackListe.add(new Nüsse());
        snackListe.add(new Trocken_Früchte());
        snackListe.add(new Sonnenblumenkerne());
        snackListe.add(new Kinderriegel());





        // GUI-Frame erstellen
        JFrame snackFrame = new JFrame("Snacks");
        snackFrame.setSize(400, 500);
        snackFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        snackFrame.setLayout(new BorderLayout());
        snackFrame.setLocationRelativeTo(null);
        snackFrame.setResizable(false);

        // Hauptpanel für Buttons
        JPanel snackPanel = new JPanel();
        snackPanel.setLayout(new GridLayout(snackListe.size(), 2)); // 5 Zeilen, 1 Spalte für Snacks
        //snackPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        snackPanel.setBackground(Color.LIGHT_GRAY);

        // Dynamische Variablen-Initialisierung für die Snackauswahl
        final int[] snackAuswahl = {-1};

        // Dynamische Buttons für jedes Snack hinzufügen
        for (int i = 0; i < snackListe.size(); i++) {
            Snack snack = snackListe.get(i); // Hole das aktuelle Snack-Objekt
            JButton button = new JButton(snack.getName() + " " + snack.getPreis() + "€");// Button-Text
            button.setFocusPainted(false);
            button.setBackground(Color.PINK);
            button.setSize(50, 50);

            // ActionListener für den Button
            int finalI = i; // Speichere Index, um ihn im Listener korrekt zu verwenden
            button.addActionListener(e -> {
                snackAuswahl[0] = finalI + 1; // Aktualisiere die Auswahl
                Snack ausgewählterSnack = snackListe.get(finalI);

                // Abfrage der PIN zur Autorisierung des Kaufes
                String pinInput = JOptionPane.showInputDialog("Bitte geben Sie Ihre PIN ein:");
                try {
                    int pin = Integer.parseInt(pinInput);

                    if (kundeKonto.CheckPin(pin)) {
                        if (kundeKonto.GeheimabhebenBool(ausgewählterSnack.getPreis(), pin)) {
                            kundeKonto.Geheimabheben(ausgewählterSnack.getPreis(), pin, ausgewählterSnack.getName());
                            if (kundeKonto.Durch) {
                                geschäftKonto.Geheimeinzahlen(ausgewählterSnack.getPreis(), null);
                            }
                            JOptionPane.showMessageDialog(snackFrame, "Danke für Ihren Kauf von "
                                    + ausgewählterSnack.getName() + " für " + ausgewählterSnack.getPreis() + "€.");
                        } else {
                            JOptionPane.showMessageDialog(snackFrame,
                                    "Nicht genug Guthaben! Kauf nicht möglich.",
                                    "Fehler",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(snackFrame, "Falscher PIN!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(snackFrame, "Ungültige PIN! Bitte erneut versuchen.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            });

            snackPanel.add(button); // Füge den Button dem Panel hinzu
        }

        // Verlassen-Button zum Rückgang ins Hauptmenü
        JButton buttonVerlassen = new JButton("Verlassen");
        buttonVerlassen.setFocusPainted(false);
        buttonVerlassen.setBackground(Color.RED);
        buttonVerlassen.addActionListener(e -> {
            //System.out.println("Zurück zum Hauptmenü...");
            mainframe.setVisible(true);
            snackFrame.dispose(); // Fenster schließen
        });

        // Bottom Panel für Verlassen-Button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(buttonVerlassen);

        // Panels zum Frame hinzufügen
        snackFrame.add(snackPanel, BorderLayout.CENTER); // Snack-Buttons in der Mitte
        snackFrame.add(bottomPanel, BorderLayout.SOUTH); // Verlassen-Button unten

        // Frame sichtbar machen
        snackFrame.setVisible(true);
    }
}