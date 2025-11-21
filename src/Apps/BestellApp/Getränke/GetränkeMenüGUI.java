package Apps.BestellApp.Getränke;

import Apps.BestellApp.Getränke.GetränkeAuswahl.*;
import Apps.Bank.Konto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GetränkeMenüGUI {

    public static void GetränkeMenü(Konto kundeKonto, Konto geschäftKonto, JFrame mainframe) {
        List<Getränk> getränkeListe = new ArrayList<>(); // Liste aller Getränke
        getränkeListe.add(new Cola());
        getränkeListe.add(new Fanta());
        getränkeListe.add(new Wasser());
        getränkeListe.add(new Eistee());
        getränkeListe.add(new Powerade());
        getränkeListe.add(new Kaffee());











        JFrame Getränkeframe = new JFrame("Getränke");
        Getränkeframe.setVisible(true);
        Getränkeframe.setSize(400, 500);
        Getränkeframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Getränkeframe.setLocationRelativeTo(null);
        Getränkeframe.setResizable(false);

        JPanel Getränkepanel = new JPanel();
        Getränkepanel.setLayout(new GridLayout(getränkeListe.size(), 2)); // Zeilen = Anzahl Getränke
        //Getränkepanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding außen
        Getränkepanel.setBackground(Color.LIGHT_GRAY);

        // Dynamische Erstellung von Buttons basierend auf der Getränkeliste
        for (int i = 0; i < getränkeListe.size(); i++) {
            Getränk getränk = getränkeListe.get(i); // Aktuelles Getränk
            JButton button = new JButton(getränk.getName() + " " + getränk.getPreis() + "€");
            button.setFocusPainted(false);
            button.setBackground(Color.PINK);

            // ActionListener für jeden Button
            int finalI = i; // Speichere den Index für den Lambda-Listener
            button.addActionListener(e -> {
                Getränk ausgewähltesGetränk = getränkeListe.get(finalI); // Hole das ausgewählte Getränk

                // PIN-Abfrage und Guthabenprüfung
                String pinInput = JOptionPane.showInputDialog("Bitte geben Sie Ihre PIN ein:");
                try {
                    int pin = Integer.parseInt(pinInput); // PIN als Zahl
                    if (kundeKonto.CheckPin(pin)) {
                        if (kundeKonto.GeheimabhebenBool(ausgewähltesGetränk.getPreis(), pin)) {
                            kundeKonto.Geheimabheben(ausgewähltesGetränk.getPreis(), pin, ausgewähltesGetränk.getName());
                            if (kundeKonto.Durch)
                                geschäftKonto.Geheimeinzahlen(ausgewähltesGetränk.getPreis(), null);

                            JOptionPane.showMessageDialog(Getränkeframe, "Danke für Ihren Kauf von "
                                    + ausgewähltesGetränk.getName() + " für " + ausgewähltesGetränk.getPreis() + "€.");
                        } else {
                            JOptionPane.showMessageDialog(Getränkeframe, "Nicht genug Guthaben! Kauf nicht möglich.",
                                    "Fehler", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(Getränkeframe, "Falscher PIN!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Getränkeframe, "Ungültige Eingabe! Bitte geben Sie eine Zahl ein.",
                            "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Button dem Panel hinzufügen
            Getränkepanel.add(button);
        }

        // "Verlassen"-Button hinzufügen
        JButton buttonVerlassen = new JButton("Verlassen");
        buttonVerlassen.setFocusPainted(false);
        buttonVerlassen.setBackground(Color.RED);
        buttonVerlassen.addActionListener(e -> {
            //System.out.println("Zurück zum Hauptmenü...");
            mainframe.setVisible(true);
            Getränkeframe.dispose(); // Schließe das Fenster
        });

        // Bottom Panel für Verlassen-Button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(buttonVerlassen);

        // Panels zum Frame hinzufügen
        Getränkeframe.add(Getränkepanel, BorderLayout.CENTER);
        Getränkeframe.add(bottomPanel, BorderLayout.SOUTH);

        // Fenster sichtbar machen
        Getränkeframe.setVisible(true);
    }
}