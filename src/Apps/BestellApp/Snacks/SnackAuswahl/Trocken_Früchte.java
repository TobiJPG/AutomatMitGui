package Apps.BestellApp.Snacks.SnackAuswahl;

import Apps.BestellApp.Snacks.Snack;

public class Trocken_Früchte extends Snack {

    @Override
    public String getName() {
        return "Trocken Früchte";
    }

    @Override
    public double getPreis() {
        return 1.50;
    }
}