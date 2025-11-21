package Apps.Minispiele.TicTacToe;

import javax.swing.*;
import java.awt.*;

public class TicTacToe_bot_oder_2_Spieler {
    // Auswahl des Spielmodus
    public static void AuswahlTicTacToe(JFrame mainframe) {
        JFrame frame = new JFrame("TicTacToe Gegner Wahl");
        frame.setSize(228, 190);
        frame.setTitle("TicTacToe Spieler");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setBackground(Color.LIGHT_GRAY);

        JLabel text = new JLabel();
        text.setText("Bitte eins auswählen");
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setFont(new Font("Arial", Font.BOLD, 16));
        text.setBounds(0, 0, 220, 50);
        text.setForeground(Color.BLACK);

        JButton Spieler = new JButton("2 Spieler");
        Spieler.setBackground(Color.GREEN);
        Spieler.setBounds(0, 50, 100, 50);


        JButton Bot = new JButton("Bot");
        Bot.setBackground(Color.YELLOW);
        Bot.setBounds(110, 50, 100, 50);

        JButton Verlassen = new JButton("Verlassen");
        Verlassen.setBackground(Color.RED);
        Verlassen.setBounds(52, 100, 100, 50);





        Verlassen.addActionListener(e-> {
            frame.dispose();
            mainframe.setVisible(true);
        });

        Spieler.addActionListener(e->{
            frame.dispose();
           TicTacToe.TicTacToeSpiel(false, mainframe);
        });
        Bot.addActionListener(e->{
            frame.dispose();
            TicTacToe.TicTacToeSpiel(true, mainframe);
        });


        frame.add(text, BorderLayout.NORTH);
        frame.add(Verlassen);
        frame.add(Spieler);
        frame.add(Bot);


        frame.setVisible(true);
    }

}
