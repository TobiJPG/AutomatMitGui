package Apps.BestellApp;

import Apps.Bank.Konto;
import Apps.BestellApp.Essen.EssenMenüPanel;
import Apps.BestellApp.Getränke.GetränkeMenüPanel;
import Apps.BestellApp.PizzaCreater.PizzaCreater;
import Apps.BestellApp.Snacks.SnackMenüPanel;


import javax.swing.*;
import java.awt.*;


public class NeuesBestellMenü {


    public static void Menü(Konto kundeKonto, Konto geschäftKonto, JFrame mainframe){
        JFrame frame = new JFrame("Bestellen");
        frame.setSize(300,400);
        frame.setLayout(new BorderLayout());
        JButton Verlassen = new JButton("Verlassen");
        JButton Bestellen = new JButton("Bestellen");
        Verlassen.setBackground(Color.WHITE);
        Bestellen.setBackground(Color.WHITE);
        JPanel buttonPanel = new JPanel();
        Verlassen.setForeground(Color.RED);
        Bestellen.setForeground(Color.GREEN);
        buttonPanel.add(Verlassen);
        buttonPanel.add(Bestellen);
        frame.setResizable(false);
        JTabbedPane tabbedPane = new JTabbedPane();



        tabbedPane.add("Getränke", GetränkeMenüPanel.createPanel(kundeKonto, geschäftKonto, mainframe));
        tabbedPane.add("Snacks", SnackMenüPanel.createPanel(kundeKonto, geschäftKonto, mainframe));
        tabbedPane.add("Essen", EssenMenüPanel.createPanel(kundeKonto, geschäftKonto, mainframe));
        tabbedPane.add("Pizza Maker", PizzaCreater.PizzaMachen(kundeKonto,geschäftKonto));

        Verlassen.addActionListener(e -> {
            mainframe.setVisible(true);
            frame.setVisible(false);
        });



        Bestellen.addActionListener(e -> {
            double gesamtPreis = 0.0;
            StringBuilder gekaufteItems = new StringBuilder();

            // Alle Tabs durchlaufen
            for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                Component comp = tabbedPane.getComponentAt(i);

                if (comp instanceof JPanel panel) {
                    for (Component c : panel.getComponents()) {
                        if (c instanceof JPanel innerPanel) {
                            for (Component innerComp : innerPanel.getComponents()) {
                                if (innerComp instanceof JCheckBox cb && cb.isSelected()) {
                                    Object item = cb.getClientProperty("item");

                                    if (item instanceof Apps.BestellApp.Getränke.Getränk g) {
                                        gesamtPreis += g.getPreis();
                                        gekaufteItems.append(g.getName()).append(", ");
                                    } else if (item instanceof Apps.BestellApp.Snacks.Snack s) {
                                        gesamtPreis += s.getPreis();
                                        gekaufteItems.append(s.getName()).append(", ");
                                    } else if (item instanceof Apps.BestellApp.Essen.Essen eItem) {
                                        gesamtPreis += eItem.getPreis();
                                        gekaufteItems.append(eItem.getName()).append(", ");
                                    }}}}}}}

            // PIN abfragen
            String pinStr = JOptionPane.showInputDialog(frame, "Gesamtpreis: " + gesamtPreis + "€ |Bitte PIN eingeben:");
            int pinEingabe = Integer.parseInt(pinStr);

            // Kauf durchführen
            kundeKonto.Geheimabheben(gesamtPreis, pinEingabe, gekaufteItems.toString());
            geschäftKonto.Geheimeinzahlen(gesamtPreis,gekaufteItems.toString());
            if (kundeKonto.Durch) {
                JOptionPane.showMessageDialog(frame, "Kauf erfolgreich! Gesamtpreis: " + gesamtPreis + "€");
            } else {
                JOptionPane.showMessageDialog(frame, "Bezahlung nicht möglich!");
            }
        });

        frame.add(buttonPanel,BorderLayout.SOUTH);
        frame.add(tabbedPane, BorderLayout.NORTH);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(300,400);
        frame.setVisible(true);
    }




}
