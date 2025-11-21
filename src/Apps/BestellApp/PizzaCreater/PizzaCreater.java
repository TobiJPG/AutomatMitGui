package Apps.BestellApp.PizzaCreater;

import Apps.Bank.Konto;
import Apps.Bank.Tools.GlobalCountdown;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public class PizzaCreater {
    public static void PizzaMachen(JFrame Mainframe, Konto kundeKonto, Konto geschäftKonto) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Pizza  12€");
            f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            f.setLayout(new BorderLayout(10,10));

            int targetW = 200;
            int targetH = 200;
            int x = 0, y = 0;

            // Icons laden
            ImageIcon teig50       = loadIcon("/Bilder/Teig.png");
            ImageIcon sosse50      = loadIcon("/Bilder/Soße.png");
            ImageIcon sosseKaese50 = loadIcon("/Bilder/SoßeKäse.png");
            ImageIcon kaese50      = loadIcon("/Bilder/Käse.png");

            ImageIcon salami50   = loadIcon("/Bilder/Salami.png");
            ImageIcon schinken50 = loadIcon("/Bilder/Schinken.png");
            ImageIcon ananas50   = loadIcon("/Bilder/Ananas.png");
            ImageIcon sucuk50    = loadIcon("/Bilder/Sucuk.png");

            ImageIcon mais50     = loadIcon("/Bilder/Mais.png");
            ImageIcon bohnen50   = loadIcon("/Bilder/Bohnen.png");
            ImageIcon chili50    = loadIcon("/Bilder/Chili.png");
            ImageIcon zwiebeln50 = loadIcon("/Bilder/Zwiebeln.png");
            ImageIcon pilze50    = loadIcon("/Bilder/Pilze.png");

            // Skalieren
            ImageIcon teig       = scaleIconHighQuality(teig50, targetW, targetH);
            ImageIcon sosse      = scaleIconHighQuality(sosse50, targetW, targetH);
            ImageIcon sosseKaese = scaleIconHighQuality(sosseKaese50, targetW, targetH);
            ImageIcon kaese      = scaleIconHighQuality(kaese50, targetW, targetH);

            ImageIcon salami   = scaleIconHighQuality(salami50, targetW, targetH);
            ImageIcon schinken = scaleIconHighQuality(schinken50, targetW, targetH);
            ImageIcon ananas   = scaleIconHighQuality(ananas50, targetW, targetH);
            ImageIcon sucuk    = scaleIconHighQuality(sucuk50, targetW, targetH);

            ImageIcon mais     = scaleIconHighQuality(mais50, targetW, targetH);
            ImageIcon bohnen   = scaleIconHighQuality(bohnen50, targetW, targetH);
            ImageIcon chili    = scaleIconHighQuality(chili50, targetW, targetH);
            ImageIcon zwiebeln = scaleIconHighQuality(zwiebeln50, targetW, targetH);
            ImageIcon pilze    = scaleIconHighQuality(pilze50, targetW, targetH);

            // Labels für Layer
            JLabel Lteig       = new JLabel(teig);
            JLabel Lsosse      = new JLabel(sosse);
            JLabel LsosseKaese = new JLabel(sosseKaese);
            JLabel Lkaese      = new JLabel(kaese);

            JLabel Lsalami   = new JLabel(salami);
            JLabel Lschinken = new JLabel(schinken);
            JLabel Lananas   = new JLabel(ananas);
            JLabel Lsucuk    = new JLabel(sucuk);

            JLabel Lpilze    = new JLabel(pilze);
            JLabel Lmais     = new JLabel(mais);
            JLabel Lbohnen   = new JLabel(bohnen);
            JLabel Lchili    = new JLabel(chili);
            JLabel Lzwiebeln = new JLabel(zwiebeln);

            // LayeredPane
            JLayeredPane layered = new JLayeredPane();
            layered.setPreferredSize(new Dimension(targetW, targetH));
            for (JLabel lbl : new JLabel[]{ Lteig, Lsosse, LsosseKaese, Lkaese, Lsalami, Lschinken, Lananas, Lsucuk, Lpilze, Lmais, Lbohnen, Lchili, Lzwiebeln }) {
                lbl.setBounds(x, y, targetW, targetH);
            }
            layered.add(Lteig, Integer.valueOf(0));
            layered.add(Lsosse,      Integer.valueOf(100));
            layered.add(LsosseKaese, Integer.valueOf(110));
            layered.add(Lkaese,      Integer.valueOf(120));

            layered.add(Lsalami,   Integer.valueOf(200));
            layered.add(Lschinken, Integer.valueOf(210));
            layered.add(Lananas,   Integer.valueOf(220));
            layered.add(Lsucuk,    Integer.valueOf(230));

            layered.add(Lpilze,    Integer.valueOf(300));
            layered.add(Lmais,     Integer.valueOf(310));
            layered.add(Lbohnen,   Integer.valueOf(320));
            layered.add(Lchili,    Integer.valueOf(330));
            layered.add(Lzwiebeln, Integer.valueOf(340));

            // Panels (jede Gruppe vertikal untereinander)
            JPanel AuswahlenGes   = new JPanel(new GridLayout(1,3,10,0));

            JPanel Auswahl_Sossen = createVerticalPanel("Grundbau:");
            JPanel Auswahl_Belag1 = createVerticalPanel("Belag 1:");
            JPanel Auswahl_Belag2 = createVerticalPanel("Belag 2:");

            // CheckBoxes
            JCheckBox CSosse = new JCheckBox("Soße");
            JCheckBox CSosseKaese = new JCheckBox("Soße mit Käse");
            JCheckBox CKaese = new JCheckBox("Käse");
            JCheckBox CNichts1 = new JCheckBox("Nichts");

            JCheckBox CSalami = new JCheckBox("Salami");
            JCheckBox CSchinken = new JCheckBox("Schinken");
            JCheckBox CAnanas = new JCheckBox("Ananas");
            JCheckBox CSucuk = new JCheckBox("Sucuk");
            JCheckBox CNichts2 = new JCheckBox("Nichts");

            JCheckBox CPilze = new JCheckBox("Pilze");
            JCheckBox CMais  = new JCheckBox("Mais");
            JCheckBox CBohnen= new JCheckBox("Bohnen");
            JCheckBox CChili = new JCheckBox("Chili");
            JCheckBox CZwiebeln = new JCheckBox("Zwiebeln");
            JCheckBox CNichts3 = new JCheckBox("Nichts");

            // Untereinander hinzufügen
            addUnderEachOther(Auswahl_Sossen,
                    CSosse, CSosseKaese, CKaese, CNichts1);
            addUnderEachOther(Auswahl_Belag1,
                    CSalami, CSchinken, CAnanas, CSucuk, CNichts2);
            addUnderEachOther(Auswahl_Belag2,
                    CPilze, CMais, CBohnen, CChili, CZwiebeln, CNichts3);

            AuswahlenGes.add(Auswahl_Sossen);
            AuswahlenGes.add(Auswahl_Belag1);
            AuswahlenGes.add(Auswahl_Belag2);

            // Styling: Pink
            Color pink = new Color(255, 105, 180);
            Color bg = Color.WHITE;
            AuswahlenGes.setBackground(bg);

            JCheckBox[] all = {
                    CSosse, CSosseKaese, CKaese, CNichts1,
                    CSalami, CSchinken, CAnanas, CSucuk, CNichts2,
                    CPilze, CMais, CBohnen, CChili, CZwiebeln, CNichts3
            };
            for (JCheckBox cb : all) {
                cb.setForeground(pink);
                cb.setBackground(bg);
                cb.setOpaque(true);
                cb.setFocusable(false);
                cb.setAlignmentX(Component.LEFT_ALIGNMENT);
            }

            // Initialsichtbarkeit
            Lsosse.setVisible(false);
            LsosseKaese.setVisible(false);
            Lkaese.setVisible(false);
            Lsalami.setVisible(false);
            Lschinken.setVisible(false);
            Lananas.setVisible(false);
            Lsucuk.setVisible(false);
            Lpilze.setVisible(false);
            Lmais.setVisible(false);
            Lbohnen.setVisible(false);
            Lchili.setVisible(false);
            Lzwiebeln.setVisible(false);

            // Gruppenlogik
            class CheckGroup {
                private final List<JCheckBox> options;
                private final JCheckBox nothing;
                private final boolean singleChoice;

                CheckGroup(List<JCheckBox> options, JCheckBox nothing, boolean singleChoice) {
                    this.options = options;
                    this.nothing = nothing;
                    this.singleChoice = singleChoice;
                    install();
                }

                private void install() {
                    // "Nichts"
                    nothing.addActionListener(e -> {
                        if (nothing.isSelected()) {
                            for (JCheckBox o : options) o.setSelected(false);
                            // Sichtbarkeit für die jeweilige Gruppe
                            if (options.contains(CSosse)) {
                                Lsosse.setVisible(false);
                                LsosseKaese.setVisible(false);
                                Lkaese.setVisible(false);
                            }
                            if (options.contains(CSalami)) {
                                Lsalami.setVisible(false);
                                Lschinken.setVisible(false);
                                Lananas.setVisible(false);
                                Lsucuk.setVisible(false);
                            }
                            if (options.contains(CPilze)) {
                                Lpilze.setVisible(false);
                                Lmais.setVisible(false);
                                Lbohnen.setVisible(false);
                                Lchili.setVisible(false);
                                Lzwiebeln.setVisible(false);
                            }
                        }
                    });

                    // Optionen
                    for (JCheckBox o : options) {
                        o.addActionListener(e -> {
                            if (o.isSelected()) {
                                if (nothing.isSelected()) nothing.setSelected(false);
                                if (singleChoice) {
                                    for (JCheckBox p : options) if (p != o) p.setSelected(false);
                                }
                            } else {
                                if (singleChoice) {
                                    boolean any = options.stream().anyMatch(AbstractButton::isSelected);
                                    if (!any) {
                                        nothing.setSelected(true);
                                    }
                                }
                            }
                        });
                    }

                    // Initialzustand
                    nothing.setSelected(true);
                    for (JCheckBox o : options) o.setSelected(false);
                }
            }

            new CheckGroup(Arrays.asList(CSosse, CSosseKaese, CKaese), CNichts1, true);
            new CheckGroup(Arrays.asList(CSalami, CSchinken, CAnanas, CSucuk), CNichts2, false);
            new CheckGroup(Arrays.asList(CPilze, CMais, CBohnen, CChili, CZwiebeln), CNichts3, false);

            // Sichtbarkeit koppeln

            // Grundbau
            CSosse.addActionListener(e -> {
                boolean sel = CSosse.isSelected();
                Lsosse.setVisible(sel);
                if (sel) { LsosseKaese.setVisible(false); Lkaese.setVisible(false); }
            });
            CSosseKaese.addActionListener(e -> {
                boolean sel = CSosseKaese.isSelected();
                LsosseKaese.setVisible(sel);
                if (sel) { Lsosse.setVisible(false); Lkaese.setVisible(false); }
            });
            CKaese.addActionListener(e -> {
                boolean sel = CKaese.isSelected();
                Lkaese.setVisible(sel);
                if (sel) { Lsosse.setVisible(false); LsosseKaese.setVisible(false); }
            });
            CNichts1.addActionListener(e -> {
                if (CNichts1.isSelected()) {
                    Lsosse.setVisible(false);
                    LsosseKaese.setVisible(false);
                    Lkaese.setVisible(false);
                }
            });

            // Belag 1
            CSalami.addActionListener(e -> Lsalami.setVisible(CSalami.isSelected()));
            CSchinken.addActionListener(e -> Lschinken.setVisible(CSchinken.isSelected()));
            CAnanas.addActionListener(e -> Lananas.setVisible(CAnanas.isSelected()));
            CSucuk.addActionListener(e -> Lsucuk.setVisible(CSucuk.isSelected()));
            CNichts2.addActionListener(e -> {
                if (CNichts2.isSelected()) {
                    Lsalami.setVisible(false);
                    Lschinken.setVisible(false);
                    Lananas.setVisible(false);
                    Lsucuk.setVisible(false);
                }
            });

            // Belag 2
            CPilze.addActionListener(e -> Lpilze.setVisible(CPilze.isSelected()));
            CMais.addActionListener(e -> Lmais.setVisible(CMais.isSelected()));
            CBohnen.addActionListener(e -> Lbohnen.setVisible(CBohnen.isSelected()));
            CChili.addActionListener(e -> Lchili.setVisible(CChili.isSelected()));
            CZwiebeln.addActionListener(e -> Lzwiebeln.setVisible(CZwiebeln.isSelected()));
            CNichts3.addActionListener(e -> {
                if (CNichts3.isSelected()) {
                    Lpilze.setVisible(false);
                    Lmais.setVisible(false);
                    Lbohnen.setVisible(false);
                    Lchili.setVisible(false);
                    Lzwiebeln.setVisible(false);
                }
            });


            JButton Bestätigen = new JButton("Bestätigen");
            JButton Verlassen = new JButton("Verlassen");

            Bestätigen.setBackground(Color.white);
            Verlassen.setBackground(Color.white);
            Bestätigen.setForeground(Color.GREEN);
            Verlassen.setForeground(Color.RED);

            Bestätigen.setSize(100,100);
            Verlassen.setSize(100,100);

            Bestätigen.setFocusable(false);
            Verlassen.setFocusable(false);
            JPanel southWrapper = new JPanel(new BorderLayout());

            Bestätigen.addActionListener( e ->{
                //f.setVisible(false);
                GlobalCountdown warten = new GlobalCountdown();
                        warten.start(60, java.util.concurrent.TimeUnit.SECONDS);
                new javax.swing.Timer(1000, ev -> {
                    if (warten.isFinished()) {
                        ((javax.swing.Timer) ev.getSource()).stop();
                        // Optional: Benutzer informieren, dass es weitergeht
                        JOptionPane.showMessageDialog(f, "Ihre Pizza ist Fertig.");
                    }
                }).start();
                southWrapper.setVisible(false);
                Bestätigen.setEnabled(false);
                f.pack();

                String pinInput = JOptionPane.showInputDialog("Bitte geben Sie Ihre PIN ein:");
                try {
                    int pin = Integer.parseInt(pinInput); // PIN als Zahl
                    if (kundeKonto.CheckPin(pin)) {
                        if (kundeKonto.GeheimabhebenBool(12, pin)) {
                            kundeKonto.Geheimabheben(12, pin, "Selfmade Pizza");
                            if (kundeKonto.Durch)
                                geschäftKonto.Geheimeinzahlen(12, null);

                            JOptionPane.showMessageDialog(f, "Danke für Ihren Kauf von "
                                    + "Selfmade Pizza" + " für " + 12 + "€.");
                        } else {
                            JOptionPane.showMessageDialog(f, "Nicht genug Guthaben! Kauf nicht möglich.",
                                    "Fehler", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Falscher PIN!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(f, "Ungültige Eingabe! Bitte geben Sie eine Zahl ein.",
                            "Fehler", JOptionPane.ERROR_MESSAGE);
                }

            });

            Verlassen.addActionListener(e->{
                f.setVisible(false);
                Mainframe.setVisible(true);
            });

            // Frame



            southWrapper.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
            southWrapper.setBackground(Color.WHITE);
            southWrapper.add(AuswahlenGes, BorderLayout.CENTER);


            f.add(Bestätigen, BorderLayout.EAST);
            f.add(Verlassen, BorderLayout.WEST);

            f.add(layered, BorderLayout.CENTER);
            f.add(southWrapper, BorderLayout.SOUTH);
            f.pack();
            f.setResizable(false);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }

    private static JPanel createVerticalPanel(String title) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        JLabel head = new JLabel(title);
        head.setAlignmentX(Component.LEFT_ALIGNMENT);
        head.setBorder(BorderFactory.createEmptyBorder(0,0,4,0));
        p.add(head);
        p.add(Box.createVerticalStrut(4));
        return p;
    }

    private static void addUnderEachOther(JPanel panel, JComponent... comps) {
        for (JComponent c : comps) {
            c.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(c);
            panel.add(Box.createVerticalStrut(4));
        }
    }

    private static ImageIcon loadIcon(String path) {
        java.net.URL url = PizzaCreater.class.getResource(path);
        if (url == null) {
            System.err.println("Bild nicht gefunden: " + path);
            return new ImageIcon();
        }
        ImageIcon icon = new ImageIcon(url);
        System.out.println(path + " -> " + icon.getIconWidth() + "x" + icon.getIconHeight());
        return icon;
    }

    private static ImageIcon scaleIconHighQuality(ImageIcon icon, int newW, int newH) {
        if (icon == null || icon.getIconWidth() <= 0 || icon.getIconHeight() <= 0) {
            return icon;
        }
        BufferedImage scaled = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = scaled.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.drawImage(icon.getImage(), 0, 0, newW, newH, null);
        g2.dispose();
        return new ImageIcon(scaled);
    }
}