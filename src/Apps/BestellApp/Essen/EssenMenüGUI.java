package Apps.BestellApp.Essen;

import Apps.BestellApp.Essen.EssenAuswahl.*;
import Apps.Bank.Konto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EssenMenüGUI {

    public static void EssenMenü(Konto kundeKonto, Konto geschäftKonto, JFrame mainframe) {
        List<Essen> EssenListe = new ArrayList<>(); // Liste aller Essen
        EssenListe.add(new Pommes());
        EssenListe.add(new Hamburger());
        EssenListe.add(new Sushi());
        EssenListe.add(new Salat());
        EssenListe.add(new Pizza());
        EssenListe.add(new ChickenNuggets());










        JFrame Essenframe = new JFrame("Essen");
        Essenframe.setVisible(true);
        Essenframe.setSize(400, 500);
        Essenframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Essenframe.setLocationRelativeTo(null);
        Essenframe.setResizable(false);

        JPanel Essenpanel = new JPanel();
        Essenpanel.setLayout(new GridLayout(EssenListe.size(), 2)); // Zeilen = Anzahl Essen
        //Essenpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding außen
        Essenpanel.setBackground(Color.LIGHT_GRAY);

        // Dynamische Erstellung von Buttons basierend auf der Essenliste
        for (int i = 0; i < EssenListe.size(); i++) {
            Essen getränk = EssenListe.get(i); // Aktuelles Essen
            JButton button = new JButton(getränk.getName() + " " + getränk.getPreis() + "€");
            button.setFocusPainted(false);
            button.setBackground(Color.PINK);

            // ActionListener für jeden Button
            int finalI = i; // Speichere den Index für den Lambda-Listener
            button.addActionListener(e -> {
                Essen ausgewähltesEssen = EssenListe.get(finalI); // Hole das ausgewählte Essen

                // PIN-Abfrage und Guthabenprüfung
                String pinInput = JOptionPane.showInputDialog("Bitte geben Sie Ihre PIN ein:");
                try {
                    int pin = Integer.parseInt(pinInput); // PIN als Zahl
                    if (kundeKonto.CheckPin(pin)) {
                        if (kundeKonto.GeheimabhebenBool(ausgewähltesEssen.getPreis(), pin)) {
                            kundeKonto.Geheimabheben(ausgewähltesEssen.getPreis(), pin, ausgewähltesEssen.getName());
                            if (kundeKonto.Durch)
                                geschäftKonto.Geheimeinzahlen(ausgewähltesEssen.getPreis(), null);

                            JOptionPane.showMessageDialog(Essenframe, "Danke für Ihren Kauf von "
                                    + ausgewähltesEssen.getName() + " für " + ausgewähltesEssen.getPreis() + "€.");
                        } else {
                            JOptionPane.showMessageDialog(Essenframe, "Nicht genug Guthaben! Kauf nicht möglich.",
                                    "Fehler", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(Essenframe, "Falscher PIN!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Essenframe, "Ungültige Eingabe! Bitte geben Sie eine Zahl ein.",
                            "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Button dem Panel hinzufügen
            Essenpanel.add(button);
        }

        // "Verlassen"-Button hinzufügen
        JButton buttonVerlassen = new JButton("Verlassen");
        buttonVerlassen.setFocusPainted(false);
        buttonVerlassen.setBackground(Color.RED);
        buttonVerlassen.addActionListener(e -> {
            //System.out.println("Zurück zum Hauptmenü...");
            mainframe.setVisible(true);
            Essenframe.dispose(); // Schließe das Fenster
        });

        // Bottom Panel für Verlassen-Button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(buttonVerlassen);

        // Panels zum Frame hinzufügen
        Essenframe.add(Essenpanel, BorderLayout.CENTER);
        Essenframe.add(bottomPanel, BorderLayout.SOUTH);

        // Fenster sichtbar machen
        Essenframe.setVisible(true);
    }
}