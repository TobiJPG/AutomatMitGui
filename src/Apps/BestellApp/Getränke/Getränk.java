package Apps.BestellApp.Getränke;

public abstract class Getränk {

    // Abstrakte Methode für den Namen des Snacks
    public abstract String getName();

    // Abstrakte Methode für den Preis des Snacks
    public abstract double getPreis();

    // Gemeinsame Methode für die Ausgabe von Informationen
    public void displayInfo() {
        System.out.println("Snack: " + getName() + " kostet " + getPreis() + "€.");
    }
}