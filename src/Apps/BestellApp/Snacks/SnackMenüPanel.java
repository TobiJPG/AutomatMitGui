package Apps.BestellApp.Snacks;

import Apps.Bank.Konto;
import Apps.BestellApp.Snacks.SnackAuswahl.*;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SnackMenüPanel {

    public static JPanel createPanel(Konto kundeKonto, Konto geschäftKonto, JFrame mainframe) {
        // Liste aller Snack
        List<Snack> SnackListe = new ArrayList<>();
        SnackListe.add(new Chips());
        SnackListe.add(new Yogurette());
        SnackListe.add(new Nüsse());
        SnackListe.add(new Trocken_Früchte());
        SnackListe.add(new Sonnenblumenkerne());
        SnackListe.add(new Kinderriegel());

        // Hauptpanel für das Menü
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel für Checkboxen
        JPanel SnackPanel = new JPanel(new GridLayout(SnackListe.size(), 1,5,5));
        SnackPanel.setBackground(Color.LIGHT_GRAY);

        // Liste der Checkboxen
        //List<JCheckBox> checkBoxListe = new ArrayList<>();

        // Dynamische Erstellung der Checkboxen
        for (Snack g : SnackListe) {
            JCheckBox cb = new JCheckBox(g.getName() + " " + g.getPreis() + "€");
            cb.setBackground(Color.LIGHT_GRAY);

            // Getränk-Objekt an die Checkbox hängen
            cb.putClientProperty("item", g);

            //checkBoxListe.add(cb);
            SnackPanel.add(cb);
        }


        // Panels ins Hauptpanel
        mainPanel.add(SnackPanel, BorderLayout.NORTH);

        return mainPanel;
    }
}