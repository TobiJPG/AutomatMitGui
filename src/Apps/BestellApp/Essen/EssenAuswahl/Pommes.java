package Apps.BestellApp.Essen.EssenAuswahl;

import Apps.BestellApp.Essen.Essen;

public class Pommes extends Essen {
    @Override
    public String getName() {
        return "Pommes";
    }

    @Override
    public double getPreis() {
        return 3.5;
    }
}
