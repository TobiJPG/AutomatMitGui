package Apps.Minispiele;

import Apps.Minispiele.MineSweeper.Debug.MinesweeperDebug;
import Apps.Minispiele.MineSweeper.Debug.MinesweeperMenüDebug;
import Apps.Minispiele.MineSweeper.MinesweeperMenü;
import Apps.Minispiele.SchereSteinPapier.SchereSteinPapier;
import Apps.Minispiele.TicTacToe.TicTacToe_bot_oder_2_Spieler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MiniSpieleMenue {
    //Todo Menü machen
    public static JFrame frame = new JFrame("MiniSpiele");
    public static void MiniSpielMenü(JFrame mainframe) {

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(300, 400);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setResizable(false);

        JPanel Mainpanel = new JPanel();
        Mainpanel.setLayout(new GridLayout(3, 2));

        JPanel VerlassenPanel = new JPanel();


        JButton SchereSteinPapierSpiel = new JButton("SchereSteinPapier");
        JButton TicTacToeSpiel = new JButton("TicTacToe ");
        JButton MineSweeper =  new JButton("MineSweeper");
        JButton NächstesSpiel2 =  new JButton("In Construction....");
        JButton Verlassen = new JButton("Verlassen");

        SchereSteinPapierSpiel.setFocusPainted(false);
        TicTacToeSpiel.setFocusPainted(false);
        NächstesSpiel2.setFocusPainted(false);
        MineSweeper.setFocusPainted(false);
        Verlassen.setFocusPainted(false);

        SchereSteinPapierSpiel.setBackground(Color.PINK);
        TicTacToeSpiel.setBackground(Color.PINK);
        MineSweeper.setBackground(Color.PINK);
        Verlassen.setBackground(Color.RED);

        SchereSteinPapierSpiel.addActionListener(e -> {
            frame.setVisible(false);
            SchereSteinPapier.main(frame);
        });

        TicTacToeSpiel.addActionListener(e -> {
            frame.setVisible(false);
            TicTacToe_bot_oder_2_Spieler.AuswahlTicTacToe(frame);
        });

        MineSweeper.addMouseListener(new CellClickListener());

        Verlassen.addActionListener(e -> {
            frame.dispose();
            mainframe.setVisible(true);
        });

        Mainpanel.add(SchereSteinPapierSpiel);
        Mainpanel.add(TicTacToeSpiel);
        Mainpanel.add(MineSweeper);
        Mainpanel.add(NächstesSpiel2);
        VerlassenPanel.add(Verlassen);



        frame.setLocationRelativeTo(null);

        frame.add(VerlassenPanel, BorderLayout.SOUTH);
        frame.add(Mainpanel);
        frame.setVisible(true);

    }

    private static class CellClickListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
                frame.setVisible(false);
                MinesweeperMenüDebug.MenüDebug(frame);

            } else if (SwingUtilities.isLeftMouseButton(e)) {
                frame.setVisible(false);
                MinesweeperMenü.Menü(frame);


            }
        }

    }

}
