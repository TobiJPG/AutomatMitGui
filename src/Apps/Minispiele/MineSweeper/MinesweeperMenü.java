package Apps.Minispiele.MineSweeper;

import javax.swing.*;
import java.awt.*;


public class MinesweeperMenü{


    public static void Menü(JFrame frame){
        JFrame Menü = new JFrame("Schwierigkeit");


        Menü.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        Menü.setResizable(false);
        Menü.setSize(new Dimension(300, 400));

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
            Minesweeper game = new Minesweeper(Menü,8,8,10);
            Menü.setVisible(false);
            game.setVisible(true);
        });

        Fortgeschritten.addActionListener(e ->{
            Minesweeper game = new Minesweeper(Menü,16,16,40);
            Menü.setVisible(false);
            game.setVisible(true);
        });

        Experte.addActionListener(e ->{
            Minesweeper game = new Minesweeper(Menü,16,30,99);
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
        Menü.setLocationRelativeTo(null);
        Menü.setVisible(true);

    }
}
