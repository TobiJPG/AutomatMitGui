package Apps.Bank.Tools;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IbanGenRealBW {
    private static final String BLZ_BW_BANK = "60050101"; // Bankleitzahl der BW-Apps.Bank
    private static final String COUNTRY_CODE = "DE"; // Ländercode für Deutschland
    private static final Set<String> generatedIbans = new HashSet<>(); // Set für eindeutige IBANs
    private static final int IBAN_LENGTH = 22; // Deutsche IBAN hat 22 Zeichen



    public static String generateUniqueValidIbanForBwBank() {
        String iban;
        do {
            iban = generateIbanForBwBank(); // IBAN generieren
        } while (!isValidIban(iban) || !generatedIbans.add(iban)); // Prüfen und sicherstellen, dass sie eindeutig ist
        return formatIban(iban); // Formatierte IBAN zurückgeben
    }

    public static String generateIbanForBwBank() {
        Random random = new Random();

        // Zufällige 10-stellige Kontonummer erstellen
        String accountNumber = String.format("%010d", random.nextInt(1000000000));

        // IBAN ohne Prüfziffer (DE-- + BLZ + Kontonummer)
        String baseIban = COUNTRY_CODE + "00" + BLZ_BW_BANK + accountNumber;

        // Prüfziffer berechnen
        String checkDigits = calculateCheckDigits(baseIban);

        // Volle IBAN mit berechneter Prüfziffer
        return COUNTRY_CODE + checkDigits + BLZ_BW_BANK + accountNumber;
    }

    public static String calculateCheckDigits(String iban) {
        // Verschiebe den Ländercode + "00" ans Ende und ersetze Buchstaben durch Zahlen
        String rearranged = iban.substring(4) + iban.substring(0, 4);
        StringBuilder numericIban = new StringBuilder();

        for (char c : rearranged.toCharArray()) {
            if (Character.isLetter(c)) {
                int numericValue = c - 'A' + 10;
                numericIban.append(numericValue);
            } else {
                numericIban.append(c);
            }
        }

        // Berechne Modulo 97
        int mod97 = mod97(numericIban.toString());
        int checkDigits = 98 - mod97;

        // Gebe die Prüfziffern als zweistelligen String zurück
        return String.format("%02d", checkDigits);
    }

    private static int mod97(String number) {
        int remainder = 0;
        for (int i = 0; i < number.length(); i++) {
            remainder = (remainder * 10 + Character.digit(number.charAt(i), 10)) % 97;
        }
        return remainder;
    }

    public static boolean isValidIban(String iban) {
        // Verschiebe den Ländercode + Prüfziffer ans Ende und ersetze Buchstaben durch Zahlen
        String rearranged = iban.substring(4) + iban.substring(0, 4);
        StringBuilder numericIban = new StringBuilder();

        for (char c : rearranged.toCharArray()) {
            if (Character.isLetter(c)) {
                int numericValue = c - 'A' + 10;
                numericIban.append(numericValue);
            } else {
                numericIban.append(c);
            }
        }

        // Modulo-97-Prüfung
        int mod97 = mod97(numericIban.toString());
        return mod97 == 1; // Gültig, wenn der Modulo-Rest gleich 1 ist
    }

    public static String formatIban(String iban) {
        // Füge Leerzeichen alle 4 Ziffern ein, um lesbare Form zu erzeugen
        return iban.replaceAll(".{4}(?!$)", "$0 ");
    }
}