package Apps.Jobs.MatheAufgaben;

import Apps.Bank.Konto;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Aufgaben {
    static Random r = new Random();
    static int zahl1;
    static int zahl2;
    static double Antwort;
    static int ZeichenZahl;

    static JButton Verlassen = new JButton("Verlassen");
    static JTextField FrageText = new JTextField();
    static JTextField FrageEingabe = new JTextField(3);

    public static void AufgabenLogik(JFrame f, Konto konto) {
        JFrame frame = new JFrame("Mathe Aufgaben");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);

        frame.setSize(300,400);
        frame.setLayout(new GridLayout(4,1));



        Verlassen.setBackground(Color.RED);
        FrageText.setEditable(false);
        FrageText.setFocusable(false);


        NeueZahlen();

        FrageEingabe.addActionListener(e -> {
            Antwort = Double.parseDouble(FrageEingabe.getText());
            switch(ZeichenZahl) {
                case 0:
                    if (Antwort == zahl1 + zahl2) {
                        JOptionPane.showMessageDialog(frame, "Richtig  +1€");
                        konto.Geheimeinzahlen(1.0, "Job: MatheAufgaben Richtig");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Falsch -0.10€");
                        konto.Geheimabheben(0.1, konto.GetPin(), "Job: MatheAufgaben Falsch");
                    }
                    break;
                case 1:
                    if (Antwort == zahl1 - zahl2) {
                        JOptionPane.showMessageDialog(frame, "Richtig  +1€");
                        konto.Geheimeinzahlen(1.0, "Job: MatheAufgaben Richtig");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Falsch -0.10€");
                        konto.Geheimabheben(0.1, konto.GetPin(), "Job: MatheAufgaben Falsch");
                    }
                    break;
            }
            NeueZahlen();
        });

        Verlassen.addActionListener(e -> {
            frame.setVisible(false);
            f.setVisible(true);
        });

        frame.add(FrageText);
        frame.add(FrageEingabe);
        frame.add(Verlassen);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private static void NeueZahlen(){
        zahl1 = r.nextInt(100);
        zahl2 = r.nextInt(100);

        ZeichenZahl = r.nextInt(2);
        switch(ZeichenZahl) {
            case 0:
                FrageText.setText(zahl1 +"+"+zahl2);
                break;
            case 1:
                FrageText.setText(zahl1 +"-"+zahl2);
                break;
        }
        FrageEingabe.setText(null);
    }

}
