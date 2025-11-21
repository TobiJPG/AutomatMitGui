package Apps.BestellApp;

import Apps.Bank.Konto;
import Apps.BestellApp.Essen.EssenMenüGUI;
import Apps.BestellApp.Getränke.GetränkeMenüGUI;
import Apps.BestellApp.PizzaCreater.PizzaCreater;
import Apps.BestellApp.Snacks.SnackMenüGUI;

import javax.swing.*;
import java.awt.*;

public class BestellMenü extends JFrame {

    public void BestellMenü(JFrame frame, Konto kundenManager, Konto geschKunde1) {
        setSize(200, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        setBackground(Color.DARK_GRAY);

        JButton Getränke = new JButton("Getränke");
        JButton Snacks = new JButton("Snacks");
        JButton Essen = new JButton("Essen");
        JButton PizzaMachen = new JButton("Pizza Auswählen");

        Getränke.setBackground(Color.pink);
        Snacks.setBackground(Color.pink);
        Essen.setBackground(Color.pink);
        PizzaMachen.setBackground(Color.pink);

        Getränke.setFocusable(false);
        Snacks.setFocusable(false);
        Essen.setFocusable(false);
        PizzaMachen.setFocusable(false);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1,10,10));
        panel.setBackground(Color.lightGray);
        JButton Verlassen = new JButton("Verlassen");
        Verlassen.setFocusable(false);
        Verlassen.setBackground(Color.RED);


        panel.add(Getränke);
        panel.add(Snacks);
        panel.add(Essen);
        panel.add(PizzaMachen);


        Getränke.addActionListener(e -> {
            setVisible(false);
            GetränkeMenüGUI.GetränkeMenü(kundenManager, geschKunde1, this);
        });

        Snacks.addActionListener(e -> {
            setVisible(false);
            SnackMenüGUI.SnackMenü(kundenManager, geschKunde1, this);
        });

        Essen.addActionListener(e ->{
            setVisible(false);
            EssenMenüGUI.EssenMenü(kundenManager, geschKunde1, this);
        });

        PizzaMachen.addActionListener(e->{
            setVisible(false);
            PizzaCreater.PizzaMachen(this, kundenManager,geschKunde1);
        });



        Verlassen.addActionListener(e -> {
            dispose();
            frame.setVisible(true);
        });

        add(Verlassen, BorderLayout.SOUTH);
        add(panel);
        setVisible(true);
    }

}
