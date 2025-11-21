package Apps.Bank.Tools;

import Apps.Bank.Konto;

import java.util.ArrayList;
import java.util.List;

public class KundenManager {

    private List<Konto> kundenListe = new ArrayList<>(); // Liste der Kunden
    private Konto currentCustomer = null;               // Aktueller Kunde
    private Konto zielKunde = null;

    public void addKonto(Konto kunde) {
        kundenListe.add(kunde);
    }

    public void setCurrentCustomer(String iban) {
        for (Konto kunde : kundenListe) {
            if (kunde.GetIban().equalsIgnoreCase(iban)) {
                currentCustomer = kunde;
                //System.out.println("Aktueller Kunde ist jetzt: " + currentCustomer.GetName());
                return;
            }
        }
        System.out.println("Kunde mit der Iban '" + iban + "' nicht gefunden.");
    }

    public Konto getKontoByIban(String iban) {
        for (Konto k : kundenListe) {
            if (k.GetIban().equalsIgnoreCase(iban)) return k;
        }
        return null;
    }


    public Konto getCurrentCustomer() {
        if (currentCustomer == null) {
            System.out.println("Kein aktueller Kunde gesetzt.");
            return null;
        }
        //System.out.println("Aktueller Kunde: " + currentCustomer.GetName());
        return currentCustomer;
    }

    public List<Konto> getKontenListe() {
        return kundenListe;
    }

    public void listKunden() {
        System.out.println("Liste aller Kunden:");
        for (Konto kunde : kundenListe) {
            System.out.println("- " + kunde.GetName() + " |Iban: "+ kunde.GetIban());
        }
    }


}
