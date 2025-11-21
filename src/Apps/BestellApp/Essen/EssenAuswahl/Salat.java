package Apps.BestellApp.Essen.EssenAuswahl;

import Apps.BestellApp.Essen.Essen;

public class Salat extends Essen {
    @Override
    public String getName() {
        return "Salat";
    }

    @Override
    public double getPreis() {
        return 5.50;
    }
}
