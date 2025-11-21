package Apps.BestellApp.Essen.EssenAuswahl;

import Apps.BestellApp.Essen.Essen;

public class Hamburger extends Essen {

    @Override
    public String getName() {
        return "Hamburger";
    }

    @Override
    public double getPreis() {
        return 6.0;
    }
}
