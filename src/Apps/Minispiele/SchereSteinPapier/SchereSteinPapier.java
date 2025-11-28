package Apps.Minispiele.SchereSteinPapier;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SchereSteinPapier {

    public static void main(JFrame frame) {

        JFrame SchereSteinPapier = new JFrame();
        SchereSteinPapier.setTitle("Schere Stein Papier");
        SchereSteinPapier.setResizable(false);
        SchereSteinPapier.setSize(300,200);
        SchereSteinPapier.setLocationRelativeTo(null);
        SchereSteinPapier.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);



        JPanel SchereSteinPapierPanel = new JPanel();
        SchereSteinPapierPanel.setLayout(new GridLayout(1,2,5,5));
        SchereSteinPapierPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        SchereSteinPapierPanel.setBackground(Color.LIGHT_GRAY);


        JLabel text = new JLabel("Bitte wähle eins aus oder drücke auf Verlassen");

        JButton Schere = new JButton(new ImageIcon("src/Bilder/icons8-schere-100.png"));
        Schere.setBackground(Color.WHITE);

        JButton Stein = new JButton(new ImageIcon("src/Bilder/icons8-stein-100.png"));
        Stein.setBackground(Color.WHITE);

        JButton Papier = new JButton(new ImageIcon("src/Bilder/icons8-papier-100.png"));
        Papier.setBackground(Color.WHITE);

        JButton verlassen = new JButton("Verlassen");
        verlassen.setBackground(Color.WHITE);
        verlassen.setForeground(Color.RED);

        Schere.setFocusPainted(false);

        Stein.setFocusPainted(false);

        Papier.setFocusPainted(false);

        verlassen.setFocusPainted(false);


        Schere.addActionListener(e -> {
            SpielGUI("Schere", RandomZahl(),Schere,Stein,Papier);
        });

        Stein.addActionListener(e -> {
            SpielGUI("Stein", RandomZahl(),Schere,Stein,Papier);
        });
        Papier.addActionListener(e -> {
            SpielGUI("Papier", RandomZahl(),Schere,Stein,Papier);
        });














        SchereSteinPapierPanel.add(Schere);
        SchereSteinPapierPanel.add(Stein);
        SchereSteinPapierPanel.add(Papier);



        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(verlassen);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(text);



        SchereSteinPapier.add(SchereSteinPapierPanel, BorderLayout.CENTER);
        SchereSteinPapier.add(bottomPanel, BorderLayout.SOUTH);
        SchereSteinPapier.add(topPanel, BorderLayout.NORTH);

        verlassen.addActionListener(e -> {
            frame.setVisible(true);
            SchereSteinPapier.dispose();
        });

        SchereSteinPapier.setVisible(true);
    }

    public static String RandomZahl(){
        Random rand = new Random();
        String auswahlComputer = "";
        int CAuswahl =  rand.nextInt(3);
        switch(CAuswahl){
            case 0:
                auswahlComputer = "Schere";
                break;
            case 1:
                auswahlComputer = "Stein";
                break;
            case 2:
                auswahlComputer = "Papier";
                break;

        }

        return auswahlComputer;
    }

    private static int Gewonnen = 0;
    private static int Verloren = 0;
    private static int Unentschieden = 0;

    public static void Spiel(String AuswahlSpieler, String AuswahlComputer){
        JFrame spielFrame = new JFrame("Spiel");


        if(AuswahlSpieler.equals(AuswahlComputer)){
            Unentschieden++;
            JOptionPane.showMessageDialog(spielFrame, "Unentschieden\n" +
                    "Computer: " + AuswahlComputer+"\n" +
                    "Spieler: " + AuswahlSpieler+"\n" +
                    "\n"+
                    "Gewonnen:" + Gewonnen +"\n" +
                    "Verloren:" + Verloren + "\n" +
                    "Unentschieden:" + Unentschieden
            );


        }else{
            if(AuswahlSpieler.equals("Schere") && AuswahlComputer.equals("Papier") ||
            AuswahlSpieler.equals("Stein") && AuswahlComputer.equals("Schere") ||
            AuswahlSpieler.equals("Papier") && AuswahlComputer.equals("Stein")){
                Gewonnen++;
                JOptionPane.showMessageDialog(spielFrame,"Gewonnen\n" +
                        "Computer: " + AuswahlComputer+"\n" +
                        "Spieler: " + AuswahlSpieler +"\n" +
                        "\n" +
                        "Gewonnen:" + Gewonnen +"\n" +
                        "Verloren:" + Verloren + "\n" +
                        "Unentschieden:" + Unentschieden
                );


            }else{
                Verloren += 1;
                JOptionPane.showMessageDialog(spielFrame,"Verloren\n"+
                        "Computer: " + AuswahlComputer+"\n" +
                        "Spieler: " + AuswahlSpieler + "\n" +
                        "\n" +
                        "Gewonnen:" + Gewonnen +"\n" +
                        "Verloren:" + Verloren + "\n" +
                        "Unentschieden:" + Unentschieden
                );

            }
        }
    }

    public static void SpielGUI(String AuswahlSpieler, String AuswahlComputer, JButton Schere, JButton Stein, JButton Papier){
        Schere.setEnabled(false);
        Stein.setEnabled(false);
        Papier.setEnabled(false);

        JFrame spielFrameGUI = new JFrame("Spiel");
        spielFrameGUI.setSize(250,350);
        spielFrameGUI.setResizable(false);
        spielFrameGUI.setLayout(new BorderLayout());
        spielFrameGUI.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        spielFrameGUI.setLocationRelativeTo(null);

        JPanel spielPanelGUI = new JPanel();
        spielPanelGUI.setBounds(0,0,250,200);
        spielPanelGUI.setLayout(new GridLayout(2, 1,1,5));
        spielPanelGUI.setBackground(Color.LIGHT_GRAY);

        JPanel SpielerErg = new JPanel();
        JPanel ComputerErg = new JPanel();
        SpielerErg.setLayout(new GridLayout(1, 2,25,5));
        SpielerErg.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        ComputerErg.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        ComputerErg.setLayout(new GridLayout(1, 2,25,5));
        JPanel Ergebnis = new JPanel();
        JLabel Ergenisssss = new JLabel();
        Ergebnis.setBackground(Color.LIGHT_GRAY);
        Ergebnis.setSize(250,20);



        JPanel panelOK_und_Ergebnisse = new JPanel();
        panelOK_und_Ergebnisse.setBounds(0,200,250,150);
        panelOK_und_Ergebnisse.setLayout(new GridLayout(4,1));
        panelOK_und_Ergebnisse.setBackground(Color.darkGray);

        JLabel GewonnenLable = new JLabel();
        JLabel VerlorenLable = new JLabel();
        JLabel UnentschiedenLable = new JLabel();
        GewonnenLable.setForeground(Color.GREEN);
        VerlorenLable.setForeground(Color.RED);
        UnentschiedenLable.setForeground(Color.YELLOW);
        JButton OK = new JButton("OK");
        OK.setSize(new Dimension(250,50));
        OK.setBackground(Color.LIGHT_GRAY);
        OK.setFocusPainted(false);





        JLabel labelSpielerUn = new JLabel();
        JLabel labelComputerUn = new JLabel();
        JLabel spielLabelSpieler = new JLabel();
        JLabel spielLabelComputer = new JLabel();
        labelComputerUn.setBackground(Color.DARK_GRAY);
        labelSpielerUn.setBackground(Color.DARK_GRAY);
        spielLabelComputer.setBackground(Color.DARK_GRAY);
        spielLabelSpieler.setBackground(Color.DARK_GRAY);



        spielLabelSpieler.setIcon(new ImageIcon("src/Bilder/icons8-mensch-100.png"));
        spielLabelComputer.setIcon(new ImageIcon("src/Bilder/icons8-bot-100.png"));
        spielFrameGUI.add(spielPanelGUI);//, BorderLayout.NORTH);
        if(AuswahlSpieler.equals(AuswahlComputer)){
            Unentschieden++;
            Ergenisssss.setText("UNENTSCHIEDEN");
            Ergebnis.setBackground(Color.YELLOW);

            labelSpielerUn.setIcon(new ImageIcon("src/Bilder/icons8-"+AuswahlSpieler.toLowerCase()+"-100.png"));
            labelComputerUn.setIcon(new ImageIcon("src/Bilder/icons8-"+AuswahlComputer.toLowerCase()+"-100.png"));

            GewonnenLable.setText( "Gewonnen: " + Gewonnen);
            VerlorenLable.setText("Verloren: " + Verloren);
            UnentschiedenLable.setText("Unentschieden: " + Unentschieden);

        }else{
            if(AuswahlSpieler.equals("Schere") && AuswahlComputer.equals("Papier") ||
                    AuswahlSpieler.equals("Stein") && AuswahlComputer.equals("Schere") ||
                    AuswahlSpieler.equals("Papier") && AuswahlComputer.equals("Stein")){
                Gewonnen++;
                Ergenisssss.setText("GEWONNEN");
                Ergebnis.setBackground(Color.GREEN);

                labelSpielerUn.setIcon(new ImageIcon("src/Bilder/icons8-"+AuswahlSpieler.toLowerCase()+"-100.png"));
                labelComputerUn.setIcon(new ImageIcon("src/Bilder/icons8-"+AuswahlComputer.toLowerCase()+"-100.png"));

                GewonnenLable.setText( "Gewonnen: " + Gewonnen);
                VerlorenLable.setText("Verloren: " + Verloren);
                UnentschiedenLable.setText("Unentschieden: " + Unentschieden);

            }else{
                Verloren++;
                Ergenisssss.setText("VERLOREN");
                Ergebnis.setBackground(Color.RED);

                labelSpielerUn.setIcon(new ImageIcon("src/Bilder/icons8-"+AuswahlSpieler.toLowerCase()+"-100.png"));
                labelComputerUn.setIcon(new ImageIcon("src/Bilder/icons8-"+AuswahlComputer.toLowerCase()+"-100.png"));

                GewonnenLable.setText( "Gewonnen: " + Gewonnen);
                VerlorenLable.setText("Verloren: " + Verloren);
                UnentschiedenLable.setText("Unentschieden: " + Unentschieden);
            }
        }
        panelOK_und_Ergebnisse.add(GewonnenLable);
        panelOK_und_Ergebnisse.add(VerlorenLable);
        panelOK_und_Ergebnisse.add(UnentschiedenLable);
        panelOK_und_Ergebnisse.add(OK, BorderLayout.SOUTH);


        SpielerErg.add(spielLabelSpieler, BorderLayout.SOUTH);
        ComputerErg.add(spielLabelComputer, BorderLayout.SOUTH);
        SpielerErg.add(labelSpielerUn);
        ComputerErg.add(labelComputerUn);
        spielPanelGUI.add(ComputerErg);
        spielPanelGUI.add(SpielerErg);
        Ergebnis.add(Ergenisssss, BorderLayout.NORTH);

        spielFrameGUI.add(Ergebnis, BorderLayout.NORTH);
        spielFrameGUI.add(spielPanelGUI, BorderLayout.CENTER);
        spielFrameGUI.add(panelOK_und_Ergebnisse, BorderLayout.SOUTH);






        OK.addActionListener(e -> {
            Schere.setEnabled(true);
            Stein.setEnabled(true);
            Papier.setEnabled(true);
            spielFrameGUI.dispose();
        });
        spielFrameGUI.setVisible(true);

    }
}
