package Apps.BestellApp.Getränke;

import Apps.BestellApp.Getränke.GetränkeAuswahl.*;
import Apps.Bank.Konto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GetränkeMenüPanel {

    public static JPanel createPanel(Konto kundeKonto, Konto geschäftKonto, JFrame mainframe) {
        // Liste aller Getränke
        List<Getränk> getränkeListe = new ArrayList<>();
        getränkeListe.add(new Cola());
        getränkeListe.add(new Fanta());
        getränkeListe.add(new Wasser());
        getränkeListe.add(new Eistee());
        getränkeListe.add(new Powerade());
        getränkeListe.add(new Kaffee());

        // Hauptpanel für das Menü
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel für Checkboxen
        JPanel getränkePanel = new JPanel(new GridLayout(getränkeListe.size(), 1,5,5));
        getränkePanel.setBackground(Color.LIGHT_GRAY);

        // Dynamische Erstellung der Checkboxen
        for (Getränk g : getränkeListe) {
            JCheckBox cb = new JCheckBox(g.getName() + " " + g.getPreis() + "€");
            cb.setBackground(Color.LIGHT_GRAY);

            // Getränk-Objekt an die Checkbox hängen
            cb.putClientProperty("item", g);


            //checkBoxListe.add(cb);
            getränkePanel.add(cb);
        }


        // Panels ins Hauptpanel
        mainPanel.add(getränkePanel, BorderLayout.NORTH);

        return mainPanel;
    }
}