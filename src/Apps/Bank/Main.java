package Apps.Bank;


import Apps.Bank.Tools.GlobalCountdown;
import Apps.Bank.Tools.KundenManager;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;


public class Main {
    static int versuche = 3;
    public static GlobalCountdown warten = new  GlobalCountdown();

    public static void Bankmain(KundenManager Manager, JFrame mainframe) {



        JFrame Bankframe = new JFrame("Bank App");
        Bankframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Bankframe.setVisible(true);
        Bankframe.setSize(400, 400);
        Bankframe.setLocationRelativeTo(null);
        Bankframe.setResizable(false);



        JPanel BankPanel = new JPanel();
        BankPanel.setLayout(new GridLayout(3,2,10,10));
        BankPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding außen
        BankPanel.setBackground(Color.LIGHT_GRAY);

        JButton buttonEinzahlen = new JButton("Einzahlen");
        buttonEinzahlen.setBackground(Color.PINK);
        buttonEinzahlen.setForeground(Color.BLACK);
        buttonEinzahlen.setFocusPainted(false);

        JButton buttonAbheben = new JButton("Abheben");
        buttonAbheben.setBackground(Color.PINK);
        buttonAbheben.setForeground(Color.BLACK);
        buttonAbheben.setFocusPainted(false);

        JButton buttonSaldo = new JButton("Saldo Anzeigen");
        buttonSaldo.setBackground(Color.PINK);
        buttonSaldo.setForeground(Color.BLACK);
        buttonSaldo.setFocusPainted(false);

        JButton buttonPinÄndern = new JButton("Pin Ändern");
        buttonPinÄndern.setBackground(Color.PINK);
        buttonPinÄndern.setForeground(Color.BLACK);
        buttonPinÄndern.setFocusPainted(false);

        JButton buttonKonto = new JButton("Konto erstellen");
        buttonKonto.setBackground(Color.PINK);
        buttonKonto.setForeground(Color.BLACK);
        buttonKonto.setFocusPainted(false);

        JButton buttonAuflisten = new JButton("Konten Auflisten");
        buttonAuflisten.setBackground(Color.PINK);
        buttonAuflisten.setForeground(Color.BLACK);
        buttonAuflisten.setFocusPainted(false);

        JButton buttonTransaktionen = new JButton("Transaktionen");
        buttonTransaktionen.setBackground(Color.PINK);
        buttonTransaktionen.setForeground(Color.BLACK);
        buttonTransaktionen.setFocusPainted(false);

        JButton buttonÜberweisung = new JButton("Überweisen");
        buttonÜberweisung.setBackground(Color.PINK);
        buttonÜberweisung.setForeground(Color.BLACK);
        buttonÜberweisung.setFocusPainted(false);





        buttonEinzahlen.addActionListener(e -> {
            // 1) Ist noch Sperrzeit aktiv?
            if (!warten.isFinished()) {
                showLiveCountdownDialog(Bankframe); // Live-Dialog statt statischer Message
                return;
            }

            // 2) Passwort-Logik
            int versuche = 3; // oder wo auch immer du das führst
            final String korrektesPasswort = "Pass wort"; // Tipp: Leerzeichen prüfen gewollt? Ja

            while (versuche > 0) {
                String eingabe = JOptionPane.showInputDialog(
                        Bankframe,
                        "Bitte geben Sie das Admin-Passwort ein! Verbleibende Versuche: " + versuche
                );

                // Abbruch durch Nutzer
                if (eingabe == null) {
                    JOptionPane.showMessageDialog(Bankframe, "Abgebrochen.");
                    return;
                }

                if (eingabe.equals(korrektesPasswort)) {
                    // 3) Passwort korrekt -> Einzahlung abfragen
                    String anzahl = JOptionPane.showInputDialog(Bankframe, "Wieviel möchten Sie einzahlen?");
                    if (anzahl == null) {
                        JOptionPane.showMessageDialog(Bankframe, "Einzahlung abgebrochen.");
                        return;
                    }
                    try {
                        double betrag = Double.parseDouble(anzahl);
                        Manager.getCurrentCustomer().einzahlen(betrag);
                        JOptionPane.showMessageDialog(Bankframe, "Sie haben " + betrag + " € eingezahlt!");
                    } catch (NumberFormatException exNum) {
                        JOptionPane.showMessageDialog(Bankframe, "Ungültige Eingabe", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                    return; // fertig
                }

                // Falsches Passwort
                versuche--;
                if (versuche > 0) {
                    JOptionPane.showMessageDialog(
                            Bankframe,
                            "Falsches Passwort! Verbleibende Versuche: " + versuche,
                            "Fehler",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }

            // 4) Keine Versuche mehr -> 30 Sekunden Sperre starten
            JOptionPane.showMessageDialog(
                    Bankframe,
                    "Zu viele falsche Versuche. Sie müssen jetzt 30 Sekunden warten.",
                    "Gesperrt",
                    JOptionPane.ERROR_MESSAGE
            );

            warten.start(30, java.util.concurrent.TimeUnit.SECONDS);
            // Optional: Du kannst hier einen Swing-Timer setzen, um nach Ablauf optisch zu informieren:
            new javax.swing.Timer(1000, ev -> {
                if (warten.isFinished()) {
                    ((javax.swing.Timer) ev.getSource()).stop();
                    // Optional: Benutzer informieren, dass es weitergeht
                    // JOptionPane.showMessageDialog(Bankframe, "Sperre aufgehoben. Bitte erneut versuchen.");
                }
            }).start();
        });

        buttonAbheben.addActionListener(e -> {
            //String anzahl = JOptionPane.showInputDialog("Wieviel möchten Sie Abheben?");
            //String pinInput = JOptionPane.showInputDialog("Bitte geben Sie Ihre PIN ein:");
            boolean validInput = false;

            while (!validInput) {
                JPanel panel = new JPanel(new GridLayout(2,2));
                panel.setBackground(Color.LIGHT_GRAY);

                JLabel anzahl = new JLabel("Wie viel:");
                JTextField anzahlField = new JTextField();
                JLabel pininput = new JLabel("Pin:");
                JTextField pininputField = new JTextField();

                panel.add(anzahl);
                panel.add(anzahlField);
                panel.add(pininput);
                panel.add(pininputField);


                int result = JOptionPane.showConfirmDialog(
                        null,
                        panel,
                        "Abheben",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );


                if (result == JOptionPane.OK_OPTION) {
                    try {
                        // Lese PIN-Werte und überprüfe, ob sie Ganzzahlen sind
                        double AnzahlGeld = Double.parseDouble(anzahlField.getText().trim());
                        int pin = Integer.parseInt(pininputField.getText().trim());
                        Manager.getCurrentCustomer().abheben(AnzahlGeld, pin);
                        JOptionPane.showMessageDialog(Bankframe,"Sie haben " + AnzahlGeld + "€ abgehoben");

                        validInput = true;

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(Bankframe, "Ungültige Eingabe", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Benutzer klickt auf Abbrechen
                    JOptionPane.showMessageDialog(Bankframe, "Einzahlung abgebrochen.");
                    validInput = true; // Brich die Schleife ab
                }
            }
        });

        buttonSaldo.addActionListener(e -> {
            JOptionPane.showMessageDialog(Bankframe, "Saldo: " + Manager.getCurrentCustomer().GetSaldo() + "€");

        });

        buttonPinÄndern.addActionListener(e -> {
            boolean validInput = false; // Damit wir die Schleife für die Eingabe steuern können

            while (!validInput) {
                // Erstelle ein Panel als Container für die zwei Eingabefelder
                JPanel panel = new JPanel(new GridLayout(2, 2));
                panel.setBackground(Color.LIGHT_GRAY);


                // Eingabefelder für alten und neuen PIN
                JLabel oldPinLabel = new JLabel("Alter PIN:");
                JTextField oldPinField = new JTextField(); // Textfeld für alte PIN
                JLabel newPinLabel = new JLabel("Neuer PIN:");
                JPasswordField newPinField = new JPasswordField(); // Textfeld für neue PIN
                newPinField.setEchoChar('*');

                // Komponenten zum Panel hinzufügen
                panel.add(oldPinLabel);
                panel.add(oldPinField);
                panel.add(newPinLabel);
                panel.add(newPinField);

                // Zeige Dialogfenster mit zwei Eingabefeldern
                int result = JOptionPane.showConfirmDialog(
                        null,
                        panel,
                        "PIN ändern",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                // Falls der Benutzer auf OK klickt
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        // Lese PIN-Werte und überprüfe, ob sie Ganzzahlen sind
                        int oldPin = Integer.parseInt(oldPinField.getText().trim());
                        char[] pinChars = newPinField.getPassword();
                        String pinString =  new String(pinChars);
                        int newPin = Integer.parseInt(pinString);

                        //int newPin = Integer.parseInt(newPinPassString);

                        // Zusätzliche Validierungen einfügen (z.B. Länge des PINs)
                        Manager.getCurrentCustomer().änderePin(oldPin, newPin);
                        JOptionPane.showMessageDialog(Bankframe,"Pin wurde geändert!");
                        System.out.println("Alter PIN: " + oldPin);
                        System.out.println("Neuer PIN: " + newPin);
                        validInput = true; // Beende die Schleife



                    } catch (NumberFormatException ex) {
                        // Zeige Fehlernachricht, wenn keine gültige Zahl eingegeben wurde
                        JOptionPane.showMessageDialog(null, "Bitte geben Sie nur ganze Zahlen ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Benutzer klickt auf Abbrechen
                    System.out.println("Vorgang abgebrochen.");
                    validInput = true; // Brich die Schleife ab
                }
            }
        });

        buttonKonto.addActionListener(e -> {
            JPanel panel = new JPanel(new GridLayout(2, 2));
            panel.setBackground(Color.LIGHT_GRAY);

            JLabel name = new JLabel("Name des Kontos:");
            JTextField nameField = new JTextField();
            JLabel pin = new JLabel("Start Pin:");
            JTextField pinField = new JTextField();


            panel.add(name);
            panel.add(nameField);
            panel.add(pin);
            panel.add(pinField);

            int result = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "PIN ändern",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                try {
                    String Name = nameField.getText().trim();
                    int Pin = Integer.parseInt(pinField.getText().trim());

                    Konto konto = new Konto(Name, Pin, 0.0);
                    Manager.addKonto(konto);
                    Manager.setCurrentCustomer(konto.GetIban());
                    JOptionPane.showMessageDialog(Bankframe, "Kunde: " + Name + " wurde erfolgreich erstellt");

                }catch (NumberFormatException ex) {
                    // Zeige Fehlernachricht, wenn keine gültige Zahl eingegeben wurde
                    JOptionPane.showMessageDialog(null, "Bitte geben Sie nur ganze Zahlen ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonAuflisten.addActionListener(e -> {
            JFrame frame = new JFrame("Kunde wechseln");
            frame.setSize(400, 300);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            // DefaultListModel zur Verwaltung der Konten
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Konto konto : Manager.getKontenListe()) {
                // Füge Name und IBAN des Kunden in die Liste ein
                listModel.addElement("Name: " + konto.GetName() + " | IBAN: " + konto.GetIban());
            }

            // JList für Kundenauflistung
            JList<String> kundenListe = new JList<>(listModel);
            kundenListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Nur eine Auswahl möglich
            JScrollPane scrollPane = new JScrollPane(kundenListe); // Scrollbare Liste
            frame.add(scrollPane, BorderLayout.CENTER);



            // Button zum Bestätigen des ausgewählten Kunden
            JButton setCustomerButton = new JButton("Aktuellen Kunden setzen");
            setCustomerButton.setFocusPainted(false);
            JButton copyIban = new  JButton("Kunden Kopieren");
            copyIban.setFocusPainted(false);

            copyIban.addActionListener(setEvent -> {
                String text = kundenListe.getSelectedValue();
                String IbanText = text.split("\\| IBAN:")[1];
                StringSelection selection = new StringSelection(IbanText);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, null);
                JOptionPane.showMessageDialog(Bankframe, "IBAN Kopiert!");
            });


            setCustomerButton.addActionListener(setEvent -> {
                // Hole die Auswahl aus der JList
                int selectedIndex = kundenListe.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Extrahiere die IBAN aus der ausgewählten Zeile
                    String selectedValue = kundenListe.getSelectedValue();
                    String selectedIban = selectedValue.split("\\| IBAN: ")[1]; // Nimm den Wert nach "IBAN:"
                    selectedIban = selectedIban.trim(); // Entferne überflüssige Leerzeichen


                    // Setze den ausgewählten Kunden als aktuellen Kunden
                    Manager.setCurrentCustomer(selectedIban);

                    JOptionPane.showMessageDialog(frame, "Der Kunde wurde als aktueller Kunde gesetzt:\n" + selectedValue);

                    // Optional: Fenster schließen
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Bitte wählen Sie einen Kunden aus!", "Fehler", JOptionPane.WARNING_MESSAGE);
                }
            });

            // Hinzufügen eines Schließen-Buttons
            JButton closeButton = new JButton("Schließen");
            closeButton.setFocusPainted(false);
            closeButton.setBackground(Color.RED);
            closeButton.addActionListener(closeEvent -> frame.dispose());

            // Buttons in ein Panel
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BorderLayout());
            buttonPanel.add(setCustomerButton, BorderLayout.CENTER);
            buttonPanel.add(closeButton, BorderLayout.WEST);
            buttonPanel.add(copyIban, BorderLayout.EAST);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            // Frame sichtbar machen
            frame.setVisible(true);
        });

        buttonTransaktionen.addActionListener(e -> {
            Konto aktuellesKonto = Manager.getCurrentCustomer(); // Aktuelles Konto holen
            if (aktuellesKonto == null) {
                JOptionPane.showMessageDialog(Bankframe, "Kein Kunde ausgewählt!", "Fehler", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Neues Fenster für die Transaktionshistorie
            JFrame frame = new JFrame("Transaktionshistorie");
            frame.setSize(400, 300);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            // Erhalte die Transaktionshistorie als Liste von Strings
            java.util.List<String> transaktionsListe = aktuellesKonto.zeigeTransaktionsHistorie();

            // Füge die Transaktionshistorie ins ListModel ein
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (String transaktion : transaktionsListe) {
                listModel.addElement(transaktion);
            }

            // JList erstellen und sie mit dem Model verbinden
            JList<String> list = new JList<>(listModel);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setEnabled(false);

            // Scroll-Panel erstellen
            JScrollPane scrollPane = new JScrollPane(list);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Schließen-Button
            JButton schliessenButton = new JButton("Schließen");
            schliessenButton.setFocusPainted(false);
            schliessenButton.addActionListener(closeEvent -> frame.dispose());

            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            bottomPanel.add(schliessenButton);

            frame.add(bottomPanel, BorderLayout.SOUTH);

            // Frame sichtbar machen
            frame.setVisible(true);
        });

        buttonÜberweisung.addActionListener(e -> {
            boolean validInput = false;

            while (!validInput) {
                JPanel panel = new JPanel(new GridLayout(3,2));
                panel.setBackground(Color.LIGHT_GRAY);

                JLabel anzahl = new JLabel("Wie viel:");
                JTextField anzahlField = new JTextField();
                JLabel pininput = new JLabel("Pin:");
                JTextField pininputField = new JTextField();
                JLabel ZielKonto = new JLabel("Ziel Konto Iban:");
                JTextField ZielKontoField = new JTextField();


                panel.add(anzahl);
                panel.add(anzahlField);
                panel.add(pininput);
                panel.add(pininputField);
                panel.add(ZielKonto);
                panel.add(ZielKontoField);


                int result = JOptionPane.showConfirmDialog(
                        null,
                        panel,
                        "Überweisen",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );


                if (result == JOptionPane.OK_OPTION) {
                    try {
                        // Lese PIN-Werte und überprüfe, ob sie Ganzzahlen sind
                        double AnzahlGeld = Double.parseDouble(anzahlField.getText().trim());
                        int pin = Integer.parseInt(pininputField.getText().trim());
                        String IbanZielKonto = ZielKontoField.getText().trim();
                        KundenManager zielkunde = new KundenManager();


                        Manager.getCurrentCustomer().Überweisen(AnzahlGeld, pin, Manager.getKontoByIban(IbanZielKonto));
                        JOptionPane.showMessageDialog(Bankframe,"Sie haben " + AnzahlGeld + "€ überwiesen");

                        validInput = true;

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(Bankframe, "Ungültige Eingabe", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Benutzer klickt auf Abbrechen
                    JOptionPane.showMessageDialog(Bankframe, "Überweisung abgebrochen.");
                    validInput = true; // Brich die Schleife ab
                }
            }





        });

        BankPanel.add(buttonEinzahlen);
        BankPanel.add(buttonAbheben);
        BankPanel.add(buttonSaldo);
        BankPanel.add(buttonPinÄndern);
        BankPanel.add(buttonKonto);
        BankPanel.add(buttonAuflisten);
        BankPanel.add(buttonTransaktionen);
        BankPanel.add(buttonÜberweisung);

        JButton buttonVerlassen = new JButton("Verlassen");
        buttonVerlassen.setFocusPainted(false);
        buttonVerlassen.setBackground(Color.RED);
        buttonVerlassen.addActionListener(e -> {
            //System.out.println("Zurück zum Hauptmenü...");
            mainframe.setVisible(true);
            Bankframe.dispose(); // Fenster schließen
        });

        // Bottom Panel für Verlassen-Button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(buttonVerlassen);


        // Füge Panels zum Frame hinzu
        Bankframe.add(BankPanel, BorderLayout.CENTER); // Hauptpanel in der Mitte
        Bankframe.add(bottomPanel, BorderLayout.SOUTH); // Verlassen-Button unten

        // Zeige das Hauptfenster
        Bankframe.setVisible(true);






    }

    private static void showLiveCountdownDialog(JFrame parent) {
        // Dialog vorbereiten
        JDialog dialog = new JDialog(parent, "Gesperrt", false); // nicht modal
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Inhalt
        JLabel label = new JLabel("", SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 16f));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(ev -> dialog.dispose()); // manuelles Schließen

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);

        JPanel content = new JPanel(new BorderLayout());
        content.add(label, BorderLayout.CENTER);
        content.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setContentPane(content);
        dialog.setSize(380, 150);
        dialog.setLocationRelativeTo(parent);

        // Timer: alle 200 ms aktualisieren
        Timer t = new Timer(200, ev -> {
            long remMs = warten.getRemainingNanos() / 1_000_000L;
            if (remMs <= 0) {
                label.setText("Sperre aufgehoben.");
                ((Timer) ev.getSource()).stop();
                dialog.dispose(); // automatisch schließen, wenn abgelaufen
                return;
            }
            long sec = remMs / 1000;
            label.setText("<html>Zu viele falsche Versuche.<br>Bitte noch " + sec + " Sekunden warten.</html>");
        });
        t.setInitialDelay(0);
        t.start();

        dialog.setVisible(true);
    }



}
