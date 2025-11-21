package Apps.BestellApp.Getränke.GetränkeAuswahl;

import Apps.BestellApp.Getränke.Getränk;

public class Kaffee extends Getränk {
    @Override
    public String getName() {return "Kaffee";}

    @Override
    public double getPreis() {return 1.1;}
}
