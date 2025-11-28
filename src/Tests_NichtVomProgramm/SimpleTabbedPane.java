package Tests_NichtVomProgramm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleTabbedPane {
    public static void main(String[] args) {


        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("TabbedPane Beispiel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // TabbedPane erstellen
            JTabbedPane tabbedPane = new JTabbedPane();

            // Panels für die Tabs
            JPanel panel1 = new JPanel();
            panel1.add(new JLabel("Inhalt von Tab 1"));

            JPanel panel2 = new JPanel();
            panel2.add(new JLabel("Inhalt von Tab 2"));

            JPanel panel3 = new JPanel();

            JButton button1 = new JButton("Inhalt von Tab 3");

            button1.addActionListener(e->{
                panel3.add(new JButton("Button in Tab 3 nach button press"));
            });



            panel3.add(button1);


            // Tabs hinzufügen
            tabbedPane.addTab("Tab 1", panel1);
            tabbedPane.addTab("Tab 2", panel2);
            tabbedPane.addTab("Tab 3", panel3);

            // TabbedPane ins Frame
            frame.add(tabbedPane);

            frame.setVisible(true);
        });
    }
}