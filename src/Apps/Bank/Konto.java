package Apps.Bank;

import Apps.Bank.Tools.IbanGen;
import Apps.Bank.Tools.IbanGenRealBW;
import Apps.Bank.Tools.KundenManager;
import Apps.Bank.Tools.Transaktionen;

import javax.swing.*;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Konto {
    private double  saldo = 0.0;
    private String InhaberName = "Unbekannt";
    private int Pin;
    public boolean Durch;
    private String Iban;
    private List<Transaktionen> transaktionen;


    public Konto(String Inhaber, int startPin, double startSaldo ) {
        this.InhaberName = Inhaber;
        this.saldo = startSaldo;
        this.Pin = startPin;
        this.Iban = IbanGenRealBW.generateUniqueValidIbanForBwBank();
        //this.Iban = IbanGen.generateValidIban();
        this.transaktionen = new ArrayList<>();
    }



    public void einzahlen(double betrag) {
        if (betrag > 0) {
            saldo += betrag;
            transaktionen.add(new Transaktionen(LocalDateTime.now(), "Einzahlung", betrag));
//            System.out.println("\033[33mEinzahlung " + betrag + "€.\033[0m");
//            System.out.println("\033[35mNeuer Saldo für  " + InhaberName + ": " + saldo + " €.\033[0m");
            JOptionPane.showMessageDialog(null, "Einzahlung von " +betrag+ "€ Wurde durcheführt!");
        } else {
//            System.out.println("\033[31mEinzahlung ungültig.\033[0m");
            JOptionPane.showMessageDialog(null, "Einzahlung nicht Möglich!");
        }
    }

    public void Geheimeinzahlen(double betrag, String was) {
        if (betrag > 0) {
            saldo += betrag;
            transaktionen.add(new Transaktionen(LocalDateTime.now(), "Verkauft: " + was, betrag));
        } else {
//            System.out.println("\033[31mEinzahlung ungültig.\033[0m");
        }
    }

    public void GeklautPlus(double betrag) {
        if (betrag > 0) {
            saldo += betrag;
            transaktionen.add(new Transaktionen(LocalDateTime.now(), "Geklaut", betrag));
        } else {
//            System.out.println("\033[31mEinzahlung ungültig.\033[0m");
        }
    }

    public void abheben(double betrag, int pinEingabe) {
        if ((betrag > 0 && saldo >= betrag) && CheckPin(pinEingabe)) {
            saldo -= betrag;
            transaktionen.add(new Transaktionen(LocalDateTime.now(), "Abhebung", betrag));
//            System.out.println("\033[35mAbhebung: " + betrag + "€.\033[0m");
//            System.out.println("\033[35mNeuer Saldo für " + InhaberName + ": " + saldo + "€.\033[0m");
            JOptionPane.showMessageDialog(null, "Abhebung von " +betrag+ "€ Wurde durcheführt!");
        } else {
//            System.out.println("\033[31mAbhebung nicht möglich\033[0m");
            JOptionPane.showMessageDialog(null, "Abhebung nicht Möglich!");
        }
    }

    public void Geheimabheben(double betrag, int pinEingabe, String was) {
        if ((betrag > 0 && saldo >= betrag) && CheckPin(pinEingabe)) {
            saldo -= betrag;
            transaktionen.add(new Transaktionen(LocalDateTime.now(), "Kauf: "+ was , betrag));
            Durch = true;
        } else {
//            System.out.println("\033[31mBezahlung nicht möglich\033[0m");
            Durch = false;
        }
    }

    public void GeklautMinus(double betrag, int pinEingabe) {
        if ((betrag > 0 && saldo >= betrag) && CheckPin(pinEingabe)) {
            saldo -= betrag;
            transaktionen.add(new Transaktionen(LocalDateTime.now(), "Klau", betrag));
            Durch = true;
        } else {
            System.out.println();
            Durch = false;
        }
    }



    public boolean änderePin(int altePinEingabe, int neuePin){

        if (altePinEingabe == this.Pin) {
            this.Pin = neuePin;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean CheckPin(int pinEingabe){
        return pinEingabe == this.Pin;
    }

    public boolean GeheimabhebenBool(double betrag, int pinEingabe) {

        return (betrag > 0 && saldo >= betrag) && CheckPin(pinEingabe);
    }

    public double GetSaldo(){
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        String currency = nf.format(saldo);
        //System.out.println("\033[34mSie haben noch " + currency + " auf ihrem Konto\033[0m");
        return saldo;
    }

    public double GetGehSaldo(){
        return saldo;
    }

    public int GetPin(){
        return Pin;
    }


    public List<String> zeigeTransaktionsHistorie() {
        List<String> transakionsListe = new ArrayList<>();
        if (transaktionen.isEmpty()) {
           transakionsListe.add("Keine Transaktionen vorhanden");
        } else {
            for (Transaktionen t : transaktionen) {
                transakionsListe.add(t.toString());
            }
        }
        return transakionsListe;
    }

    public void Überweisen(double wieViel, int pin, Konto ZielKonto) {


        if((wieViel > 0 &&  saldo >= wieViel) && CheckPin(pin)){
            this.saldo -= wieViel;
            ZielKonto.saldo += wieViel;
            transaktionen.add(new Transaktionen(LocalDateTime.now(), "Überweisung", wieViel));
            ZielKonto.transaktionen.add(new Transaktionen(LocalDateTime.now(), "Überweisung", wieViel));
            JOptionPane.showMessageDialog(null, "Überweisung von " + wieViel +"€ durchegführt!");
        }else{
            JOptionPane.showMessageDialog(null, "Überweisung nicht möglich!");
        }
    }





    public String GetIban(){
        return Iban;
    }

    public String GetName(){
        return InhaberName;
    }
}
