package Apps.BestellApp.Getränke;

import Apps.BestellApp.Getränke.GetränkeAuswahl.*;
import Apps.Bank.Konto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetränkeMenü {

    public static void GetränkeMenü(Konto kundeKonto, Konto geschäftKonto){
        List<Getränk> getränkeListe = new ArrayList<>();
        getränkeListe.add(new Cola());
        getränkeListe.add(new Fanta());
        getränkeListe.add(new Wasser());
        getränkeListe.add(new Eistee());
        getränkeListe.add(new Powerade());

        Scanner scanner = new Scanner(System.in);
        int getränkeAuswahl;
        int pin;

        do {
            System.out.println("Welches Getränk möchten Sie kaufen?");
            for (int i = 0; i < getränkeListe.size(); i++) {
                Getränk getränk = getränkeListe.get(i);
                System.out.println((i + 1) + ". " + getränk.getName() + " - " + getränk.getPreis() + "€");
            }
            System.out.println("0. Zurück zum Hauptmenü");
            System.out.print("Ihre Auswahl: ");
            getränkeAuswahl = scanner.nextInt();
            System.out.println();

            if (getränkeAuswahl >= 1 && getränkeAuswahl <= getränkeListe.size()) {
                Getränk ausgewähltesGetränk = getränkeListe.get(getränkeAuswahl - 1);
                System.out.print("Bitte geben Sie Ihre PIN ein: ");
                pin = scanner.nextInt();

                if (kundeKonto.CheckPin(pin)) {
                    boolean erfolgreich = kundeKonto.GeheimabhebenBool(ausgewähltesGetränk.getPreis(), pin);
                    if (erfolgreich) {
                        kundeKonto.Geheimabheben(ausgewähltesGetränk.getPreis(), pin, ausgewähltesGetränk.getName());
                        if (kundeKonto.Durch)
                            geschäftKonto.Geheimeinzahlen(ausgewähltesGetränk.getPreis(), null);

                        System.out.println("Danke für Ihren Kauf von " + ausgewähltesGetränk.getName() + " für " + ausgewähltesGetränk.getPreis() + "€.");
                        System.out.println("Viel Spaß mit Ihrem Getränk!");
                    } else {
                        System.out.println("Nicht genug Guthaben! Kauf nicht möglich.");
                    }
                } else {
                    System.out.println("Falscher PIN!");
                }
                System.out.println();
            } else if (getränkeAuswahl == 0) {
                System.out.println("Zurück zum Hauptmenü...");
                return;
            } else {
                System.out.println("Ungültige Eingabe. Bitte erneut versuchen.");
                System.out.println();
            }
        } while (getränkeAuswahl != 0);
    }
}