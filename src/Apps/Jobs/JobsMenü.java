package Apps.Jobs;

import Apps.Bank.Konto;
import Apps.Bank.Main;
import Apps.Jobs.MatheAufgaben.Aufgaben;

import javax.swing.*;
import java.awt.*;
import java.security.KeyPair;

public class JobsMenü {

    public static void Menü(JFrame Mainframe,Konto konto){
        JFrame frame = new JFrame();
        frame.setTitle("Jobs");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(300,400);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10,10));

        JButton Verlassen = new JButton("Verlassen");
        JButton Mathe = new JButton("Mathe Aufgaben");
        JButton XXXXX = new JButton("XXXXX");
        JButton XXX = new JButton("XXXXX");
        JButton XX = new JButton("XXXXX");

        Mathe.setFocusable(false);
        XX.setFocusable(false);
        XXXXX.setFocusable(false);
        XXX.setFocusable(false);
        Verlassen.setFocusable(false);


        Mathe.setBackground(Color.PINK);
        XXXXX.setBackground(Color.PINK);
        XXX.setBackground(Color.PINK);
        XX.setBackground(Color.PINK);
        Verlassen.setBackground(Color.RED);

        Mathe.addActionListener(e-> {
            frame.setVisible(false);
            Aufgaben.AufgabenLogik(frame, konto);
        });

        Verlassen.addActionListener(e-> {
            frame.setVisible(false);
            Mainframe.setVisible(true);
        });







        panel.add(Mathe);
        panel.add(XXXXX);
        panel.add(XXX);
        panel.add(XX);
        frame.add(panel);
        frame.add(Verlassen, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
