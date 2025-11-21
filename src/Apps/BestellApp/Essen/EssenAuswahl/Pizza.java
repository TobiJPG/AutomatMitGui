package Apps.BestellApp.Essen.EssenAuswahl;

import Apps.BestellApp.Essen.Essen;

public class Pizza extends Essen {
    @Override
    public String getName() {
        return "Pizza";
    }

    @Override
    public double getPreis() {
        return 9.0;
    }
}
