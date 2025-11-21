package Apps.BestellApp.Snacks;

import Apps.BestellApp.Snacks.SnackAuswahl.*;
import Apps.Bank.Konto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SnackMenü {


    public static void SnackMenü(Konto kundeKonto, Konto geschäftKonto){
        List<Snack> snackListe = new ArrayList<>();
        snackListe.add(new Chips());
        snackListe.add(new Yogurette());
        snackListe.add(new Nüsse());
        snackListe.add(new Trocken_Früchte());
        snackListe.add(new Sonnenblumenkerne());

        Scanner scanner = new Scanner(System.in);
        int snackAuswahl;
        int pin;

        do {
            System.out.println("Was möchten Sie kaufen?");
            for (int i = 0; i < snackListe.size(); i++) {
                Apps.BestellApp.Snacks.Snack snack = snackListe.get(i);
                System.out.println((i + 1) + ". " + snack.getName() + " - " + snack.getPreis() + "€");
            }
            System.out.println("0. Zurück zum Hauptmenü");
            System.out.print("Ihre Auswahl: ");
            snackAuswahl = scanner.nextInt();
            System.out.println();

            if (snackAuswahl >= 1 && snackAuswahl <= snackListe.size()) {
                Apps.BestellApp.Snacks.Snack ausgewählterSnack = snackListe.get(snackAuswahl - 1);
                System.out.print("Bitte geben Sie Ihre PIN ein: ");
                pin = scanner.nextInt();

                if (kundeKonto.CheckPin(pin)) {
                    boolean erfolgreich = kundeKonto.GeheimabhebenBool(ausgewählterSnack.getPreis(), pin);
                    if (erfolgreich) {
                        kundeKonto.Geheimabheben(ausgewählterSnack.getPreis(), pin, ausgewählterSnack.getName());
                        if (kundeKonto.Durch)
                            geschäftKonto.Geheimeinzahlen(ausgewählterSnack.getPreis(), null);
                        System.out.println("Danke für Ihren Kauf von " + ausgewählterSnack.getName() + " für " + ausgewählterSnack.getPreis() + "€.");
                        System.out.println("Viel Spaß mit Ihrem Snack!");
                    } else {
                        System.out.println("Nicht genug Guthaben! Kauf nicht möglich.");
                    }
                } else {
                    System.out.println("Falscher PIN!");
                }
                System.out.println();
            } else if (snackAuswahl == 0) {
                System.out.println("Zurück zum Hauptmenü...");
            } else {
                System.out.println("Ungültige Auswahl. Bitte erneut versuchen.");
                System.out.println();
            }
        } while (snackAuswahl != 0);
    }
}