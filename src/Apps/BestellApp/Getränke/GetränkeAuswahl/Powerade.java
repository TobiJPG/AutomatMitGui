package Apps.BestellApp.Getränke.GetränkeAuswahl;

import Apps.BestellApp.Getränke.Getränk;

public class Powerade extends Getränk {

    @Override
    public String getName() {
        return "Powerade";
    }

    @Override
    public double getPreis() {
        return 2;
    }
}
