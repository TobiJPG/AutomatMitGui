package Apps.Bank.Tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaktionen {

    private LocalDateTime datum;   // Datum und Uhrzeit der Transaktion
    private String typ;            // Typ der Transaktion (Einzahlung, Abhebung, Kauf, etc.)
    private double betrag;         // Betrag
       // Kaufbeschreibung oder Transaktionsdetails

    // Konstruktor
    public Transaktionen(LocalDateTime datum, String typ, double betrag) {
        this.datum = datum;
        this.typ = typ;
        this.betrag = betrag;

    }

    // Getter für das Datum
    public String getFormattedDatum() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return datum.format(formatter);
    }

    // Getter für den Typ
    public String getTyp() {
        return typ;
    }

    // Getter für den Betrag
    public double getBetrag() {
        return betrag;
    }


    @Override
    public String toString() {
        return "[" + getFormattedDatum() + "] " +
                "Typ: " + typ +
                "  Betrag: " + String.format("%.2f €", betrag);
    }
}