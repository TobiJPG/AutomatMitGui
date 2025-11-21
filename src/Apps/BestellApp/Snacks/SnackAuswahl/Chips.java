package Apps.BestellApp.Snacks.SnackAuswahl;

import Apps.BestellApp.Snacks.Snack;

public class Chips extends Snack {

    @Override
    public String getName() {
        return "Chips";
    }

    @Override
    public double getPreis() {
        return 1.50;
    }
}

