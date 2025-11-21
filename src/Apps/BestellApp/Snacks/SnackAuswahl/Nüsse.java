package Apps.BestellApp.Snacks.SnackAuswahl;

import Apps.BestellApp.Snacks.Snack;

public class Nüsse extends Snack {

    @Override
    public String getName() {
        return "Nüsse";
    }

    @Override
    public double getPreis() {
        return 1.30;
    }
}