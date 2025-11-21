package Apps.BestellApp.Essen.EssenAuswahl;

import Apps.BestellApp.Essen.Essen;

public class ChickenNuggets extends Essen {
    @Override
    public String getName() {
        return "Chicken Nuggets";
    }

    @Override
    public double getPreis() {
        return 3.70;
    }
}
