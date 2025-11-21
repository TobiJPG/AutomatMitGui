package Apps.BestellApp.Essen.EssenAuswahl;

import Apps.BestellApp.Essen.Essen;

public class Sushi extends Essen {
    @Override
    public String getName() {
        return "Sushi";
    }

    @Override
    public double getPreis() {
        return 7.0;
    }
}
