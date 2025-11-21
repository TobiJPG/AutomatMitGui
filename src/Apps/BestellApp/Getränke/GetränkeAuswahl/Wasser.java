package Apps.BestellApp.Getränke.GetränkeAuswahl;

import Apps.BestellApp.Getränke.Getränk;

public class Wasser extends Getränk {

    @Override
    public String getName() {
        return "Wasser";
    }

    @Override
    public double getPreis() {
        return 1.00;
    }
}
