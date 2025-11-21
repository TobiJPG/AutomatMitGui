package Apps.Minispiele.MineSweeper.Debug;

import Apps.Minispiele.MineSweeper.Minesweeper;

import javax.swing.*;
import java.awt.*;


public class MinesweeperMenüDebug {


    public static void MenüDebug(JFrame frame){
        JFrame Menü = new JFrame("Schwierigkeit");


        Menü.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        Menü.setLocationRelativeTo(null);
        Menü.setResizable(false);
        Menü.setSize(new Dimension(300, 150));

        JButton Anfänger = new JButton("Anfänger"); // 8x8 10 minen
        JButton Fortgeschritten = new JButton("Fortgeschritten"); // 16x16 40 minen
        JButton Experte = new JButton("Experte"); // 30x16 99 minen

        Anfänger.setBackground(Color.yellow);
        Fortgeschritten.setBackground(Color.orange);
        Experte.setBackground(Color.red);

        Anfänger.setFocusable(false);
        Fortgeschritten.setFocusable(false);
        Experte.setFocusable(false);

        JButton Verlassen = new JButton("Verlassen");
        Verlassen.setBackground(Color.white);
        Verlassen.setForeground(Color.red);
        Verlassen.setFont(new Font("Arial", Font.BOLD, 20));
        Verlassen.setSize(50, 50);
        Verlassen.setFocusable(false);

        Verlassen.addActionListener(e -> {
            Menü.dispose();
            frame.setVisible(true);
        });


        Anfänger.addActionListener(e ->{
            MinesweeperDebug game = new MinesweeperDebug(Menü,8,8,10);
            Menü.setVisible(false);
            game.setVisible(true);
        });

        Fortgeschritten.addActionListener(e ->{
            MinesweeperDebug game = new MinesweeperDebug(Menü,16,16,40);
            Menü.setVisible(false);
            game.setVisible(true);
        });

        Experte.addActionListener(e ->{
            MinesweeperDebug game = new MinesweeperDebug(Menü,16,30,99);
            Menü.setVisible(false);
            game.setVisible(true);
        });


        JPanel Schwer = new JPanel();
        Schwer.setLayout(new GridLayout());




        Schwer.add(Anfänger);
        Schwer.add(Fortgeschritten);
        Schwer.add(Experte);



        Menü.add(Verlassen, BorderLayout.SOUTH);
        Menü.add(Schwer, BorderLayout.NORTH);
        Menü.setVisible(true);

    }
}
