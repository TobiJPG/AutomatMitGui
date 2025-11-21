package Apps.BestellApp.Getränke.GetränkeAuswahl;

import Apps.BestellApp.Getränke.Getränk;

public class Eistee extends Getränk {

    @Override
    public String getName() {
        return "Eistee";
    }

    @Override
    public double getPreis() {
        return 1.75;
    }
}
