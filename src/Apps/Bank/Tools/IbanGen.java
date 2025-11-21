package Apps.Bank.Tools;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IbanGen {
    private static final Set<String> generatedIbans = new HashSet<>(); // Speicher für generierte IBANs

    public static String generateValidIban() {
        String iban;
        do {
            iban = generateIban(); // Erzeuge eine IBAN
        } while (!isValidIban(iban) || !generatedIbans.add(iban)); // Prüfe die IBAN und füge sie zum Set hinzu
        return formatIban(iban); // Gib die erste gültige und einzigartige IBAN zurück
    }

    public static String generateIban() {
        String start = "DE";
        Random value = new Random();

        // Generiere die zwei zufälligen Prüfziffern (diese werden später bei der Prüfung wichtig)
        int r1 = value.nextInt(10);
        int r2 = value.nextInt(10);
        start += Integer.toString(r1) + Integer.toString(r2);

        // Generiere 18 zufällige Ziffern für die Kontonummer (12 waren zu wenig für DE)
        for (int i = 0; i < 18; i++) {
            int n = value.nextInt(10);
            start += n;
        }

        return start; // Gib die IBAN zurück (mit zufälligen Werten)
    }

    public static boolean isValidIban(String iban) {
        // 1. Bringe die IBAN in die richtige Reihenfolge (Ländercode & Prüfziffer ans Ende setzen)
        String rearranged = iban.substring(4) + iban.substring(0, 4);

        // 2. Ersetze Buchstaben durch Zahlen (A = 10, B = 11, ..., Z = 35)
        StringBuilder numericIban = new StringBuilder();
        for (char c : rearranged.toCharArray()) {
            if (Character.isLetter(c)) {
                int numericValue = c - 'A' + 10;
                numericIban.append(numericValue);
            } else {
                numericIban.append(c);
            }
        }

        // 3. Führe die Modulo-97-Prüfung durch
        String numericIbanStr = numericIban.toString();
        int mod97 = mod97(numericIbanStr);

        // Gültig, wenn der Rest 1 ist
        return mod97 == 1;
    }

    private static int mod97(String number) {
        int remainder = 0;
        for (int i = 0; i < number.length(); i++) {
            remainder = (remainder * 10 + Character.digit(number.charAt(i), 10)) % 97;
        }
        return remainder;
    }


    public static String formatIban(String iban) {
        // Füge Leerzeichen alle 4 Ziffern ein, um lesbare Form zu erzeugen
        return iban.replaceAll(".{4}(?!$)", "$0 ");
    }


}