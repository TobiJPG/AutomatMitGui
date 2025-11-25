import javax.swing.*;

import java.awt.*;

import Apps.BestellApp.BestellMenü;
import Apps.Bank.Konto;
import Apps.Bank.Main;
import Apps.Bank.Tools.KundenManager;
import Apps.Jobs.JobsMenü;
import Apps.Jobs.MatheAufgaben.Aufgaben;
import Apps.Minispiele.MiniSpieleMenue;

public class Gui {
    public static void main(String[] args){



        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }


        KundenManager kundenManager = new KundenManager();

        Konto StandardKonto = new Konto("Standard Konto", 1234, 100.0);
        kundenManager.addKonto(StandardKonto);
        kundenManager.setCurrentCustomer(StandardKonto.GetIban());

        Konto geschKunde1 = new Konto("Tobi's BestellApp", 2009, 100.0);
        kundenManager.addKonto(geschKunde1);


        JFrame mainframe = new JFrame("Handy");
        mainframe.setVisible(true);
        mainframe.setSize(300, 400);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainframe.setLocationRelativeTo(null);
        mainframe.setResizable(false);


        JPanel Mainpanel = new JPanel();
        Mainpanel.setLayout(new GridLayout(3,2,10,10));
        Mainpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding außen
        Mainpanel.setBackground(Color.LIGHT_GRAY);


        JButton buttonBestellen = new JButton("Bestell App");
        JButton buttonSnacks = new JButton("In Construction....");
        JButton buttonBankApp = new JButton("Bank App");
        JButton buttonGames = new JButton("Games");
        JButton buttonVerlassen = new JButton("Verlassen");

        buttonBestellen.setBackground(Color.PINK);
        buttonBestellen.setForeground(Color.BLACK);
        buttonBestellen.setFocusPainted(false);

        //buttonSnacks.setBackground(Color.PINK);
        buttonSnacks.setForeground(Color.BLACK);
        buttonSnacks.setFocusPainted(false);

        buttonBankApp.setBackground(Color.PINK);
        buttonBankApp.setForeground(Color.BLACK);
        buttonBankApp.setFocusPainted(false);

        buttonGames.setBackground(Color.PINK);
        buttonGames.setForeground(Color.BLACK);
        buttonGames.setFocusPainted(false);


        buttonVerlassen.setBackground(Color.RED);
        buttonVerlassen.setForeground(Color.BLACK);
        buttonVerlassen.setFocusPainted(false);




        //AllesZone => Amazon Anspielung....
        buttonBestellen.addActionListener(e -> {
            mainframe.setVisible(false);
            BestellMenü Lol = new BestellMenü();
            Lol.BestellMenü(mainframe, kundenManager.getCurrentCustomer(), geschKunde1);
        });

        //Paint
        buttonSnacks.addActionListener(e -> {
            mainframe.setVisible(false);
            JobsMenü.Menü(mainframe, kundenManager.getCurrentCustomer());
        });

        //Bank App
        buttonBankApp.addActionListener(e -> {
            mainframe.setVisible(false);
            Main.Bankmain(kundenManager, mainframe);
        });

        //MiniSpiele
        buttonGames.addActionListener(e -> {
            mainframe.setVisible(false);
            //SchereSteinPapier.main(mainframe);
            //TicTacToe_bot_oder_2_Spieler.AuswahlTicTacToe(mainframe);
            MiniSpieleMenue.MiniSpielMenü(mainframe);
        });


        //Verlassen
        buttonVerlassen.addActionListener(e -> {
            System.exit(0);
        });


        Mainpanel.add(buttonBestellen);
        Mainpanel.add(buttonSnacks);
        Mainpanel.add(buttonBankApp);
        Mainpanel.add(buttonGames);

        // Zentriere den Verlassen-Button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(buttonVerlassen);

        // Füge Panels zum Frame hinzu
        mainframe.add(Mainpanel, BorderLayout.CENTER); // Hauptpanel in der Mitte
        mainframe.add(bottomPanel, BorderLayout.SOUTH); // Verlassen-Button unten

        // Zeige das Hauptfenster
        mainframe.setVisible(true);
    }
}
