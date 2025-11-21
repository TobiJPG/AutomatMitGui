package Apps.BestellApp.Snacks.SnackAuswahl;

import Apps.BestellApp.Snacks.Snack;

public class Kinderriegel extends Snack {
    @Override
    public String getName() {
        return "Kinderriegel";
    }

    @Override
    public double getPreis() {
        return 2.30;
    }
}
