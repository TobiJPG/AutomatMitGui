package Apps.BestellApp.Getränke.GetränkeAuswahl;

import Apps.BestellApp.Getränke.Getränk;

public class Fanta extends Getränk {

    @Override
    public String getName() {
        return "Fanta";
    }

    @Override
    public double getPreis() {
        return 2.50;
    }
}
