package Apps.BestellApp.Snacks.SnackAuswahl;

import Apps.BestellApp.Snacks.Snack;

public class Yogurette extends Snack {

    @Override
    public String getName() {
        return "Yogurette";
    }

    @Override
    public double getPreis() {
        return 2.30;
    }
}