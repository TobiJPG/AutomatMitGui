package Apps.BestellApp.Essen;

import Apps.Bank.Konto;
import Apps.BestellApp.Essen.EssenAuswahl.*;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EssenMenüPanel {

    public static JPanel createPanel(Konto kundeKonto, Konto geschäftKonto, JFrame mainframe) {
        // Liste aller Essen
        List<Essen> EssenListe = new ArrayList<>(); // Liste aller Essen
        EssenListe.add(new Pommes());
        EssenListe.add(new Hamburger());
        EssenListe.add(new Sushi());
        EssenListe.add(new Salat());
        EssenListe.add(new Pizza());
        EssenListe.add(new ChickenNuggets());

        // Hauptpanel für das Menü
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel für Checkboxen
        JPanel EssenPanel = new JPanel(new GridLayout(EssenListe.size(), 1,5,5));
        EssenPanel.setBackground(Color.LIGHT_GRAY);

        // Liste der Checkboxen
        //List<JCheckBox> checkBoxListe = new ArrayList<>();

        // Dynamische Erstellung der Checkboxen
        // Beispiel für EssenMenüPanel
        for (Essen g : EssenListe) {
            JCheckBox cb = new JCheckBox(g.getName() + " " + g.getPreis() + "€");
            cb.setBackground(Color.LIGHT_GRAY);

            // Objekt an Checkbox hängen
            cb.putClientProperty("item", g);

            EssenPanel.add(cb);
        }


        // Panels ins Hauptpanel
        mainPanel.add(EssenPanel, BorderLayout.NORTH);

        return mainPanel;
    }
}